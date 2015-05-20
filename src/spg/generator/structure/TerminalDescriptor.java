package spg.generator.structure;

public class TerminalDescriptor {
    public final String value;
    public final boolean ignore;

    public TerminalDescriptor(String value, boolean ignore) {
        this.value = value;
        this.ignore = ignore;
    }
}
