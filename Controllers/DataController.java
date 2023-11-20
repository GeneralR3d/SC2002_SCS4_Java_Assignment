package Controllers;
import java.util.ArrayList;
import Entity.*;

public class DataController {
  private ArrayList<Camp> camps;
  private ArrayList<Student> students;
  private ArrayList<Staff> staffs;
  private ArrayList<CommitteeMember> commMembers;

  public void addCamp(Camp camp) {
    camps.add(camp);
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  public void addStaff(Staff staff) {
    staffs.add(staff);
  }

  public void addCommMember(CommitteeMember commMember) {
    commMembers.add(commMember);
  }

  public void removeCamp(Camp camp) {
    camps.remove(camp);
  }

  public void removeStudent(Student student) {
    students.remove(student);
  }

  public void removeStaff(Staff staff) {
    staffs.remove(staff);
  }

  public void removeCommMember(CommitteeMember commMember) {
    commMembers.remove(commMember);
  }

  public Camp findCamp(int campID) {
    return camps.get(campID);
  }

  public Student findStudent(int userID) {
    return students.get(userID);
  }

  public Staff findStaff(int userID) {
    return staffs.get(userID);
  }

  public CommitteeMember findCommMember(int userID) {
    return commMembers.get(userID);
  }

}
