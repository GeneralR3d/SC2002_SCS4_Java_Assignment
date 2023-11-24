package boundary;


import control.*;
import entity.*;
import handler.InputHandler;
import java.util.ArrayList;

import app.SessionInfo;

public class StudentUI {
    public static void displayMenu() {
        int option;
        do {
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("1. View open camps");
            System.out.println("2. See all registered camps");
            System.out.println("3. Withdraw from registered camps");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            if (SessionInfo.getUserType() == "CommitteeMember") {
                System.out.println();
                System.out.println("You are a committee member");
                System.out.println("6. Committee member menu");
            }

            option = InputHandler.nextInt();
            switch(option){
                case 1:
                    ArrayList<Camp> openCamps = CampController.getAvailableCamps();
                    displayOpenCamps(openCamps);
                    break;
                case 2:
                    ArrayList<Camp> signedUpCamps = CampController.getSignedUpCamps();
                    displayRegisteredCamps(signedUpCamps);
                    break;
                case 3:
                    withdrawRegisteredCamps();
                    break;
                case 4:
                    AccountUI.changePasswordMenu();
                    break;
                case 5:
                    AccountUI.logout();
                    option = 0;
                    break;
                default:
                    break;
            }
        } while (option != 0);
        AccountUI.loginMenu();
    }


    /**
     * @param openCamps
     */
    private static void displayOpenCamps(ArrayList<Camp> openCamps) {
        System.out.println();
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back.");

        DisplayHelper.displayResult(openCamps);


        while (true) {
            int option = InputHandler.nextInt();
            if (option == 0)
                return;
            else if (option < 0 || option > openCamps.size()){
                System.out.println("Invalid input!");
                continue;
            }
            else viewCampOptions(openCamps.get(option-1));
        }
    }

    private static void displayRegisteredCamps(ArrayList<Camp> registeredCamps) {
        System.out.println();
        System.out.println("Command Options: ");
        System.out.println("Enter 0 to go back");
        DisplayHelper.displayResult(registeredCamps, SessionInfo.getUser());
        while (true){
            int option = InputHandler.nextInt();
            if (option == 0)
                break;
            else if (option < 0 || option > registeredCamps.size()){
                System.out.println("Invalid input!");
                continue;
            }
        }
        displayMenu();
    }

    private static void withdrawRegisteredCamps() {
        ArrayList<Camp> registeredCamps = CampController.getSignedUpCamps();

        System.out.println("Command Options: ");
        System.out.println("Enter number to withdraw from that camp....");
        System.out.println("Enter 0 to go back");
        DisplayHelper.displayResult(registeredCamps, SessionInfo.getUser());

        while (true){
            int option = InputHandler.nextInt();
            if (option == 0)
                break;
            else if (option<0 || option>registeredCamps.size()){
                System.out.println("Invalid input!");
                continue;
            }
            else CampController.removeAttendee(registeredCamps.get(option-1));
        }
        displayMenu();
    }

    private static void viewCampOptions(Camp camp) {
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back");
        System.out.println("Number of slots left for "+ camp.getName());
        System.out.println("Attendees: " + camp.getAttendeeSlotsLeft() + ", Committee: " + camp.getCommSlotsLeft());
        System.out.println("1. Register as attendee");
        System.out.println("2. Register as camp committee member");
        System.out.println("3. Submit enquiry about camp");
        System.out.println("4. View all enquiries");
        System.out.println("5. Manage my enquiries");

        int option = InputHandler.nextInt();
        switch(option){
            case 1:
                try {
                    CampController.registerAttendee(camp);
                    System.out.println("You have successfully registered as attendee!");
                    System.out.println("Enter 0 to go back");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Enter 0 to go back");
                }
                break;
            case 2:
                try {
                    CampController.registerCommittee(camp);
                    System.out.println("You have successfully registered as Committee Member!");
                    System.out.println("Enter 0 to go back");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Enter 0 to go back");
                }
                break;
            case 3:
                System.out.println("Enter ~ to quit...");
                System.out.println("Key in enquiry, press enter to confirm: ");
                String question = InputHandler.nextLine();
                if (question.equals("~")) break;
                EnquiryController.post(camp, question);
                System.out.println("Enquiry has been posted!");
                break;
            case 4:
                ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
                for (int i = 0; i < enquiries.size(); i++) {
                    DisplayHelper.displayResult(enquiries.get(i));
                }
            case 5:
                ArrayList<Enquiry> enquiriesByStudent = EnquiryController.getUserEnquiries(camp);
                if (enquiriesByStudent == null) {
                    System.out.println("You have no enquiries for this camp!");
                    break;
                }
                displayMyEnquiries(camp, enquiriesByStudent);
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    private static void displayMyEnquiries(Camp camp, ArrayList<Enquiry> enquiries) {
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back");
        for (int i = 0; i < enquiries.size(); i++) {
            DisplayHelper.displayResult(enquiries.get(i));
            if (enquiries.get(i).isProcessed()) {
                System.out.println("Status: Processed");
            }
            if (!enquiries.get(i).isProcessed()) {
                System.out.println("Status: Not Processed");
            }
        }
        while (true) {
            int option = InputHandler.nextInt();
            if (option == 0) return;
            if (option < 0 || option > enquiries.size()) {
                System.out.println("Invalid input!");
                continue;
            }
            viewEnquiryOptions(camp, enquiries.get(option));
        }
    }

    private static void viewEnquiryOptions(Camp camp, Enquiry enquiry) {

        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back");
        System.out.println("1. Edit enquiry");
        System.out.println("2. Delete enquiry");
        int option = InputHandler.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter ~ to quit");
                System.out.println("Key in enquiry, press enter to confirm: ");
                String question = InputHandler.nextLine();

                if (question.equals("~")) break;

                try {
                    EnquiryController.edit(enquiry, question);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("Enquiry has been edited!");
                break;
            case 2:
                try {
                    EnquiryController.delete(camp, enquiry);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("Enquiry has been edited!");
                break;
            default:
                break;
        }
    }

}
