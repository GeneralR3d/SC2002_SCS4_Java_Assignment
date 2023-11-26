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

/**
 * Static control class for interfacing with the UI classes to handle all requests regarding {@link Enquiry}.
 * <p>
 * These include:<br>
 * 1) Creation, edit and deletion of {@link Enquiry}<br>
 * 2) Returning {@link java.util.ArrayList} of enquiries<br>
 * </p>
 */
public class EnquiryController {

  /**
   * Creates an {@link Enquiry} for a {@link Camp}
   * @param camp
   * @param content of the enquiry
   * @throws Exception when a committee member tries to add an enquiry to a camp he/she is already in-charge-of
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

  /**
   * Deletes an enquiry if it has not been replied to
   * @param camp
   * @param enquiry
   * @throws Exception if that {@link Enquiry} already has a reply by {@link Staff} or {@link CommitteeMember}
   */
  public static void delete(Camp camp, Enquiry enquiry) throws Exception {
    if (enquiry.isProcessed()) {
      throw new Exception("Enquiry has been processed and cannot be deleted.");
    }
    camp.removeEnquiry(enquiry);
  }

    /**
   * Edits an enquiry if it has not been replied to
   * @param content
   * @param enquiry
   * @throws Exception if that {@link Enquiry} already has a reply by {@link Staff} or {@link CommitteeMember}
   */
  public static void edit(Enquiry enquiry, String content) throws Exception {
    if (enquiry.isProcessed()) {
      throw new Exception("Enquiry has been processed and cannot be edited.");
    }
    enquiry.edit(content);
  }

  /**
   * Adds a {@link Reply} to an {@link Enquiry}
   * <div> Only {@link CommitteeMember} or {@link Staff} is allowed, {@link Student} cannot reply to enquiries</div>
   * <div> {@link CommitteeMember} can only reply to enquiries of the {@link Camp} he/she is in-charge-of</div>
   * <div> {@link CommitteeMember} cannot reply to his/her own {@link Enquiry}</div>
   * @param camp
   * @param enquiry
   * @param content
   * @throws Exception if not {@link CommitteeMember} or {@link Staff}
   * @throws Exception if {@link CommitteeMember} tries to reply to an {@link Enquiry} under a camp which he/she is not in-charge-of
   * @throws Exception if {@link CommitteeMember} tries to reply to an {@link Enquiry} which he/she is the owner of
   */
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

   /**
   * Returns an {@link java.util.ArrayList} of {@link Enquiry} for a {@link Camp} which the current {@link User} is the owner of
   * @param camp
   * @return enquiry {@link java.util.ArrayList}
   */
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

    /**
   * Returns an {@link java.util.ArrayList} of {@link Enquiry} for a {@link Camp}
   * @param camp
   * @return enquiry {@link java.util.ArrayList}
   */
  public static ArrayList<Enquiry> getAllEnquiries(Camp camp) {
    ArrayList<Enquiry> campEnquiries = camp.getEnquiries();
    return campEnquiries;
  }

}
