package resourses;

import java.util.Scanner;

class Hex extends NumSystem {
    private int value;

    public Hex(String val) {
        super(val);
    }

    public Hex(int val) {
        super(val);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(String val) {
        if (val.matches("[0-9A-F]+")) {
            value = Integer.parseInt(val, 16);
        }
    }

    @Override
    public void setValue(int val) {
        value = val;
    }

    private String toCurrentNumSystem(int val) {
        return Integer.toHexString(val);
    }

    @Override
    public String toString() {
        return toCurrentNumSystem(value);
    }
}

class Dec extends NumSystem {
    private int value;

    public Dec(String val) {
        super(val);
    }

    public Dec(int val) {
        super(val);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(String val) {
        if (val.matches("\\d+")) {
            value = Integer.parseInt(val);
        }
    }

    @Override
    public void setValue(int val) {
        value = val;
    }

    private String toCurrentNumSystem(int val) {
        return Integer.toString(val);
    }

    @Override
    public String toString() {
        return toCurrentNumSystem(value);
    }
}

class Oct extends NumSystem {
    private int value;

    public Oct(String val) {
        super(val);
    }

    public Oct(int val) {
        super(val);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(String val) {
        if (val.matches("[0-7]+")) {
            value = Integer.parseInt(val);
        }
    }

    @Override
    public void setValue(int val) {
        value = val;
    }

    private String toCurrentNumSystem(int val) {
        return Integer.toOctalString(val);
    }

    @Override
    public String toString() {
        return toCurrentNumSystem(value);
    }
}

class Bin extends NumSystem {
    private int value;

    public Bin(String val) {
        super(val);
    }

    public Bin(int val) {
        super(val);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(String val) {
        if (val.matches("[0-1]+")) {
            value = Integer.parseInt(val);
        }
    }

    @Override
    public void setValue(int val) {
        value = val;
    }

    private String toCurrentNumSystem(int val) {
        return Integer.toBinaryString(val);
    }

    @Override
    public String toString() {
        return toCurrentNumSystem(value);
    }
}

public class ProgrammerCalculator extends Calculator {

    NumeralSystem numeralSys = NumeralSystem.DEC;

    private enum Operation {
        EQUAL,
        ADD, SUBTRACT, MULT, DIVISION,
        MOD, XOR, AND, OR,
        ESC;
    }
    private enum NumeralSystem {
        HEX, DEC, OCT, BIN
    }
    public ProgrammerCalculator() {
        System.out.println("Selected Programmer Calculator");
        showAvOp();

        scanner = new Scanner(System.in);
        update();
    }

    @Override
    protected void showAvOp() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("The following programming operations are available to you:");
        System.out.println("addition: +\nsubtraction: -\nmultiplication: *\ndivision: /\n" +
                "\nremainder: mod\nbitwise exclusion: xor\nbitwise and: and\nbitwise or: or");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    void showAvNS() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("The following programming numeral systems are available to you:");
        System.out.println("bin: 2\noct: 8\ndec: 10\nhex: 16");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    @Override
    protected void update() {
        while (running) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            System.out.print("Enter the first number: ");
            NumSystem num1 = getNum();

            System.out.print("Enter the operation: ");
            Operation operation = getOperation();

            System.out.print("Enter the second number: ");
            NumSystem num2 = getNum();

            int result = solution(num1, num2, operation);

            System.out.format("Result: \n%s %s %s = %s\n", num1, operation, num2, result);

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            System.out.println("Change the numeral system?");
            if (getValue().contains("y")) numeralSys = changeNumeralSystem();
            clear();
        }
    }

    protected int solution(NumSystem num1, NumSystem num2, Operation operation) {
        switch (operation) {
            case ADD -> num1.AddNumSys(num2);
            case SUBTRACT -> num1.SubtractNumSys(num2);
            case MULT -> num1.MultNumSys(num2);
            case DIVISION -> {
                if (num2.getValue() == 0) System.out.println("Error: Divided by zero\nTry again");
                else num1.DivisionNumSys(num2);
            }
            case MOD -> num1.ModNumSys(num2);
            case XOR -> num1.XorNumSys(num2);
            case AND -> num1.AddNumSys(num2);
            case OR -> num1.OrNumSys(num2);
            case ESC -> running = false;
            default -> System.out.println("Error: Unsupported operation");
        }
        return num1.getValue();
    }

    private NumeralSystem getNumeralSystem() {
        return switch (getValue()) {
            case "2" -> NumeralSystem.BIN;
            case "8" -> NumeralSystem.OCT;
            case "10" -> NumeralSystem.DEC;
            case "16" -> NumeralSystem.HEX;
            default -> {
                System.out.println("Error: entering the numeral system");
                showAvNS();
                yield getNumeralSystem();
            }
        };
    }

    @Override
    protected String getValue() {
        return scanner.next();
    }

    private NumeralSystem changeNumeralSystem() {
        showAvNS();
        System.out.println("Print numerical system");
        return getNumeralSystem();
    }

    protected NumSystem getNum() {
        return switch (numeralSys) {
            case BIN -> new Bin(getValue());
            case OCT -> new Oct(getValue());
            case DEC -> new Dec(getValue());
            case HEX -> new Hex(getValue());
        };
    }

    protected Operation getOperation() {
        return switch (getValue()) {
            case "+" -> Operation.ADD;
            case "-" -> Operation.SUBTRACT;
            case "*" -> Operation.MULT;
            case "/" -> Operation.DIVISION;
            case "mod" -> Operation.MOD;
            case "xor" -> Operation.XOR;
            case "and" -> Operation.AND;
            case "or" -> Operation.OR;
            case "=" -> Operation.EQUAL;
            default -> {
                System.out.println("Error: entering the operation");
                showAvOp();
                yield getOperation();
            }
        };
    }
}
