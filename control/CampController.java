package control;

import java.time.LocalDate;
import java.util.ArrayList;

import app.SessionInfo;
import entity.Camp;
import entity.CommitteeMember;
import entity.Faculty;
import entity.Staff;
import entity.Student;
import entity.User;

/**
 * Static control class for interfacing with the UI classes to handle all requests regarding camps.
 * <p>
 * These include:<br>
 * 1) Creation, edit and deletion of camps<br>
 * 2) Filtering and returning {@link java.util.ArrayList} of camps based on different scenarios <br>
 * 3) adding and removing signups for a {@link entity.Camp}
 * </p>
 */
public class CampController {

    /**
     * <div>creates a camp.</div>
     * <div>Only staff can do create a camp.</div>
     * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
     * @param name
     * @param startDate
     * @param endDate can be same as startDate to indicate a day camp
     * @param regCloseDate cannot be after startDate
     * @param openToFaculty which school this camp is for, can be for whole NTU
     * @param location
     * @param totalSlots both attendees and committee members
     * @param commSlots committee member slots
     * @param description
     * @param visibleToStudents whether the camp can be seen by students/accepting signups
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
     * <div>Edits a camp after creation</div>
     * <div>Only staff can edit a camp.</div>
     * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
     * <div>Checks if a camp already has signups. If yes, the camp cannot be set to invisible or turned off</div>
     * @param name
     * @param startDate
     * @param endDate can be same as startDate to indicate a day camp
     * @param regCloseDate cannot be after startDate
     * @param openToFaculty which school this camp is for, can be for whole NTU
     * @param location
     * @param totalSlots both attendees and committee members
     * @param commSlots committee member slots
     * @param description
     * @param visibleToStudents whether the camp can be seen by students/accepting signups
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
        camp.setVisibleToStudents(visibleToStudents);
    }

    /**
     * <div>Deletes a camp.</div>
     * <div>Only staff can delete a camp</div>
     * <div>Checks if a camp already has signups. If yes, the camp cannot be deleted</div>
     * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
     * @param deletingCamp the camp to be deleted
     * @throws Exception when the camp already has signups
     * @throws Exception when camp to be deleted cannot be found
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
     * Gets the list of camps the staff has created.
     * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
     * @return camps {@link java.util.ArrayList}
     * @throws Exception when the current user is not a staff
     */
    public static ArrayList<Camp> getCreatedCamps() throws Exception {
        UserController.assertUserType(Staff.class);
        Staff staff = (Staff) SessionInfo.getUser();
        ArrayList<Camp> createdCamps = staff.getCreatedCamps();
        return createdCamps;
    }

    /**
     * Gets list of camps available camps according to user type.
     * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
     * Staff can see all the camps in the system.
     * <p>
     * CommitteeMembers and Students can only see if <br>
     * 1) Its visibility is turned on for students <br> 
     * 2) Today's date is before the registration close date <br>
     * 3) The camp is meant for his/her faculty or open to the whole school
     * </p>
     * @return camps {@link java.util.ArrayList}
     */
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

    /**
     * Gets list of camps a student or committee member has signed up.
     * <div> If he/she is a commitee member, add the camp he/she is in-charge-of to the list</div>
     * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
     * @return camps {@link java.util.ArrayList}
     */
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

    /**
     * Removes an attendee from list of attendees of a {@link Camp}
     * <div> Also adds the attendee to a blacklist so he/she will not be able to register for this camp again</div>
     * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
     * @param camp the camp he/she is withdrawing from
     */
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

    /**
     * Registers an attendee for a {@link Camp} by adding current {@link User} to the list
     * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
     * <div> Student or commitee member can only register if</div>
     * <ol>
     * <li> He/she is not in the blacklist, ie have not withdrew from this camp before
     * <li> He/she is not already an attendee
     * <li> He/she is not already a committee member
     * <li> This {@link Camp}'s start and end date do not overlap with any of his/her existing camps
     * </ol>
     * @param camp
     * @throws Exception when he/she is in blacklist
     * @throws Exception when he/she is already an attendee
     * @throws Exception when he/she is already a commitee
     * @throws Exception when the dates clashes
     */
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

/**
 * Registers a committee member for a {@link Camp}
 * <div> Interfaces with {@link DataController} which stores all the run-time data.</div>
 * <div>can only register if</div>
 * <ol>
 * <li> He/she is not in the blacklist, ie have not withdrew from this camp before
 * <li> He/she is not already an attendee
 * <li> He/she is not already a committee member
 * <li> This {@link Camp}'s start and end date do not overlap with any of his/her existing camps
 * </ol>
 * <div> Also converts him/her from a {@link Student} to a {@link CommitteeMember}</div>
 * @param camp
 * @throws Exception when he/she is in blacklist
 * @throws Exception when he/she is already an attendee
 * @throws Exception when he/she is already a commitee
 * @throws Exception when the dates clashes
 */
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
