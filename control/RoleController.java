package control;

import app.SessionInfo;
import entity.*;

public class RoleController {

  public static void studentToCommittee(Student student, Camp camp) {
    CommitteeMember committee = new CommitteeMember(student.getName(), student.getUserID(), student.getFaculty(), student.getPassword(), camp);
    DataController.addCommMember(committee);
    DataController.removeStudent(student);
    SessionInfo.setUser(committee);
  };

  public static void CommitteeToStudent(CommitteeMember committee) {
    Student student = new Student(committee.getName(), committee.getUserID(), committee.getFaculty());
    DataController.addStudent(student);
    DataController.removeCommMember(committee);
    SessionInfo.setUser(student);
  };

}
