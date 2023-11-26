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

public class ReportController {


    public static void generateReport(Camp camp, int sortBy, String fileName, boolean isStaff) {
        ArrayList<Student> attendees = camp.getAttendees();
        ArrayList<CommitteeMember> committeeMembers = camp.getCommittee();


        try {
            BufferedWriter reportStream = new BufferedWriter(new FileWriter(fileName,isStaff));

            if (isStaff) reportStream.write("======== Staff Report For Camp " + camp.getName() + " ========\r");
            else reportStream.write("======== Committee Member Report For Camp " + camp.getName() + " ========\r");

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
            reportStream.write("\n\r");
            reportStream.write("Camp participants: \r");
            if (sortBy == 1) {
                int j =1;
                for (int i = 0; i < attendees.size(); i++) {
                    reportStream.write(j++ + ": " + attendees.get(i).getName() + " from " + attendees.get(i).getFaculty() + "---- attendee" + "\r");
                }

                for (int i = 0; i < committeeMembers.size(); i++) {
                    reportStream.write(j++ + ": " + committeeMembers.get(i).getName() + " from " + committeeMembers.get(i).getFaculty() + "---- committee" + "\r");
                }
            }
            if (sortBy == 2) {
                int j = 1;
                for (int i = 0; i < committeeMembers.size(); i++) {
                    reportStream.write(j++ + ": " + committeeMembers.get(i).getName() + " from " + committeeMembers.get(i).getFaculty() + "---- committee" + "\r");
                }
                for (int i = 0; i < attendees.size(); i++) {
                    reportStream.write(j++ + ": " + attendees.get(i).getName() + " from " + attendees.get(i).getFaculty() + "---- attendee" + "\r");
                }
            }

            // camp enquiries
            reportStream.write("\n\r");
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

            if(isStaff){
            reportStream.write("\n\r");
            reportStream.write("Performance report for committee members: \r");

            }
            reportStream.write("======== End-of-Report ========");
            reportStream.write("\n\r");
            reportStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(fileName+" not found!\n\r" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("file error!\n\r" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}
