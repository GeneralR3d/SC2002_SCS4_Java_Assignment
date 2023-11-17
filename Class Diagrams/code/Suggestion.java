public class Suggestion implements Message {

	private String ownerID;
	private String content;
	private boolean isApproved;

	public String getOwnerID() {
		return this.ownerID;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isIsApproved() {
		return this.isApproved;
	}

	/**
	 * 
	 * @param userID
	 * @param content
	 */
	public Suggestion(String userID, String content) {
		// TODO - implement Suggestion.Suggestion
		throw new UnsupportedOperationException();
	}

	public void approve() {
		// TODO - implement Suggestion.approve
		throw new UnsupportedOperationException();
	}

}