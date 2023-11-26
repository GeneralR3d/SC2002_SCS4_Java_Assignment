package entity;

/**
 * A non-editable message
 * <div>Contains the ownerID as a {@link String} and the content as a {@link String}</div>
 */
public class Reply implements Message {

	private String ownerID;
	private String content;

	/**
	 * Accesor
	 * @return String the ownerID
	 */
	public String getOwnerID() {
		return this.ownerID;
	}

	/**
     * Accesor
     * @return the content of this enquiry which is a {@link String}
     */
	public String view() {
		return this.content;
	}

	/**
	 * Constructor
	 * @param userID a {@link String}
	 * @param content a {@link String}
	 */
	public Reply(String userID, String content) {
		this.ownerID = userID;
		this.content = content;
	}

}
