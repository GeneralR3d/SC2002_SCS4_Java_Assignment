package boundary;

import handler.DisplayHandler;
import handler.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;

import app.SessionInfo;
import control.CampController;
import control.EnquiryController;
import control.SearchController;
import entity.Camp;
import entity.CommitteeMember;
import entity.Enquiry;
import entity.Student;

/**
 * User interface boundary class for {@link Student}
 */
public class StudentUI {
    /**
     * The main menu.
     * Only point of exit is through {@code Logout}
     */
    public static void displayMenu() {
        int option;
        while (true) {
            String userType = SessionInfo.getUserType();
            System.out.println();
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
            System.out.println("Please select an option...");
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
     * Displays all {@link Camp}s visible to {@link Student} and he/she fufils the criteria to {@code SignUp}
     * <div> Allows {@link Student} to select more options regarding a {@link Camp} via {@link StudentUI#menu_ViewCampOptions(Camp)}</div>
     * @param openCamps
     */
    private static void menu_DisplayOpenCamps(ArrayList<Camp> openCamps) {
        int option;
        while (true) {
            System.out.println();
            DisplayHandler.displayResult(openCamps);
            System.out.println("Select a camp to register / submit an enquiry...");
            System.out.println("Enter 0 to go back...");
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

    /**
     * Filters a camp by certain attributes and displays the search results through {@link StudentUI#menu_DisplayOpenCamps(ArrayList)}
     * <div>Attributes</div>
     * <ul>
     * <li>Camp name
     * <li>Camp start date
     * <li>Camp end date
     * <li>Camp faculty
     * <li>Camp location
     * <li>A certain Attendee
     * <li>A certain Committee member
     * </ul>
     * <div>Calls {@link SearchController} methods for filtering</div>
     */
    private static void menu_SearchForCamp() {
        int option;
        while (true) {
            System.out.println("Search by:");
            System.out.println("1. Camp name");
            System.out.println("2. Camp start date");
            System.out.println("3. Camp end date");
            System.out.println("4. Camp location");
            System.out.println("5. Attendee");
            System.out.println("6. Committee member");
            System.out.println("Select attribute to search by...");
            System.out.println("Enter 0 to go back...");
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

    /** 
     * Displays a list of camps the {@link Student} has signed up for, as well as his/her role
     * <div>Role either as:
     * Committee Member or Attendee</div>
     * <div> Calls {@link StudentUI#menu_ViewCampOptions(Camp)} to display more options for a {@link Camp}</div>
     * @param registeredCamps an {@link java.util.ArrayList}
     */
    private static void menu_DisplayRegisteredCamps(ArrayList<Camp> registeredCamps) {
        while (true) {
            System.out.println();
            System.out.println("Enter 0 to go back...");
            DisplayHandler.displayResult(registeredCamps, SessionInfo.getUser());
            int option;
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

    /**
     * Displays a list of {@link Camp}s the {@link Student} has signed up for from {@link CampController#getSignedUpCamps()}
     * <div> Allows withdraw from a {@link Camp} only if he/she is not a committee member</div>
     * <div> Calls {@link CampController#removeAttendee(Camp)} to perform {@code withdrawl}</div>
     */
    private static void menu_WithdrawRegisteredCamps() {
        int option;
        while (true) {
            ArrayList<Camp> registeredCamps = CampController.getSignedUpCamps();

            DisplayHandler.displayResult(registeredCamps, SessionInfo.getUser());
            System.out.println("Select a camp to withdraw from it...");
            System.out.println("Enter 0 to go back...");

            option = InputHandler.nextInt();

            if (option == 0)
                return;
            if (option == 1 && SessionInfo.getUserType().equals("CommitteeMember"))
                System.out.println("Unable to withdraw as committee member!");
            if (option < 0 || option > registeredCamps.size()) {
                System.out.println("Invalid input!");
                continue;
            }
            CampController.removeAttendee(registeredCamps.get(option - 1));
            System.out.println("You have succesfully withdrawn from the camp!");
        }
    }

    /** 
     * Displays more options for a {@link Student} to see more about a {@link Camp}
     * <div>Calls {@link CampController#registerAttendee(Camp)} to register as attendee</div>
     * <div>Calls {@link CampController#registerCommittee(Camp)} to register as committee</div>
     * <div>Calls {@link EnquiryController#post} to add an enquiry to a {@link Camp} even without registering</div>
     * <div>Calls {@link StudentUI#menu_DisplayMyEnquiries(Camp)} to let {@link Student} manage past {@link Enquiry}s about this {@link Camp}</div>
    */
    private static void menu_ViewCampOptions(Camp camp) {
        int option;
        while (true) {
            System.out.println();
            System.out.println("Number of slots left for " + camp.getName());
            System.out.println("Attendees: " + camp.getAttendeeSlotsLeft() + ", Committee: " + camp.getCommSlotsLeft());
            System.out.println("1. Register as attendee");
            System.out.println("2. Register as camp committee member");
            System.out.println("3. Submit enquiry about camp");
            System.out.println("4. Manage my enquiries");
            System.out.println("Please select an option...");
            System.out.println("Enter 0 to go back...");

            option = InputHandler.nextInt();

            switch (option) {
            case 0:
                return;
            case 1:
                try {
                    CampController.registerAttendee(camp);
                    System.out.println("SUCCESS: You have successfully registered as attendee!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    CampController.registerCommittee(camp);
                    System.out.println("SUCCESS: You have successfully registered as Committee Member!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                System.out.println("Enter your enquiry...");
                System.out.println("Enter ~ to quit...");
                String question = InputHandler.nextLine();
                if (question.equals("~"))
                    break;
                try {
                    EnquiryController.post(camp, question);
                    System.out.println("SUCCESS: Enquiry has been posted!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
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

    /**
     * Gets a list of {@link Enquiry} which current {@link Student} has made about a {@link Camp}
     * <div>Displays the list via {@link DisplayHandler#displayResult(Enquiry)}</div>
     * <div>Allows {@link Student} to select more options via {@link StudentUI#menu_ViewEnquiryOptions(Camp, Enquiry)}</div>
     * @param camp
     */
    private static void menu_DisplayMyEnquiries(Camp camp) {
        int option;
        while (true) {
            ArrayList<Enquiry> enquiriesByStudent = EnquiryController.getUserEnquiries(camp);
            if (enquiriesByStudent == null) {
                System.out.println("NOTICE: You have no enquiries for this camp!");
                System.out.println("Enter any key to exit...");
                InputHandler.nextLine();
                return;
            }
            System.out.println();
            System.out.println("Select an enquiry to manage it...");
            System.out.println("Enter 0 to go back...");
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

    /**
     * Displays more options for an {@link Enquiry}
     * For {@link Student} allows for {@code edit} and {@code delete} of {@link Enquiry} via {@link EnquiryController#edit(Enquiry, String)} and {@link EnquiryController#delete(Camp, Enquiry)}
     * For {@link CommitteeMember} allows for {@code reply} of {@link Enquiry} via {@link CommitteeUI#menu_ReplyEnquiry(Camp, Enquiry)}
     * @param camp
     * @param enquiry
     */
    private static void menu_ViewEnquiryOptions(Camp camp, Enquiry enquiry) {
        int option;
        String userType = SessionInfo.getUserType();
        while (true) {
            System.out.println();
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
                System.out.println("Enter your edited enquiry...");
                System.out.println("Enter ~ to quit...");
                String question = InputHandler.nextLine();

                if (question.equals("~"))
                    break;

                try {
                    EnquiryController.edit(enquiry, question);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("SUCCESS: Enquiry has been edited!");
                break;
            case 2:
                try {
                    EnquiryController.delete(camp, enquiry);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("SUCCESS: Enquiry has been deleted!");
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
