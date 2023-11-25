package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

import control.CampController;
import control.SuggestionController;
import entity.Camp;
import entity.Faculty;
import entity.Suggestion;
import handler.DisplayHandler;
import handler.InputHandler;

public class StaffUI {
    public static void displayMenu() {
        while (true) {
            System.out.println("1. View All Camps");
            System.out.println("2. View Created Camps");
            System.out.println("3. Create New Camp");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            System.out.println("0. Quit");
            System.out.println("Please select an option...");
            int choice = InputHandler.nextInt();
            System.out.println("Choice: " + choice);
            if (choice == 0)
                break;
            switch (choice) {
            case 1: // View All Camps
                menu_ViewAllCamps();
                break;
            case 2: // View Created Camps
                menu_ViewCreatedCamps();
                break;
            case 3: // Create New Camp
                menu_CreateNewCamp();
                break;
            case 4:
                AccountUI.changePasswordMenu();
                break;
            case 5:
                AccountUI.logout();
                choice = 0;
                break;
            default:
                break;
            }
        }
    }
    //

    public static void menu_ViewAllCamps() {
        // get all camps
        ArrayList<Camp> allCamps;
        allCamps = CampController.getAvailableCamps();
        while (true) {
            System.out.println("All Camps:");
            DisplayHandler.displayResult(allCamps);
            System.out.println("0. Exit");
            System.out.println("Select a camp to view its details...");
            int choice = InputHandler.nextInt();
            if (choice == 0) // exit
                break;
            if (choice < 0 || choice >= allCamps.size()) {
                System.out.println("Invalid Option");
                continue;
            }
            DisplayHandler.displayResult(allCamps.get(choice));
        }
    }

    public static void menu_ViewCreatedCamps() {
        // get created camps
        ArrayList<Camp> createdCamps;
        try {
            createdCamps = CampController.getCreatedCamps();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        while (true) {
            System.out.println("Created Camps:");
            DisplayHandler.displayResult(createdCamps);
            System.out.println("0. Exit");
            System.out.println("Select a camp to manage it...");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            if (choice < 0 || choice >= createdCamps.size()) {
                System.out.println("Invalid Option");
                continue;
            }
            menu_ManageCreatedCamp(createdCamps.get(choice));
        }
    }

    /**
     * @param camp camp created by current user
     */
    public static void menu_ManageCreatedCamp(Camp camp) {
        while (true) {
            DisplayHandler.displayResult(camp);
            System.out.println("1. View Enquiries");
            System.out.println("2. View Suggestions");
            System.out.println("3. Edit Camp");
            System.out.println("4. Delete Camp");
            System.out.println("0. Exit");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
            case 1: // View Enquiries
                menu_ViewEnquiries(camp);
                break;
            case 2: // View Suggestions
                menu_ViewSuggestions(camp);
                break;
            case 3: // Edit Camp
                menu_EditCreatedCamp(camp);
                break;
            case 4: // Delete Camp
                System.out.println("Type CONFIRM to confirm deletion of this camp (this action CANNOT be undone).");
                if (InputHandler.nextLine().equals("CONFIRM"))
                    try {
                        CampController.deleteCamp(camp);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                break;
            default:
                break;
            }
        }
    }

    public static void menu_ViewEnquiries(Camp camp) {
        while (true) {
            // TODO: displayEnquiries(campID)
            System.out.println("TODO: display enquiries");
            System.out.println("0. Exit");
            System.out.println("Select an enquiry to add a reply to it");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            // TODO: input range check
            System.out.println("TODO: input range check");
            // TODO: replyToEnquiry(campID, choice);
            System.out.println("TODO: reply to enquiry");
        }
    }

    public static void menu_ViewSuggestions(Camp camp) {
        ArrayList<Suggestion> suggestions = camp.getSuggestions();
        while (true) {
            for (int i = 0; i < suggestions.size(); i++) {
                System.out.println(i + " " + suggestions.get(i).view());
            }
            System.out.println("0. Exit");
            System.out.println("Select a suggestion to approve / reject it");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;

            menu_SetSuggestionStatus(suggestions.get(choice));
        }
    }

    public static void menu_SetSuggestionStatus(Suggestion suggestion) {
        while (true) {
            System.out.println("1. Approve");
            System.out.println("2. Reject");
            System.out.println("0. Cancel");

            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;

            switch (choice) {
            case 1: // Approve
                // TODO: setSuggestionStatus(campID, suggestionID, APPROVED);
                SuggestionController.approve(null, null, null);
                System.out.println("TODO: approve suggestion");
                break;
            case 2: // Reject
                // TODO: setSuggestionStatus(campID, suggestionID, REJECTED);
                System.out.println("TODO: reject suggestions");
                break;
            default:
                break;
            }
        }
    }

    public static void menu_EditCreatedCamp(Camp camp) {
        while (true) {
            DisplayHandler.displayResult(camp);
            System.out.println("1.  Toggle Visibility On / Off");
            System.out.println("2.  Edit Name");
            System.out.println("3.  Edit Start Date");
            System.out.println("4.  Edit End Date");
            System.out.println("5.  Edit Registration Closing Date");
            System.out.println("6.  Edit School");
            System.out.println("7.  Edit Location");
            System.out.println("8.  Edit Total Slots");
            System.out.println("9.  Edit Camp Committee Slots");
            System.out.println("10. Edit Description");
            System.out.println("11. Apply Changes");
            System.out.println("0.  Exit WITHOUT Saving");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
            case 1: // Toggle Visibility On / Off
                menu_ViewAllCamps();
                break;
            case 2: // Edit Name
                // menu_ViewMyCamps();
                break;
            case 3: // Edit Start Date
                // TODO: Edit Start Date
                System.out.println("TODO: Edit Start Date");
                break;
            case 4: // Edit End Date
                // TODO: Edit End Date
                System.out.println("TODO: Edit End Date");
                break;
            case 5: // Edit Registration Closing Date
                // TODO: Edit Registration Closing Date
                System.out.println("TODO: Edit Registration Closing Date");
                break;
            case 6: // Edit School
                // TODO: Edit School
                System.out.println("TODO: Edit School");
                break;
            case 7: // Edit Location
                // TODO: Edit Location
                System.out.println("TODO: Edit Location");
                break;
            case 8: // Edit Total Slots
                // TODO: Edit Total Slots
                System.out.println("TODO: Edit Total Slots");
                break;
            case 9: // Edit Camp Committee Slots
                // TODO: Edit Camp Committee Slots
                System.out.println("TODO: Edit Camp Committee Slots");
                break;
            case 10: // Edit Description
                // TODO: Edit Description
                System.out.println("TODO: Edit Description");
                break;
            case 11: // Apply Changes
                System.out.println("TODO: Apply Changes");
                // CampController.editCamp(camp, )
                break;
            default:
                System.out.println("Invalid Input");
                break;
            }
        }
    }

    public static void menu_CreateNewCamp() {
        String name, location, description;
        LocalDate startDate, endDate, regCloseDate;
        Faculty openToFaculty;
        int totalSlots, commSlots;
        boolean visibleToStudents;

        System.out.println("Enter ~ to cancel and exit.");
        // Name
        System.out.println("Name:");
        name = InputHandler.nextLine();
        // Start Date
        while (true) {
            try {
                System.out.println("Start Date (YYYY-MM-DD):");
                startDate = LocalDate.parse(InputHandler.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Error: Invalid Date");
            }
        }
        // End Date
        while (true) {
            try {
                System.out.println("End Date (YYYY-MM-DD):");
                endDate = LocalDate.parse(InputHandler.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Error: Invalid Date");
            }
        }
        // Registration CLosing Date
        while (true) {
            try {
                System.out.println("Registration Closing Date (YYYY-MM-DD):");
                regCloseDate = LocalDate.parse(InputHandler.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Error: Invalid Date");
            }
        }
        // Faculty
        System.out.println("Faculty:");
        for (Faculty f : Faculty.values())
            System.out.println((f.ordinal() + 1) + ". " + f);
        openToFaculty = Faculty.values()[InputHandler.nextInt() - 1];
        // Location
        System.out.println("Location:");
        location = InputHandler.nextLine();
        // Total Slots
        System.out.println("Total Slots:");
        totalSlots = InputHandler.nextInt();
        // Committee Member Slots
        System.out.println("Committee Member Slots:");
        commSlots = InputHandler.nextInt();
        // Description
        System.out.println("Description:");
        description = InputHandler.nextLine();
        // Visible to Students
        System.out.println("Visible to Students (0 - no | 1 - yes):");
        visibleToStudents = (InputHandler.nextInt() != 0);

        CampController.createCamp(0, name, startDate, endDate, regCloseDate, openToFaculty, location, totalSlots, commSlots, description, visibleToStudents);
    }
}
