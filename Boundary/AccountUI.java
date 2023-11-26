package boundary;

import app.SessionInfo;
import control.DataController;
import control.UserController;
import entity.User;
import handler.InputHandler;

public class AccountUI {
    public static void loginMenu() {
        while (true) {
            System.out.println();
            System.out.println("Camp Application and Management System (CAMS) Login");
            System.out.println("Please enter your username:");
            String username = InputHandler.nextLine();
            System.out.println("Please enter your password:");
            String password = InputHandler.nextLine();

            User user = UserController.login(username, password);
            if (user == null) {
                System.out.println("Wrong username or password! Please try again");
                continue;
            }
            // TODO: check for default password
            if (password.equals("password")) {
                System.out.println();
                System.out.println("Welcome to the CAMS! As this is your first time logging in, please change your password.");
                changePasswordMenu();
                continue;
            }
            switch (SessionInfo.getUserType()) {
            case "Staff":
                StaffUI.displayMenu();
                break;
            case "CommitteeMember":
            case "Student":
                StudentUI.displayMenu();
                break;
            default:
                System.out.println("Invalid user type");
                break;
            }
        }
    }

    public static void changePasswordMenu() {
        System.out.println("Change Password");

        String newPW;
        while (true) {
            System.out.println();
            System.out.println("Enter your new password:");
            newPW = InputHandler.nextLine();
            System.out.println("Enter your new password again:");
            String newPW2 = InputHandler.nextLine();
            if (!newPW.equals(newPW2)) {
                System.out.println("Passwords do not match!");
                continue;
            }
            if (newPW.equals(SessionInfo.getUser().getPassword())) {
                System.out.println("New password cannot be your old password!");
                continue;
            }
            if (newPW.equals("password")) {
                System.out.println("New password cannot be the default password!");
                continue;
            }
            break;
        }

        UserController.changePassword(newPW);
        System.out.println("Password changed successfully!");
        logout();
    }

    public static void logout() {
        DataController.save();
        UserController.logout();
        System.out.println("You have been logged out.");
    }
}
