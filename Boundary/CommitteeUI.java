package boundary;

import java.util.Scanner;

import Entity.Camp;
import Controllers.CampController;

import java.util.ArrayList;

public class CommitteeUI{

    public static void displayMenu(){
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");

        Camp campCurrent = CampController.getCommiteeMemberFor();
        System.out.println("You are a committee member for camp "+ campCurrent.getName());
        System.out.println("1. View details for camp");
        System.out.println("2. Submit suggestion");
        System.out.println("3. View enquries");
        System.out.println("4. Generate report");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option){
            case 1:
                displayCampDetails(campCurrent);
                break;
            case 2:
                System.out.println("Enter ~ to quit…");
                System.out.println("Key in suggestion, press enter to confirm: ")
                String suggestion = sc.nextLine();
                if (suggestion.charAt(0)=='~')
                    break;
                else
                    CampController.addSuggestion(campCurrent,suggestion);
                break;
            case 3:
                ArrayList<Enquiry> allEnquiries = CampController.getEnquiries(campCurrent);
                displayEnquiries(allEnquiries);
                break;
            case 4:
                generateReport(campCurrent);
                break;
            default:
                break;
        }
    }

    private static void displayCampDetails(Camp camp){
        System.out.println("Name: "+ camp.getName());
        System.out.println("Start Date: "+ camp.getStartDate());
        System.out.println("End Date: "+ camp.getEndDate());
        System.out.println("Registration close Date: "+ camp.getRegCloseDate());
        System.out.println("School it is opened to: "+ camp.getOpenToFaculty());
        System.out.println("Location: "+ camp.getLocation());
        System.out.println("Total slots available: "+ camp.getTotalSlotsLeft());
        System.out.println("Total camp committee slots available: "+ camp.getCommSlotsLeft());
        System.out.println("Description: "+ camp.getDescription());
        System.out.println("Staff in charge is "+ camp.getStaffInCharge.getUserID());
    }

    private static void displayEnquiries(ArrayList<Enquiry> allEnquiries){
        System.out.println("Command Options: ");
        System.out.println("Enter number to reply....");
        System.out.println("Enter 0 to go back/exit…");
        for(int i = 1; i<= allEnquiries.length();i++){
            System.out.print(i + ". " + allEnquiries.get(i-1).getContent() + ": ");
            if(allEnquiries.get(i-1).isProcessed())
            System.out.println("Processed");
            else
            System.out.println("Not Processed");
        }
        int option = sc.nextInt();
        if (option == 0)
            committeeMainMenu();
        else if (option <0 || option >allEnquiries.length())
            return;
        else
            System.out.println("Enter ~ to quit…");
            System.out.println("Key in reply, press enter to confirm: ")
            String reply = sc.nextLine();
            if (reply.charAt(0)=='~')
                break;
            else
                CampController.addReply(camp,allEnquiries.get(option-1),reply);
            break;

    }

    private static void generateReport(Camp camp){
        ;
    }


}
