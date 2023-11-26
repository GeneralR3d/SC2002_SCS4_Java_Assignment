package entity;

import java.util.ArrayList;

/**
 * Represents a committee member who is a sub-class of a student.
 * <div>Can only be commitee member of one {@link Camp}.</div>
 * <div>Has points depending on the number of enquries answered and suggestions posted.</div>
 */

public class CommitteeMember extends Student {

	private Camp commiteeMemberFor;
	private int points = 0;

	/**
	 * Accessor
	 *
	 * @return Camp the camp which he/she is in-charge of
	 */
	public Camp getCommiteeMemberFor() {
		return this.commiteeMemberFor;
	}

	/**
	 * Accessor
	 *
	 * @return int the number of points he/she has
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Constructor for committee member
	 *
	 * @param userID
	 * @param faculty            of type {@link Faculty}
	 * @param pw                 password
	 * @param committeeMemberFor of type {@link Camp}
	 * @param signedUpCamps      of type {@link ArrayList}
	 */
	public CommitteeMember(String name, String userID, Faculty faculty, String pw, Camp committeeMemberFor,
			ArrayList<Camp> signedUpCamps) {
		super(name, userID, faculty, pw, signedUpCamps);
		this.commiteeMemberFor = committeeMemberFor;
	}

	/**
	 * Adds a point because he/she answered an enquiry or posted a suggestion
	 */
	public void addPoint() {
		points++;
	}

	/**
	 * Removes a point because he/she deleted a suggestion
	 */
	public void deductPoint() {
		if (points == 0)
			return;
		points--;
	}

}
