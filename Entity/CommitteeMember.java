package entity;
/**
 * Represents a committee member who is a sub-class of a student.
 * Can only be commitee member of one {@link Camp}.
 * Has points depending on the number of enquries answered and suggestions posted.
 */

public class CommitteeMember extends Student {

	private Camp commiteeMemberFor;
	private int points = 0;

	/**
	 * Accessor
	 * @return Camp the camp which he/she is in-charge of
	 */
	public Camp getCommiteeMemberFor() {
		return this.commiteeMemberFor;
	}

	
	/** 
	 * Accessor
	 * @return int the number of points he/she has
	 */
	public int getPoints() {
		return this.points;
	}


	/**
	 *Constructor for committee member
	 * @param userID
	 * @param faculty of type {@link Faculty}
	 * @param pw password
	 */
	public CommitteeMember(String name, String userID, Faculty faculty, String pw, Camp committeeMemberFor) {
		super(name, userID, faculty, pw);
		this.commiteeMemberFor = committeeMemberFor;
	}

	/**
	 * Adds a point because he/she answered an enquiry or posted a suggestion
	 */
	public void addPoint() {
		points++;
	}

}
