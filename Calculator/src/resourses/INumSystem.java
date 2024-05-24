package resourses;

interface INumSystem {
    int getValue();

    void setValue(String val) throws Exception;

    void setValue(int val) throws Exception;
    String toString();

    default void AddNumSys(INumSystem other) throws Exception {
        if (other != null) {
            setValue(getValue() + other.getValue());
        } else throw new Exception("Different types");
    }

    default void SubtractNumSys(INumSystem other) throws Exception {
        if (other != null) {
            setValue(getValue() - other.getValue());
        } else throw new Exception("Different types");
    }

    default void MultNumSys(INumSystem other) throws Exception {
        if (other != null) {
            setValue(getValue() * other.getValue());
        } else throw new Exception("Different types");
    }

    default void DivisionNumSys(INumSystem other) throws Exception {
        if (other != null) {
            setValue(getValue() / other.getValue());
        } else throw new Exception("Different types");
    }

    default void ModNumSys(INumSystem other) throws Exception {
        if (other != null) {
            setValue(getValue() % other.getValue());
        } else throw new Exception("Different types");
    }

    default void XorNumSys(INumSystem other) throws Exception {
        if (other != null) {
            setValue(getValue() ^ other.getValue());
        } else throw new Exception("Different types");
    }

    default void AndNumSys(INumSystem other) throws Exception {
        if (other != null) {
            setValue(getValue() & other.getValue());
        } else throw new Exception("Different types");
    }

    default void OrNumSys(INumSystem other) throws Exception {
        if (other != null) {
            setValue(getValue() | other.getValue());
        } else throw new Exception("Different types");
    }
}
