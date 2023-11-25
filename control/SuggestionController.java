package control;

import java.util.ArrayList;
import app.SessionInfo;
import entity.Camp;
import entity.CommitteeMember;
import entity.Suggestion;
import entity.User;

public class SuggestionController {

  /**
   * @param user
   * @param camp
   * @param content
   */
  public static void post(Camp camp, String content) {
    User user = SessionInfo.getUser();
    CommitteeMember commMember = (CommitteeMember) user;
    Suggestion suggestion = new Suggestion(commMember, content);
    camp.addSuggestion(suggestion);
    commMember.addPoint();
  }

  public static void delete(Camp camp, Suggestion suggestion) throws Exception {
    if (suggestion.isProcessed()) {
      throw new Exception("Suggestion has already been processed!");
    }
    camp.removeSuggestion(suggestion);
  }

  public static void edit(Suggestion suggestion, String content) throws Exception {
    if (suggestion.isProcessed()) {
      throw new Exception("Suggestion has already been processed!");
    }
    suggestion.edit(content);
  }

  public static void approve(Suggestion suggestion) throws Exception {
    if (suggestion.isProcessed()) {
      throw new Exception("Suggestion has already been processed!");
    }
    suggestion.approve();
    suggestion.getOwner().addPoint();
  }

  public static void reject(Suggestion suggestion) throws Exception {
    if (suggestion.isProcessed()) {
      throw new Exception("Suggestion has already been processed!");
    }
    suggestion.reject();
  }

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
