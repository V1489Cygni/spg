package spg.runtime;

import java.util.List;

public interface Configuration {
    int getLength(int n);

    RuleManager getManager();

    Table getTable();

    int getTerminalNumber();

    int getNonTerminalNumber();

    List<String> getValues();

    List<Boolean> getIgnore();
}
