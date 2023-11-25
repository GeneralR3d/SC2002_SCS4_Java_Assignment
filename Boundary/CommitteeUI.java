package boundary;

import handler.DisplayHandler;
import handler.InputHandler;

import entity.*;
import control.EnquiryController;
import control.SuggestionController;

import java.util.ArrayList;

import app.SessionInfo;

public class CommitteeUI {

    public static void displayMenu() {
        int option;
        while (true) {
            CommitteeMember committee = (CommitteeMember) SessionInfo.getUser();
            Camp camp = committee.getCommiteeMemberFor();
            System.out.println();
            System.out.println("You are a committee member for camp: " + camp.getName());
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back.");
            System.out.println();
            System.out.println("1. View details for camp");
            System.out.println("2. View enquries");
            System.out.println("3. Submit suggestion");
            System.out.println("4. Manage suggestions");
            System.out.println("5. Generate report");

            option = InputHandler.nextInt();

            switch (option) {
                case 0:
                    return;
                case 1:
                    menu_DisplayCampDetails(camp);
                    break;
                case 2:
                    menu_DisplayEnquiries(camp);
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Enter ~ to quit");
                    System.out.println("Key in suggestion, press enter to confirm: ");
                    String suggestion = InputHandler.nextLine();
                    if (suggestion.equals("~"))
                        break;
                    else
                        SuggestionController.post(camp, suggestion);
                    System.out.println("Suggestion has been posted!");
                    break;
                case 4:
                    menu_DisplayMySuggestions(camp);
                    break;
                case 5:
                    menu_GenerateReport(camp);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @param camp
     */
    private static void menu_DisplayCampDetails(Camp camp) {
        DisplayHandler.displayResult(camp);
        System.out.println();
        System.out.println("Enter 0 to go back");
        InputHandler.nextLine();
    }

    private static void menu_DisplayEnquiries(Camp camp) {
        int option;
        while (true) {
            ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
            if (enquiries == null) {
                System.out.println("There are no enquiries for this camp!");
                return;
            }
            System.out.println("Command Options: ");
            System.out.println("Enter number to reply....");
            System.out.println("Enter 0 to go back");
            for (int i = 0; i < enquiries.size(); i++) {
                System.out.println();
                System.out.println((i + 1) + ": ");
                DisplayHandler.displayResult(enquiries.get(i));
            }
            while (true) {

                option = InputHandler.nextInt();

                if (option == 0)
                    return;
                if (option < 0 || option > enquiries.size()) {
                    System.out.println("Invalid input!");
                    continue;
                }
                menu_ReplyEnquiry(camp, enquiries.get(option - 1));
                break;
            }
        }
    }

    public static void menu_ReplyEnquiry(Camp camp, Enquiry enquiry) {
        System.out.println("Enter ~ to go back");
        System.out.println("Key in reply, press enter to confirm: ");
        String reply = InputHandler.nextLine();
        if (reply.equals("~"))
            return;
        try {
            EnquiryController.addReply(camp, enquiry, reply);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    private static void menu_DisplayMySuggestions(Camp camp) {
        int option;
        while (true) {
            ArrayList<Suggestion> mySuggestions = SuggestionController.getMySuggestions(camp);
            if (mySuggestions.size() == 0) {
                System.out.println();
                System.out.println("You have no suggestions!");
                System.out.println();
                System.out.println("Enter 0 to exit");
                InputHandler.nextLine();
                return;
            }
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            for (int i = 0; i < mySuggestions.size(); i++) {
                System.out.println();
                System.out.println((i + 1) + ":");
                DisplayHandler.displayResult(mySuggestions.get(i));
            }

            option = InputHandler.nextInt();

            if (option == 0)
                return;
            if (option < 0 || option > mySuggestions.size()) {
                System.out.println("Invalid input!");
                continue;
            }
            menu_ManageMySuggestions(camp, mySuggestions.get(option - 1));
        }
    }

    private static void menu_ManageMySuggestions(Camp camp, Suggestion suggestion) {
        int option;
        while (true) {
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            System.out.println();
            System.out.println("1: Edit Suggestion");
            System.out.println("2: Delete Suggestion");

            option = InputHandler.nextInt();

            switch (option) {
                case 0:
                    return;
                case 1:
                    System.out.println();
                    System.out.println("Enter ~ to go back");
                    System.out.println("Key in your new Suggestion, press enter to confirm: ");
                    String newSuggestion = InputHandler.nextLine();
                    if (newSuggestion.equals("~"))
                        return;
                    try {
                        SuggestionController.edit(suggestion, newSuggestion);
                        System.out.println("Suggestion has been edited successfully!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                case 2:
                    try {
                        SuggestionController.delete(camp, suggestion);
                        System.out.println("Suggestion has been deleted successfully!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                default:
                    System.out.println("Invalid Input");
                    break;
            }

        }
    }

    private static void menu_GenerateReport(Camp camp) {
        ;
    }

}
