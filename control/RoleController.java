package control;

import app.SessionInfo;
import entity.Camp;
import entity.CommitteeMember;
import entity.Student;

/**
 * Static class which handles the conversion of {@link entity.User} type when {@link Student} becomes a {@link CommitteeMember} and vice versa
 */
public class RoleController {

  /**
   * Converts {@link Student} to {@link CommitteeMember} when {@link Student} registers for a {@link Camp} as {@link CommitteeMember}
   * @param student
   * @param camp
   */
  public static void studentToCommittee(Student student, Camp camp) {
    CommitteeMember committee = new CommitteeMember(student.getName(), student.getUserID(), student.getFaculty(),
        student.getPassword(), camp, student.getSignedUpCamps());
    DataController.addCommMember(committee);
    DataController.removeStudent(student);
    SessionInfo.setUser(committee);
  };

    /**
   * Converts {@link CommitteeMember} to {@link Student}
   * @param committee
   * @deprecated not used since Committee is not allowed to withdraw from a camp once registered
   */
  public static void committeeToStudent(CommitteeMember committee) {
    Student student = new Student(committee.getName(), committee.getUserID(), committee.getFaculty());
    DataController.addStudent(student);
    DataController.removeCommMember(committee);
    SessionInfo.setUser(student);
  };

}
