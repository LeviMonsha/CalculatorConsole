package Resourses;

import java.io.IOException;
import java.util.Scanner;

public class Calculator {
    private static final int ESC = 27;
    private static boolean running = true;
    private double result;
    private final Scanner scanner;

    public Calculator() {
        System.out.println("Welcome to the Console Calculator");
        System.out.println("The following mathematical operations are available to you:");
        System.out.println("addition: +\nsubtraction: -\nmultiplication: *\ndivision: /");

        scanner = new Scanner(System.in);
        update();
    }

    void update() {
        double num1;
        double num2;
        char operation;
        while (running) {
            System.out.format("first num = ");
            num1 = scanner.nextDouble();
            System.out.format("second num = ");
            num2 = scanner.nextDouble();

            operation = scanner.next().charAt(0);

            switch (operation) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> {
                    if (num2 == 0) System.out.println("Divided by zero\nTry again");
                    else result = num1 / num2;
                }
                case ESC -> running = false;
            }
            System.out.format("Result: \n%.2f %c %.2f = %.2f\n", num1, operation, num2, result);
        }
    }

}
