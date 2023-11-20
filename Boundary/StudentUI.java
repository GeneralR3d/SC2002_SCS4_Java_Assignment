package Boundary;

import java.util.Scanner;

import Entity.*;
import Controllers.CampController;

import java.util.ArrayList;

public class StudentUI{
    public static void displayMenu(){
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        //System.out.println("Enter 0 to go back/exit…");
        System.out.println("1. View open camps");
        System.out.println("2. See all registered camps");
        System.out.println("3. Withdraw from registered camps");
        System.out.println("4. Committee member menu");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option){
            case 1:
            // TODO: import sessionInfo class
                ArrayList<Camp> openCamps = CampController.viewAvailableCamps(SessionInfo.user);
                displayOpenCamps(openCamps);
                break;
            case 2:
                ArrayList<Camp> signedUpCamps = CampController.viewSignedUpCamps((Student)SessionInfo.user);
                
                break;
            case 3:
                ArrayList<Camp> registeredCamps = CampController.viewSignedUpCamps((Student)SessionInfo.user);
                withdrawRegisteredCamps((Student)SessionInfo.user);
                break;
            case 4:
                //pass thru curr user and check if is committee member
                break:
            default:
                break;
        }
    }

    private static void displayOpenCamps(ArrayList<Camp> openCamps){
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back/exit…");

        DisplayHelper.displayResult(openCamps);

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        if (option == 0)
            displayMenu();
        else if (option <0 || option >openCamps.size())
            return;
        else
            viewCampOptions(openCamps.get(option-1));

    }

    private static void displayRegisteredCamps(ArrayList<Camp> registeredCamps){        
        System.out.println("Command Options: ");
        System.out.println("Enter 0 to go back/exit…");
        DisplayHelper.displayResult(registeredCamps, SessionInfo.user);

    }

    private static void withdrawRegisteredCamps(Student student){
        ArrayList<Camp> registeredCamps = CampController.viewSignedUpCamps(student);

        System.out.println("Command Options: ");
        System.out.println("Enter number to withdraw from that camp....");
        System.out.println("Enter 0 to go back/exit…");
        DisplayHelper.displayResult(registeredCamps, SessionInfo.user);

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        if (option == 0)
            displayMenu();
        else if (option <0 || option >registeredCamps.size())
            return;
        else
            CampController.removeAttendee(registeredCamps.get(option-1));

    }

    private static void viewCampOptions(Camp camp){
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back/exit…");
        System.out.println("Number of slots left for "+ camp.getName() + " is " + camp.getTotalSlotsLeft());
        System.out.println("1. Register as attendee");
        System.out.println("2. Register as camp committee member");
        System.out.println("3. Submit enquiry about camp");
        System.out.println("4. Manage my enquiries");
        int option = sc.nextInt();
        switch(option){
            case 1:
                boolean result = CampController.registerAttendee(camp);
                if(result == true)
                    System.out.println("You have successfully registered as attendee!");
                else
                    System.out.println("Not registered!");
                break;
            case 2:
                boolean result = CampController.registerCommittee(camp);
                if(result == true)
                    System.out.println("You have successfully registered as camp committee member!");
                else
                    System.out.println("Not registered!");
                break;
            case 3:
                System.out.println("Enter ~ to quit…");
                System.out.println("Key in enquiry, press enter to confirm: ")
                String question = sc.nextLine();
                if (question.charAt(0)=='~')
                    break;
                else
                    CampController.addEnquiry(camp,question);
                break;
            case 4:
                ArrayList<Enquiry> enquiriesByStudent = CampController.getEnquiries(camp);
                displayEnquiries(enquiriesByStudent);
                break;
            default:
                break;
        }


    }

    private static void displayEnquiries(ArrayList<Enquiry> enquiriesByStudent){
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back/exit…");
        for(int i = 1; i<= enquiriesByStudent.length();i++){
            System.out.print(i + ". " + enquiriesByStudent.get(i-1).getContent() + ": ");
            if(enquiriesByStudent.get(i-1).isProcessed())
            System.out.println("Processed");
            else
            System.out.println("Not Processed");
        }
        int option = sc.nextInt();
        if (option == 0)
            displayMenu();
        else if (option <0 || option >enquiriesByStudent.length())
            return;
        else
            viewEnquiryOptions(enquiriesByStudent.get(option-1));

    }

    private static void viewEnquiryOptions(Enquiry enquiry){
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back/exit…");
        System.out.println("1. Edit enquiry");
        System.out.println("2. Delete enquiry");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter ~ to quit…");
                System.out.println("Key in enquiry, press enter to confirm: ")
                String question = sc.nextLine();
                if (question.charAt(0)=='~')
                    break;
                else
                    CampController.editEnquiry(camp,enquiry,question);
                break;
            case 2:
                CampController.deleteEnquiry(camp,enquiry);
                break;
            default:
                break;
        }

    }

}