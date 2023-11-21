package entity;

import java.util.Date;
import java.util.ArrayList;

public class Camp {

	private int campID;
	private String name;
	private Date startDate;
	private Date endDate;
	private Date regCloseDate;
	private Faculty openToFaculty;
	private String location;
	private int attendeeSlotsLeft;
	private int commSlotsLeft;
	private String description;
	private boolean visibleToStudents;
	private Staff staffInCharge;
	private ArrayList<Student> attendeeList;
	private ArrayList<CommitteeMember> committeeList;
	private ArrayList<Enquiry> enquiryList;
	private ArrayList<Suggestion> suggestionList;

	public int getCampID() {
		return this.campID;
	}

	public String getName() {
		return this.name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRegCloseDate() {
		return this.regCloseDate;
	}

	public void setRegCloseDate(Date regCloseDate) {
		this.regCloseDate = regCloseDate;
	}

	public Faculty getOpenToFaculty() {
		return this.openToFaculty;
	}

	public void setOpenToFaculty(Faculty openToFaculty) {
		this.openToFaculty = openToFaculty;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAttendeeSlotsLeft() {
		return this.attendeeSlotsLeft;
	}

	public void setattendeeSlotsLeft(int attendeeSlotsLeft) {
		this.attendeeSlotsLeft = attendeeSlotsLeft;
	}

	public int getCommSlotsLeft() {
		return this.commSlotsLeft;
	}

	public void setComSlotsLeft(int comSlotsLeft) {
		this.commSlotsLeft = comSlotsLeft;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVisibleToStudents() {
		return this.visibleToStudents;
	}

	public void setVisibleToStudents(boolean visibleToStudents) {
		this.visibleToStudents = visibleToStudents;
	}

	public Staff getStaffInCharge() {
		return this.staffInCharge;
	}

	public void setStaffInCharge(Staff staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

	public ArrayList<Student> getAttendees() {
		return this.attendeeList;
	}

	public ArrayList<CommitteeMember> getCommittee() {
		return this.committeeList;
	}

	public ArrayList<Enquiry> getEnquiries() {
		return this.enquiryList;
	}

	public ArrayList<Suggestion> getSuggestions() {
		return this.suggestionList;
	}

	/**
	 *
	 * @param campID
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param regCloseDate
	 * @param openToNTU
	 * @param location
	 * @param totalSlotsLeft
	 * @param comSlotsLeft
	 * @param description
	 * @param visibleToStudents
	 */
	public Camp(int campID, String name, Date startDate, Date endDate, Date regCloseDate, Faculty openToFaculty, String location, int totalSlots, int commSlots, String description, boolean visibleToStudents) {
		this.campID = campID;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regCloseDate = regCloseDate;
		this.openToFaculty = openToFaculty;
		this.location = location;
		this.attendeeSlotsLeft = totalSlots - commSlots;
		this.commSlotsLeft = commSlots;
		this.description = description;
		this.visibleToStudents = visibleToStudents;
		attendeeList = new ArrayList<Student>();
		committeeList = new ArrayList<CommitteeMember>();
		enquiryList = new ArrayList<Enquiry>();
		suggestionList = new ArrayList<Suggestion>();
	}

	/**
	 *
	 * @param attendee
	 */
	public boolean hasAttendeeSlots() {
		return attendeeSlotsLeft != 0;
	}

	/**
	 *
	 * @param attendee
	 */
	public void addAttendee(Student attendee) {
		attendeeList.add(attendee);
		this.attendeeSlotsLeft--;
	}

	/**
	 *
	 * @param attendee
	 */
	public void removeAttendee(Student attendee) {
		attendeeList.remove(attendee);
	}

	/**
	 *
	 * @param committee
	 */
	public boolean hasCommitteeSlots() {
		return commSlotsLeft != 0;
	}

	/**
	 *
	 * @param committee
	 */
	public void addCommittee(CommitteeMember committee) {
		committeeList.add(committee);
		this.commSlotsLeft++;
	}

	/**
	 *
	 * @param committee
	 */
	public void removeCommittee(CommitteeMember committee) {
		committeeList.remove(committee);
		this.commSlotsLeft--;
	}

	/**
	 *
	 * @param enquiry
	 */
	public void addEnquiry(Enquiry enquiry) {
		enquiryList.add(enquiry);
	}

	/**
	 *
	 * @param enquiry
	 */
	public void removeEnquiry(Enquiry enquiry) {
		enquiryList.remove(enquiry);
	}

	/**
	 *
	 * @param suggestion
	 */
	public void addSuggestion(Suggestion suggestion) {
		suggestionList.add(suggestion);
	}

	/**
	 *
	 * @param suggestion
	 */
	public void removeSuggestion(Suggestion suggestion) {
		suggestionList.remove(suggestion);
	}

}
