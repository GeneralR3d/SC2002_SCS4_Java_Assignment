package app;

import entity.CommitteeMember;
import entity.Staff;
import entity.User;

/**
 * Helper class to store runtime information about current user
 * <div>Stores sessionInfo about cuurent {@link User}</div>
 */
public class SessionInfo {
    private static User user;
    private static String userType;

    /**
     * Accessor
     * @return userType of {@link String}
     */
    public static String getUserType() {
        return userType;
    }

    /**
     * Mutator
     * <div>Sets both {@code user} and {@code userType}</div>
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

    /**
     * Accessor
     * @return user of type {@link User}
     */
    public static User getUser() {
        return user;
    }
}
