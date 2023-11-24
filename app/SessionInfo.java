package app;

import entity.CommitteeMember;
import entity.Staff;
import entity.User;

public class SessionInfo {
    private static User user;
    private static String userType;

    public static String getUserType() {
        return userType;
    }

    /**
     * @param user
     */
    public static void setUser(User user) {
        if (user == null)
            return;
        SessionInfo.user = user;
        if (user instanceof Staff) {
            SessionInfo.userType = "Staff";
        } else if (user instanceof CommitteeMember) {
            SessionInfo.userType = "CommitteeMember";
        } else {
            SessionInfo.userType = "Student";
        }
    }

    public static User getUser() {
        return user;
    }
}
