package entity;

import java.util.ArrayList;

public class Enquiry implements EditableMessage {

    private Student owner;
    private String content;
    private ArrayList<Reply> replies;

    /**
     * @return Student
     */
    public Student getOwner() {
        return this.owner;
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
    public Enquiry(Student owner, String content) {
        this.owner = owner;
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
