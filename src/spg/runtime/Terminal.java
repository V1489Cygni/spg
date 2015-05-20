package spg.runtime;

public class Terminal extends Unit {
    public final String value;

    public Terminal(int number, String value) {
        super(number);
        this.value = value;
    }
}
