package control;

import app.SessionInfo;
import entity.Camp;
import entity.CommitteeMember;
import entity.Student;


public class RoleController {

  public static void studentToCommittee(Student student, Camp camp) {
    CommitteeMember committee = new CommitteeMember(student.getName(), student.getUserID(), student.getFaculty(),
        student.getPassword(), camp, student.getSignedUpCamps());
    DataController.addCommMember(committee);
    DataController.removeStudent(student);
    SessionInfo.setUser(committee);
  };

  public static void committeeToStudent(CommitteeMember committee) {
    Student student = new Student(committee.getName(), committee.getUserID(), committee.getFaculty());
    DataController.addStudent(student);
    DataController.removeCommMember(committee);
    SessionInfo.setUser(student);
  };

}
