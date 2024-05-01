package resourses;

public abstract class NumSystem {
    public NumSystem(String val) {
        setValue(val);
    }

    public NumSystem(int val) {
        setValue(val);
    }

    public abstract int getValue();

    public abstract void setValue(String val);

    public abstract void setValue(int val);

    public void AddNumSys(NumSystem other) {
        setValue(getValue() + other.getValue());
    }

    public void SubtractNumSys(NumSystem other) {
        setValue(getValue() - other.getValue());
    }

    public void MultNumSys(NumSystem other) {
        setValue(getValue() * other.getValue());
    }

    public void DivisionNumSys(NumSystem other) {
        setValue(getValue() / other.getValue());
    }

    public void ModNumSys(NumSystem other) {
        setValue(getValue() % other.getValue());
    }

    public void XorNumSys(NumSystem other) {
        setValue(getValue() ^ other.getValue());
    }

    public void AndNumSys(NumSystem other) {
        setValue(getValue() & other.getValue());
    }

    public void OrNumSys(NumSystem other) {
        setValue(getValue() | other.getValue());
    }
}
