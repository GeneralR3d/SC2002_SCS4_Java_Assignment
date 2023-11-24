package control;

import java.util.ArrayList;

import app.SessionInfo;
import entity.*;

public class EnquiryController {


  /**
   * @param camp
   * @param content
   */
  public static void post(Camp camp, String content) {
    User user = SessionInfo.getUser();
    Student student = (Student) user;
    Enquiry enquiry = new Enquiry(student, content);
    camp.addEnquiry(enquiry);
  }

  public static void delete(Camp camp, Enquiry enquiry) throws Exception {
    if (enquiry.isProcessed()) {
      throw new Exception("Enquiry has been processed and cannot be deleted.");
    }
    camp.removeEnquiry(enquiry);
    // call boundary class
    System.out.println("Enquiry has been deleted");
  }

  public static void edit(Enquiry enquiry, String content) throws Exception {
    if (enquiry.isProcessed()) {
      // call boundary class
      throw new Exception("Enquiry has been processed and cannot be edited.");
    }
    enquiry.edit(content);
  }

  public static void addReply(Camp camp, Enquiry enquiry, String content) {
    User user = SessionInfo.getUser();
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

  public static ArrayList<Enquiry> getUserEnquiries(Camp camp) {
    User user = SessionInfo.getUser();
    ArrayList<Enquiry> userEnquiries = new ArrayList<Enquiry>();
    ArrayList<Enquiry> campEnquiries = camp.getEnquiries();
    for (int i = 0; i < campEnquiries.size(); i++) {
      Enquiry enquiry = campEnquiries.get(i);
      if (user.equals(enquiry.getOwner())) userEnquiries.add(enquiry);
    }
    if (campEnquiries.size() == 0) {
      return null;
    }
    return userEnquiries;
  }

  public static ArrayList<Enquiry> getAllEnquiries(Camp camp) {
    return camp.getEnquiries();
  }

}
