package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * This class provides a comment
 */
public class Comment {

    private int id;
    private String user;
    private String text;
    private Date date;
    @SerializedName("by_author")
    private boolean byAuthor;
    @SerializedName("by_admin")
    private boolean byAdmin;

    /**
     * Returns the ID of this comment
     *
     * @return the ID of this comment
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the username of the author of this comment
     *
     * @return the comment author's username
     */
    public String getUser() {
        return user;
    }

    /**
     * Returns the content of this comment
     *
     * @return the content of this comment
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the date of this comment
     *
     * @return the date of this comment
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns <code>true</code> if this comment was made by the author of the file
     *
     * @return <code>true</code> if this comment was made by the author of the file;
     *         <code>false</code> otherwise
     */
    public boolean isByAuthor() {
        return byAuthor;
    }

    /**
     * Returns <code>true</code> if this comment was made by an administrator
     *
     * @return <code>true</code> if this comment was made by an administrator;
     *         <code>false</code> otherwise
     */
    public boolean isByAdmin() {
        return byAdmin;
    }
}
