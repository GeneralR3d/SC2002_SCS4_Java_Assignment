package boundary;

import control.*;
import entity.*;
import handler.DisplayHandler;
import handler.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

import app.SessionInfo;

public class StudentUI {
    public static void displayMenu() {
        int option;
        while (true) {
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("1. View open camps");
            System.out.println("2. Search for camps");
            System.out.println("3. See all registered camps");
            System.out.println("4. Withdraw from registered camps");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            if (SessionInfo.getUserType() == "CommitteeMember") {
                CommitteeMember commMember = (CommitteeMember) SessionInfo.getUser();
                System.out.println();
                System.out.println("You are a committee member. Points: " + commMember.getPoints());
                System.out.println("7. Committee member menu");
            }
            try {
                option = InputHandler.nextInt();
            } catch (InputMismatchException e) {
                // clear input buffer
                InputHandler.next();
                continue;
            }

            switch (option) {
                case 1:
                    ArrayList<Camp> openCamps = CampController.getAvailableCamps();
                    menu_DisplayOpenCamps(openCamps);
                    break;
                case 2:
                    menu_SearchForCamp();
                    break;
                case 3:
                    ArrayList<Camp> signedUpCamps = CampController.getSignedUpCamps();
                    menu_DisplayRegisteredCamps(signedUpCamps);
                    break;
                case 4:
                    menu_WithdrawRegisteredCamps();
                    break;
                case 5:
                    AccountUI.changePasswordMenu();
                    return;
                case 6:
                    AccountUI.logout();
                    return;
                case 7:
                    CommitteeUI.displayMenu();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @param openCamps
     */
    private static void menu_DisplayOpenCamps(ArrayList<Camp> openCamps) {
        int option;
        while (true) {
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back.");

            DisplayHandler.displayResult(openCamps);
            try{
                option = InputHandler.nextInt();
            }
            catch(InputMismatchException e){
                InputHandler.next();
                continue;
            }
            if (option == 0) return;
            if (option < 0 || option > openCamps.size()) {
                System.out.println("Invalid input!");
                continue;
            }
            menu_ViewCampOptions(openCamps.get(option - 1));
        }
    }

    private static void menu_SearchForCamp() {
        int option;
        while(true){
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            System.out.println("Search by:");
            System.out.println("1. Camp name");
            System.out.println("2. Camp start date");
            System.out.println("3. Camp end date");
            System.out.println("4. Camp faculty");
            System.out.println("5. Camp location");
            System.out.println("6. Attendee");
            System.out.println("7. Committee member");

            try{
                option = InputHandler.nextInt();
            }
            catch(InputMismatchException e){
                InputHandler.next();
                continue;
            }
            
            ArrayList<Camp> result;
            switch (option) {
                case 0:
                    return;
                case 1:
                    System.out.println("Enter camp name:");
                    String campName = InputHandler.nextLine();
                    result = SearchController.searchByCampName(campName);
                    DisplayHandler.displaySearchResult(result);
                    break;
                case 2:
                    System.out.println("Enter camp start date (YYYY-MM-DD):");
                    LocalDate startDate = InputHandler.nextDate();
                    result = SearchController.searchByStartDate(startDate);
                    DisplayHandler.displaySearchResult(result);
                    break;
                case 3:
                    System.out.println("Enter camp end date (YYYY-MM-DD):");
                    LocalDate endDate = InputHandler.nextDate();
                    result = SearchController.searchByEndDate(endDate);
                    DisplayHandler.displaySearchResult(result);
                    break;
                case 4:
                    System.out.println("Enter camp faculty:");
                    try{
                        Faculty faculty = Faculty.valueOf(InputHandler.nextLine());
                        result = SearchController.searchByFaculty(faculty);
                        DisplayHandler.displaySearchResult(result);
                    } catch (Exception e){
                        System.out.println("No results found!");
                    }
                    break;
                case 5:
                    System.out.println("Enter camp location:");
                    String location = InputHandler.nextLine();
                    result = SearchController.searchByLocation(location);
                    DisplayHandler.displaySearchResult(result);
                    break;
                case 6:
                    System.out.println("Enter attendee name:");
                    String attendeeName = InputHandler.nextLine();
                    result = SearchController.searchByAttendee(attendeeName);
                    DisplayHandler.displaySearchResult(result);
                    break;
                case 7:
                    System.out.println("Enter committee member name:");
                    String commMemberName = InputHandler.nextLine();
                    result = SearchController.searchByAttendee(commMemberName);
                    DisplayHandler.displaySearchResult(result);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }

    private static void menu_DisplayRegisteredCamps(ArrayList<Camp> registeredCamps) {
        System.out.println();
        System.out.println("Command Options: ");
        System.out.println("Enter 0 to go back");
        DisplayHandler.displayResult(registeredCamps, SessionInfo.getUser());
        int option;
        while (true) {
            try{
            option = InputHandler.nextInt();
            }
            catch(InputMismatchException e){
                InputHandler.next();
                continue;
            }
            if (option == 0) return;
            if (option < 0 || option > registeredCamps.size()) {
                System.out.println("Invalid input!");
                continue;
            }
        }
    }

    private static void menu_WithdrawRegisteredCamps() {
        int option;
        while (true) {
            ArrayList<Camp> registeredCamps = CampController.getSignedUpCamps();

            System.out.println("Command Options: ");
            System.out.println("Enter number to withdraw from that camp....");
            System.out.println("Enter 0 to go back");
            DisplayHandler.displayResult(registeredCamps, SessionInfo.getUser());
            
            try{
                option = InputHandler.nextInt();
            }
            catch(InputMismatchException e){
                InputHandler.next();
                continue;
            }
            if (option == 0) return;
            if (option < 0 || option > registeredCamps.size()) {
                System.out.println("Invalid input!");
                continue;
            }
            CampController.removeAttendee(registeredCamps.get(option - 1));
        }
    }

    private static void menu_ViewCampOptions(Camp camp) {
        int option;
        while(true){
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            System.out.println("Number of slots left for " + camp.getName());
            System.out.println("Attendees: " + camp.getAttendeeSlotsLeft() + ", Committee: " + camp.getCommSlotsLeft());
            System.out.println("1. Register as attendee");
            System.out.println("2. Register as camp committee member");
            System.out.println("3. Submit enquiry about camp");
            System.out.println("4. View all enquiries");
            System.out.println("5. Manage my enquiries");

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
                    if (question.equals("~"))
                        break;
                    EnquiryController.post(camp, question);
                    System.out.println("Enquiry has been posted!");
                    break;
                case 4:
                    ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
                    if(enquiries == null) {
                        System.out.println("There are no enquiries for this camp!");
                        break;
                    }
                    for (int i = 0; i < enquiries.size(); i++) {
                        DisplayHandler.displayResult(enquiries.get(i));
                    }
                case 5:
                    menu_DisplayMyEnquiries(camp);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }

    private static void menu_DisplayMyEnquiries(Camp camp) {
        int option;
        while (true) {
            ArrayList<Enquiry> enquiriesByStudent = EnquiryController.getUserEnquiries(camp);
            if (enquiriesByStudent == null) {
                System.out.println("You have no enquiries for this camp!");
                return;
            }
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            for (int i = 0; i < enquiriesByStudent.size(); i++) {
                DisplayHandler.displayResult(enquiriesByStudent.get(i));
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
                if (option < 0 || option > enquiriesByStudent.size()) {
                    System.out.println("Invalid input!");
                    continue;
                }
                menu_ViewEnquiryOptions(camp, enquiriesByStudent.get(option));
                break;
        }
    }
}

    private static void menu_ViewEnquiryOptions(Camp camp, Enquiry enquiry) {
        int option;
        while(true){
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            System.out.println("1. Edit enquiry");
            System.out.println("2. Delete enquiry");

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
                    System.out.println("Enter ~ to quit");
                    System.out.println("Key in enquiry, press enter to confirm: ");
                    String question = InputHandler.nextLine();

                    if (question.equals("~"))
                        break;

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
                    System.out.println("Invalid input!");
                    break;
            }
        }
}
}

