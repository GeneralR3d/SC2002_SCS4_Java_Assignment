package control;

import app.SessionInfo;
import entity.User;

/**
 * Static class for handling the {@code login}, {@code logout} and {@code changePassword} for {@link User}
 */
public class UserController {

    /**
     * Verifies the {@code USERID} and {@code password} are correct
     * Logs in the user by setting {@link SessionInfo}
     * @param userID
     * @param password
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

    /**
     * Logs the user out
     */
    public static void logout() {
        SessionInfo.setUser(null);
    }

    /**
     * Changes password of the user
     * @param newPassword
     */
    public static void changePassword(String newPassword) {
        SessionInfo.getUser().changepassword(newPassword);
    }

    /**
     * Ensures the current {@link User} is of a certain type
     * @param clist the {@link User} type
     * @throws Exception if current user is not of the {@link User} type passed in
     */
    public static void assertUserType(Class<?>... clist) throws Exception {
        for (int i = 0; i < clist.length; i++) {
            if (!clist[i].isInstance(SessionInfo.getUser()))
                throw new Exception("Error: User does not have permission to use this function!");
        }
    }
}
