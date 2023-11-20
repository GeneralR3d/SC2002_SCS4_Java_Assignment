package Entity;

public class SessionInfo {
    public static User user;
    public static Class<?> userType;

    public static void setUser(User user) {
        SessionInfo.user = user;
        if (user != null)
            SessionInfo.userType = user.getClass();
        else
            SessionInfo.userType = null;
    }
}
