package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 22/11/13
 * Time: 10:17
 */
public class Comment {

    private int id;
    private String user;
    private String text;
    private String date;
    @SerializedName("by_author")
    private boolean byAuthor;
    @SerializedName("by_admin")
    private boolean byAdmin;

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public boolean isByAuthor() {
        return byAuthor;
    }

    public boolean isByAdmin() {
        return byAdmin;
    }
}
