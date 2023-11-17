public class Enquiry implements Message {

	private String ownerID;
	private String content;
	private ArrayList<Reply> replies;

	public String getOwnerID() {
		return this.ownerID;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<Reply> getReplies() {
		return this.replies;
	}

	/**
	 * 
	 * @param userID
	 * @param content
	 */
	public Enquiry(String userID, String content) {
		// TODO - implement Enquiry.Enquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reply
	 */
	public void addReply(Reply reply) {
		// TODO - implement Enquiry.addReply
		throw new UnsupportedOperationException();
	}

}