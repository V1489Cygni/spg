package spg.runtime;

import java.util.List;

public class Table {
    public final List<List<Action>> actions;
    public final List<List<Integer>> next;

    public Table(List<List<Action>> actions, List<List<Integer>> next) {
        this.actions = actions;
        this.next = next;
    }
}
