package control;

import app.SessionInfo;
import entity.User;

public class UserController {

    /**
     * @param userID
     * @param password
     * @return boolean
     */
    public static User login(String userID, String password) {
        User user = DataController.findUser(userID);
        if (user == null)
            return null;
        if (!user.verifyPassword(password)) {
            return null;
        }
        SessionInfo.setUser(user);
        return user;
    }

    public static void logout() {
        DataController.save();
        SessionInfo.setUser(null);
    }

    public static void changePassword(String newPassword) {
        SessionInfo.getUser().changepassword(newPassword);
    }

    public static boolean checkPermission(Class<?> c) {
        return c.isInstance(SessionInfo.getUser());
    }
}
