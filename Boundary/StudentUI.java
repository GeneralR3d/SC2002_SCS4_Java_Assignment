package Boundary;

import java.util.Scanner;
import java.util.ArrayList;

public class StudentUI{
    public static void studentMainMenu(){
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
                ArrayList<Camp> openCamps = CampController.getOpenCamps();
                displayOpenCamps(openCamps);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break:
            default:
                break;
        }
    }

    private static void displayOpenCamps(ArrayList<Camp> openCamps){
        System.out.println("Command Options: ");
        System.out.println("Enter number to select....");
        System.out.println("Enter 0 to go back/exit…");
        for(int i = 1; i<= openCamps.length();i++){
            System.out.println(i + ". " + openCamps.get(i-1).getName() + ": "+ openCamps.get(i-1).getTotalSlotsLeft());
        }
        int option = sc.nextInt();
        if (option == 0)
            studentMainMenu();
        else if (option <0 || option >openCamps.length())
            return;
        else
            viewCampOptions(camp);

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
                if(result == True)
                    System.out.println("You have successfully registered as attendee!");
                else
                    System.out.println("Not registered!");
                break;
            case 2:
                boolean result = CampController.registerCommittee(camp);
                if(result == True)
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
                
                break;
            default:
                break;
        }


    }

}