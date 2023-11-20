package Controllers;
import java.util.ArrayList;
import Entity.*;

public class DataController {
  private static ArrayList<Camp> camps;
  private static ArrayList<Student> students;
  private static ArrayList<Staff> staffs;
  private static ArrayList<CommitteeMember> commMembers;

  public static ArrayList<Camp> getCamps() {
    return camps;
  }

  public static ArrayList<Student> getStudents() {
    return students;
  }

  public static ArrayList<Staff> getStaffs() {
    return staffs;
  }

  public static ArrayList<CommitteeMember> getCommMembers() {
    return commMembers;
  }

  public static void addCamp(Camp camp) {
    camps.add(camp);
  }

  public static void addStudent(Student student) {
    students.add(student);
  }

  public static void addStaff(Staff staff) {
    staffs.add(staff);
  }

  public static void addCommMember(CommitteeMember commMember) {
    commMembers.add(commMember);
  }

  public static void removeCamp(Camp camp) {
    camps.remove(camp);
  }

  public static void removeStudent(Student student) {
    students.remove(student);
  }

  public static void removeStaff(Staff staff) {
    staffs.remove(staff);
  }

  public static void removeCommMember(CommitteeMember commMember) {
    commMembers.remove(commMember);
  }

  public static Camp findCamp(int campID) {
    for (int i = 0; i < camps.size(); i++) {
      Camp camp = camps.get(i);
      if (campID == camp.getCampID()) return camp;
    }
    return null;
  }

  public static Student findStudent(String userID) {
    for (int i = 0; i < students.size(); i++) {
      Student student = students.get(i);
      if (userID.matches(student.getUserID())) return student;
    }
    return null;
  }

  public static Staff findStaff(String userID) {
    for (int i = 0; i < students.size(); i++) {
      Staff staff = staffs.get(i);
      if (userID.matches(staff.getUserID())) return staff;
    }
    return null;
  }

  public static CommitteeMember findCommMember(String userID) {
    for (int i = 0; i < students.size(); i++) {
      CommitteeMember commMember = commMembers.get(i);
      if (userID.matches(commMember.getUserID())) return commMember;
    }
    return null;
  }

}
