package Entity;

import java.util.ArrayList;

public class Staff extends User {

	ArrayList<Camp> createdCamps;

		/**
	 *
	 * @param userID
	 * @param faculty
	 * @param pw
	 */
	public Staff(String userID, Faculty faculty, String pw) {
		super(userID, faculty, pw);
		createdCamps = new ArrayList<Camp>();
	}

	/**
	 *
	 * @param userID
	 * @param faculty
	 */
	public Staff(String userID, Faculty faculty) {
		super(userID, faculty);
		createdCamps = new ArrayList<Camp>();
	}

	/**
	 *
	 * @param camp
	 */
	public void createCamp(Camp camp) {
		createdCamps.add(camp);
	}

	/**
	 *
	 * @param camp
	 */
	public void deleteCamp(Camp camp) {
		createdCamps.remove(camp);
	}

}
