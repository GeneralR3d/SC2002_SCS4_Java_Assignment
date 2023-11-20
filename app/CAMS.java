package app;

import Boundary.AccountUI;
import Entity.SessionInfo;

public class CAMS {
    public static void main(String[] args) {
        // TODO: dataController.init()
        System.out.println("TODO: initalize data");
        while (SessionInfo.user == null) {
            AccountUI.loginMenu();
        }
    }
}
