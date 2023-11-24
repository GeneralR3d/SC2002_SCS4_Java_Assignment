package boundary;

import java.util.ArrayList;

import app.SessionInfo;
import control.DataController;
import control.UserController;
import handler.InputHandler;
import entity.*;

public class AccountUI {
    public static void loginMenu(){
        while (true) {
            System.out.println("Camp Application and Management System (CAMS) Login");
            System.out.println("Please enter your username:");
            String username = InputHandler.nextLine();
            System.out.println("Please enter your password:");
            String password = InputHandler.nextLine();

            User user = UserController.login(username, password);
            if (user == null) {
                System.out.println("Wrong username or password! Please try again");
                System.out.println();
                continue;
            }
            System.out.println();
            switch (SessionInfo.getUserType()) {
                case "Staff":
                    StaffUI.displayMenu();
                    break;
                case "Student":
                    StudentUI.displayMenu();
                    break;
            }
        }
    }

    public static void changePasswordMenu() {
        System.out.println("Change Password");

        String newPW;
        do {
            System.out.println("Enter your new password:");
            newPW = InputHandler.nextLine();
            System.out.println("Enter your new password again:");
            String newPW2 = InputHandler.nextLine();
            if (newPW.equals(newPW2)) break;
            else System.out.println("Passwords do not match! Try again.");
        } while(true);

        UserController.changePassword(newPW);
        System.out.println("Password reset successfully!");
    }

    public static void logout(){
        UserController.logout();
        System.out.println("User logged out successfully!");
    }
}
