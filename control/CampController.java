package control;

import java.util.ArrayList;
import java.util.Date;

import entity.*;

public class CampController {

    /**
     * @param newCamp
     */
    public static void addCamp(Camp newCamp) {
        DataController.addCamp(newCamp);
    }

    /**
     * @param camp
     */
    public static void editCamp(Camp camp) {
        // TODO: need set camps function
        ArrayList<Camp> campData = DataController.getCamps();
        // TODO: change staffUI
        int choice = boundary.StaffUI.menu_EditMyCamp();

        switch (choice) {
            case 1:
                // TODO: add setName in camp
                camp.setName();
                break;
        }
        //
    }

    /**
     * @param deletingCamp
     * @return boolean
     */
    public static void deleteCamp(Camp deletingCamp) throws Exception {
        if (!UserController.checkPermission(Staff.class.toString()))
            throw new Exception("Error: Current user is not allowed to run this operation.");
        ArrayList<Camp> allCamps = DataController.getCamps();

        // check if deletingcamp is in camps
        for (Camp camp : allCamps) {
            if (deletingCamp.getCampID() == camp.getCampID()) {
                DataController.removeCamp(deletingCamp);
            }
        }
        throw new Exception("Error: Camp to be deleted cannot be found.");
    }

    /**
     * @return ArrayList<Camp>
     * @throws Exception
     */
    public static ArrayList<Camp> getAllCamps() throws Exception {
        if (!UserController.checkPermission(Staff.class.toString()))
            throw new Exception("Error: Current user is not allowed to run this operation.");
        ArrayList<Camp> allCamps = DataController.getCamps();
        return allCamps;
    }

    /**
     * @return ArrayList<Camp>
     * @throws Exception
     */
    public static ArrayList<Camp> getCreatedCamps() throws Exception {
        if (!UserController.checkPermission(Staff.class.toString()))
            throw new Exception("Error: Current user is not allowed to run this operation.");
        ArrayList<Camp> allCamps = DataController.getCamps();
        ArrayList<Camp> createdCamps = new ArrayList<Camp>();
        for (int i = 0; i < allCamps.size(); i++) {
            Camp camp = allCamps.get(i);
            if (camp.getStaffInCharge().getUserID().equals(SessionInfo.user.getUserID()))
                createdCamps.add(camp);
        }
        return createdCamps;
    }

    public static ArrayList<Camp> getAvailableCamps() {
        ArrayList<Camp> campData = DataController.getCamps();
        ArrayList<Camp> availableCamps = new ArrayList<Camp>();
        Date today = new Date();

        // add committeememberfor camp and add as first in result
        switch (SessionInfo.userType) {
            case "Student":
                for (Camp currCamp : campData) {
                    // check for RegCloseDate
                    // check visibility and faculty of camp
                    // TODO: update enum of 'NTU'
                    if (currCamp.isVisibleToStudents() &&
                            (currCamp.getOpenToFaculty() == Faculty.NTU
                                    || currCamp.getOpenToFaculty() == SessionInfo.user.getFaculty())
                            &&
                            !today.after(currCamp.getRegCloseDate())) {
                        availableCamps.add(currCamp);
                    }
                }
                break;
            case "CommitteeMember":
                CommitteeMember currUser = (CommitteeMember) SessionInfo.user;
                for (Camp currCamp : campData) {
                    // check for RegCloseDate
                    // check visibility and faculty of camp
                    // TODO: update enum of 'NTU'
                    if (currCamp.isVisibleToStudents() &&
                            (currCamp.getOpenToFaculty() == Faculty.NTU
                                    || currCamp.getOpenToFaculty() == SessionInfo.user.getFaculty())
                            &&
                            !today.after(currCamp.getRegCloseDate())) {
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
        switch (SessionInfo.userType) {
            case "Student":
                for (Camp camp : ((Student) SessionInfo.user).getSignedUpCamps())
                    registeredCamps.add(camp);
                break;
            case "CommitteeMember":
                // add first camp as committeemember camp
                registeredCamps.add(((CommitteeMember) SessionInfo.user).getCommiteeMemberFor());
                for (Camp camp : ((CommitteeMember) SessionInfo.user).getSignedUpCamps())
                    registeredCamps.add(camp);
                break;
        }
        return registeredCamps;
    }

    public static boolean removeAttendee(Camp camp) {
        ArrayList<Student> attendees = camp.getAttendees();
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).getUserID() == SessionInfo.user.getUserID()) {
                // TODO: does not work! need dataController to have editCamps or make array of
                // camps public
                camp.removeAttendee(attendees.get(i));
                camp.setattendeeSlotsLeft(camp.getAttendeeSlotsLeft() + 1);
                return true;
            }
        }
        // user not found
        return false;
    }

    public static boolean registerAttendee(Camp camp) {
        ArrayList<Student> attendees = camp.getAttendees();
        // check for registration deadline
        // Date today = new Date();
        // if (today.after(camp.getRegCloseDate())) return false;

        // check if user already signed up
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).getUserID() == SessionInfo.user.getUserID()) {
                return false;
            }
        }
        // add into camps
        attendees.add((Student) SessionInfo.user);
        // TODO: does not work! need dataController to have editCamps or make array of
        // camps public
        camp.addAttendee((Student) SessionInfo.user);
        return true;
    }

    public static boolean registerCommittee(Camp camp) {
        ArrayList<CommitteeMember> committeeMembers = camp.getCommittee();
        // check for registration deadline
        // Date today = new Date();
        // if (today.after(camp.getRegCloseDate())) return false;

        // check if already committee member
        for (int i = 0; i < committeeMembers.size(); i++) {
            if (committeeMembers.get(i).getUserID() == SessionInfo.user.getUserID()) {
                return false;
            }
        }

        User currUser = SessionInfo.user;
        // TODO: add getpw() in entity
        CommitteeMember newCommMember = new CommitteeMember(currUser.getName(), currUser.getUserID(),
                currUser.getFaculty(), currUser.getPW(), camp);
        // TODO: does not work! need dataController to have editCamps or make array of
        // camps public
        DataController.addCommMember(newCommMember);
        camp.addCommittee(newCommMember);
        return true;
    }
}
