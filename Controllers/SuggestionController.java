package Controllers;

import Entity.Camp;
import Entity.CommitteeMember;
import Entity.Suggestion;
import Entity.User;

public class SuggestionController {

  public static void post(User user, Camp camp, String content) {
    if (!(user instanceof CommitteeMember)) {
      // call boundary class
      System.out.println("You are not a committee member");
      return;
    }
    CommitteeMember commMember = (CommitteeMember) user;
    Suggestion suggestion = new Suggestion(commMember, content);
    camp.addSuggestion(suggestion);
    // call boundary class
    System.out.println("Suggestion has been posted");
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