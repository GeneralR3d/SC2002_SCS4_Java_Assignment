package control;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.Camp;
import entity.CommitteeMember;
import entity.Faculty;
import entity.Student;

public class SearchController {
    public static ArrayList<Camp> searchByCampName(String campName) {
        ArrayList<Camp> campData = CampController.getAvailableCamps();
        ArrayList<Camp> result = new ArrayList<Camp>();
        for (Camp camp : campData) {
            if (camp.getName().equals(campName))
                result.add(camp);
        }

        return result;
    }

    public static ArrayList<Camp> searchByStartDate(LocalDate startDate) {
        ArrayList<Camp> campData = CampController.getAvailableCamps();
        ArrayList<Camp> result = new ArrayList<Camp>();
        for (Camp camp : campData) {
            if (camp.getStartDate().isEqual(startDate))
                result.add(camp);
        }

        sort(result);
        return result;
    }

    public static ArrayList<Camp> searchByEndDate(LocalDate endDate) {
        ArrayList<Camp> campData = CampController.getAvailableCamps();
        ArrayList<Camp> result = new ArrayList<Camp>();
        for (Camp camp : campData) {
            if (camp.getEndDate().isEqual(endDate))
                result.add(camp);
        }

        sort(result);
        return result;
    }

    public static ArrayList<Camp> searchByFaculty(Faculty faculty) {
        ArrayList<Camp> campData = CampController.getAvailableCamps();
        ArrayList<Camp> result = new ArrayList<Camp>();
        for (Camp camp : campData) {
            if (camp.getOpenToFaculty() == faculty)
                result.add(camp);
        }

        sort(result);
        return result;
    }

    public static ArrayList<Camp> searchByLocation(String location) {
        ArrayList<Camp> campData = CampController.getAvailableCamps();
        ArrayList<Camp> result = new ArrayList<Camp>();
        for (Camp camp : campData) {
            if (camp.getLocation().equals(location))
                result.add(camp);
        }

        sort(result);
        return result;
    }

    public static ArrayList<Camp> searchByAttendee(String attendeeName) {
        ArrayList<Student> studentData = DataController.getStudents();
        ArrayList<Camp> result = new ArrayList<Camp>();
        for (Student student : studentData) {
            if (student.getName().equals(attendeeName)) {
                for (Camp camp : student.getSignedUpCamps()) {
                    result.add(camp);
                }
                break; // skip the rest of students as student found
            }
        }

        sort(result);
        return result;
    }

    public static ArrayList<Camp> searchByCommMember(String commMemberName) {
        ArrayList<CommitteeMember> commMemberData = DataController.getCommMembers();
        ArrayList<Camp> result = new ArrayList<Camp>();
        for (CommitteeMember commMember : commMemberData) {
            if (commMember.getName().equals(commMemberName)) {
                result.add(commMember.getCommiteeMemberFor());
                break; // skip rest of the commMembers as commMember found
            }
        }
        return result;
    }

    public static void sort(ArrayList<Camp> arr) {
        arr.sort((o1, o2)
                  -> o1.getName().compareTo(
                      o2.getName()));
    }
}
