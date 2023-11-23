package Entity;

public class SessionInfo {
    public static User user;
    public static String userType;

    public static void setUser(User user) {
        SessionInfo.user = user;
        if (user != null)
            SessionInfo.userType = user.getClass().toString();
        else
            SessionInfo.userType = null;
    }

    public static User getUser() {
        return user;
    }
}
