package entity;

import java.util.ArrayList;

/**
 * Represents a staff who is a sub-class of {@link User}
 * Has a {@link java.util.ArrayList} to store all the camps they have created
 */
public class Staff extends User {

	ArrayList<Camp> createdCamps;

	/**
	 * Constructor
	 * @param name
	 * @param userID
	 * @param faculty of type {@link Faculty}
	 * @param pw password
	 */
	public Staff(String name, String userID, Faculty faculty, String pw) {
		super(name, userID, faculty, pw);
		createdCamps = new ArrayList<Camp>();
	}

	/**
	 * Constructor
	 * @param name
	 * @param userID
	 * @param faculty of type {@link Faculty}
	 */
	public Staff(String name, String userID, Faculty faculty) {
		super(name, userID, faculty);
		createdCamps = new ArrayList<Camp>();
	}

	/**
	 * Adds a camp to the list of camps the staff has created
	 * @param camp
	 */
	public void createCamp(Camp camp) {
		createdCamps.add(camp);
	}

	/**
	 * Removes a camp from the list of camps the staff has created
	 * @param camp
	 */
	public void deleteCamp(Camp camp) {
		createdCamps.remove(camp);
	}

	/**
	 * Returns a list of camps the staff has created
	 * @return ArrayList<Camp>
	 */
	public ArrayList<Camp> getCreatedCamps() {
		return this.createdCamps;
	}
}
