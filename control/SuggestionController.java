package control;

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
  public static void post(User user, Camp camp, String content) {
    CommitteeMember commMember = (CommitteeMember) user;
    Suggestion suggestion = new Suggestion(commMember, content);
    camp.addSuggestion(suggestion);
    commMember.addPoint();
  }

  public static void delete(User user, Camp camp, Suggestion suggestion) {
    if (suggestion.isProcessed()) {
      // call boundary class
      return;
    }
    camp.removeSuggestion(suggestion);
    // call boundary class
  }

  public static void edit(User user, Suggestion suggestion, String content) {
    if (suggestion.isProcessed()) {
      // call boundary class
      return;
    }
    suggestion.edit(content);
    // call boundary class
  }

  public static void approve(User user, Camp camp, Suggestion suggestion) {
    suggestion.approve();
    suggestion.getOwner().addPoint();
  }

  public static void reject(User user, Suggestion suggestion) {
    suggestion.reject();
  }

}
