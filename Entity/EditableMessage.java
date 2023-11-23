package Entity;

public interface EditableMessage extends Message{

  boolean isProcessed();

  /**
	 *
	 * @param content
	 */
	void edit(String content);

}
