package resourses;

import java.util.Scanner;

public class SimpleCalculator extends Calculator {
    private enum Operation {
        EQUAL,
        ADD, SUBTRACT, MULT, DIVISION,
        POW, SQRT,
        ESC
    }
    public SimpleCalculator() {
        System.out.println("Selected Simple Calculator");
        showAvOp();

        scanner = new Scanner(System.in);
        update();
    }

    @Override
    protected void showAvOp() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("The following mathematical operations are available to you:");
        System.out.println("addition: +\nsubtraction: -\nmultiplication: *\ndivision: /\n" +
                            "sqrt: %\npow: ^");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }


    @Override
    protected void update() {
        while (running) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            System.out.print("Enter the first number: ");
            double num1 = getNum();
            double num1_sh = num1;

            System.out.print("Enter the operation: ");
            SimpleCalculator.Operation operation = getOperation();


            if (operation.equals(Operation.POW) || operation.equals(Operation.SQRT)) {
                double rank;
                num1 = solution(num1, operation)[0];
                rank = solution(num1, operation)[1];
                System.out.format("Result: \n%f %s %f = %f\n", num1_sh, operation, rank, num1);
            }
            else {
                System.out.print("Enter the second number: ");
                double num2 = getNum();

                num1 = solution(num1, num2, operation);
                System.out.format("Result: \n%s %s %s = %s\n", num1_sh, operation, num2, num1);
            }

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            clear();
        }
    }

    protected double solution(double num1, double num2, Operation operation) {
        switch (operation) {
            case ADD -> num1 += num2;
            case SUBTRACT -> num1 -= num2;
            case MULT -> num1 *= num2;
            case DIVISION -> {
                if (num2 == 0) System.out.println("Error: Divided by zero\nTry again");
                else num1 /= num2;
            }
            case ESC -> running = false;
            default -> System.out.println("Error: Unsupported operation");
        }
        return num1;
    }

    private double[] solution(double num1, Operation operation) {
        double rank = 1;
        switch (operation) {
            case POW -> {
                System.out.print("Enter the degree`s rank: ");
                rank = getNum();
                num1 = Math.pow(num1, rank);
            }
            case SQRT -> {
                System.out.print("Enter the root`s rank: ");
                rank = 1 / getNum();
                num1 = Math.pow(num1, rank);
            }
            case ESC -> running = false;
            default -> clear();
        }
        return new double[]{num1, rank};
    }

    @Override
    protected String getValue() {
        return scanner.next();
    }

    private double getNum() {
        double num = 0;
        if (scanner.hasNextDouble()) num = scanner.nextDouble();
        else {
            System.out.println("Error: entering the num\nTry again");
            getNum();
        }
        return num;
    }

    protected Operation getOperation() {
        return switch (getValue()) {
            case "+" -> Operation.ADD;
            case "-" -> Operation.SUBTRACT;
            case "*" -> Operation.MULT;
            case "/" -> Operation.DIVISION;
            case "%" -> Operation.SQRT;
            case "^" -> Operation.POW;
            case "=" -> Operation.EQUAL;
            default -> {
                System.out.println("Error: entering the operation");
                showAvOp();
                yield getOperation();
            }
        };
    }
}
