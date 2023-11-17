public abstract class User {

	private String userID;
	private String faculty;
	private String pw = pasword;

	public String getUserID() {
		return this.userID;
	}

	public String getFaculty() {
		return this.faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	/**
	 * 
	 * @param userID
	 * @param faculty
	 * @param pw
	 */
	public User(String userID, String faculty, String pw) {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

	public abstract boolean signIn();

	/**
	 * 
	 * @param pw
	 */
	public boolean changePw(String pw) {
		// TODO - implement User.changePw
		throw new UnsupportedOperationException();
	}

}