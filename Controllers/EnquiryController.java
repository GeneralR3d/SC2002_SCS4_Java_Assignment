package Controllers;
import Entity.*;

public class EnquiryController {

  public static void post(User user, Camp camp, String content) {
    if (!(user instanceof Student)) {
      // call boundary class
      System.out.println("You are not a student");
      return;
    }
    Student student = (Student) user;
    Enquiry enquiry = new Enquiry(student, content);
    camp.addEnquiry(enquiry);
    // call boundary class
    System.out.println("Enquiry has been posted");
  }

  public static void delete(User user, Camp camp, Enquiry enquiry) {
    if (enquiry.isProcessed()) {
      // call boundary class
      System.out.println("Enquiry has been processed and cannot be deleted.");
      return;
    }
    camp.removeEnquiry(enquiry);
    // call boundary class
    System.out.println("Enquiry has been deleted");
  }

  public static void edit(User user, Enquiry enquiry, String content) {
    if (enquiry.isProcessed()) {
      // call boundary class
      System.out.println("Enquiry has been processed and cannot be edited.");
      return;
    }
    enquiry.edit(content);
    // call boundary class
    System.out.println("Enquiry has been edits");
  }

  public static void addReply(User user, Camp camp, Enquiry enquiry, String content) {
    if (!(user instanceof CommitteeMember) && !(user instanceof Staff)) {
      // call boundary class
      System.out.println("You are not a committee member or a staff");
      return;
    }
    if (user instanceof CommitteeMember) {
      CommitteeMember commMember = (CommitteeMember) user;
      if (!camp.equals(commMember.getCommiteeMemberFor())) {
        // call boundary class
        System.out.println("You are not a committee member for this camp");
      }
      String userID = user.getUserID();
      Reply reply = new Reply(userID, content);
      enquiry.addReply(reply);
      // call boundary class
      System.out.println("Reply has been posted");
      commMember.addPoint();
    }
    if (user instanceof Staff) {
      String userID = user.getUserID();
      Reply reply = new Reply(userID, content);
      enquiry.addReply(reply);
      // call boundary class
      System.out.println("Reply has been posted");
    }
  }

}
