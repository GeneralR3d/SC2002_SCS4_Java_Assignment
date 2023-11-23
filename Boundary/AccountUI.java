package Boundary;

import java.util.ArrayList;
import java.util.Scanner;

import control.UserController;
import handler.InputHandler;
import Entity.*;

public class AccountUI {
    public static void loginMenu(){
        System.out.println("Camp Application and Management System (CAMS) Login");
        System.out.println("Please enter your username:");
        String username = InputHandler.nextLine();
        System.out.println("Please enter your password:");
        String password = InputHandler.nextLine();

        if (UserController.login(username, password)){
            //if success return UI for that user
            for (User user: users){
                if (user.getUserID() == username) UI(user);
            }
        }
        else System.out.println("Wrong username or password! Please try again");
    }

    public static void resetPasswordMenu(ArrayList<User> users, User currUser){
        System.out.println("Reset Password");

        String newPW;
        do{
            System.out.println("Enter your new password:");
            newPW = InputHandler.nextLine();
            System.out.println("Enter your new password again:");
            String newPW2 = InputHandler.nextLine();
            if (newPW == newPW2) break;
            else System.out.println("Passwords do not match! Try again.");
        }while(true);

        UserController.changePassword(newPW);
        System.out.println("Password reset successfully!");
    }

    public static void logout(ArrayList<Camp> camps, ArrayList<User> users){
        //save camps into xlsx
        //save users into xlsx

        System.out.println("User logged out successfully!");
        loginMenu(users);
    }
}
