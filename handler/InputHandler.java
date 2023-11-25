package handler;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles the input handling from user, so only one scanner is needed across
 * the entire application.
 */
public class InputHandler {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Analogous to {@link Scanner#next}
     *
     * @return String
     */
    public static String next() {
        System.out.print(">> ");
        String s = sc.next();
        sc.nextLine();
        return s;
    }

    /**
     * Analogous to {@link Scanner#nextLine}
     *
     * @return String
     */
    public static String nextLine() {
        System.out.print(">> ");
        return sc.nextLine();
    }

    /**
     * Analogous to {@link Scanner#nextInt}
     *
     * @return int
     */
    public static int nextInt() throws InputMismatchException {
        System.out.print(">>> ");
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }
}
