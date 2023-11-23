import java.util.ArrayList;

public class Enquiry implements EditableMessage {

	private String ownerID;
	private String content;
	private ArrayList<Reply> replies;

	
	/** 
	 * @return String
	 */
	public String getOwnerID() {
		return this.ownerID;
	}

	public ArrayList<Reply> getReplies() {
		return this.replies;
	}

	public String view() {
		return this.content;
	}

	public boolean isProcessed() {
		return this.replies.size() == 0;
	}

	/**
	 *
	 * @param content
	 */
	public void edit(String content) {
		this.content = content;
	}

	/**
	 *
	 * @param userID
	 * @param content
	 */
	public Enquiry(String userID, String content) {
		this.ownerID = userID;
		this.content = content;
	}

	/**
	 *
	 * @param reply
	 */
	public void addReply(Reply reply) {
		this.replies.add(reply);
	}

}
