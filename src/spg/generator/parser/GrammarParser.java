package spg.generator.parser;

import spg.generator.structure.Grammar;
import spg.generator.structure.NonTerminalDescriptor;
import spg.generator.structure.Rule;
import spg.generator.structure.TerminalDescriptor;
import spg.runtime.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class GrammarParser {
    public static Grammar parse(String fileName) throws FileNotFoundException, ParseException {
        int p = fileName.lastIndexOf('/');
        String prefix = "";
        if (p != -1) {
            prefix = fileName.substring(0, p);
        }
        return parse(new FileInputStream(fileName), prefix);
    }

    private static Grammar parse(InputStream is, String prefix) throws ParseException {
        try {
            Scanner sc = new Scanner(is);
            String s1 = sc.next(), s2 = sc.next(), s3 = sc.next(), s4 = sc.next(), s5 = sc.next(), s6 = sc.next(),
                    s7 = sc.next(), s8 = sc.next(), s9 = sc.next();
            if (!s1.equals("package") || !s2.equals("=") || !s4.equals("class") || !s5.equals("=")
                    || !s7.equals("start") || !s8.equals("=")) {
                throw new ParseException("Parser declaration expected.");
            }
            List<String> imports = parseImports(sc);
            List<String> names = new ArrayList<>();
            List<String> values = new ArrayList<>();
            List<String> ignore = new ArrayList<>();
            parseTerminals(sc, names, values, ignore);
            List<String> ntNames = new ArrayList<>();
            List<String> fields = new ArrayList<>();
            parseNonTerminals(sc, ntNames, fields);
            List<String> nt = new ArrayList<>();
            List<List<String>> rules = new ArrayList<>();
            List<String> actions = new ArrayList<>();
            parseRules(sc, nt, rules, actions);
            for (int i = 0; i < ntNames.size(); i++) {
                if (ntNames.equals(s9)) {
                    String temp = ntNames.get(0);
                    ntNames.set(0, s9);
                    ntNames.set(i, temp);
                    temp = fields.get(0);
                    fields.set(0, fields.get(i));
                    fields.set(i, temp);
                    break;
                }
            }
            List<TerminalDescriptor> terminals = new ArrayList<>();
            Map<String, Integer> tNumbers = new HashMap<>();
            for (int i = 0; i < names.size(); i++) {
                terminals.add(new TerminalDescriptor(values.get(i), ignore.contains(names.get(i))));
                tNumbers.put(names.get(i), ntNames.size() + 1 + i);
            }
            List<NonTerminalDescriptor> descriptors = new ArrayList<>();
            List<Integer> zRule = new ArrayList<>();
            zRule.add(0);
            descriptors.add(new NonTerminalDescriptor("", zRule, ""));
            Map<String, Integer> ntNumbers = new HashMap<>();
            for (int i = 0; i < ntNames.size(); i++) {
                List<Integer> ntRules = new ArrayList<>();
                for (int j = 0; j < nt.size(); j++) {
                    if (ntNames.get(i).equals(nt.get(j))) {
                        ntRules.add(j + 1);
                    }
                }
                descriptors.add(new NonTerminalDescriptor(ntNames.get(i), ntRules, fields.get(i)));
                ntNumbers.put(ntNames.get(i), i + 1);
                if (tNumbers.containsKey(ntNames.get(i)) || tNumbers.containsKey(ntNames.get(i))) {
                    throw new ParseException("Multiple declaration with same name.");
                }
            }
            if (ntNumbers.containsKey("EPS")) {
                throw new ParseException("EPS shouldn't be used as a non-terminal.");
            }
            List<Rule> r = new ArrayList<>();
            List<Integer> zr = new ArrayList<>();
            zr.add(1);
            r.add(new Rule(0, zr, ""));
            for (int i = 0; i < nt.size(); i++) {
                List<Integer> rule = new ArrayList<>();
                for (String s : rules.get(i)) {
                    if (!s.equals("EPS")) {
                        if (ntNumbers.containsKey(s)) {
                            rule.add(ntNumbers.get(s));
                        } else if (tNumbers.containsKey(s)) {
                            rule.add(tNumbers.get(s));
                        } else {
                            throw new ParseException("Undefined unit.");
                        }
                    }
                }
                if (!ntNumbers.containsKey(nt.get(i))) {
                    throw new ParseException("Undefined unit.");
                }
                r.add(new Rule(ntNumbers.get(nt.get(i)), rule, actions.get(i)));
            }
            return new Grammar(prefix, s3, s6, imports, descriptors, terminals, r);
        } catch (NoSuchElementException | IllegalStateException e) {
            throw new ParseException("Incomplete grammar.");
        }
    }

    private static List<String> parseImports(Scanner sc) throws ParseException {
        String im = sc.next();
        switch (im) {
            case "Imports:":
                List<String> result = new ArrayList<>();
                while (true) {
                    String s = sc.next();
                    switch (s) {
                        case "Terminals:":
                            return result;
                        case "import":
                            result.add(sc.next());
                            break;
                        default:
                            throw new ParseException("Import expected.");
                    }
                }
            case "Terminals:":
                return new ArrayList<>();
            default:
                throw new ParseException("Section expected.");
        }
    }

    private static void parseTerminals(Scanner sc, List<String> names, List<String> values, List<String> ignore) throws ParseException {
        while (true) {
            String s = sc.next();
            switch (s) {
                case "NonTerminals:":
                    return;
                case "ignore":
                    while (true) {
                        String s1 = sc.next();
                        if (s1.equals("NonTerminals:")) {
                            return;
                        }
                        ignore.add(s1);
                    }
                default:
                    names.add(s);
                    if (!sc.next().equals("=")) {
                        throw new ParseException("Terminal declaration expected.");
                    }
                    values.add(sc.nextLine());
            }
        }
    }

    private static void parseNonTerminals(Scanner sc, List<String> names, List<String> fields) throws ParseException {
        while (true) {
            String s = sc.next();
            if (s.equals("Rules:")) {
                return;
            }
            names.add(s);
            if (!sc.next().equals("=") || !sc.next().equals("{")) {
                throw new ParseException("Non-terminal declaration expected.");
            }
            Result r = parseCode(new AdditiveScanner(sc));
            fields.add(r.first);
            if (new Scanner(r.second).hasNext()) {
                throw new ParseException("Nothing expected after non-terminal declaration.");
            }
        }
    }

    private static void parseRules(Scanner sc, List<String> nt, List<List<String>> rules, List<String> actions) throws ParseException {
        AdditiveScanner scanner = new AdditiveScanner(sc);
        while (scanner.hasNext()) {
            String name = scanner.next();
            if (!scanner.next().equals("->")) {
                throw new ParseException("Rule expected.");
            }
            while (true) {
                nt.add(name);
                rules.add(new ArrayList<>());
                while (true) {
                    String s = scanner.next();
                    if (s.equals("{")) {
                        break;
                    }
                    rules.get(rules.size() - 1).add(s);
                }
                Result r = parseCode(scanner);
                actions.add(r.first);
                scanner = new AdditiveScanner(new AdditiveScanner(new Scanner(r.second)), scanner);
                if (!scanner.hasNext()) {
                    return;
                }
                String s = scanner.next();
                if (!s.equals("|")) {
                    scanner = new AdditiveScanner(new AdditiveScanner(new Scanner(s)), scanner);
                    break;
                }
            }
        }
    }

    private static Result parseCode(AdditiveScanner sc) throws ParseException {
        boolean str = false;
        int cnt = 0;
        String res = "";
        boolean lastBS = false;
        while (true) {
            String s = sc.nextLine();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '\\') {
                    lastBS = !lastBS;
                } else {
                    if (c == '"' && !lastBS) {
                        str = !str;
                    } else if (c == '{' && !str) {
                        cnt++;
                    } else if (c == '}' && !str) {
                        cnt--;
                        if (cnt == -1) {
                            return new Result(res + s.substring(0, i), s.substring(i + 1));
                        }
                    }
                    lastBS = false;
                }
            }
            res += s;
        }
    }

    private static class AdditiveScanner {
        private Scanner sc;
        private AdditiveScanner first, second;

        public AdditiveScanner(Scanner sc) {
            this.sc = sc;
        }

        public AdditiveScanner(AdditiveScanner first, AdditiveScanner second) {
            this.first = first;
            this.second = second;
        }

        public boolean hasNext() {
            if (sc != null) {
                return sc.hasNext();
            }
            return first.hasNext() || second.hasNext();
        }

        public String next() {
            if (sc != null) {
                return sc.next();
            }
            if (first.hasNext()) {
                return first.next();
            }
            return second.next();
        }

        public String nextLine() {
            if (sc != null) {
                return sc.nextLine();
            }
            if (first.hasNext()) {
                return first.nextLine();
            }
            return second.nextLine();
        }
    }

    private static class Result {
        String first;
        String second;

        public Result(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }
}


