package Entity;

public class Suggestion implements EditableMessage {

	private CommitteeMember owner;
	private String content;
	private Status status;

	
	/** 
	 * @return CommitteeMember
	 */
	public CommitteeMember getOwner() {
		return this.owner;
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
	public Suggestion(CommitteeMember commMember, String content) {
		this.owner = commMember;
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
