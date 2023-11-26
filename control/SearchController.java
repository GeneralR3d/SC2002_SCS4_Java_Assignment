package control;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.Camp;
import entity.CommitteeMember;
import entity.Faculty;
import entity.Student;

/**
 * Static class for handling filtering of camps based on attributes like
 * <ul>
 * <li>campName
 * <li>startDate
 * <li>endDate
 * <li>faculty
 * <li>location
 * <li>attendeeName
 * <li>commMemberName
 * </ul>
 */
public class SearchController {
    /**
     * Filters all camps by campName and returns {@link java.util.ArrayList} of {@link Camp}s
     * <div>Returns empty {@link java.util.ArrayList} if no search results
     * @param campName
     * @return ArrayList<Camp> search result
     */
    public static ArrayList<Camp> searchByCampName(String campName) {
        ArrayList<Camp> campData = CampController.getAvailableCamps();
        ArrayList<Camp> result = new ArrayList<Camp>();
        for (Camp camp : campData) {
            if (camp.getName().equals(campName))
                result.add(camp);
        }

        return result;
    }

    /**
     * Filters all camps by startDate and returns {@link java.util.ArrayList} of {@link Camp}s
     * <div>Returns empty {@link java.util.ArrayList} if no search results
     * @param startDate of type {@link java.time.LocalDate}
     * @return ArrayList<Camp> search result
     */
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

    /**
     * Filters all camps by endDate and returns {@link java.util.ArrayList} of {@link Camp}s
     * <div>Returns empty {@link java.util.ArrayList} if no search results
     * @param endDate of type {@link java.time.LocalDate}
     * @return ArrayList<Camp> search result
     */
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

    /**
     * Filters all camps by {@link Faculty} and returns {@link java.util.ArrayList} of {@link Camp}s
     * <div>Returns empty {@link java.util.ArrayList} if no search results
     * @param faculty of type {@link Faculty}
     * @return ArrayList<Camp> search result
     */
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

    /**
     * Filters all camps by location and returns {@link java.util.ArrayList} of {@link Camp}s
     * <div>Returns empty {@link java.util.ArrayList} if no search results
     * @param location of type {@link String}
     * @return ArrayList<Camp> search result
     */
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

    /**
     * Filters all camps by attendeeName and returns {@link java.util.ArrayList} of {@link Camp}s
     * <div>Returns empty {@link java.util.ArrayList} if no search results
     * @param attendeeName of type {@link String}
     * @return ArrayList<Camp> search result
     */
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

    /**
     * Filters all camps by commMemberName and returns {@link java.util.ArrayList} of {@link Camp}s
     * <div>Returns empty {@link java.util.ArrayList} if no search results
     * @param commMemberName of type {@link String}
     * @return ArrayList<Camp> search result
     */
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

    /**
     * Helper method
    * Sorts in place, an {@link java.util.ArrayList} of {@link Camp} according alphabetical order
    * @param arr the {@link java.util.ArrayList} of {@link Camp} to be sorted
    * @param ArrayList<Camp> to be sorted
    */
    public static void sort(ArrayList<Camp> arr) {
        arr.sort((o1, o2)
                  -> o1.getName().compareTo(
                      o2.getName()));
    }
}
