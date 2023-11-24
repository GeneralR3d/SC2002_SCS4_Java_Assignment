package entity;

public class CommitteeMember extends Student {

	private Camp commiteeMemberFor;
	private int points = 0;


	/**
	 * @return Camp
	 */
	public Camp getCommiteeMemberFor() {
		return this.commiteeMemberFor;
	}

	public void setCommiteeMemberFor(Camp commiteeMemberFor) {
		this.commiteeMemberFor = commiteeMemberFor;
	}

	public int getPoints() {
		return this.points;
	}


	/**
	 *
	 * @param userID
	 * @param faculty
	 * @param pw
	 */
	public CommitteeMember(String name, String userID, Faculty faculty, String pw, Camp committeeMemberFor) {
		super(name, userID, faculty, pw);
		this.commiteeMemberFor = committeeMemberFor;
	}

	public void addPoint() {
		points++;
	}

}
