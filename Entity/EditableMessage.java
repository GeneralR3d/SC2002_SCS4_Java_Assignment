package entity;

/**
 * Sub-interface of {@link Message}
 * Has a method to allow it to edit the content of {@link Message}
 */
public interface EditableMessage extends Message {

	boolean isProcessed();

	/**
	 * Virtual method
	 * <div>Allows editing of the string content of the {@link Message}</div>
	 * @param content the new {@link String}
	 */
	void edit(String content);

}
