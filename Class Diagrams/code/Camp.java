public class Camp {

	Staff incharge;
	private int campID;
	private String name;
	private Date startDate;
	private Date endDate;
	private Date regCloseDate;
	private boolean openToNTU;
	private String location;
	private int totalSlotsLeft;
	private int comSlotsLeft = 10;
	private String description;
	private boolean visibleToStudents;
	private Staff staffInCharge;
	private ArrayList<Student> attendees;
	private ArrayList<Student> committee;
	private ArrayList<Enquiry> enquiries;
	private ArrayList<Suggestion> suggestions;

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

	public boolean isOpenToNTU() {
		return this.openToNTU;
	}

	public void setOpenToNTU(boolean openToNTU) {
		this.openToNTU = openToNTU;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getTotalSlotsLeft() {
		return this.totalSlotsLeft;
	}

	public void setTotalSlotsLeft(int totalSlotsLeft) {
		this.totalSlotsLeft = totalSlotsLeft;
	}

	public int getComSlotsLeft() {
		return this.comSlotsLeft;
	}

	public void setComSlotsLeft(int comSlotsLeft) {
		this.comSlotsLeft = comSlotsLeft;
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
		return this.attendees;
	}

	public ArrayList<Student> getCommittee() {
		return this.committee;
	}

	public ArrayList<Enquiry> getEnquiries() {
		return this.enquiries;
	}

	public ArrayList<Suggestion> getSuggestions() {
		return this.suggestions;
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
	public Camp(int campID, String name, Date startDate, Date endDate, Date regCloseDate, boolean openToNTU, String location, int totalSlotsLeft, int comSlotsLeft, string description, boolean visibleToStudents) {
		// TODO - implement Camp.Camp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attendee
	 */
	public void addAttendee(Student attendee) {
		// TODO - implement Camp.addAttendee
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param committee
	 */
	public void addCommittee(Student committee) {
		// TODO - implement Camp.addCommittee
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param enquiry
	 */
	public void addEnquiry(Enquiry enquiry) {
		// TODO - implement Camp.addEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param suggestion
	 */
	public void addSuggestion(Suggestion suggestion) {
		// TODO - implement Camp.addSuggestion
		throw new UnsupportedOperationException();
	}

}