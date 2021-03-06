package spg.runtime;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Configuration configuration;
    private final Lexer lexer;
    private final Table table;
    private final List<Unit> stack = new ArrayList<>();
    private final List<Integer> states = new ArrayList<>();

    public Parser(Configuration configuration) {
        lexer = new Lexer();
        lexer.setConfig(configuration);
        this.configuration = configuration;
        this.table = configuration.getTable();
    }

    public Unit parse(String s) throws ParseException {
        lexer.init(s);
        states.add(0);
        lexer.next();
        while (true) {
            Terminal t = lexer.getNext();
            Action a = table.actions.get(states.get(states.size() - 1)).get(t.getNumber());
            int n = table.next.get(states.get(states.size() - 1)).get(t.getNumber());
            switch (a) {
                case SHIFT:
                    states.add(n);
                    stack.add(t);
                    lexer.next();
                    break;
                case REDUCE:
                    int num = configuration.getLength(n);
                    List<Unit> args = new ArrayList<>();
                    for (int i = stack.size() - num; i < stack.size(); i++) {
                        args.add(stack.get(i));
                    }
                    for (int i = 0; i < num; i++) {
                        stack.remove(stack.size() - 1);
                        states.remove(states.size() - 1);
                    }
                    Unit unit = configuration.reduce(n, args);
                    int x = table.next.get(states.get(states.size() - 1)).get(unit.getNumber());
                    stack.add(unit);
                    states.add(x);
                    break;
                case ERROR:
                    throw new ParseException("Error in state " + states.get(states.size() - 1));
                case ACCEPT:
                    return stack.get(stack.size() - 1);
                default:
                    throw new AssertionError();
            }
        }
    }
}
