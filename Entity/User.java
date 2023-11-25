package entity;

/**
 * An abstract class which represents a user of the CAMS system
 * The default password is "password"
 */
public abstract class User {

	private String name;
	private String userID;
	private Faculty faculty;
	private String password = "password";

	/**
	 * Accessor
	 * @return String the name of the user
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * accessor
	 * @return userID
	 */
	public String getUserID() {
		return this.userID;
	}

	/**
	 * accessor
	 * @return faculty of user
	 */
	public Faculty getFaculty() {
		return this.faculty;
	}

	/**
	 * Accessor
	 * @return passowrd of user
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Mutator
	 * @param faculty the school which the user belongs to
	 */
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	/**
	 * constructor
	 * @param name
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
	 * Constructor
	 * @param name
	 * @param userID
	 * @param faculty
	 */
	public User(String name, String userID, Faculty faculty) {
		this.name = name;
		this.userID = userID;
		this.faculty = faculty;
	}

	/**
	 * Changes the user's password
	 * @param password
	 */
	public void changepassword(String password) {
		this.password = password;
	}

	/**
	 * Verifies the password entered against the password attribute of the user
	 * @param password
	 * @return boolean true if the password is the same
	 */
	public boolean verifyPassword(String password) {
		return password.equals(this.password);
	}
}
