package spg.runtime;

public abstract class Unit {
    private final int _number_;

    public Unit(int _number_) {
        this._number_ = _number_;
    }

    public int getNumber() {
        return _number_;
    }
}
