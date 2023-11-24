package app;

import Boundary.AccountUI;
import control.DataController;
import entity.SessionInfo;


public class CAMS {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        DataController.init();
        //System.out.println("TODO: initalize data");
        while (SessionInfo.user == null) {
            AccountUI.loginMenu();
        }
    }
}
