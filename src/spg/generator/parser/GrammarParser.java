package spg.generator.parser;

import spg.generator.structure.Grammar;
import spg.generator.structure.NonTerminalDescriptor;
import spg.generator.structure.Rule;
import spg.generator.structure.TerminalDescriptor;

import java.io.InputStream;
import java.util.*;

public class GrammarParser {
    public static Grammar parseGrammar(InputStream is) {
        Map<String, String> options = new HashMap<>();
        Scanner sc = new Scanner(is);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            int p = s.indexOf('=');
            options.put(s.substring(0, p), s.substring(p + 1));
        }
        String prefix = options.get("prefix");
        String pkg = options.get("pkg");
        List<String> imports = new ArrayList<>();
        imports.add(options.get("imports"));
        int tNum = Integer.parseInt(options.get("terminals"));
        List<TerminalDescriptor> terminals = new ArrayList<>();
        for (int i = 0; i < tNum; i++) {
            terminals.add(new TerminalDescriptor(options.get("terminal." + i),
                    Boolean.parseBoolean(options.get("terminal." + i + ".ignore"))));
        }
        int ntNum = Integer.parseInt(options.get("nonTerminals"));
        List<List<Integer>> ntRules = new ArrayList<>();
        List<List<String>> types = new ArrayList<>();
        List<List<String>> attributes = new ArrayList<>();
        for (int i = 0; i < ntNum; i++) {
            ntRules.add(new ArrayList<>());
            types.add(new ArrayList<>());
            attributes.add(new ArrayList<>());
            String a = options.get("nt." + i + ".attributes");
            String t = options.get("nt." + i + ".types");
            Scanner sa = new Scanner(a);
            Scanner st = new Scanner(t);
            while (sa.hasNext()) {
                types.get(i).add(st.next());
                attributes.get(i).add(sa.next());
            }
        }
        int rNum = Integer.parseInt(options.get("rules"));
        List<Rule> rules = new ArrayList<>();
        List<Integer> rs = new ArrayList<>();
        rs.add(1);
        String action = "";
        for (String s : attributes.get(0)) {
            action += "res." + s + "=$0." + s + ";\n";
        }
        rules.add(new Rule(0, rs, action));
        for (int i = 0; i < rNum; i++) {
            int nt = Integer.parseInt(options.get("rule." + i + ".nt")) + 1;
            String r = options.get("rule." + i + ".rule");
            List<Integer> rule = new ArrayList<>();
            Scanner s = new Scanner(r);
            while (s.hasNext()) {
                String sn = s.next();
                if (sn.charAt(0) == 't') {
                    rule.add(ntNum + 1 + Integer.parseInt(sn.substring(1)));
                } else {
                    rule.add(1 + Integer.parseInt(sn.substring(1)));
                }
            }
            action = options.get("rule." + i + ".action");
            ntRules.get(nt - 1).add(i + 1);
            rules.add(new Rule(nt, rule, action));
        }
        List<NonTerminalDescriptor> nonTerminals = new ArrayList<>();
        List<Integer> init = new ArrayList<>();
        init.add(0);
        nonTerminals.add(new NonTerminalDescriptor(init, types.get(0), attributes.get(0)));
        for (int i = 0; i < ntNum; i++) {
            nonTerminals.add(new NonTerminalDescriptor(ntRules.get(i), types.get(i), attributes.get(i)));
        }
        return new Grammar(prefix, pkg, imports, nonTerminals, terminals, rules);
    }
}
