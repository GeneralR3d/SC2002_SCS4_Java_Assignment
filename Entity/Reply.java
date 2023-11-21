package entity;

public class Reply implements Message {

	private String ownerID;
	private String content;

	public String getOwnerID() {
		return this.ownerID;
	}

	public String view() {
		return content;
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
	public Reply(String userID, String content) {
		this.ownerID = userID;
		this.content = content;
	}

}
