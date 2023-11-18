public class CommitteeMember extends Student {

	private Camp commiteeMemberFor;
	private int points = 0;

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
	public CommitteeMember(String userID, Faculty faculty, String pw, Camp committeeMemberFor) {
		super(userID, faculty, pw);
		this.commiteeMemberFor = committeeMemberFor;
	}

	public void addPoint() {
		points++;
	}

}
