package control;

import java.time.LocalDate;
import java.util.ArrayList;

import app.SessionInfo;
import entity.*;

public class CampController {

    /**
     * @param newCamp
     */
    public static void createCamp(int campID, String name, LocalDate startDate, LocalDate endDate, LocalDate regCloseDate, Faculty openToFaculty, String location, int totalSlots, int commSlots, String description, boolean visibleToStudents)
            throws Exception {
        UserController.assertUserType(Staff.class);
        Camp newCamp = new Camp(campID, name, startDate, endDate, regCloseDate, openToFaculty, location, totalSlots, commSlots, description, visibleToStudents, (Staff) SessionInfo.getUser());
        Staff staff = (Staff) SessionInfo.getUser();
        staff.createCamp(newCamp);
        DataController.addCamp(newCamp);
    }

    /**
     * @param camp
     */
    public static void editCamp(Camp camp, int campID, String name, LocalDate startDate, LocalDate endDate, LocalDate regCloseDate, Faculty openToFaculty, String location, int totalSlots, int commSlots, String description, boolean visibleToStudents)
            throws Exception {
        UserController.assertUserType(Staff.class);
        camp.setName(name);
        camp.setStartDate(startDate);
        camp.setEndDate(endDate);
        camp.setRegCloseDate(regCloseDate);
        camp.setOpenToFaculty(openToFaculty);
        camp.setLocation(location);
        camp.setTotalSlotsLeft(totalSlots);
        camp.setComSlotsLeft(commSlots);
        camp.setDescription(description);
        camp.setVisibleToStudents(visibleToStudents);
    }

    /**
     * @param deletingCamp
     * @return boolean
     */
    public static void deleteCamp(Camp deletingCamp) throws Exception {
        UserController.assertUserType(Staff.class);
        ArrayList<Camp> allCamps = DataController.getCamps();

        // check if deletingcamp is in camps
        for (Camp camp : allCamps) {
            if (deletingCamp.getCampID() == camp.getCampID()) {
                DataController.removeCamp(deletingCamp);
                Staff staff = (Staff) SessionInfo.getUser();
                staff.deleteCamp(camp);
                return;
            }
        }
        throw new Exception("Error: Camp to be deleted cannot be found.");
    }

    /**
     * @return ArrayList<Camp>
     * @throws Exception
     */
    public static ArrayList<Camp> getCreatedCamps() throws Exception {
        UserController.assertUserType(Staff.class);
        Staff staff = (Staff) SessionInfo.getUser();
        ArrayList<Camp> createdCamps = staff.getCreatedCamps();
        return createdCamps;
    }

    public static ArrayList<Camp> getAvailableCamps() {
        ArrayList<Camp> campData = DataController.getCamps();
        ArrayList<Camp> availableCamps = new ArrayList<Camp>();
        LocalDate today = LocalDate.now();

        // add committeememberfor camp and add as first in result
        switch (SessionInfo.getUserType()) {
        case "Student":
        case "CommitteeMember":
            for (Camp currCamp : campData) {
                // check for RegCloseDate
                // check visibility and faculty of camp
                if (currCamp.isVisibleToStudents() && !today.isAfter(currCamp.getRegCloseDate()) && (currCamp.getOpenToFaculty() == Faculty.NTU || currCamp.getOpenToFaculty() == SessionInfo.getUser().getFaculty())) {
                    availableCamps.add(currCamp);
                }
            }
            break;
        case "Staff":
            availableCamps = campData;
        }

        return availableCamps;
    }

    public static ArrayList<Camp> getSignedUpCamps() {
        ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
        switch (SessionInfo.getUserType()) {
        case "Student":
            for (Camp camp : ((Student) SessionInfo.getUser()).getSignedUpCamps())
                registeredCamps.add(camp);
            break;
        case "CommitteeMember":
            // add first camp as committeemember camp
            Camp committeCamp = ((CommitteeMember) SessionInfo.getUser()).getCommiteeMemberFor();
            registeredCamps.add(committeCamp);
            for (Camp camp : ((CommitteeMember) SessionInfo.getUser()).getSignedUpCamps()) {
                if (camp.equals(committeCamp))
                    continue;
                registeredCamps.add(camp);
            }
            break;
        }
        return registeredCamps;
    }

    public static boolean removeAttendee(Camp camp) {
        ArrayList<Student> attendees = camp.getAttendees();
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).getUserID() == SessionInfo.getUser().getUserID()) {
                //remove from camp attendees
                camp.removeAttendee(attendees.get(i));
                //remove from student signedupcamps
                Student currUser = (Student) SessionInfo.getUser();
                currUser.getSignedUpCamps().remove(camp);
                return true;
            }
        }
        // user not found
        return false;
    }

    public static void registerAttendee(Camp camp) throws Exception {
        if (camp.getAttendeeSlotsLeft() == 0) throw new Exception("There are no slots available!");

        // check if user already signed up as attendee
        ArrayList<Student> attendees = camp.getAttendees();
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).getUserID() == SessionInfo.getUser().getUserID()) {
                throw new Exception("You have already registered as attendee!");
            }
        }

        // check if user already signed up as committee member
        ArrayList<CommitteeMember> commMembers = camp.getCommittee();
        for (int i = 0; i < commMembers.size(); i++) {
            if (commMembers.get(i).getUserID() == SessionInfo.getUser().getUserID()) {
                throw new Exception("You have already registered as a committee member!");
            }
        }

        Student student = (Student) SessionInfo.getUser();
        student.register(camp);
        camp.addAttendee((Student) SessionInfo.getUser());
    }

    public static void registerCommittee(Camp camp) throws Exception {
        if (camp.getCommSlotsLeft() == 0) throw new Exception("There are no slots available!");

        // check if user already signed up as attendee
        ArrayList<Student> attendees = camp.getAttendees();
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).getUserID() == SessionInfo.getUser().getUserID()) {
                throw new Exception("You have already registered as attendee!");
            }
        }

        // check if user is already a committee member
        if (SessionInfo.getUserType().equals("CommitteeMember"))
            throw new Exception("You are already a committee member!");

        // if they are not, convert them into a committee member
        Student student = (Student) SessionInfo.getUser();
        RoleController.studentToCommittee(student, camp);

        CommitteeMember committee = (CommitteeMember) SessionInfo.getUser();
        committee.register(camp);
        camp.addCommittee(committee);
    }
}
