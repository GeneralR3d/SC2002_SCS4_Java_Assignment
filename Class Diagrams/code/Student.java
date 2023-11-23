import java.util.ArrayList;

public class Student extends User {

	private ArrayList<Camp> signedUpCamps;

	/**
	 *
	 * @param userID
	 * @param faculty
	 * @param pw
	 */
	public Student(String name,String userID, Faculty faculty, String pw) {
		super(name,userID, faculty, pw);
		signedUpCamps = new ArrayList<Camp>();
	}

	/**
	 *
	 * @param userID
	 * @param faculty
	 */
	public Student(String name, String userID, Faculty faculty) {
		super(name,userID, faculty);
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

	
	/** 
	 * @return ArrayList<Camp>
	 */
	public ArrayList<Camp> getSignedUpCamps(){
		return signedUpCamps;
	}
}
