package resourses;

import java.util.Scanner;

class Hex implements INumSystem {
    protected int value;

    public Hex(String val) throws Exception {
        setValue(val);
    }

    public Hex(int val) {
        setValue(val);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(String val) throws Exception {
        if (!val.matches("[0-9A-F]+") || val.charAt(0) == '0') {
            System.out.println("Wrong input");
            throw new Exception("Wrong value input format");
        }
        this.value = Integer.parseInt(val, 16);
    }

    @Override
    public void setValue(int val) {
        this.value = val;
    }

    private String toCurrentNumSystem(int val) {
        return Integer.toHexString(val);
    }

    @Override
    public String toString() {
        return toCurrentNumSystem(value);
    }
}

class Dec implements INumSystem {
    protected int value;

    public Dec(String val) throws Exception {
        setValue(val);
    }

    public Dec(int val) {
        setValue(val);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(String val) throws Exception {
        if (!val.matches("\\d+") || val.charAt(0) == '0') {
            System.out.println("Wrong input");
            throw new Exception("Wrong value input format");
        }
        this.value = Integer.parseInt(val);
    }

    @Override
    public void setValue(int val) {
        this.value = val;
    }

    private String toCurrentNumSystem(int val) {
        return String.valueOf(val);
    }

    @Override
    public String toString() {
        return toCurrentNumSystem(value);
    }
}

class Oct implements INumSystem {
    protected int value;

    public Oct(String val) throws Exception {
        setValue(val);
    }

    public Oct(int val) {
        setValue(val);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(String val) throws Exception {
        if (!val.matches("[0-7]+") || val.charAt(0) == '0') {
            System.out.println("Wrong input");
            throw new Exception("Wrong value input format");
        }
        this.value = Integer.parseInt(val, 8);
    }

    @Override
    public void setValue(int val) {
        this.value = val;
    }

    private String toCurrentNumSystem(int val) {
        return Integer.toOctalString(val);
    }

    @Override
    public String toString() {
        return toCurrentNumSystem(value);
    }
}

class Bin implements INumSystem {
    private int value;

    public Bin(String val) throws Exception {
        setValue(val);
    }

    public Bin(int val) {
        setValue(val);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(String val) throws Exception {
        if (!val.matches("[01]+")) {
            System.out.println("Wrong input");
            throw new Exception("Wrong value input format");
        }
        this.value = Integer.parseInt(val, 2);
    }

    @Override
    public void setValue(int val) {
        this.value = val;
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
    public ProgrammerCalculator() throws Exception {
        System.out.println("Selected Programmer Calculator");
        showAvOp();

        scanner = new Scanner(System.in);
        update();
    }

    @Override
    protected void showAvOp() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("The following programming operations are available to you:");
        System.out.println("""
                addition: +
                subtraction: -
                multiplication: *
                division: /

                remainder: mod
                bitwise exclusion: xor
                bitwise and: and
                bitwise or: or""");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    void showAvNS() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("The following programming numeral systems are available to you:");
        System.out.println("bin: 2\noct: 8\ndec: 10\nhex: 16");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    @Override
    protected void update() throws Exception {
        changeNumSys();
        while (running) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            System.out.print("Enter the first number: ");
            INumSystem num1 = getNum();
            String num1_sh = num1.toString();

            while(true) {
                System.out.print("Enter the operation: ");
                Operation operation = getOperation();

                if (operation == Operation.EQUAL) break;

                System.out.print("Enter the second number: ");
                INumSystem num2 = getNum();

                solution(num1, num2, operation);
                System.out.format("Result: \n%s %s %s = %s\n", num1_sh, operation, num2, num1);
                num1_sh = num1.toString();
            }

            System.out.format("FINAL Result: %s\n", num1_sh);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            changeNumSys();
            clear();
        }
    }

    private void changeNumSys() {
        System.out.printf("Change the numeral system? Current is %s\n", numeralSys);
        if (getValue().contains("y")) numeralSys = changeNumeralSystem();
        else System.out.println("So continue...");
    }

    protected void solution(INumSystem num1, INumSystem num2, Operation operation) throws Exception {
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
            case AND -> num1.AndNumSys(num2);
            case OR -> num1.OrNumSys(num2);
            case ESC -> running = false;
            default -> System.out.println("Error: Unsupported operation");
        };
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

    protected INumSystem getNum() throws Exception {
        try {
            String input = getValue();
            return switch (numeralSys) {
                case BIN -> new Bin(input);
                case OCT -> new Oct(input);
                case DEC -> new Dec(input);
                case HEX -> new Hex(input);
            };
        } catch (Exception ex) {
            System.out.println("Enter wrong value! Try again...");
            return getNum();
        }
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
