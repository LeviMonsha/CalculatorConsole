package resourses;

import java.util.Objects;
import java.util.Scanner;

public class SimpleCalculator extends Calculator {
    private enum Operation {
        EQUAL,
        ADD, SUBTRACT, MULT, DIVISION,
        POW, SQRT
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
        System.out.println("addition: +\nsubtraction: -\nmultiplication: *\ndivision: /");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }


    @Override
    protected void update() {

//        String num1_con = null, num2_con = null;
//        double num1 = 0, num2 = 0;
//        String operation = "=";
//        boolean b_select_operation = false;
//
//        while (running) {
//
//            if (scanner.hasNextDouble()) {
//                if (!b_select_operation) {
//                    num1 = getNum();
//                    num1_con = String.valueOf(num1);
//                }
//                else {
//                    b_select_operation = false;
//                }
//            }
//            else if (scanner.hasNext()) {
//                operation = getOperation();
//                if (Objects.equals(operation, "=")) {
//                    //System.out.format("Result: \n%s %s %s = %.2f\n", num1_con, operation, num2_con, result);
//                    clear();
//                    num1 = 0; num1_con = null;
//                    num2 = 0; num2_con = null;
//                    continue;
//                }
//                num2 = num1; num2_con = num1_con;
//                num1 = 0; num1_con = null;
//                b_select_operation = true;
//                continue;
//            }
//            else continue;
//
//            if (num2_con == null) result = solution(num1, operation);
//            else result = solution(num2, num1, operation);
//
//
//            if (!b_select_operation) {
//                num1 = result; num1_con = String.valueOf(result);
//                num2 = num1; num2_con = num1_con;
//            }
//            else {
//                num2 = result; num2_con = String.valueOf(result);
//            }
//        }
//        System.out.format("Result: %.2f\n", result);
    }

    protected double solution(double num1, double num2, String operation) {
        result = 0;
        switch (operation) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> {
                if (num2 == 0) System.out.println("Error: Divided by zero\nTry again");
                else result = num1 / num2;
            }
            case ESC -> running = false;
            default -> System.out.println("Error: Unsupported operation");
        }
        return result;
    }

    private double solution(double num1, String operation) {
        result = 0;
        switch (operation) {
            case "^" -> result = Math.pow(num1, 2);
            case "%" -> result = Math.pow(num1, 0.5);
            case ESC -> running = false;
            default -> clear();
        }
        System.out.format("Result: \n%.2f %s = %.2f\n", num1, operation, result);
        return result;
    }

    @Override
    protected String getValue() {
        return null;
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
