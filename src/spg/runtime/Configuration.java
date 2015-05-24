package spg.runtime;

import java.util.List;

public interface Configuration {
    int getLength(int n);

    Table getTable();

    int getTerminalNumber();

    int getNonTerminalNumber();

    List<String> getValues();

    List<Boolean> getIgnore();

    Unit reduce(int i, List<Unit> rule);
}
