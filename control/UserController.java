package control;

import entity.SessionInfo;
import entity.User;

public class UserController {

    /**
     * @param userID
     * @param password
     * @return boolean
     */
    public static boolean login(String userID, String password) {
        User user = DataController.findUser(userID);
        if (user == null)
            return false;
        if (user.verifyPassword(password)) {
            SessionInfo.setUser(user);
            return true;
        } else
            return false;
    }

    public static void logout() {
        SessionInfo.setUser(null);
    }

    public static void changePassword(String newPassword) {
        SessionInfo.user.changePw(newPassword);
    }

    public static boolean checkPermission(Class<?> c) {
        return c.isInstance(SessionInfo.getUser());
    }
}
