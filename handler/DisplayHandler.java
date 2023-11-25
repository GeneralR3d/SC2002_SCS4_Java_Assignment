package handler;

import java.util.ArrayList;

import entity.*;

public class DisplayHandler {

    /**
     * @param camp
     */
    // display individual camps
    public static void displayResult(Camp camp) {
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

    // display available camps
    public static void displayResult(ArrayList<Camp> camps) {
        System.out.println();
        if (camps.size() == 0) {
            System.out.println("There are no camps available!");
            return;
        }
        for (int i = 0; i < camps.size(); i++) {
            System.out.println((i + 1) + ": ");
            System.out.println("Camp name: " + camps.get(i).getName());
            System.out.println("Slots Left:");
            System.out.println("Attendees: " + camps.get(i).getAttendeeSlotsLeft());
            System.out.println("Committee: " + camps.get(i).getCommSlotsLeft());
            System.out.println();
        }
    }

    // display signed up camps
    public static void displayResult(ArrayList<Camp> camps, User currUser) {
        System.out.println();
        if (camps.size() == 0) {
            System.out.println("You have not registered for a camp!");
            return;
        }
        for (int i = 1; i <= camps.size(); i++) {
            if (i == 0 && currUser instanceof CommitteeMember) {
                System.out.println(i + ". " + camps.get(i).getName() + " - Committee Member");
            } else
                System.out.println(i + ". " + camps.get(i).getName() + " - Attendee");
        }
    }

    // display created camps for staff
    public static void displayResult(Staff staff) {
        int count = 1;
        for (Camp camp : staff.getCreatedCamps()) {
            System.out.println(count + ". Camp name: " + camp.getName());
            count++;
        }
    }

    public static void displayResult(Enquiry enquiry) {
        System.out.println();
        System.out.println("Enquiry by: " + enquiry.getOwner().getName());
        System.out.println(enquiry.view());
        System.out.println("Replies:");
        if (!enquiry.isProcessed()) {
            System.out.println("Status: Not processed");
            return;
        }
        for (Reply reply : enquiry.getReplies()) {
            System.out.println("Replied by " + reply.getOwnerID() + ": " + reply.view());
        }
    }

    public static void displayResult(Suggestion suggestion) {

    }

    public static void displaySearchResult(ArrayList<Camp> result){
        System.out.println();
        if (result.size() == 0) {
            System.out.println("No search results found!");
            return;
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println((i+1) + ". " + result.get(i).getName());
        }
    }
}
