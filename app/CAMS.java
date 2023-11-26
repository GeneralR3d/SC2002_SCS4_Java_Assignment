package app;

import boundary.AccountUI;
import control.DataController;

/**
 * Main entry point into CAMS app
 */
public class CAMS {

    /**
     * Loads information about {@link Student} and {@link Staff} into memory via {@link DataController#init()}
     * @param args
     */
    public static void main(String[] args) {
        DataController.init();
        AccountUI.loginMenu();
    }
}
