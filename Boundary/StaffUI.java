package boundary;

import handler.InputHandler;
import control.SuggestionController;

public class StaffUI {
    public static void displayMenu() {
        while (true) {
            System.out.println("1. View All Camps");
            System.out.println("2. View My Camps");
            System.out.println("3. Create New Camp");
            System.out.println("4. Generate Student List");
            System.out.println("5. Generate Performance Report");
            System.out.println("0. Quit");

            System.out.print("Please select an option: ");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;

            switch (choice) {
                case 1: // View All Camps
                    // TODO: displayAllCamps()
                    System.out.println("TODO: display all camps");
                    menu_ViewAllCamps();
                    break;
                case 2: // View My Camps
                    // TODO: displayMyCamps()
                    System.out.println("TODO: Display My Camps");
                    menu_ViewMyCamps();
                    break;
                case 3: // Create New Camp
                    // TODO: Create New Camp
                    System.out.println("TODO: create new camp");
                    break;
                case 4: // Generate Student List
                    // TODO: Generate Student List
                    System.out.println("TODO: generate student list");
                    break;
                case 5: // Generate Performance Report
                    // TODO: Generate Performance Report
                    System.out.println("TODO: generate performance report");
                    break;
                default:
                    break;
            }
        }
    }

    public static void menu_ViewAllCamps() {
        while (true) {
            System.out.println("0. Exit");

            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;

            // TODO: displayCampDetails(choice);
            System.out.println("TODO: display camp details");
        }
    }

    public static void menu_ViewMyCamps() {
        while (true) {
            System.out.println("0. Exit");

            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;

            // TODO: displayCampDetails(choice);
            menu_ManageMyCamp(choice);
        }
    }


    /**
     * @param campID
     */
    public static void menu_ManageMyCamp(int campID) {
        while (true) {
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
                    // TODO: displayEnquiries(campID)
                    System.out.println("TODO: display enquiries");
                    menu_ViewMyCampEnquiries(campID);
                    break;
                case 2: // View Suggestions
                    // TODO: displaySuggestions(campID)
                    System.out.println("TODO: display suggestions");
                    menu_ViewMyCampSuggestions(campID);
                    break;
                case 3: // Edit Camp
                    menu_EditMyCamp(campID);
                    break;
                case 4: // Delete Camp
                    // TODO: deleteCamp(campID);
                    System.out.println("TODO: delete camp");
                    break;
                default:
                    break;
            }
        }
    }

    public static void menu_ViewMyCampEnquiries(int campID) {
        while (true) {
            System.out.println("0. Exit");

            System.out.println("Select an enquiry to add a reply to it");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;

            // TODO: replyToEnquiry(campID, choice);
            System.out.println("TODO: reply to enquiry");
        }
    }

    public static void menu_ViewMyCampSuggestions(int campID) {
        while (true) {
            System.out.println("0. Exit");

            System.out.println("Select a suggestion to approve / reject it");
            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;

            menu_setSuggestionStatus(campID, choice);
        }
    }

    public static void menu_setSuggestionStatus(int campID, int suggestionID) {
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

    public static void menu_EditMyCamp(int campID) {
        while (true) {
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
            System.out.println("0.  Quit");

            int choice = InputHandler.nextInt();
            if (choice == 0)
                break;

            switch (choice) {
                case 1: // Toggle Visibility On / Off
                    menu_ViewAllCamps();
                    break;
                case 2: // Edit Name
                    menu_ViewMyCamps();
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
                default:
                    break;
            }
            choice = InputHandler.nextInt();
        }
    }

    public static void main(String[] args) {
        displayMenu();
    }
}
