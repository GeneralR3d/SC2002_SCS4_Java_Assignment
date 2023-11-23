package app;

import Boundary.AccountUI;
import Entity.SessionInfo;
import control.DataController;


public class CAMS {
    public static void main(String[] args) {
        DataController.init();
        //System.out.println("TODO: initalize data");
        while (SessionInfo.user == null) {
            AccountUI.loginMenu();
        }
    }
}
