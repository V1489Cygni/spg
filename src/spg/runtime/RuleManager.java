package spg.runtime;

import java.util.List;

public interface RuleManager {
    Unit reduce(int n, List<Unit> rule);
}
