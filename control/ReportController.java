package control;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import app.SessionInfo;
import entity.Camp;
import entity.CommitteeMember;
import entity.Enquiry;
import entity.Reply;
import entity.Staff;
import entity.Student;
import handler.InputHandler;

public class ReportController {
    public static void generateReport(int sortBy, String fileName) {

        try {
            BufferedWriter reportStream = new BufferedWriter(new FileWriter(fileName));
            for (Camp camp : ((Staff) SessionInfo.getUser()).getCreatedCamps()) {

                ArrayList<Student> attendees = camp.getAttendees();
                ArrayList<CommitteeMember> committeeMembers = camp.getCommittee();
                reportStream.write("======== Committee Member Report For Camp " + camp.getName() + " ========\r");
                // camp details
                reportStream.write("\n");
                reportStream.write("Camp Details: \r");
                reportStream.write("Camp name: " + camp.getName() + "\r");
                reportStream.write("Start date: " + camp.getStartDate() + "\r");
                reportStream.write("End date: " + camp.getEndDate() + "\r");
                reportStream.write("Registration Closing Date: " + camp.getRegCloseDate() + "\r");
                reportStream.write("Faculty: " + camp.getOpenToFaculty() + "\r");
                reportStream.write("Location: " + camp.getLocation() + "\r");
                reportStream.write("Total slots: " + camp.getTotalSlotsLeft() + "\r");
                reportStream.write("Camp committee slots: " + camp.getCommSlotsLeft() + "\r");
                reportStream.write("Description: " + camp.getDescription() + "\r");
                reportStream.write("Staff in charge: " + camp.getStaffInCharge().getName() + "\r");
                // camp participants
                reportStream.write("Camp participants: \r");
                reportStream.write("\n\r");
                if (sortBy == 1) {
                    for (int i = 0; i < attendees.size(); i++) {
                        reportStream.write((i + 1) + ": " + attendees.get(i).getName() + " from " + attendees.get(i).getFaculty() + "---- attendee" + "\r");
                    }

                    for (int i = 0; i < committeeMembers.size(); i++) {
                        reportStream.write((i + 1) + ": " + committeeMembers.get(i).getName() + " from " + committeeMembers.get(i).getFaculty() + "---- committee" + "\r");
                    }
                }
                if (sortBy == 2) {
                    for (int i = 0; i < committeeMembers.size(); i++) {
                        reportStream.write((i + 1) + ": " + committeeMembers.get(i).getName() + " from " + committeeMembers.get(i).getFaculty() + "---- attendee" + "\r");
                    }
                    reportStream.write("Camp Attendees: \r");
                    for (int i = 0; i < attendees.size(); i++) {
                        reportStream.write((i + 1) + ": " + attendees.get(i).getName() + " from " + attendees.get(i).getFaculty() + "---- attendee" + "\r");
                    }
                }

                // camp enquiries
                reportStream.write("\n");
                reportStream.write("All Camp Enquiries: \r");

                ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
                if (enquiries == null)
                    reportStream.write("No enquiries!");
                else
                    for (Enquiry enquiry : enquiries) {

                        reportStream.write("Enquiry by " + enquiry.getOwner().getName() + ": " + enquiry.view() + "\r");
                        if (!enquiry.isProcessed()) {
                            reportStream.write("\tThere are currently no replies!\r");
                        } else
                            for (Reply reply : enquiry.getReplies()) {
                                reportStream.write("\tReplied by " + reply.getOwnerID() + ": " + reply.view() + "\r");
                            }
                    }

            }
            reportStream.write("======== End-of-Report ========");
            reportStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("committeeReport.txt not found!\n\r" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("file error!\n\r" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static void generateReport(Camp camp, int sortBy, String fileName) {
        ArrayList<Student> attendees = camp.getAttendees();
        ArrayList<CommitteeMember> committeeMembers = camp.getCommittee();

        int option;
        while (true) {
            System.out.println("How do you want the list of attendees to be displayed?");
            System.out.println("1: Attendees first");
            System.out.println("2: Commiteee first");
            System.out.println("Select how you want the list of attendees to be displayed...");
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

        try {
            BufferedWriter reportStream = new BufferedWriter(new FileWriter(fileName));
            reportStream.write("======== Committee Member Report For Camp " + camp.getName() + " ========\r");
            // camp details
            reportStream.write("\n");
            reportStream.write("Camp Details: \r");
            reportStream.write("Camp name: " + camp.getName() + "\r");
            reportStream.write("Start date: " + camp.getStartDate() + "\r");
            reportStream.write("End date: " + camp.getEndDate() + "\r");
            reportStream.write("Registration Closing Date: " + camp.getRegCloseDate() + "\r");
            reportStream.write("Faculty: " + camp.getOpenToFaculty() + "\r");
            reportStream.write("Location: " + camp.getLocation() + "\r");
            reportStream.write("Total slots: " + camp.getTotalSlotsLeft() + "\r");
            reportStream.write("Camp committee slots: " + camp.getCommSlotsLeft() + "\r");
            reportStream.write("Description: " + camp.getDescription() + "\r");
            reportStream.write("Staff in charge: " + camp.getStaffInCharge().getName() + "\r");
            // camp participants
            reportStream.write("Camp participants: \r");
            reportStream.write("\n\r");
            if (option == 1) {
                for (int i = 0; i < attendees.size(); i++) {
                    reportStream.write((i + 1) + ": " + attendees.get(i).getName() + " from " + attendees.get(i).getFaculty() + "---- attendee" + "\r");
                }

                for (int i = 0; i < committeeMembers.size(); i++) {
                    reportStream.write((i + 1) + ": " + committeeMembers.get(i).getName() + " from " + committeeMembers.get(i).getFaculty() + "---- committee" + "\r");
                }
            }
            if (option == 2) {
                for (int i = 0; i < committeeMembers.size(); i++) {
                    reportStream.write((i + 1) + ": " + committeeMembers.get(i).getName() + " from " + committeeMembers.get(i).getFaculty() + "---- attendee" + "\r");
                }
                reportStream.write("Camp Attendees: \r");
                for (int i = 0; i < attendees.size(); i++) {
                    reportStream.write((i + 1) + ": " + attendees.get(i).getName() + " from " + attendees.get(i).getFaculty() + "---- attendee" + "\r");
                }
            }

            // camp enquiries
            reportStream.write("\n");
            reportStream.write("All Camp Enquiries: \r");

            ArrayList<Enquiry> enquiries = EnquiryController.getAllEnquiries(camp);
            if (enquiries == null)
                reportStream.write("No enquiries!");
            else
                for (Enquiry enquiry : enquiries) {

                    reportStream.write("Enquiry by " + enquiry.getOwner().getName() + ": " + enquiry.view() + "\r");
                    if (!enquiry.isProcessed()) {
                        reportStream.write("\tThere are currently no replies!\r");
                    } else
                        for (Reply reply : enquiry.getReplies()) {
                            reportStream.write("\tReplied by " + reply.getOwnerID() + ": " + reply.view() + "\r");
                        }
                }

            reportStream.write("======== End-of-Report ========");
            reportStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("committeeReport.txt not found!\n\r" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("file error!\n\r" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}
