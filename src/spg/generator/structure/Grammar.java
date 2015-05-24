package spg.generator.structure;

import java.util.List;

public class Grammar {
    public final String prefix;
    public final String pkg;
    public final String className;
    public final List<String> imports;
    public final List<NonTerminalDescriptor> nonTerminals;
    public final List<TerminalDescriptor> terminals;
    public final List<Rule> rules;

    public Grammar(String prefix, String pkg, String className, List<String> imports, List<NonTerminalDescriptor> nonTerminals,
                   List<TerminalDescriptor> terminals, List<Rule> rules) {
        this.prefix = prefix;
        this.pkg = pkg;
        this.className = className;
        this.imports = imports;
        this.nonTerminals = nonTerminals;
        this.terminals = terminals;
        this.rules = rules;
    }
}
