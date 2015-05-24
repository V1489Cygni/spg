package spg.generator.structure;

import java.util.List;

public class NonTerminalDescriptor {
    public final String name;
    public final List<Integer> rules;
    public final String fields;

    public NonTerminalDescriptor(String name, List<Integer> rules, String fields) {
        this.name = name;
        this.rules = rules;
        this.fields = fields;
    }
}
