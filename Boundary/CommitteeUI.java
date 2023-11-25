package boundary;

import handler.DisplayHandler;
import handler.InputHandler;

import entity.*;
import control.EnquiryController;
import control.SuggestionController;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import app.SessionInfo;

public class CommitteeUI {

    public static void displayMenu() {
        int option;
        while (true) {
            CommitteeMember committee = (CommitteeMember) SessionInfo.getUser();
            Camp camp = committee.getCommiteeMemberFor();
            reportStream.write("You are a committee member for camp " + camp.getName());
            reportStream.write("Command Options: ");
            reportStream.write("Enter number to select....");
            reportStream.write("Enter 0 to go back.");
            reportStream.write("1. View details for camp");
            reportStream.write("2. View enquries");
            reportStream.write("3. Submit suggestion");
            reportStream.write("4. Manage suggestions");
            reportStream.write("5. Generate report");

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
                    reportStream.write("Enter ~ to quit");
                    reportStream.write("Key in suggestion, press enter to confirm: ");
                    String suggestion = InputHandler.nextLine();
                    if (suggestion.equals("~"))
                        break;
                    else
                        SuggestionController.post(camp, suggestion);
                    reportStream.write("Suggestion has been posted!");
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
        reportStream.write();
        reportStream.write("Press any key to go back");
        InputHandler.nextLine();
    }

    private static void menu_DisplayEnquiries(Camp camp) {
        int option;
        while (true) {
            ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
            if (enquiries == null) {
                reportStream.write("There are no enquiries for this camp!");
                return;
            }
            reportStream.write("Command Options: ");
            reportStream.write("Enter number to reply....");
            reportStream.write("Enter 0 to go back");
            for (int i = 0; i < enquiries.size(); i++) {
                reportStream.write();
                reportStream.write((i + 1) + ": ");
                DisplayHandler.displayResult(enquiries.get(i));
            }
            while (true) {

                option = InputHandler.nextInt();

                if (option == 0)
                    return;
                if (option < 0 || option > enquiries.size()) {
                    reportStream.write("Invalid input!");
                    continue;
                }
                menu_ReplyEnquiry(camp, enquiries.get(option - 1));
                break;
            }
        }
    }

    public static void menu_ReplyEnquiry(Camp camp, Enquiry enquiry) {
        reportStream.write("Enter ~ to go back");
        reportStream.write("Key in reply, press enter to confirm: ");
        String reply = InputHandler.nextLine();
        if (reply.equals("~"))
            return;
        try {
            EnquiryController.addReply(camp, enquiry, reply);
        } catch (Exception e) {
            reportStream.write(e.getMessage());
            return;
        }

    }

    private static void menu_DisplayMySuggestions(Camp camp) {
        ArrayList<Suggestion> mySuggestions = SuggestionController.getMySuggestions(camp);
        if (mySuggestions.size() == 0) {
            reportStream.write();
            reportStream.write("You have no suggestions!");
            reportStream.write();
            reportStream.write("Press any key to exit");
            InputHandler.nextLine();
        }
        int option;
        while (true) {
            reportStream.write();
            reportStream.write("Command Options: ");
            reportStream.write("Enter number to reply....");
            reportStream.write("Enter 0 to go back");
            for (int i = 0; i < mySuggestions.size(); i++) {
                reportStream.write();
                reportStream.write((i + 1) + ":");
                DisplayHandler.displayResult(mySuggestions.get(i));
            }

            option = InputHandler.nextInt();

            if (option == 0)
                return;
            if (option < 0 || option > mySuggestions.size()) {
                reportStream.write("Invalid input!");
                continue;
            }
            menu_ManageMySuggestions(camp, mySuggestions.get(option - 1));
        }
    }

    private static void menu_ManageMySuggestions(Camp camp, Suggestion suggestion) {
        int option;
        while (true) {
            reportStream.write();
            reportStream.write("Command Options: ");
            reportStream.write("Enter number to reply....");
            reportStream.write("Enter 0 to go back");
            reportStream.write();
            reportStream.write("1: Edit Suggestion");
            reportStream.write("2: Delete Suggestion");

            option = InputHandler.nextInt();

            switch (option) {
                case 0:
                    return;
                case 1:
                    reportStream.write("Enter ~ to go back");
                    reportStream.write("Key in your new Suggestion, press enter to confirm: ");
                    String newSuggestion = InputHandler.nextLine();
                    if (newSuggestion.equals("~"))
                        return;
                    try {
                        SuggestionController.edit(suggestion, newSuggestion);
                        reportStream.write("Suggestion has been edited successfully!");
                    } catch (Exception e) {
                        reportStream.write(e.getMessage());
                    }
                    return;
                case 2:
                    try {
                        SuggestionController.delete(camp, suggestion);
                        reportStream.write("Suggestion has been deleted successfully!");
                    } catch (Exception e) {
                        reportStream.write(e.getMessage());
                    }
                    break;
                default:
                    reportStream.write("Invalid Input");
                    break;
            }

        }
    }

    private static void menu_GenerateReport(Camp camp) {
        ArrayList<Student> attendees = camp.getAttendees();
        ArrayList<CommitteeMember> committeeMembers = camp.getCommittee();

        int option;
        while(true){
        System.out.println("How do you want the list of attendees to be displayed?");
        System.out.println("1: Attendees first");
        System.out.println("2: Commiteee first");
        System.out.println("0: exit");

        option = InputHandler.nextInt();

        if(option != 0 || option != 1 || option != 2) {
            System.out.println("Inavlid input!");
            continue;
        }
        if(option == 0) return;
        else break;
    }


        try {
            BufferedWriter reportStream = new BufferedWriter(new FileWriter("committeeReport.txt"));
            reportStream.write("=====Committee Member Report For Camp "+camp.getName()+" =============\r");
            //camp details
            reportStream.write("\n");
            reportStream.write("Camp Details: \r");
            reportStream.write("Camp name: " + camp.getName()+"\r");
            reportStream.write("Start date: " + camp.getStartDate()+"\r");
            reportStream.write("End date: " + camp.getEndDate()+"\r");
            reportStream.write("Registration Closing Date: " + camp.getRegCloseDate()+"\r");
            reportStream.write("Faculty: " + camp.getOpenToFaculty()+"\r");
            reportStream.write("Location: " + camp.getLocation()+"\r");
            reportStream.write("Total slots: " + camp.getTotalSlotsLeft()+"\r");
            reportStream.write("Camp committee slots: " + camp.getCommSlotsLeft()+"\r");
            reportStream.write("Description: " + camp.getDescription()+"\r");
            reportStream.write("Staff in charge: " + camp.getStaffInCharge().getName()+"\r");
            //camp participants
            reportStream.write("\n");
            if(option ==1){
                reportStream.write("Camp Attendees: \r");
                for(int i =0;i<attendees.size();i++){
                    reportStream.write((i+1)+": "+attendees.get(i).getName()+" from "+attendees.get(i).getFaculty()+"\r");
                }
                reportStream.write("Camp Committee Members: \r");
                for(int i =0;i<committeeMembers.size();i++){
                    reportStream.write((i+1)+": "+committeeMembers.get(i).getName()+" from "+committeeMembers.get(i).getFaculty()+"\r");
                }
            }
            if(option == 2){
                reportStream.write("Camp Committee Members: \r");
                for(int i =0;i<committeeMembers.size();i++){
                    reportStream.write((i+1)+": "+committeeMembers.get(i).getName()+" from "+committeeMembers.get(i).getFaculty()+"\r");
                }
                reportStream.write("Camp Attendees: \r");
                for(int i =0;i<attendees.size();i++){
                    reportStream.write((i+1)+": "+attendees.get(i).getName()+" from "+attendees.get(i).getFaculty()+"\r");
                }
            }
            
            
            //camp enquiries
            reportStream.write("\n");
            reportStream.write("All Camp Enquiries: \r");

            ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
            if(enquiries == null) reportStream.write("No enquiries!");
            else
            for(Enquiry enquiry: enquiries){

                reportStream.write("Enquiry by " + enquiry.getOwner().getName() + ": " + enquiry.view()+"\r");
                if (!enquiry.isProcessed()) {
                    reportStream.write("\tThere are currently no replies!\r");
                }
                else 
                for (Reply reply : enquiry.getReplies()) {
                    reportStream.write("\rReplied by " + reply.getOwnerID() + ": " + reply.view()+"\r");
                }
            }         
            
            
            reportStream.write("============End-of-Report=========================================");
            reportStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("committeeReport.txt not found!\n\r" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("file error!\n\r" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

}
