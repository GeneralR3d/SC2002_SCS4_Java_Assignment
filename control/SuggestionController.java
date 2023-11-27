package control;

import java.util.ArrayList;
import app.SessionInfo;
import entity.Camp;
import entity.CommitteeMember;
import entity.Suggestion;
import entity.User;

/**
 * Static control class for interfacing with all UI classes and handling
 * requests regarding{@link Suggestion}
 * <p>
 * These include:<br>
 * 1) Creation, edit and deletion of {@link Suggestion}<br>
 * 2) Returning {@link java.util.ArrayList} of suggestions<br>
 * 3) Approving or rejecting {@link Suggestion} by {@link entity.Staff}
 * </p>
 */
public class SuggestionController {

  /**
   * Creates a {@link Suggestion} for a {@link Camp}
   * Only {@link CommitteeMember} can create suggestion
   * 
   * @param camp
   * @param content of the suggestion
   */
  public static void post(Camp camp, String content) {
    User user = SessionInfo.getUser();
    CommitteeMember commMember = (CommitteeMember) user;
    Suggestion suggestion = new Suggestion(commMember, content);
    camp.addSuggestion(suggestion);
    commMember.addPoint();
  }

  /**
   * Deletes the suggestion if not processed ie, {@code APPROVED} or
   * {@code REJECTED}
   * 
   * @param camp
   * @param suggestion
   * @throws Exception if the suggestion has already been processed
   */
  public static void delete(Camp camp, Suggestion suggestion) throws Exception {
    if (suggestion.isProcessed()) {
      throw new Exception("Suggestion has already been processed!");
    }
    camp.removeSuggestion(suggestion);
    User user = SessionInfo.getUser();
    CommitteeMember commMember = (CommitteeMember) user;
    commMember.deductPoint();
  }

  /**
   * Edits a suggestion if not processed ie, {@code APPROVED} or {@code REJECTED}
   * 
   * @param suggestion
   * @param content    new content as {@link String}
   * @throws Exception if the suggestion has already been processed
   */
  public static void edit(Suggestion suggestion, String content) throws Exception {
    if (suggestion.isProcessed()) {
      throw new Exception("Suggestion has already been processed!");
    }
    suggestion.edit(content);
  }

  /**
   * Allows {@link entity.Staff} to approve a {@link Suggestion} made by a
   * {@link CommitteeMember}
   * 
   * @param suggestion
   * @throws Exception if the suggestion has already been processed
   */
  public static void approve(Suggestion suggestion) throws Exception {
    if (suggestion.isProcessed()) {
      throw new Exception("Suggestion has already been processed!");
    }
    suggestion.approve();
    suggestion.getOwner().addPoint();
  }

  /**
   * Allows {@link entity.Staff} to reject a {@link Suggestion} made by a
   * {@link CommitteeMember}
   * 
   * @param suggestion
   * @throws Exception if the suggestion has already been processed
   */
  public static void reject(Suggestion suggestion) throws Exception {
    if (suggestion.isProcessed()) {
      throw new Exception("Suggestion has already been processed!");
    }
    suggestion.reject();
  }

  /**
   * Returns a list of {@link entity.Suggestion} which the current
   * {@link CommitteeMember} is the owner of
   * 
   * @param camp
   * @return suggestions of type {@link java.util.ArrayList}
   */
  public static ArrayList<Suggestion> getMySuggestions(Camp camp) {
    CommitteeMember commMember = (CommitteeMember) SessionInfo.getUser();
    ArrayList<Suggestion> suggestions = camp.getSuggestions();
    ArrayList<Suggestion> mySuggestions = new ArrayList<Suggestion>();
    for (Suggestion suggestion : suggestions) {
      if (suggestion.getOwner().equals(commMember)) {
        mySuggestions.add(suggestion);
      }
    }
    return mySuggestions;
  }

}
