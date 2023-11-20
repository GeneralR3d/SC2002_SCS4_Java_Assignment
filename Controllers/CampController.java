package Controllers;

import Entity.*;
import java.util.ArrayList;

public class CampController {
    public static void addCamp(ArrayList<Camp> camps, Camp newCamp) {
        // TODO: use datacontroller to add to arr camp
        camps.add(newCamp);
    }

    public static void editCamp(Camp camp){
        // TODO: use datacontroller to get arr camp
        // ArrayList<Camp> campData = DataController.getcamps();
        //        
    }

    public static void deleteCamp(ArrayList<Camp> camps, Camp deletingCamp) {
        //use datacontoller to remove from camp arr
        camps.remove(deletingCamp.getCampID());
    }

    public static ArrayList<Camp> viewAvailableCamps(User currUser){
        // TODO: use datacontroller to get arr camp
        // ArrayList<Camp> campData = DataController.getcamps();
        ArrayList<Camp> availableCamps = new ArrayList<Camp>();

        // add committeememberfor camp and add as first in result
        if (currUser instanceof Student){
            for (Camp currCamp: campData){
                //check visibility and faculty of camp
                //update enum of 'NTU'
                if (currCamp.isVisibleToStudents() && (currCamp.getOpenToFaculty() == Faculty.NTU || currCamp.getOpenToFaculty() == currentUser.getFaculty())){
                    availableCamps.add(currCamp);
                }
            }
        }
        else if (currUser instanceof Staff){
            //staff can view all camps
            return campData;
        }
        return availableCamps;
    }

    public static ArrayList<Camp> viewSignedUpCamps(Student currStudent){
        ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
        if (currStudent instanceof CommitteeMember){
            CommitteeMember user = (CommitteeMember) currStudent;
            registeredCamps.add(user.getCommiteeMemberFor());
        }
        return registeredCamps;
    }

    public static void removeAttendee(Camp camp){
        ArrayList<Student> attendees = camp.getAttendees();
        for (int i=0; i<attendees.size(); i++){
            if (attendees.get(i).getUserID() == SessionInfo.user.getUserID()){
                camp.removeAttendee(i);
                break;
            }
        }
        camp.setTotalSlotsLeft(camp.getTotalSlotsLeft()+1);
    }

    public static boolean registerAttendee(Camp camp){
        ArrayList<Student> attendees = camp.getAttendees();
        for (int i=0; i<attendees.size(); i++){
            if (attendees.get(i).getUserID() == SessionInfo.user.getUserID()){
                return false;
            }
        }
        attendees.add((Student)SessionInfo.user);
        camp.setTotalSlotsLeft(camp.getTotalSlotsLeft()-1);
        return true;
    }

    public static boolean registerCommittee(Camp camp){
        ArrayList<CommitteeMember> committeeMembers = camp.getCommittee();
        for (int i=0; i<committeeMembers.size(); i++){
            if (committeeMembers.get(i).getUserID() == SessionInfo.user.getUserID()){
                return false;
            }
        }
        committeeMembers.add((CommitteeMember)SessionInfo.user);
        camp.setTotalSlotsLeft(camp.getTotalSlotsLeft()-1);
        return true;
    }
}
