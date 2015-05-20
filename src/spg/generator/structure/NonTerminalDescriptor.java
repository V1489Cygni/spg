package spg.generator.structure;

import java.util.List;

public class NonTerminalDescriptor {
    public final List<Integer> rules;
    public final List<String> types;
    public final List<String> attributes;

    public NonTerminalDescriptor(List<Integer> rules, List<String> types, List<String> attributes) {
        this.rules = rules;
        this.types = types;
        this.attributes = attributes;
    }
}
