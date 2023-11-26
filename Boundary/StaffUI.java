package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

import control.CampController;
import control.EnquiryController;
import control.ReportController;
import control.SuggestionController;
import control.SearchController;
import entity.Camp;
import entity.CommitteeMember;
import entity.Enquiry;
import entity.Faculty;
import entity.Student;
import entity.Suggestion;
import handler.DisplayHandler;
import handler.InputHandler;

public class StaffUI {
    public static void displayMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. View All Camps");
            System.out.println("2. Search For Camp");
            System.out.println("3. View My Camps");
            System.out.println("4. Generate Report For My Camps");
            System.out.println("5. Create New Camp");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.println("Please select an option...");
            int choice = InputHandler.nextInt();
            switch (choice) {
            case 1: // View All Camps
                menu_ViewCamps(CampController.getAvailableCamps());
                break;
            case 2:
                menu_SearchForCamp();
                break;
            case 3: // View Created Camps
                menu_ViewMyCamps();
                break;
            case 4: // View Created Camps
                menu_GenerateReport();
                break;
            case 5: // Create New Camp
                menu_CreateNewCamp();
                break;
            case 6: // Change Password
                AccountUI.changePasswordMenu();
                return;
            case 7: // Logout
                AccountUI.logout();
                return;
            default:
                System.out.println("Invalid Input");
                break;
            }
        }
    }

    /**
     * @param camps
     */
    public static void menu_ViewCamps(ArrayList<Camp> camps) {
        if (camps.size() == 0) {
            System.out.println();
            System.out.println("NOTICE: There are no camps to view");
            System.out.println("Enter any key to go back...");
            InputHandler.nextLine();
            return;
        }
        while (true) {
            System.out.println();
            System.out.println("Camps: ");
            DisplayHandler.displayResult(camps);
            System.out.println("Select a camp to view its details...");
            System.out.println("Enter 0 to go back...");
            int choice = InputHandler.nextInt();
            if (choice == 0) // exit
                return;
            if (choice < 0 || choice > camps.size()) {
                System.out.println("Invalid Input");
                continue;
            }
            DisplayHandler.displayResult(camps.get(choice - 1));
        }
    }

    private static ArrayList<Camp> menu_SearchForCamp() {

            System.out.println();
            System.out.println("1. Camp name");
            System.out.println("2. Camp start date");
            System.out.println("3. Camp end date");
            System.out.println("4. Camp faculty");
            System.out.println("5. Camp location");
            System.out.println("6. Attendee");
            System.out.println("7. Committee member");
            System.out.println("Select attribute to search by....");
            System.out.println("Enter 0 to go back...");

            int option = InputHandler.nextInt();

            ArrayList<Camp> result = null;
            switch (option) {
            case 0:
                return null;
            case 1:
                System.out.println("Enter camp name:");
                String campName = InputHandler.nextLine();
                result = SearchController.searchByCampName(campName);
                menu_ViewCamps(result);
                break;
            case 2:
                System.out.println("Enter camp start date (YYYY-MM-DD):");
                LocalDate startDate = InputHandler.nextDate();
                result = SearchController.searchByStartDate(startDate);
                menu_ViewCamps(result);
                break;
            case 3:
                System.out.println("Enter camp end date (YYYY-MM-DD):");
                LocalDate endDate = InputHandler.nextDate();
                result = SearchController.searchByEndDate(endDate);
                menu_ViewCamps(result);
                break;
            case 4:
                System.out.println("Enter camp faculty:");
                try {
                    Faculty faculty = Faculty.valueOf(InputHandler.nextLine());
                    result = SearchController.searchByFaculty(faculty);
                    menu_ViewCamps(result);
                } catch (Exception e) {
                    System.out.println("No results found!");
                }
                break;
            case 5:
                System.out.println("Enter camp location:");
                String location = InputHandler.nextLine();
                result = SearchController.searchByLocation(location);
                menu_ViewCamps(result);
                break;
            case 6:
                System.out.println("Enter attendee name:");
                String attendeeName = InputHandler.nextLine();
                result = SearchController.searchByAttendee(attendeeName);
                menu_ViewCamps(result);
                break;
            case 7:
                System.out.println("Enter committee member name:");
                String commMemberName = InputHandler.nextLine();
                result = SearchController.searchByAttendee(commMemberName);
                menu_ViewCamps(result);
                break;
            default:
                System.out.println("Invalid input!");
                break;
            }

        return result;
    }

    public static void menu_ViewMyCamps() {
        // get created camps
        ArrayList<Camp> myCamps;
        try {
            myCamps = CampController.getCreatedCamps();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (myCamps.size() == 0) {
            System.out.println();
            System.out.println("NOTICE: You have not created any camps");
            System.out.println("Enter any key to go back...");
            InputHandler.nextLine();
            return;
        }
        while (true) {
            System.out.println();
            System.out.println("Created Camps:");
            DisplayHandler.displayResult(myCamps);
            System.out.println("Select a camp to manage it...");
            System.out.println("Enter 0 to go back...");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            if (choice < 0 || choice > myCamps.size()) {
                System.out.println("Invalid Input");
                continue;
            }
            menu_ManageMyCamp(myCamps.get(choice - 1));
        }
    }

    /**
     * @param camp camp created by current user
     */
    public static void menu_ManageMyCamp(Camp camp) {
        while (true) {
            DisplayHandler.displayResult(camp);
            System.out.println();
            System.out.println("1: View Signups");
            System.out.println("2. View Enquiries");
            System.out.println("3. View Suggestions");
            System.out.println("4. Edit Camp");
            System.out.println("5. Delete Camp");
            System.out.println("0. Exit");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
            case 1:
                menu_ViewSignUps(camp);
                break;
            case 2: // View Enquiries
                menu_ViewEnquiries(camp);
                break;
            case 3: // View Suggestions
                menu_ViewSuggestions(camp);
                break;
            case 4: // Edit Camp
                menu_EditCreatedCamp(camp);
                break;
            case 5: // Delete Camp
                System.out.println("WARNING: This action CANNOT be undone");
                System.out.println("Enter CONFIRM to confirm deletion of this camp...");
                System.out.println("Enter any key to go back...");
                if (InputHandler.nextLine().equals("CONFIRM"))
                    try {
                        CampController.deleteCamp(camp);
                        System.out.println("SUCCESS: Camp has been deleted!");
                        return;
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
     * @param camp
     */
    private static void menu_ViewSignUps(Camp camp) {
        // TODO: display message if no signups
        ArrayList<Student> attendees = camp.getAttendees();
        ArrayList<CommitteeMember> commMembers = camp.getCommittee();
        System.out.println();
        System.out.println("Signups for the camp:");
        DisplayHandler.displayResult(attendees, commMembers);
        System.out.println("Enter 0 to go back");
        InputHandler.nextLine();
    }

    /**
     * @param camp
     */
    public static void menu_ViewEnquiries(Camp camp) {
        ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
        if (enquiries.size() == 0) {
            System.out.println();
            System.out.println("NOTICE: There are no enquiries for this camp");
            System.out.println("Enter any key to exit...");
            InputHandler.nextLine();
            return;
        }
        while (true) {
            // TODO: replace with displayhandler function
            for (int i = 0; i < enquiries.size(); i++) {
                System.out.println();
                System.out.println((i + 1) + ":");
                DisplayHandler.displayResult(enquiries.get(i));
            }
            System.out.println();
            System.out.println("Select an enquiry to add a reply to it...");
            System.out.println("Enter 0 to go back...");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                return;
            if (choice < 0 || choice > enquiries.size()) {
                System.out.println("Invalid Input");
                continue;
            }
            menu_AddReplyToEnquiry(camp, enquiries.get(choice - 1));
        }
    }

    private static void menu_AddReplyToEnquiry(Camp camp, Enquiry enquiry) {
        System.out.println();
        System.out.println("Enter your reply...");
        System.out.println("Enter ~ to go back...");
        String reply = InputHandler.nextLine();
        if (reply.equals("~"))
            return;
        try {
            EnquiryController.addReply(camp, enquiry, reply);
            System.out.println("SUCCESS: Reply has been added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menu_ViewSuggestions(Camp camp) {
        ArrayList<Suggestion> suggestions = camp.getSuggestions();
        while (true) {
            // TODO: replace with displayhandler function
            for (int i = 0; i < suggestions.size(); i++) {
                System.out.println();
                System.out.println((i + 1) + ": ");
                DisplayHandler.displayResult(suggestions.get(i));
            }
            System.out.println();
            System.out.println("Select a suggestion to approve / reject it...");
            System.out.println("Enter 0 to go back...");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            if (choice < 0 || choice > suggestions.size()) {
                System.out.println("Invalid Input");
                continue;
            }
            menu_SetSuggestionStatus(suggestions.get(choice - 1));
        }
    }

    public static void menu_SetSuggestionStatus(Suggestion suggestion) {
        while (true) {
            System.out.println();
            System.out.println("1. Approve");
            System.out.println("2. Reject");
            System.out.println("Select an option...");
            System.out.println("Enter 0 to go back...");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
            case 1: // Approve
                try {
                    SuggestionController.approve(suggestion);
                    System.out.println("SUCCESS: Suggestion has been approved!");
                    return;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2: // Reject
                try {
                    SuggestionController.reject(suggestion);
                    System.out.println("SUCCESS: Suggestion has been rejected!");
                    return;
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

    public static void menu_GenerateReport(Camp camp) {

    }

    public static void menu_EditCreatedCamp(Camp camp) {
        String name = camp.getName(), location = camp.getLocation(), description = camp.getDescription();
        LocalDate startDate = camp.getStartDate(), endDate = camp.getEndDate(), regCloseDate = camp.getRegCloseDate();
        Faculty openToFaculty = camp.getOpenToFaculty();
        int totalSlots = camp.getTotalSlotsLeft(), commSlots = camp.getCommSlotsLeft();
        boolean visibleToStudents = camp.isVisibleToStudents();
        while (true) {
            System.out.println();
            System.out.println("1.  " + (name == camp.getName() ? "" : "*") + "Edit Name" + " (" + name + ")");
            System.out.println("2.  " + (startDate == camp.getStartDate() ? "" : "*") + "Edit Start Date" + " (" + startDate + ")");
            System.out.println("3.  " + (endDate == camp.getEndDate() ? "" : "*") + "Edit End Date" + " (" + endDate + ")");
            System.out.println("4.  " + (regCloseDate == camp.getRegCloseDate() ? "" : "*") + "Edit Registration Closing Date" + " (" + regCloseDate + ")");
            System.out.println("5.  " + (openToFaculty == camp.getOpenToFaculty() ? "" : "*") + "Edit Faculty" + " (" + openToFaculty.toString() + ")");
            System.out.println("6.  " + (location == camp.getLocation() ? "" : "*") + "Edit Location" + " (" + location + ")");
            System.out.println("7.  " + (totalSlots == camp.getTotalSlotsLeft() ? "" : "*") + "Edit Total Slots" + " (" + totalSlots + ")");
            System.out.println("8.  " + (commSlots == camp.getCommSlotsLeft() ? "" : "*") + "Edit Camp Committee Slots" + " (" + commSlots + ")");
            System.out.println("9.  " + (description == camp.getDescription() ? "" : "*") + "Edit Description" + " (" + description + ")");
            System.out.println("10. " + (visibleToStudents == camp.isVisibleToStudents() ? "" : "*") + "Toggle Visibility On / Off" + " (" + (visibleToStudents ? "On" : "Off") + ")");
            System.out.println("11. Apply Changes");
            System.out.println("Select an attribute to edit it...");
            System.out.println("Enter 0 to go back (WITHOUT saving)...");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
            case 1: // Edit Name
                System.out.println("Name:");
                name = InputHandler.nextLine();
                break;
            case 2: // Edit Start Date
                do {
                    System.out.println("Start Date (YYYY-MM-DD):");
                    startDate = InputHandler.nextDate();
                    if (startDate.isAfter(endDate))
                        System.out.println("Start Date cannot be after End Date!");
                } while (startDate.isAfter(endDate));
                break;
            case 3: // Edit End Date
                do {
                    System.out.println("End Date (YYYY-MM-DD):");
                    endDate = InputHandler.nextDate();
                    if (endDate.isBefore(startDate))
                        System.out.println("End Date cannot be before Start Date!");
                } while (endDate.isBefore(startDate));
                break;
            case 4: // Edit Registration Closing Date
                do {
                    System.out.println("Registration Closing Date (YYYY-MM-DD):");
                    regCloseDate = InputHandler.nextDate();
                    if (!regCloseDate.isBefore(startDate))
                        System.out.println("Registration Closing Date must be before Start Date!");
                } while (!regCloseDate.isBefore(startDate));
                break;
            case 5: // Edit Faculty
                int i;
                while (true) {
                    System.out.println("Faculty:");
                    for (Faculty f : Faculty.values())
                        System.out.println((f.ordinal() + 1) + ". " + f);
                    i = InputHandler.nextInt();
                    if (i <= 0 || i > Faculty.values().length) {
                        System.out.println("Invalid Input");
                        continue;
                    }
                    break;
                }
                openToFaculty = Faculty.values()[i - 1];
                break;
            case 6: // Edit Location
                System.out.println("Location:");
                location = InputHandler.nextLine();
                break;
            case 7: // Edit Total Slots
                do {
                    System.out.println("Total Slots:");
                    totalSlots = InputHandler.nextInt();
                    if (totalSlots < 0)
                        System.out.println("Total Slots cannot be less than 0!");
                } while (totalSlots < 0);
                break;
            case 8: // Edit Camp Committee Slots
                do {
                    System.out.println("Committee Member Slots:");
                    commSlots = InputHandler.nextInt();
                    if (commSlots < 0)
                        System.out.println("Committee Member Slots cannot be less than 0!");
                    if (commSlots > 10)
                        System.out.println("Committee Member Slots cannot be more than 10!");
                    if (commSlots > totalSlots)
                        System.out.println("Committee Member Slots cannot be more than Total Slots!");
                } while (commSlots < 0 || commSlots > 10 || commSlots > totalSlots);
                break;
            case 9: // Edit Description
                System.out.println("Description:");
                description = InputHandler.nextLine();
                break;
            case 10: // Toggle Visibility On / Off
                visibleToStudents = visibleToStudents ? false : true;
                break;
            case 11: // Apply Changes
                try {
                    CampController.editCamp(camp, name, startDate, endDate, regCloseDate, openToFaculty, location, totalSlots, commSlots, description, visibleToStudents);
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

    public static void menu_GenerateReport() {

        int option;
        while (true) {
            System.out.println("Select how you want the report to be generated...");
            System.out.println("1: Report for all camps created");
            System.out.println("2: Filter camps to generate report");
            System.out.println("Enter 0 to go back...");

            option = InputHandler.nextInt();

            if (option < 0 || option > 2) {
                System.out.println("Inavlid input!");
                continue;
            }
            if (option == 0)
                return;
            break;
        }

        int sortBy;
        while (true) {
            System.out.println("How do you want the list of attendees to be displayed?");
            System.out.println("1: Attendees first");
            System.out.println("2: Commiteee first");
            System.out.println("Select how you want the list of attendees to be displayed...");

            sortBy = InputHandler.nextInt();

            if (sortBy <= 0 || sortBy > 2) {
                System.out.println("Inavlid input!");
                continue;
            }
            break;
        }
        
        ArrayList<Camp> campsList = null;
        if(option == 1){
            
            try{
                campsList = CampController.getCreatedCamps();
            }catch (Exception e) {
                System.out.println("You have no created camps!");
                System.out.println(e.getMessage());
                return;
            }
        }
        
        if(option == 2){
            campsList = menu_SearchForCamp();
            if(campsList == null || campsList.size()==0){
                System.out.println("There are no such camps!");
                return;
            } 
        }

        for(Camp camp : campsList){
            ReportController.generateReport(camp, sortBy, "StaffReport.txt", true);
        }

        System.out.println("SUCCESS: Report saved to 'StaffReport.txt'");
    }

    public static void menu_CreateNewCamp() {
        String name, location, description;
        LocalDate startDate, endDate, regCloseDate;
        Faculty openToFaculty;
        int totalSlots, commSlots;
        boolean visibleToStudents;

        System.out.println();
        System.out.println("Enter all requested details...");
        System.out.println("Confirmation message will be displayed after all details have been entered...");
        // Name
        System.out.println("Name:");
        name = InputHandler.nextLine();
        // Start Date & End Date & Registration Closing Date
        regCloseDate = LocalDate.now(); // placeholder
        while (true) {
            // Start Date
            System.out.println("Start Date (YYYY-MM-DD):");
            startDate = InputHandler.nextDate();
            // End Date
            System.out.println("End Date (YYYY-MM-DD):");
            endDate = InputHandler.nextDate();
            if (startDate.isAfter(endDate)) {
                System.out.println("End Date cannot be before Start Date!");
                continue;
            }
            // Registration Closing Date
            System.out.println("Registration Closing Date (YYYY-MM-DD):");
            regCloseDate = InputHandler.nextDate();
            if (!regCloseDate.isBefore(startDate)) {
                System.out.println("Registration Closing Date must be before End Date!");
                continue;
            }
            break;
        }
        // Faculty
        int i;
        while (true) {
            System.out.println("Faculty:");
            for (Faculty f : Faculty.values())
                System.out.println((f.ordinal() + 1) + ". " + f);
            i = InputHandler.nextInt();
            if (i <= 0 || i > Faculty.values().length) {
                System.out.println("Invalid Input");
                continue;
            }
            break;
        }
        openToFaculty = Faculty.values()[i - 1];
        // Location
        System.out.println("Location:");
        location = InputHandler.nextLine();
        // Total Slots & Committee Member Slots
        while (true) {
            // Total Slots
            System.out.println("Total Slots:");
            totalSlots = InputHandler.nextInt();
            if (totalSlots < 0) {
                System.out.println("Total Slots cannot be less than 0!");
                continue;
            }
            // Committee Member Slots
            System.out.println("Committee Member Slots:");
            commSlots = InputHandler.nextInt();
            if (commSlots > totalSlots) {
                System.out.println("Committee Member Slots cannot be more than the Total Slots!");
                continue;
            }
            if (commSlots > 10) {
                System.out.println("Committee Member Slots cannot be more than 10!");
                continue;
            }
            if (commSlots < 0) {
                System.out.println("Total Slots cannot be less than 0!");
                continue;
            }
            break;
        }
        // Description
        System.out.println("Description:");
        description = InputHandler.nextLine();
        // Visible to Students
        System.out.println("Visible to Students (0 - no | 1 - yes):");
        visibleToStudents = (InputHandler.nextInt() != 0);
        // Confirmation Message
        System.out.println("Enter ~ to cancel creation...");
        System.out.println("Enter any other key to continue...");
        if (InputHandler.nextLine().equals("~")) {
            System.out.println("NOTICE: Camp was not created");
            return;
        }
        System.out.println("SUCCESS: Camp has been successfully created");
        // Create Camp
        try {
            CampController.createCamp(name, startDate, endDate, regCloseDate, openToFaculty, location, totalSlots, commSlots, description, visibleToStudents);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
