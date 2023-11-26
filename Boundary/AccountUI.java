package boundary;

import app.SessionInfo;
import control.DataController;
import control.UserController;
import entity.User;
import handler.InputHandler;

/**
 * Static UI boundary class for manageing user interface for {@code login}, {@code logout} and {@code changePassword} functionalities
 */
public class AccountUI {
    /**
     * Displays menu for login. This is the main loop of the program when different users {@code login} and {@code logout}.
     * <div>Prompts {@link User} to change {@code password} upon first {@code login}
     * <div>After logging in, dislays the current UI menu for the appropriate {@link User} type
     */
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
            if (password.equals("password")) {
                System.out.println();
                System.out.println(
                        "Welcome to the CAMS! As this is your first time logging in, please change your password.");
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

    /**
     * Menu for prompting {@link User} to change {@code password}
     * <div> Validates both {@code password} entries
     * <div> {@link User} not allowed to change back to deafult {@code password}
     */
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

    /**
     * Displays {@code logout} menu for user
     * <div> Calls {@link DataController#save()} to write data about current {@link User} to persistent memory 
     */
    public static void logout() {
        DataController.save();
        UserController.logout();
        System.out.println("You have been logged out.");
    }
}
