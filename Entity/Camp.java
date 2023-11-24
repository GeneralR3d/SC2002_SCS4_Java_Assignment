package entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a camp where all attributes are private.
 * Uses {@link java.time.LocalDate} to store the date information
 * Uses {@link java.util.ArrayList} to store list of attendees, committee
 * members, enquiries and suggestions.
 */
public class Camp {

	private int campID;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate regCloseDate;
	private Faculty openToFaculty;
	private String location;
	private int totalSlotsLeft;
	private int commSlotsLeft;
	private String description;
	private boolean visibleToStudents;
	private Staff staffInCharge;
	private ArrayList<Student> attendeeList;
	private ArrayList<CommitteeMember> committeeList;
	private ArrayList<Enquiry> enquiryList;
	private ArrayList<Suggestion> suggestionList;

	/**
	 * Accessor method
	 *
	 * @return int a unique campID
	 */
	public int getCampID() {
		return this.campID;
	}

	/**
	 * Accessor method
	 * Returns the name of the camp(can be duplicated).
	 *
	 * @return {@link String} name of camp
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Mutator method
	 * Mutates the name of the came.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Accessor method
	 *
	 * @return {@link java.util.Date} the start date of the camp
	 */
	public LocalDate getStartDate() {
		return this.startDate;
	}

	/**
	 * Mutator method
	 *
	 * @param startDate the start date of the camp of type {@link java.util.Date}
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Accessor method
	 *
	 * @return {@link java.util.Date} the end date of the camp
	 */
	public LocalDate getEndDate() {
		return this.endDate;
	}

	/**
	 * Mutator method
	 *
	 * @param endDate the end date of the camp {@link java.util.Date}
	 */

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Accessor method
	 *
	 * @return {@link java.util.Date} the registration close date of the camp
	 */
	public LocalDate getRegCloseDate() {
		return this.regCloseDate;
	}

	/**
	 * Mutator method
	 *
	 * @param regCloseDate the registration date of the camp of type
	 *                     {@link java.util.Date}
	 */
	public void setRegCloseDate(LocalDate regCloseDate) {
		this.regCloseDate = regCloseDate;
	}

	/**
	 * Accessor method
	 * Returns an enum value to see which faculty this camp is created in, and which
	 * faculty it is open to
	 * {@code NTU} means it is open to the whole school regardless of faculty
	 *
	 * @return {@link Faculty} an enum of faculties
	 */
	public Faculty getOpenToFaculty() {
		return this.openToFaculty;
	}

	/**
	 * Mutator method
	 * Sets enum value of which faculty this camp is open to
	 *
	 * @param openToFaculty an enum of faculties of type {@link Faculty}
	 */
	public void setOpenToFaculty(Faculty openToFaculty) {
		this.openToFaculty = openToFaculty;
	}

	/**
	 * Accessor method
	 *
	 * @return {@link String} location of camp
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Mutator method
	 *
	 * @param location of camp of type {@link String}
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return int
	 */
	public int getTotalSlotsLeft() {
		return this.totalSlotsLeft;
	}

	public void setTotalSlotsLeft(int totalSlotsLeft) {
		this.totalSlotsLeft = totalSlotsLeft;
	}

	/**
	 * Accessor method
	 *
	 * @return int the number of committee member slots left
	 */
	public int getCommSlotsLeft() {
		return this.commSlotsLeft;
	}

	/**
	 * Mutator method
	 *
	 * @param comSlotsLeft the number of committee member slots left
	 */
	public void setComSlotsLeft(int comSlotsLeft) {
		this.commSlotsLeft = comSlotsLeft;
	}

	/**
	 * Accessor method
	 *
	 * @return {@link String} description of camp
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Mutator method
	 *
	 * @param description of camp of type {@link String}
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Accessor method
	 *
	 * @return boolean whether this camp is visible to students
	 */
	public boolean isVisibleToStudents() {
		return this.visibleToStudents;
	}

	/**
	 * Mutator method
	 *
	 * @param visibleToStudents whether this camp is visible to students
	 */
	public void setVisibleToStudents(boolean visibleToStudents) {
		this.visibleToStudents = visibleToStudents;
	}

	/**
	 * Accesor method
	 * Normally the staff in charge of a camp is the staff who created the camp
	 *
	 * @return {@link Staff} the staff who is in charge of this camp
	 */
	public Staff getStaffInCharge() {
		return this.staffInCharge;
	}

	/**
	 * Mutator method
	 *
	 * @deprecated not supposed to change the in-charge
	 * @param staffInCharge the staff who is in charge of this camp
	 */
	public void setStaffInCharge(Staff staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

	/**
	 * Accesor method
	 *
	 * @return {@link java.util.ArrayList} the list of attendees of a camp
	 */
	public ArrayList<Student> getAttendees() {
		return this.attendeeList;
	}

	/**
	 * Accesor method
	 *
	 * @return {@link java.util.ArrayList} the list of committee members of a camp
	 */
	public ArrayList<CommitteeMember> getCommittee() {
		return this.committeeList;
	}

	/**
	 * Accesor method
	 *
	 * @return {@link java.util.ArrayList} the list of enquiries of a camp
	 */
	public ArrayList<Enquiry> getEnquiries() {
		return this.enquiryList;
	}

	/**
	 * Accesor method
	 *
	 * @return {@link java.util.ArrayList} the list of suggestions of a camp
	 */
	public ArrayList<Suggestion> getSuggestions() {
		return this.suggestionList;
	}

	public int getAttendeeSlotsLeft() {
		return this.totalSlotsLeft - this.commSlotsLeft;
	}

	/**
	 * Constructor
	 *
	 * @param campID            unqiue integer
	 * @param name              type {@link String}
	 * @param startDate         type {@link java.util.Date}
	 * @param endDate           type {@link java.util.Date}
	 * @param regCloseDate      type {@link java.util.Date}
	 * @param openToFaculty     enum type {@link Faculty} to indicate which faculty
	 *                          this camp is created in
	 * @param location          type {@link String}
	 * @param totalSlotsLeft    total number of slots available for the camp
	 * @param comSlotsLeft      total number of committe member slots available for
	 *                          the camp
	 * @param description       type {@link String}
	 * @param visibleToStudents boolean value indicating whether the camp is visible
	 *                          to students
	 */
	public Camp(int campID, String name, LocalDate startDate, LocalDate endDate, LocalDate regCloseDate,
			Faculty openToFaculty,
			String location, int totalSlots, int commSlots, String description, boolean visibleToStudents) {
		this.campID = campID;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regCloseDate = regCloseDate;
		this.openToFaculty = openToFaculty;
		this.location = location;
		this.totalSlotsLeft = totalSlots;
		this.commSlotsLeft = commSlots;
		this.description = description;
		this.visibleToStudents = visibleToStudents;
		attendeeList = new ArrayList<Student>();
		committeeList = new ArrayList<CommitteeMember>();
		enquiryList = new ArrayList<Enquiry>();
		suggestionList = new ArrayList<Suggestion>();
	}

	/**
	 * Checks if there are remaining attendee slots for the camp
	 *
	 * @return {@code true} if there are attendee slots left, {@code false} if there
	 *         are no attendee slots left
	 */
	public boolean hasAttendeeSlots() {
		return this.totalSlotsLeft != this.commSlotsLeft;
	}

	/**
	 * Adds one attendee to the list of attendees of the camp
	 *
	 * @param attendee the attendee to be added of type {@link Student}
	 */
	public void addAttendee(Student attendee) {
		attendeeList.add(attendee);
		this.totalSlotsLeft--;
	}

	/**
	 * Removes one attendee from the list of attendee of the camp
	 *
	 * @param attendee the attendee to be removed of type {@link Student}
	 */
	public void removeAttendee(Student attendee) {
		attendeeList.remove(attendee);
		this.totalSlotsLeft++;
	}

	/**
	 * Checks whether the are remaining slots for new committee members to join
	 *
	 * @return boolean
	 */
	public boolean hasCommitteeSlots() {
		return commSlotsLeft != 0;
	}

	/**
	 * Adds one committee member to the list of committee members of the camp
	 *
	 * @param committee the committee member to be added of type
	 *                  {@link CommitteeMember}
	 */
	public void addCommittee(CommitteeMember committee) {
		committeeList.add(committee);
		this.commSlotsLeft--;
		this.totalSlotsLeft--;
	}

	/**
	 * Removes one committee member from the list of committee members of the camp
	 *
	 * @param committee the committee member to be removed of type
	 *                  {@link CommitteeMember}
	 */
	public void removeCommittee(CommitteeMember committee) {
		committeeList.remove(committee);
		this.commSlotsLeft++;
		this.totalSlotsLeft++;
	}

	/**
	 * Adds one enquiry to the list of enquiries of the camp
	 *
	 * @param enquiry the enquiry to be added of type {@link Enquiry}
	 */
	public void addEnquiry(Enquiry enquiry) {
		enquiryList.add(enquiry);
	}

	/**
	 * Removes one enquiry from the list of enquiries of the camp
	 *
	 * @param enquiry the enruiry to be removed of type {@link Enquiry}
	 */
	public void removeEnquiry(Enquiry enquiry) {
		enquiryList.remove(enquiry);
	}

	/**
	 * Adds one suggestion to the list of suggestions of the camp
	 *
	 * @param suggestion the enquiry to be added of type {@link Suggestion}
	 */
	public void addSuggestion(Suggestion suggestion) {
		suggestionList.add(suggestion);
	}

	/**
	 * Removes one suggestion from the list of suggestions of the camp
	 *
	 * @param suggestion the enruiry to be removed of type {@link Suggestion}
	 */
	public void removeSuggestion(Suggestion suggestion) {
		suggestionList.remove(suggestion);
	}

}
