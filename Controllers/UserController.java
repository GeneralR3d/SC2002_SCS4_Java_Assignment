package Controllers;

import java.util.ArrayList;
import Entity.User;

public class UserController {
    public static boolean login(ArrayList<User> users, String username, String password){
        for (User user: users){
            // to add get and set methods for password
            if (user.getUserID() == username && user.getPW()) return true;
        }
        return false;
    }

    public static void resetPassword(ArrayList<User> users, User currUser, String newPW){
        for (User user: users){
            if (user.getUserID() == currUser.getUserID()){
                user.changePw(newPW);
            }
        }
    }
}
