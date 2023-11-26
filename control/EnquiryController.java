package control;

import java.util.ArrayList;

import app.SessionInfo;
import entity.Camp;
import entity.CommitteeMember;
import entity.Enquiry;
import entity.Reply;
import entity.Staff;
import entity.Student;
import entity.User;

public class EnquiryController {

  /**
   * @param camp
   * @param content
   */
  public static void post(Camp camp, String content) throws Exception {
    if (SessionInfo.getUserType().equals("CommitteeMember")) {
      CommitteeMember commMember = (CommitteeMember) SessionInfo.getUser();
      if (camp.equals(commMember.getCommiteeMemberFor())) {
        throw new Exception("You cannot post an enquiry as you are a committee member for this camp!");
      }
    }
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
  }

  public static void edit(Enquiry enquiry, String content) throws Exception {
    if (enquiry.isProcessed()) {
      throw new Exception("Enquiry has been processed and cannot be edited.");
    }
    enquiry.edit(content);
  }

  public static void addReply(Camp camp, Enquiry enquiry, String content) throws Exception {
    User user = SessionInfo.getUser();
    if (!(user instanceof CommitteeMember) && !(user instanceof Staff)) {
      throw new Exception("You are not a committee member or a staff");
    }
    if (user instanceof CommitteeMember) {

      CommitteeMember commMember = (CommitteeMember) user;
      if (!camp.equals(commMember.getCommiteeMemberFor())) {
        throw new Exception("You are not a committee member for this camp");
      }

      String userID = user.getUserID();

      // check if commMember is enquiry owner
      if (userID.equals(enquiry.getOwner().getUserID()))
        throw new Exception("You cannot reply to your own enquiry!");

      Reply reply = new Reply(userID, content);
      enquiry.addReply(reply);

      ArrayList<Reply> enquiryReplies = enquiry.getReplies();
      // check if commMember already replied, only first reply add points
      for (Reply enquiryReply : enquiryReplies) {
        if (user.getUserID().equals(enquiryReply.getOwnerID()))
          return;
      }
      commMember.addPoint();
    }
    if (user instanceof Staff) {
      String userID = user.getUserID();
      Reply reply = new Reply(userID, content);
      enquiry.addReply(reply);
    }
  }

  public static ArrayList<Enquiry> getUserEnquiries(Camp camp) {
    User user = SessionInfo.getUser();
    ArrayList<Enquiry> userEnquiries = new ArrayList<Enquiry>();
    ArrayList<Enquiry> campEnquiries = camp.getEnquiries();
    for (int i = 0; i < campEnquiries.size(); i++) {
      Enquiry enquiry = campEnquiries.get(i);
      if (user.equals(enquiry.getOwner()))
        userEnquiries.add(enquiry);
    }
    if (campEnquiries.size() == 0) {
      return null;
    }
    return userEnquiries;
  }

  public static ArrayList<Enquiry> getAllEnquiries(Camp camp) {
    ArrayList<Enquiry> campEnquiries = camp.getEnquiries();
    return campEnquiries;
  }

}
