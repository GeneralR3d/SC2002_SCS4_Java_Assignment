package entity;

import java.util.ArrayList;

/**
 * Concrete class for {@link EditableMessage}
 * Represents an enquiry with an owner who is a {@link Student}, and a {@link java.util.ArrayList} of replies under this enquiry
 */
public class Enquiry implements EditableMessage {

    private Student owner;
    private String content;
    private ArrayList<Reply> replies;

    /**
     * Accessor
     * @return student who posted this enquiry
     */
    public Student getOwner() {
        return this.owner;
    }

    /**
     * Accessor
     * @return replies which is a {@link java.util.ArrayList}
     */
    public ArrayList<Reply> getReplies() {
        return this.replies;
    }

    /**
     * Accesor
     * @return the content of this enquiry which is a {@link String}
     */
    public String view() {
        return this.content;
    }

    /**
     * Returns status of the enquiry.
     * The enquiry is considered processed when it has a {@link Reply} or more
     */
    public boolean isProcessed() {
        return this.replies.size() == 0;
    }

    /**
     *Allows editing of the string content of the {@link Message}
	 * @param content the new {@link String}
     */
    public void edit(String content) {
        this.content = content;
    }

    /**
     * Constructor
     * @param owner the student who posted the enquiry
     * @param content
     */
    public Enquiry(Student owner, String content) {
        this.owner = owner;
        this.content = content;
    }

    /**
     * Adds a reply from a {@link Staff} or {@link CommitteeMember} to this enquiry
     * @param reply
     */
    public void addReply(Reply reply) {
        this.replies.add(reply);
    }

}
