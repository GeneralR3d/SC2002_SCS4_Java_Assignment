package entity;

public abstract class User {

	private String name;
	private String userID;
	private Faculty faculty;
	private String password = "password";

	/**
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	public String getUserID() {
		return this.userID;
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

	public String getPassword() {
		return this.password;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	/**
	 *
	 * @param userID
	 * @param faculty
	 * @param password
	 */
	public User(String name, String userID, Faculty faculty, String password) {
		this.name = name;
		this.userID = userID;
		this.faculty = faculty;
		this.password = password;
	}

	/**
	 *
	 * @param userID
	 * @param faculty
	 */
	public User(String name, String userID, Faculty faculty) {
		this.name = name;
		this.userID = userID;
		this.faculty = faculty;
	}

	/**
	 *
	 * @param userID
	 * @param password
	 */
	public boolean signIn(String userID, String password) {
		return userID.equals(this.userID) && password.equals(this.password);
	};

	/**
	 *
	 * @param password
	 */
	public void changepassword(String password) {
		this.password = password;
	}

	/**
	 *
	 * @param password
	 */
	public boolean verifyPassword(String password) {
		return password.equals(this.password);
	}
}
