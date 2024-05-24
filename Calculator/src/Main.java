import resourses.Calculator;
import resourses.CalculatorType;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Console Calculator");
        System.out.println("Choose a calculator type:");
        for (CalculatorType type : CalculatorType.values()) {
            System.out.println(type);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the calculator type: ");
        CalculatorType selectedType = CalculatorType.PROGRAMMER;
        try {
            selectedType = CalculatorType.valueOf(scanner.next().toUpperCase());
        } catch (Exception e) {
            System.out.println("Error: Not such calculator type");

        }
        Calculator calculator = selectedType.getCalculator();
    }
}