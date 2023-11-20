package Controllers;

import java.util.ArrayList;

import Entity.SessionInfo;
import Entity.User;

public class UserController {
    public static boolean login(String userID, String password) {
        // TODO: initialize users array with correct data
        System.out.println("TODO: initialize users array with correct data");
        ArrayList<User> users = new ArrayList<User>();
        for (User user : users) {
            if (userID == user.getUserID()) {
                if (user.verifyPassword(password)) {
                    SessionInfo.setUser(user);
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    public static void logout() {
        SessionInfo.setUser(null);
    }

    public static void changePassword(String newPassword) {
        // TODO: DataController.changePassword(newPassword);
        System.out.println("TODO: write new password to DataController");
    }
}
