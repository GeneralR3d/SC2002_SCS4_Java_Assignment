package entity;

/**
 * Concrete class for {@link EditableMessage}
 * <div>Represents an sugesstion with an owner who is a {@link Student}, and a status
 * of type {@link Status}
 */
public class Suggestion implements EditableMessage {

	private CommitteeMember owner;
	private String content;
	private Status status;

	/**
	 * Accessor
	 * 
	 * @return CommitteeMember the committee member who created the suggestion
	 */
	public CommitteeMember getOwner() {
		return this.owner;
	}

	/**
	 * Accesor
	 * 
	 * @return the content of this enquiry which is a {@link String}
	 */
	public String view() {
		return this.content;
	}

	/**
	 * Accesor
	 * 
	 * @return the content of this enquiry which is a {@link Status}
	 */
	public Status getStatus() {
		return this.status;
	}

	/**
	 * Allows editing of the string content of the {@link Message}
	 * 
	 * @param content the new {@link String}
	 */
	public void edit(String content) {
		this.content = content;
	}

	/**
	 * Checks if the suggestion is approved by a {@link Staff}
	 * 
	 * @return
	 */
	public boolean isApproved() {
		return this.status == Status.APPROVED;
	}

	/**
	 * Checks if the suggestion is processed by a {@link Staff}
	 */
	public boolean isProcessed() {
		return this.status != Status.PENDING;
	}

	/**
	 * Constructor
	 * 
	 * @param commMember the commiteee member who created the suggestion
	 * @param content
	 */
	public Suggestion(CommitteeMember commMember, String content) {
		this.owner = commMember;
		this.content = content;
		this.status = Status.PENDING;
	}

	/**
	 * Allows a {@link Staff} to approve the suggestion
	 */
	public void approve() {
		this.status = Status.APPROVED;
	}

	/**
	 * Allows a {@link Staff} to reject the suggestion
	 */
	public void reject() {
		this.status = Status.REJECTED;
	}

}
