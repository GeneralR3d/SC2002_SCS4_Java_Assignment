package boundary;

import handler.DisplayHandler;
import handler.InputHandler;

import entity.*;
import control.EnquiryController;
import control.SuggestionController;

import java.util.ArrayList;
import java.util.InputMismatchException;

import app.SessionInfo;

public class CommitteeUI {

    public static void displayMenu() {
        int option;
        while(true){
            CommitteeMember committee = (CommitteeMember) SessionInfo.getUser();
            Camp camp = committee.getCommiteeMemberFor();
            System.out.println("You are a committee member for camp " + camp.getName());
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back.");
            System.out.println("1. View details for camp");
            System.out.println("2. Submit suggestion");
            System.out.println("3. View enquries");
            System.out.println("4. Generate report");
            try{
                option = InputHandler.nextInt();
            }
            catch(InputMismatchException e){
                InputHandler.next();
                continue;
            }
            switch (option) {
                case 0:
                    return;
                case 1:
                    menu_DisplayCampDetails(camp);
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
                    menu_DisplayEnquiries(camp);
                    break;
                case 4:
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

    private static void menu_DisplayEnquiries(Camp camp) {
        int option;
        while(true){
            ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
            if(enquiries == null) {
                System.out.println("There are no enquiries for this camp!");
                return;
            }
            System.out.println("Command Options: ");
            System.out.println("Enter number to reply....");
            System.out.println("Enter 0 to go back");
            for (int i = 0; i < enquiries.size(); i++) {
                System.out.println((i+1)+": ");
                DisplayHandler.displayResult(enquiries.get(i));
            }
            while(true){
                try{
                    option = InputHandler.nextInt();
                }
                catch(InputMismatchException e){
                    InputHandler.next();
                    continue;
                }
                if (option == 0) return;
                if (option < 0 || option > enquiries.size()){
                    System.out.println("Invalid input!");
                    continue;
                }
                menu_ReplyEnquiry(camp,enquiries.get(option - 1));
                break;
        }
        }
    }


    private static void menu_ReplyEnquiry(Camp camp, Enquiry enquiry){
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
    private static void menu_GenerateReport(Camp camp) {
        ;
    }

}
