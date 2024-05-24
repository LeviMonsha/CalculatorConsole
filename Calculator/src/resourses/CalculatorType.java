package resourses;

public enum CalculatorType {
    SIMPLE {
        @Override
        public Calculator getCalculator() {
            return new SimpleCalculator();
        }
    },
    PROGRAMMER {
        @Override
        public Calculator getCalculator() throws Exception {
            return new ProgrammerCalculator();
        }
    };

    public abstract Calculator getCalculator() throws Exception;
}
