package control;

import java.time.LocalDate;
import java.util.ArrayList;

import app.SessionInfo;
import entity.Camp;
import entity.CommitteeMember;
import entity.Faculty;
import entity.Staff;
import entity.Student;

public class CampController {

    /**
     * @param newCamp
     */
    public static void createCamp(String name, LocalDate startDate, LocalDate endDate, LocalDate regCloseDate,
            Faculty openToFaculty, String location, int totalSlots, int commSlots, String description,
            boolean visibleToStudents) throws Exception {
        UserController.assertUserType(Staff.class);
        Camp newCamp = new Camp(0, name, startDate, endDate, regCloseDate, openToFaculty, location, totalSlots,
                commSlots, description, visibleToStudents, (Staff) SessionInfo.getUser());
        Staff staff = (Staff) SessionInfo.getUser();
        staff.createCamp(newCamp);
        DataController.addCamp(newCamp);
    }

    /**
     * @param camp
     */
    public static void editCamp(Camp camp, String name, LocalDate startDate, LocalDate endDate, LocalDate regCloseDate,
            Faculty openToFaculty, String location, int totalSlots, int commSlots, String description,
            boolean visibleToStudents) throws Exception {
        UserController.assertUserType(Staff.class);
        if (visibleToStudents == false) {
            if (camp.getAttendees().size() != 0 || camp.getCommittee().size() != 0)
                throw new Exception("Visibility cannot be turned to false as there have already been signups!");
        }
        camp.setName(name);
        camp.setStartDate(startDate);
        camp.setEndDate(endDate);
        camp.setRegCloseDate(regCloseDate);
        camp.setOpenToFaculty(openToFaculty);
        camp.setLocation(location);
        camp.setTotalSlotsLeft(totalSlots);
        camp.setComSlotsLeft(commSlots);
        camp.setDescription(description);

        if(visibleToStudents == false){
            // check if thr r any attendees/comm in deletingCamp
            int numAttendees = camp.getAttendees().size();
            int numCommMembers = camp.getCommittee().size();
            if (numAttendees != 0 || numCommMembers != 0)
                throw new Exception("Camp cannot be turned off as there have already been signups!");
        }   
        camp.setVisibleToStudents(visibleToStudents);
    }

    /**
     * @param deletingCamp
     * @return boolean
     */
    public static void deleteCamp(Camp deletingCamp) throws Exception {
        UserController.assertUserType(Staff.class);
        ArrayList<Camp> allCamps = DataController.getCamps();

        // check if thr r any attendees/comm in deletingCamp
        int numAttendees = deletingCamp.getAttendees().size();
        int numCommMembers = deletingCamp.getCommittee().size();
        if (numAttendees != 0 || numCommMembers != 0)
            throw new Exception("Camp cannot be deleted as there have already been signups!");
        for (

        // check if deletingcamp is in camps
        Camp camp : allCamps) {
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
                    if (currCamp.isVisibleToStudents() && !today.isAfter(currCamp.getRegCloseDate())
                            && (currCamp.getOpenToFaculty() == Faculty.NTU
                                    || currCamp.getOpenToFaculty() == SessionInfo.getUser().getFaculty())) {
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

    public static void removeAttendee(Camp camp) {
        ArrayList<Student> attendees = camp.getAttendees();
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).getUserID() == SessionInfo.getUser().getUserID()) {
                Student student = attendees.get(i);
                // remove from camp attendees
                camp.removeAttendee(student);
                // add to camp blacklist
                camp.addBlacklist(student);
                break;
            }
        }
        // remove from student signedupcamps
        Student currUser = (Student) SessionInfo.getUser();
        currUser.getSignedUpCamps().remove(camp);
    }

    public static void registerAttendee(Camp camp) throws Exception {
        if (camp.getAttendeeSlotsLeft() == 0)
            throw new Exception("There are no slots available!");

        // check if user has been blacklisted from the camp;
        ArrayList<Student> blacklistStudents = camp.getBlacklist();
        for (Student blacklistStudent : blacklistStudents) {
            if (blacklistStudent.getUserID() == SessionInfo.getUser().getUserID()) {
                throw new Exception("You can no longer register for this camp as you have previously withdrawn!");
            }
        }

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

        // check if student has any overlapping camp dates
        ArrayList<Camp> signedUpCamps = student.getSignedUpCamps();
        LocalDate campStartDate = camp.getStartDate();
        LocalDate campEndDate = camp.getEndDate();
        for (Camp signedUpCamp : signedUpCamps) {
            LocalDate startDate = signedUpCamp.getStartDate();
            LocalDate endDate = signedUpCamp.getEndDate();
            if (!campStartDate.isBefore(startDate) && !campStartDate.isAfter(endDate)) {
                throw new Exception("The dates for this camp clashes with a camp you have already signed up for!");
            }
            if (!campEndDate.isBefore(startDate) && !campStartDate.isAfter(endDate)) {
                throw new Exception("The dates for this camp clashes with a camp you have already signed up for!");
            }
        }

        student.register(camp);
        camp.addAttendee((Student) SessionInfo.getUser());
    }

    public static void registerCommittee(Camp camp) throws Exception {
        if (camp.getCommSlotsLeft() == 0)
            throw new Exception("There are no slots available!");

        // check if user has been blacklisted from the camp;
        ArrayList<Student> blacklistStudents = camp.getBlacklist();
        for (Student blacklistStudent : blacklistStudents) {
            if (blacklistStudent.getUserID() == SessionInfo.getUser().getUserID()) {
                throw new Exception("You can no longer register for this camp as you have previously withdrawn!");
            }
        }

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

        Student student = (Student) SessionInfo.getUser();

        // check if student has any overlapping camp dates
        ArrayList<Camp> signedUpCamps = student.getSignedUpCamps();
        LocalDate campStartDate = camp.getStartDate();
        LocalDate campEndDate = camp.getEndDate();
        for (Camp signedUpCamp : signedUpCamps) {
            LocalDate startDate = signedUpCamp.getStartDate();
            LocalDate endDate = signedUpCamp.getEndDate();
            if (!campStartDate.isBefore(startDate) && !campStartDate.isAfter(endDate)) {
                throw new Exception("The dates for this camp clashes with a camp you have already signed up for!");
            }
            if (!campEndDate.isBefore(startDate) && !campStartDate.isAfter(endDate)) {
                throw new Exception("The dates for this camp clashes with a camp you have already signed up for!");
            }
        }

        // if they are not, convert them into a committee member
        RoleController.studentToCommittee(student, camp);

        CommitteeMember committee = (CommitteeMember) SessionInfo.getUser();
        committee.register(camp);
        camp.addCommittee(committee);
    }
}
