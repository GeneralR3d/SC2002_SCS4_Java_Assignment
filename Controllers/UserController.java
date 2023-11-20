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
            if (userID == user.getUserID())
                return true;
        }
        return false;
    }

    public static void logout() {
        SessionInfo.user = null;
    }

    public static void changePassword(String newPassword) {
        // TODO: DataController.changePassword(newPassword);
        System.out.println("TODO: write new password to DataController");
    }
}
