package Boundary;

import java.util.ArrayList;

import Entity.*;

public class DisplayHelper {
    public static void displayResult(Camp camp){
        System.out.println("Camp name: " + camp.getName());
        System.out.println("Start date: " + camp.getStartDate());
        System.out.println("End date: " + camp.getEndDate());
        System.out.println("Registration Closing Date: " + camp.getRegCloseDate());
        System.out.println("Faculty: " + camp.getOpenToFaculty());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Total slots: " + camp.getTotalSlotsLeft());
        System.out.println("Camp committee slots: " + camp.getCommSlotsLeft());
        System.out.println("Description: " + camp.getDescription());
        System.out.println("Staff in charge: " + camp.getStaffInCharge().getName());
    }

    public static void displayResult(ArrayList<Camp> camps){
        for (Camp camp: camps){
            System.out.println("Camp name: " + camp.getName() + " (" + camp.getTotalSlotsLeft() + " slots left)");
        }
    }

    public static void displayResult(Enquiry enquiry){
        
    }

    public static void displayResult(Suggestion suggestion){
        
    }
}
