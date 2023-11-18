public class Suggestion implements EditableMessage {

	private String ownerID;
	private String content;
	private Status status;

	public String getOwnerID() {
		return this.ownerID;
	}

	public String view() {
		return this.content;
	}

	public void edit(String content) {
		this.content = content;
	}

	public boolean isApproved() {
		return this.status == Status.APPROVED;
	}

	public boolean isProcessed() {
		return this.status != Status.PENDING;
	}

	/**
	 *
	 * @param userID
	 * @param content
	 */
	public Suggestion(String userID, String content) {
		this.ownerID = userID;
		this.content = content;
		this.status = Status.PENDING;
	}

	public void approve() {
		this.status = Status.APPROVED;
	}

	public void reject() {
		this.status = Status.REJECTED;
	}

}
