package boundary;

import handler.InputHandler;

import entity.*;
import control.EnquiryController;
import control.SuggestionController;

import java.util.ArrayList;

import app.SessionInfo;

public class CommitteeUI {

    public static void displayMenu() {
        CommitteeMember committee = (CommitteeMember) SessionInfo.getUser();
        Camp camp = committee.getCommiteeMemberFor();
        System.out.println("You are a committee member for camp " + camp.getName());
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("1. View details for camp");
        System.out.println("2. Submit suggestion");
        System.out.println("3. View enquries");
        System.out.println("4. Generate report");
        int option = InputHandler.nextInt();
        switch (option) {
        case 1:
            displayCampDetails(camp);
            break;
        case 2:
            System.out.println("Enter ~ to quit");
            System.out.println("Key in suggestion, press enter to confirm: ");
            String suggestion = InputHandler.nextLine();
            if (suggestion.equals("~"))
                break;
            else
                SuggestionController.post(SessionInfo.getUser(), camp, suggestion);
            System.out.println("Suggestion has been posted!");
            break;
        case 3:
            displayEnquiries(camp);
            break;
        case 4:
            generateReport(camp);
            break;
        default:
            break;
        }
    }

    /**
     * @param camp
     */
    private static void displayCampDetails(Camp camp) {
        System.out.println("Name: " + camp.getName());
        System.out.println("Start Date: " + camp.getStartDate());
        System.out.println("End Date: " + camp.getEndDate());
        System.out.println("Registration close Date: " + camp.getRegCloseDate());
        System.out.println("School it is opened to: " + camp.getOpenToFaculty());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Total slots available: " + camp.getTotalSlotsLeft());
        System.out.println("Total camp committee slots available: " + camp.getCommSlotsLeft());
        System.out.println("Description: " + camp.getDescription());
        System.out.println("Staff in charge is " + camp.getStaffInCharge().getUserID());
    }

    private static void displayEnquiries(Camp camp) {
        ArrayList<Enquiry> enquiries = camp.getEnquiries();
        while (true) {
            System.out.println("Command Options: ");
            System.out.println("Enter number to reply....");
            System.out.println("Enter 0 to go back");
            for (int i = 0; i < enquiries.size(); i++) {
                DisplayHelper.displayResult(enquiries.get(i));
            }
            while (true) {
                int option = InputHandler.nextInt();
                if (option == 0)
                    return;
                if (option < 0 || option > enquiries.size())
                    return;
                else
                    System.out.println("Enter ~ to go back");
                System.out.println("Key in reply, press enter to confirm: ");
                String reply = InputHandler.nextLine();
                if (reply.equals("~"))
                    break;
                EnquiryController.addReply(camp, enquiries.get(option - 1), reply);
                break;
            }
        }
    }

    private static void generateReport(Camp camp) {
        ;
    }

}
