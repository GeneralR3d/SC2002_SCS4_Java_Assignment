package boundary;

import handler.DisplayHandler;
import handler.InputHandler;


import control.EnquiryController;
import control.ReportController;
import control.SuggestionController;
import entity.Camp;
import entity.CommitteeMember;
import entity.Enquiry;
import entity.Reply;
import entity.Staff;
import entity.Suggestion;

import java.util.ArrayList;

import app.SessionInfo;

/**
 * User interface boundary class for {@link CommitteeMember}
 */
public class CommitteeUI {

    /**
     * The main menu.
     * Only point of exit is through {@code Logout}
     */
    public static void displayMenu() {
        int option;
        while (true) {
            System.out.println();
            CommitteeMember committee = (CommitteeMember) SessionInfo.getUser();
            Camp camp = committee.getCommiteeMemberFor();
            System.out.println("You are a committee member for camp " + camp.getName());
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back.");
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
     * Displays all camp details of a {@link Camp}
     * @param camp
     */
    private static void menu_DisplayCampDetails(Camp camp) {
        DisplayHandler.displayResult(camp);
        System.out.println();
        System.out.println("Press any key to go back");
        InputHandler.nextLine();
    }

    /**
     * Displays all {@link Enquiry}s of a {@link Camp}
     * <div>Also allows user to reply to an {@link Enquiry} via {@link CommitteeUI#menu_ReplyEnquiry(Camp, Enquiry)}
     * @param camp
     */
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

     /**
     * Creates and adds {@link Reply} to an {@link Enquiry} from a {@link Camp}
     * @param camp
     * @param enquiry
     */
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

    /**
     * Gets all {@link Suggestion} of a {@link Camp} via {@link SuggestionController#getMySuggestions(Camp)}
     * <div>Displays {@link Suggestion}s via {@link DisplayHandler#displayResult(Suggestion)}
     * <div> Allows {@link Committee} to select options for {@link Suggestion} via {@link CommitteeUI#menu_ManageMySuggestions(Camp, Suggestion)}
     * @param camp
     */
    private static void menu_DisplayMySuggestions(Camp camp) {
        ArrayList<Suggestion> mySuggestions = SuggestionController.getMySuggestions(camp);
        if (mySuggestions.size() == 0) {
            System.out.println();
            System.out.println("You have no suggestions!");
            System.out.println();
            System.out.println("Press any key to exit");
            InputHandler.nextLine();
            return;
        }
        int option;
        while (true) {
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

    /**
     * Allows {@link CommitteeMember} to {@code edit} or {@code delete} {@link Suggestion}s which are still {@code PENDING}
     * <div> Calls {@link SuggestionController#edit(Suggestion, String)} and {@link SuggestionController#delete(Camp, Suggestion)}
     * @param camp
     * @param suggestion
     */
    private static void menu_ManageMySuggestions(Camp camp, Suggestion suggestion) {
        int option;
        while (true) {
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to reply....");
            System.out.println("Enter 0 to go back");
            System.out.println();
            System.out.println("1: Edit Suggestion");
            System.out.println("2: Delete Suggestion");

            option = InputHandler.nextInt();

            switch (option) {
            case 0:
                return;
            case 1:
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
                break;
            default:
                System.out.println("Invalid Input");
                break;
            }

        }
    }

    /**
     * Generates {@link CommitteeMember} report via {@link ReportController#generateReport(Camp, int, String, boolean)}
     * <div> {@link CommitteeMember} can only generate report for the {@link Camp} they are in-charge-of
     * <div> Allows {@link CommitteeMember} to select filter to choose the order which the attendees and committee appears
     */
    private static void menu_GenerateReport(Camp camp) {
        int option;
        while (true) {
            System.out.println("How do you want the list of attendees to be displayed?");
            System.out.println("1: Attendees first");
            System.out.println("2: Commiteee first");
            System.out.println("0: exit");

            option = InputHandler.nextInt();

            if (option < 0 || option > 2) {
                System.out.println("Inavlid input!");
                continue;
            }
            if (option == 0)
                return;
            else
                break;
        }
        ReportController.generateReport(camp, option, "CommitteeReport.txt",false);
        System.out.println("SUCCESS: Report saved to 'CommitteeReport.txt'");
    }

}
