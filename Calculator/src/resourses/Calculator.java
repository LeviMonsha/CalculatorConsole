package resourses;

import java.util.Scanner;



public abstract class Calculator {
    protected static final String ESC = "esc";
    protected static boolean running = true;
    protected double result;
    protected Scanner scanner;

    public Calculator() {

    }

    protected abstract void showAvOp();

    protected abstract void update() throws Exception;

    protected abstract String getValue();

    protected static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
