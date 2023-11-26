package entity;

import java.util.ArrayList;

/**
 * Represents a student who is a sub-class of {@link User}
 * <div>Has a {@link java.util.ArrayList} to store all the camps they have signed up
 * <div>for, both as attendee and committee member
 */
public class Student extends User {

	private ArrayList<Camp> signedUpCamps;

	/**
	 * Constructor
	 *
	 * @param name
	 * @param userID
	 * @param faculty       of type {@link Faculty}
	 * @param pw            password
	 * @param signedUpCamps of type {@link ArrayList}
	 */
	public Student(String name, String userID, Faculty faculty, String pw, ArrayList<Camp> signedUpCamps) {
		super(name, userID, faculty, pw);
		this.signedUpCamps = new ArrayList<Camp>();
		this.signedUpCamps = signedUpCamps;
	}

	/**
	 * Constructor
	 *
	 * @param name
	 * @param userID
	 * @param faculty of type {@link Faculty}
	 */
	public Student(String name, String userID, Faculty faculty) {
		super(name, userID, faculty);
		signedUpCamps = new ArrayList<Camp>();
	}

	/**
	 * Adds that camp to a list of camps the student is registered for
	 *
	 * @param camp
	 */
	public void register(Camp camp) {
		signedUpCamps.add(camp);
	}

	/**
	 * Removes that camp from a list of camps the student is registered for
	 *
	 * @param camp
	 */
	public void withdraw(Camp camp) {
		signedUpCamps.remove(camp);
	}

	/**
	 * Returns a list of camps the student is registered for
	 *
	 * @return ArrayList<Camp>
	 */
	public ArrayList<Camp> getSignedUpCamps() {
		return signedUpCamps;
	}

}
