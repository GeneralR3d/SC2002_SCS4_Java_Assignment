package app;

import boundary.AccountUI;
import control.DataController;


public class CAMS {

    /**
     * @param args
     */
    public static void main(String[] args) {
        DataController.init();
        //System.out.println("TODO: initalize data");
        while (SessionInfo.getUser() == null) {
            AccountUI.loginMenu();
        }
    }
}
