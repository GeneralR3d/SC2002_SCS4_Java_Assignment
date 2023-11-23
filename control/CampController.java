package control;

import java.util.ArrayList;
import java.util.Date;

import Entity.*;

public class CampController {
    
    /** 
     * @param newCamp
     */
    public static void addCamp(Camp newCamp) {
        DataController.addCamp(newCamp);
    }

    public static void editCamp(Camp camp){
        // TODO: need set camps function
        ArrayList<Camp> campData = DataController.getCamps();
        // TODO: change staffUI
        int choice = Boundary.StaffUI.menu_EditMyCamp();

        switch(choice){
            case 1:
                //TODO: add setName in camp
                camp.setName();
                break;                
        }
        //
    }

    public static boolean deleteCamp(Camp deletingCamp) {
        ArrayList<Camp> openCamps = DataController.getCamps();

        //check if deletingcamp is in camps
        for(Camp camp: openCamps){
            if (deletingCamp.getCampID() == camp.getCampID()){
                DataController.removeCamp(deletingCamp);
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Camp> getAvailableCamps(){
        ArrayList<Camp> campData = DataController.getCamps();
        ArrayList<Camp> availableCamps = new ArrayList<Camp>();
        Date today = new Date();

        // add committeememberfor camp and add as first in result
        switch(SessionInfo.userType){
            case "Student":
                for (Camp currCamp: campData){
                    //check for RegCloseDate
                    //check visibility and faculty of camp
                    //TODO: update enum of 'NTU'
                    if (currCamp.isVisibleToStudents() && 
                        (currCamp.getOpenToFaculty() == Faculty.NTU || currCamp.getOpenToFaculty() == SessionInfo.user.getFaculty()) &&
                        !today.after(currCamp.getRegCloseDate())){
                        availableCamps.add(currCamp);
                    }
                }
                break;
            case "CommitteeMember":
                CommitteeMember currUser = (CommitteeMember) SessionInfo.user;
                for (Camp currCamp: campData){
                    //check for RegCloseDate
                    //check visibility and faculty of camp
                    //TODO: update enum of 'NTU'
                    if (currCamp.isVisibleToStudents() && 
                        (currCamp.getOpenToFaculty() == Faculty.NTU || currCamp.getOpenToFaculty() == SessionInfo.user.getFaculty()) &&
                        !today.after(currCamp.getRegCloseDate())){
                        availableCamps.add(currCamp);
                    }
                }
                break;
            case "Staff":
                availableCamps = campData;
            }

        return availableCamps;
    }

    public static ArrayList<Camp> getSignedUpCamps(){
        ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
        switch (SessionInfo.userType){
            case "Student":
                for (Camp camp: ((Student)SessionInfo.user).getSignedUpCamps()) registeredCamps.add(camp);
                break;
            case "CommitteeMember":
                //add first camp as committeemember camp
                registeredCamps.add(((CommitteeMember) SessionInfo.user).getCommiteeMemberFor());
                for (Camp camp: ((CommitteeMember)SessionInfo.user).getSignedUpCamps()) registeredCamps.add(camp);
                break;
        }
        return registeredCamps;
    }

    public static boolean removeAttendee(Camp camp){
        ArrayList<Student> attendees = camp.getAttendees();
        for (int i=0; i<attendees.size(); i++){
            if (attendees.get(i).getUserID() == SessionInfo.user.getUserID()){
                //TODO: does not work! need dataController to have editCamps or make array of camps public
                camp.removeAttendee(attendees.get(i));
                camp.setattendeeSlotsLeft(camp.getAttendeeSlotsLeft()+1);
                return true;
            }
        }
        //user not found
        return false;
    }

    public static boolean registerAttendee(Camp camp){
        ArrayList<Student> attendees = camp.getAttendees();
        //check for registration deadline
        // Date today = new Date();
        // if (today.after(camp.getRegCloseDate())) return false;

        //check if user already signed up
        for (int i=0; i<attendees.size(); i++){
            if (attendees.get(i).getUserID() == SessionInfo.user.getUserID()){
                return false;
            }
        }
        //add into camps
        attendees.add((Student)SessionInfo.user);
        //TODO: does not work! need dataController to have editCamps or make array of camps public
        //camp.setAttendees(attendees);
        camp.setattendeeSlotsLeft(camp.getAttendeeSlotsLeft()-1);
        return true;
    }

    public static boolean registerCommittee(Camp camp){
        ArrayList<CommitteeMember> committeeMembers = camp.getCommittee();
        //check for registration deadline
        // Date today = new Date();
        // if (today.after(camp.getRegCloseDate())) return false;

        //check if already committee member
        for (int i=0; i<committeeMembers.size(); i++){
            if (committeeMembers.get(i).getUserID() == SessionInfo.user.getUserID()){
                return false;
            }
        }
        //TODO: does not work! need dataController to have editCamps or make array of camps public
        committeeMembers.add((CommitteeMember)SessionInfo.user);
        camp.setComSlotsLeft(camp.getCommSlotsLeft()-1);
        return true;
    }
}
