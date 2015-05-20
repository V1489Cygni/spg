package spg.generator;

import spg.generator.structure.Grammar;
import spg.generator.structure.Rule;
import spg.runtime.Action;
import spg.runtime.Table;

import java.util.*;

public class TableGenerator {
    public static Table generateTable(Grammar grammar) throws GenerationException {
        Map<Integer, Set<Integer>> first = first(grammar);
        int n = grammar.nonTerminals.size() + grammar.terminals.size() + 1;
        List<List<Action>> actions = new ArrayList<>();
        List<List<Integer>> next = new ArrayList<>();
        List<List<Situation>> situations = new ArrayList<>();
        situations.add(new ArrayList<>());
        situations.get(0).add(new Situation(0, 0, n - 1));
        closure(grammar, first, situations.get(0));
        actions.add(generateActions(n));
        next.add(generateNext(n));
        for (int i = 0; i < situations.size(); i++) {
            for (int j = 0; j < n - 1; j++) {
                List<Situation> s = next(grammar, first, situations.get(i), j);
                if (!s.isEmpty()) {
                    int in = -1;
                    for (int k = 0; k < situations.size(); k++) {
                        if (s.equals(situations.get(k))) {
                            in = k;
                            break;
                        }
                    }
                    if (in == -1) {
                        situations.add(s);
                        actions.add(generateActions(n));
                        next.add(generateNext(n));
                        actions.get(i).set(j, Action.SHIFT);
                        next.get(i).set(j, situations.size() - 1);
                    } else {
                        actions.get(i).set(j, Action.SHIFT);
                        next.get(i).set(j, in);
                    }
                }
            }
        }
        for (int i = 0; i < situations.size(); i++) {
            for (Situation situation : situations.get(i)) {
                Rule rule = grammar.rules.get(situation.rule);
                if (situation.position == rule.rule.size()) {
                    if (rule.nonTerminal == 0 && situation.lookAhead == n - 1) {
                        if (actions.get(i).get(situation.lookAhead).equals(Action.ERROR)) {
                            actions.get(i).set(situation.lookAhead, Action.ACCEPT);
                        } else {
                            throw new GenerationException("Conflict!");
                        }
                    } else {
                        if (actions.get(i).get(situation.lookAhead).equals(Action.ERROR)
                                || actions.get(i).get(situation.lookAhead).equals(Action.REDUCE)
                                && next.get(i).get(situation.lookAhead) == situation.rule) {
                            actions.get(i).set(situation.lookAhead, Action.REDUCE);
                            next.get(i).set(situation.lookAhead, situation.rule);
                        } else {
                            throw new GenerationException("Conflict!");
                        }
                    }
                }
            }
        }
        return new Table(actions, next);
    }

    private static Map<Integer, Set<Integer>> first(Grammar grammar) {
        Map<Integer, Set<Integer>> first = new HashMap<>();
        for (int i = grammar.nonTerminals.size(); i < grammar.nonTerminals.size() + grammar.terminals.size() + 1; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(i);
            first.put(i, set);
        }
        for (int i = 0; i < grammar.nonTerminals.size(); i++) {
            first.put(i, new HashSet<>());
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < grammar.rules.size(); i++) {
                Set<Integer> f = getFirst(first, grammar.rules.get(i).rule);
                for (int x : f) {
                    if (!first.get(grammar.rules.get(i).nonTerminal).contains(x)) {
                        changed = true;
                        first.get(grammar.rules.get(i).nonTerminal).add(x);
                    }
                }
            }
        }
        return first;
    }

    private static Set<Integer> getFirst(Map<Integer, Set<Integer>> first, List<Integer> s) {
        Set<Integer> result = new HashSet<>();
        boolean eps = true;
        int j;
        for (j = 0; j < s.size() && eps; j++) {
            Set<Integer> f = first.get(s.get(j));
            boolean nps = false;
            for (int x : f) {
                if (x == -1) {
                    nps = true;
                } else {
                    result.add(x);
                }
            }
            eps = nps;
        }
        if (eps) {
            result.add(-1);
        }
        return result;
    }

    private static List<Action> generateActions(int n) {
        List<Action> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(Action.ERROR);
        }
        return res;
    }

    private static List<Integer> generateNext(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(-1);
        }
        return res;
    }

    public static void closure(Grammar grammar, Map<Integer, Set<Integer>> first, List<Situation> situations) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < situations.size(); i++) {
                Situation situation = situations.get(i);
                if (situation.position < grammar.rules.get(situation.rule).rule.size()) {
                    int n = grammar.rules.get(situation.rule).rule.get(situation.position);
                    if (n < grammar.nonTerminals.size()) {
                        List<Integer> left = new ArrayList<>();
                        for (int j = situation.position + 1; j < grammar.rules.get(situation.rule).rule.size(); j++) {
                            left.add(grammar.rules.get(situation.rule).rule.get(j));
                        }
                        left.add(situation.lookAhead);
                        Set<Integer> f = getFirst(first, left);
                        for (int x : grammar.nonTerminals.get(n).rules) {
                            for (int y : f) {
                                if (y >= grammar.nonTerminals.size()) {
                                    Situation s = new Situation(x, 0, y);
                                    if (!situations.contains(s)) {
                                        situations.add(s);
                                        changed = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static List<Situation> next(Grammar grammar, Map<Integer, Set<Integer>> first, List<Situation> situations, int n) {
        List<Situation> result = new ArrayList<>();
        situations.stream().filter(situation -> situation.position < grammar.rules.get(situation.rule).rule.size()
                && n == grammar.rules.get(situation.rule).rule.get(situation.position)).forEach(situation -> {
            Situation s = new Situation(situation.rule, situation.position + 1, situation.lookAhead);
            if (!result.contains(s)) {
                result.add(s);
            }
        });
        closure(grammar, first, result);
        return result;
    }

    private static class Situation {
        public final int rule;
        public final int position;
        public final int lookAhead;

        public Situation(int rule, int position, int lookAhead) {
            this.rule = rule;
            this.position = position;
            this.lookAhead = lookAhead;
        }

        @Override
        public int hashCode() {
            return rule + position << 16 + lookAhead << 24;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Situation && rule == ((Situation) o).rule && position == ((Situation) o).position
                    && lookAhead == ((Situation) o).lookAhead;
        }

        @Override
        public String toString() {
            return rule + " " + position + " " + lookAhead;
        }
    }
}
