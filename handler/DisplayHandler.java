package handler;

import java.util.ArrayList;

import entity.*;

/**
 * Handles the displaying and printing of information onto the terminal.
 * <div>Contains a series of overloaded methods called {@code displayResult}</div>
 * 
 */
public class DisplayHandler {

    /**
     * Displays all details regarding a camp
     * Used by staff or commitee members
     * @param camp {@link Camp}
     */
    // display individual camps
    public static void displayResult(Camp camp) {
        System.out.println();
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

    
    /** 
     * Does not display all information of the camp
     * <div>Mainly used to display basic information for non-owners of a camp to see</div>
     * @param camps an {@link java.util.ArrayList}
     */
    // display available camps
    public static void displayResult(ArrayList<Camp> camps) {
        System.out.println();
        if (camps.size() == 0) {
            System.out.println("No camps found!");
            return;
        }
        for (int i = 0; i < camps.size(); i++) {
            System.out.print((i + 1) + ":");
            System.out.println("\tCamp name: " + camps.get(i).getName());
            System.out.println("\tDescription: " + camps.get(i).getDescription());
            System.out.println("\tFrom: " + camps.get(i).getStartDate() + " to " + camps.get(i).getEndDate());
            System.out.println("\tSlots Left: " + camps.get(i).getTotalSlotsLeft());
            System.out.println("\t- Attendees: " + camps.get(i).getAttendeeSlotsLeft());
            System.out.println("\t- Committee: " + camps.get(i).getCommSlotsLeft());
            System.out.println();
        }
    }

    
    /** 
     * Displays a list of camps a certain user has signed up for, as well as his/her role 
     * <div>Role either as:
     * Committee Member or Attendee</div>
     * @param camps an {@link java.util.ArrayList}
     * @param currUser the {@link User} 
     */
    // display signed up camps
    public static void displayResult(ArrayList<Camp> camps, User currUser) {
        System.out.println();
        if (camps.size() == 0) {
            System.out.println("You have not registered for a camp!");
            return;
        }
        for (int i = 0; i < camps.size(); i++) {
            if (i == 0 && currUser instanceof CommitteeMember) {
                System.out.println((i + 1) + ". " + camps.get(i).getName() + " - Committee Member");
            } else
                System.out.println((i + 1) + ". " + camps.get(i).getName() + " - Attendee");
        }
    }

    
    /** 
     * Displays a list of camps a certain staff has created
     * @param staff
     */
    // display created camps for staff
    public static void displayResult(Staff staff) {
        int count = 1;
        for (Camp camp : staff.getCreatedCamps()) {
            System.out.println(count + ". Camp name: " + camp.getName());
            count++;
        }
    }

    
    /** 
     * Displays information about a single enquiry.
     * <div>This includes the original content of that enquiry as well as replies, if there are any.</div>
     * @param enquiry
     */
    public static void displayResult(Enquiry enquiry) {
        System.out.println("Enquiry by " + enquiry.getOwner().getName() + ": " + enquiry.view());
        if (!enquiry.isProcessed()) {
            System.out.println("There are currently no replies!");
            return;
        }
        for (Reply reply : enquiry.getReplies()) {
            System.out.println("Replied by " + reply.getOwnerID() + ": " + reply.view());
        }
    }

    
    /** 
     * Displays information about a single suggestion, as well as its {@link Status}, whether it has been approved, rejected or pending
     * @param suggestion
     */
    public static void displayResult(Suggestion suggestion) {
        System.out.println("Suggestion by " + suggestion.getOwner().getName() + ": " + suggestion.view());
        System.out.println("Status: " + suggestion.getStatus());
    }

    
    /** 
     * Displays a list of signups from a particular camp.
     * <div>The signups are split into a list of committee members and attendees.</div>
     * @param students an {@link java.util.ArrayList}
     * @param commMembers an {@link java.util.ArrayList}
     */
    public static void displayResult(ArrayList<Student> students, ArrayList<CommitteeMember> commMembers) {
        System.out.println();
        System.out.println("Total Committee Members: " + commMembers.size());
        for (CommitteeMember commMember : commMembers) {
            System.out.println(commMember.getName());
        }
        System.out.println();
        System.out.println("Total Attendees: " + students.size());
        for (Student student : students) {
            System.out.println(student.getName());
        }
        System.out.println();
    }

    // public static void displaySearchResult(ArrayList<Camp> result) {
    // System.out.println();
    // if (result.size() == 0) {
    // System.out.println("No search results found!");
    // return;
    // }
    // for (int i = 0; i < result.size(); i++) {
    // System.out.println((i + 1) + ". " + result.get(i).getName());
    // }
    // System.out.println();
    // }
}
