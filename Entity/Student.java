package Entity;

import java.util.ArrayList;

public class Student extends User {

	private ArrayList<Camp> signedUpCamps;

	/**
	 *
	 * @param userID
	 * @param faculty
	 * @param pw
	 */
	public Student(String userID, Faculty faculty, String pw) {
		super(userID, faculty, pw);
		signedUpCamps = new ArrayList<Camp>();
	}

	/**
	 *
	 * @param userID
	 * @param faculty
	 */
	public Student(String userID, Faculty faculty) {
		super(userID, faculty);
		signedUpCamps = new ArrayList<Camp>();
	}

	/**
	 *
	 * @param campID
	 */
	public void register(Camp camp) {
		signedUpCamps.add(camp);
	}

	/**
	 *
	 * @param camp
	 */
	public void withdraw(Camp camp) {
		signedUpCamps.remove(camp);
	}

    public ArrayList<Camp> getSignedUpCamps() {
        return signedUpCamps;
    }

}
