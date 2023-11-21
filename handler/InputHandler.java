package handler;

import java.util.Scanner;

public class InputHandler {
    private static Scanner sc = new Scanner(System.in);

    public static String next() {
        System.out.print(">> ");
        return sc.next();
    }

    public static String nextLine() {
        System.out.print(">> ");
        return sc.nextLine();
    }

    public static int nextInt() {
        System.out.print(">> ");
        return sc.nextInt();
    }
}