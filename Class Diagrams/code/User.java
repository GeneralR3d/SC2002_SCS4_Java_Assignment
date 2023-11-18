public abstract class User {

	private String userID;
	private Faculty faculty;
	private String pw = "password";

	public String getUserID() {
		return this.userID;
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	/**
	 *
	 * @param userID
	 * @param faculty
	 * @param pw
	 */
	public User(String userID, Faculty faculty, String pw) {
		this.userID = userID;
		this.faculty = faculty;
		this.pw = pw;
	}

	/**
		 *
		 * @param userID
		 * @param faculty
		 */
	public User(String userID, Faculty faculty) {
		this.userID = userID;
		this.faculty = faculty;
	}

	/**
	 *
	 * @param userID
	 * @param pw
	 */
	public boolean signIn(String userID, String pw) {
		return userID.equals(this.userID) && pw.equals(this.pw);
	};

	/**
	 *
	 * @param pw
	 */
	public void changePw(String pw) {
		this.pw = pw;
	}

	/**
	 *
	 * @param pw
	 */
	public boolean verifyPassword(String pw) {
		return pw.equals(this.pw);
	}
}
