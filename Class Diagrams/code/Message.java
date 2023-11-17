public interface Message {

	String view();

	/**
	 * 
	 * @param content
	 */
	void edit(String content);

	void delete();

}