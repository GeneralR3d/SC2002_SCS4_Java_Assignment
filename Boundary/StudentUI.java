package boundary;

import control.*;
import entity.*;
import handler.DisplayHandler;
import handler.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;

import app.SessionInfo;

public class StudentUI {
    public static void displayMenu() {
        int option;
        while (true) {
            String userType = SessionInfo.getUserType();
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("1. View open camps");
            System.out.println("2. Search for camps");
            System.out.println("3. See all registered camps");
            System.out.println("4. Withdraw from registered camps");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            if (userType.equals("CommitteeMember")) {
                CommitteeMember commMember = (CommitteeMember) SessionInfo.getUser();
                System.out.println();
                System.out.println("You are a committee member. Points: " + commMember.getPoints());
                System.out.println("7. Committee member menu");
            }
            option = InputHandler.nextInt();

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
                    if (!userType.equals("CommitteeMember")) {
                        System.out.println("Invalid input!");
                        break;
                    }
                    CommitteeUI.displayMenu();
                    break;
                default:
                    System.out.println("Invalid input!");
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
            option = InputHandler.nextInt();
            if (option == 0)
                return;
            if (option < 0 || option > openCamps.size()) {
                System.out.println("Invalid input!");
                continue;
            }
            menu_ViewCampOptions(openCamps.get(option - 1));
        }
    }

    private static void menu_SearchForCamp() {
        int option;
        while (true) {
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            System.out.println("Search by:");
            System.out.println("1. Camp name");
            System.out.println("2. Camp start date");
            System.out.println("3. Camp end date");
            System.out.println("4. Camp location");
            System.out.println("5. Attendee");
            System.out.println("6. Committee member");

            option = InputHandler.nextInt();

            ArrayList<Camp> result;
            switch (option) {
                case 0:
                    return;
                case 1:
                    System.out.println("Enter camp name:");
                    String campName = InputHandler.nextLine();
                    result = SearchController.searchByCampName(campName);
                    menu_DisplayOpenCamps(result);
                    break;
                case 2:
                    System.out.println("Enter camp start date (YYYY-MM-DD):");
                    LocalDate startDate = InputHandler.nextDate();
                    result = SearchController.searchByStartDate(startDate);
                    menu_DisplayOpenCamps(result);
                    break;
                case 3:
                    System.out.println("Enter camp end date (YYYY-MM-DD):");
                    LocalDate endDate = InputHandler.nextDate();
                    result = SearchController.searchByEndDate(endDate);
                    menu_DisplayOpenCamps(result);
                    break;
                case 4:
                    System.out.println("Enter camp location:");
                    String location = InputHandler.nextLine();
                    result = SearchController.searchByLocation(location);
                    menu_DisplayOpenCamps(result);
                    break;
                case 5:
                    System.out.println("Enter attendee name:");
                    String attendeeName = InputHandler.nextLine();
                    result = SearchController.searchByAttendee(attendeeName);
                    menu_DisplayOpenCamps(result);
                    break;
                case 6:
                    System.out.println("Enter committee member name:");
                    String commMemberName = InputHandler.nextLine();
                    result = SearchController.searchByAttendee(commMemberName);
                    menu_DisplayOpenCamps(result);
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

            option = InputHandler.nextInt();

            if (option == 0)
                return;
            if (option < 0 || option > registeredCamps.size()) {
                System.out.println("Invalid input!");
                continue;
            }
            menu_ViewCampOptions(registeredCamps.get(option - 1));
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

            option = InputHandler.nextInt();

            if (option == 0)
                return;
            if (option < 0 || option > registeredCamps.size()) {
                System.out.println("Invalid input!");
                continue;
            }
            CampController.removeAttendee(registeredCamps.get(option - 1));
        }
    }

    private static void menu_ViewCampOptions(Camp camp) {
        int option;
        while (true) {
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            System.out.println();
            System.out.println("Number of slots left for " + camp.getName());
            System.out.println("Attendees: " + camp.getAttendeeSlotsLeft() + ", Committee: " + camp.getCommSlotsLeft());
            System.out.println("1. Register as attendee");
            System.out.println("2. Register as camp committee member");
            System.out.println("3. Submit enquiry about camp");
            System.out.println("4. Manage my enquiries");

            option = InputHandler.nextInt();

            switch (option) {
                case 0:
                    return;
                case 1:
                    try {
                        CampController.registerAttendee(camp);
                        System.out.println("You have successfully registered as attendee!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        CampController.registerCommittee(camp);
                        System.out.println("You have successfully registered as Committee Member!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
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
                System.out.println("Enter any key to exit");
                InputHandler.nextLine();
                return;
            }
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            for (int i = 0; i < enquiriesByStudent.size(); i++) {
                System.out.println();
                System.out.println((i + 1) + ":");
                DisplayHandler.displayResult(enquiriesByStudent.get(i));
            }
            while (true) {

                option = InputHandler.nextInt();

                if (option == 0)
                    return;
                if (option < 0 || option > enquiriesByStudent.size()) {
                    System.out.println("Invalid input!");
                    continue;
                }
                menu_ViewEnquiryOptions(camp, enquiriesByStudent.get(option - 1));
                break;
            }
        }
    }

    private static void menu_ViewEnquiryOptions(Camp camp, Enquiry enquiry) {
        int option;
        String userType = SessionInfo.getUserType();
        while (true) {
            System.out.println();
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            System.out.println("Enter 0 to go back");
            System.out.println("1. Edit enquiry");
            System.out.println("2. Delete enquiry");
            if (userType.equals("CommitteeMember"))
                System.out.println("3. Reply enquiry");

            option = InputHandler.nextInt();

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
                    System.out.println("Enquiry has been deleted!");
                    return;
                case 3:
                    if (!userType.equals("CommitteeMember")) {
                        System.out.println("Invalid input!");
                        break;
                    }
                    CommitteeUI.menu_ReplyEnquiry(camp, enquiry);
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }
}
