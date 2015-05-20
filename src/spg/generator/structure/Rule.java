package spg.generator.structure;

import java.util.List;

public class Rule {
    public final int nonTerminal;
    public final List<Integer> rule;
    public final String action;

    public Rule(int nonTerminal, List<Integer> rule, String action) {
        this.nonTerminal = nonTerminal;
        this.rule = rule;
        this.action = action;
    }
}
