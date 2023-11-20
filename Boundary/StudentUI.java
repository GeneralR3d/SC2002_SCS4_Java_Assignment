package Boundary;

import java.util.Scanner;

import Entity.*;
import Controllers.CampController;

import java.util.ArrayList;

public class StudentUI{
    public static void displayMenu(){
        int option;
        do{
            System.out.println("Command Options: ");
            System.out.println("Enter number to select....");
            //System.out.println("Enter 0 to go back/exit…");
            System.out.println("1. View open camps");
            System.out.println("2. See all registered camps");
            System.out.println("3. Withdraw from registered camps");
            if (SessionInfo.user instanceof CommitteeMember) System.out.println("4. Committee member menu");
            
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            switch(option){
                case 1:
                // TODO: import sessionInfo class
                    ArrayList<Camp> openCamps = CampController.viewAvailableCamps(SessionInfo.user);
                    displayOpenCamps(openCamps);
                    break;
                case 2:
                    ArrayList<Camp> signedUpCamps = CampController.viewSignedUpCamps((Student)SessionInfo.user);
                    displayRegisteredCamps(signedUpCamps);                
                    break;
                case 3:
                    ArrayList<Camp> registeredCamps = CampController.viewSignedUpCamps((Student)SessionInfo.user);
                    withdrawRegisteredCamps((Student)SessionInfo.user);
                    break;
                case 4:
                    //pass thru curr user and check if is committee member
                    break;
                default:
                    break;
            }
        }while(option != 0);
        AccountUI.loginMenu();
    }

    private static void displayOpenCamps(ArrayList<Camp> openCamps){
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back/exit…");

        DisplayHelper.displayResult(openCamps);

        Scanner sc = new Scanner(System.in);
        
        while (true){
            int option = sc.nextInt();
            if (option == 0)
                displayMenu();
            else if (option <0 || option >openCamps.size()){
                System.out.println("Invalid input!");
                continue;
            }
            else viewCampOptions(openCamps.get(option-1));
        }

    }

    private static void displayRegisteredCamps(ArrayList<Camp> registeredCamps){        
        System.out.println("Command Options: ");
        System.out.println("Enter 0 to go back/exit…");
        DisplayHelper.displayResult(registeredCamps, SessionInfo.user);

        Scanner sc = new Scanner(System.in);
        while (true){
            int option = sc.nextInt();
            if (option == 0)
                displayMenu();
            else if (option<0 || option>registeredCamps.size()){
                System.out.println("Invalid input!");
                continue;
            }
        }
    }

    private static void withdrawRegisteredCamps(Student student){
        ArrayList<Camp> registeredCamps = CampController.viewSignedUpCamps(student);

        System.out.println("Command Options: ");
        System.out.println("Enter number to withdraw from that camp....");
        System.out.println("Enter 0 to go back/exit…");
        DisplayHelper.displayResult(registeredCamps, SessionInfo.user);

        Scanner sc = new Scanner(System.in);
        while (true){
            int option = sc.nextInt();
            if (option == 0)
                displayMenu();
            else if (option<0 || option>registeredCamps.size()){
                System.out.println("Invalid input!");
                continue;
            }
            else CampController.removeAttendee(registeredCamps.get(option-1));
        }
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

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option){
            case 1:
                if(CampController.registerAttendee(camp)){
                    System.out.println("You have successfully registered as attendee!");
                    System.out.println("Enter 0 to go back/exit…");
                }
                else{
                    System.out.println("You are already registered for this camp!");
                    System.out.println("Enter 0 to go back/exit…");
                }
                break;
            case 2:
                if(CampController.registerCommittee(camp)){
                    System.out.println("You have successfully registered as camp committee member!");
                    System.out.println("Enter 0 to go back/exit…");
                }
                else{
                    System.out.println("You are already a committee member for this camp!");
                    System.out.println("Enter 0 to go back/exit…");
                }
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
                System.out.println("Invalid input!");
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