package entity;

public class SessionInfo {
    private static User user;
    private static Class<?> userType;

    public static void setUser(User user) {
        SessionInfo.user = user;
        if (user != null)
            SessionInfo.userType = user.getClass();
        else
            SessionInfo.userType = null;
    }

    public static User getUser() {
        return user;
    }
}
