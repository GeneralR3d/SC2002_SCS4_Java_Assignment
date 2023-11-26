package handler;

import java.time.LocalDate;
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
     * <div>Handles the {@link java.util.InputMismatchException} thrown by {@link Scanner#nextInt()} if the user does not type in an integer</div>
     * @return int
     */
    public static int nextInt() {
        int num;
        while (true) {
            try {
                System.out.print(">> ");
                num = sc.nextInt();
                break;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Invalid Input");
            }
        }
        sc.nextLine();
        return num;
    }



    /**
     * gets a LocalDate from the user.
     * <div>Catches the exception thrown by {@link java.time.LocalDate#parse(CharSequence)}</div>
     * @return {@link java.time.LocalDate}
     */
    public static LocalDate nextDate() {
        LocalDate date;
        while (true) {
            try {
                date = LocalDate.parse(nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid Date");
            }
        }
        return date;
    }
}
