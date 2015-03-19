package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * This class provides a user profile
 */
public class User {

    protected int id;
    protected String fullname;
    protected String username;
    protected Date registered;
    protected Date lastLogin;

    @SerializedName("user_level")
    protected int userLevel;

    protected String email;
    @SerializedName("login_count")

    protected int loginCount;

    @SerializedName("is_active")
    protected boolean active;

    @SerializedName("is_donator")
    protected boolean donator;

    @SerializedName("is_rater")
    protected boolean rater;

    @SerializedName("is_uploader")
    protected boolean uploader;

    @SerializedName("is_author")
    protected boolean author;

    @SerializedName("is_admin")
    protected boolean admin;

    public User() {
        /*
         * Hacky solution to avoid having to write separate Date parsing rules for User.lastLogin, which uses yyyyMMddHHmmss
         * compared to the rest of the LEX API, which uses yyyyMMdd. We could parse, but there would be timezone issues.
         * Since the LEX API handles Basic Auth as a "log in", the lastLogin would always equal the current time anyhow.
         */
        this.lastLogin = new Date();
    }

    /**
     * Returns the ID of this user
     *
     * @return the ID of this user
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the full name of this user
     *
     * @return the full name of this user
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Returns the username of this user
     *
     * @return the username of this user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the registration date of this user
     *
     * @return the registration date of this user
     */
    public Date getRegistered() {
        return registered;
    }

    /**
     * Returns the last login date of this user. Since API calls count as a login, this will generally be the time
     * when the user request was executed.
     *
     * @return the last login date of this user
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * Returns the user level of this user. Can be one of:
     * <ol>
     *     <li>Normal user</li>
     *     <li>Uploader</li>
     *     <li>Admin</li>
     * </ol>
     * However, it is suggested to use {@link #isAdmin}, {@link #isUploader}, etc. instead
     *
     * @return the user level of this user
     */
    public int getUserLevel() {
        return userLevel;
    }

    /**
     * Returns the e-mail of this user
     *
     * @return the e-mail of this user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the login count of this user
     *
     * @return the login count of this user
     */
    public int getLoginCount() {
        return loginCount;
    }

    /**
     * Returns <code>true</code> if this user is active
     *
     * @return <code>true</code> if this user is active;
     *         <code>false</code> otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns <code>true</code> if this user is a donator
     *
     * @return <code>true </code> if this user is a donator;
     *         <code>false</code> otherwise
     */
    public boolean isDonator() {
        return donator;
    }

    /**
     * Returns <code>true</code> if this user is a rater
     *
     * @return <code>true </code> if this user is a rater;
     *         <code>false</code> otherwise
     */
    public boolean isRater() {
        return rater;
    }

    /**
     * Returns <code>true</code> if this user is an uploader
     *
     * @return <code>true </code> if this user is an uploader;
     *         <code>false</code> otherwise
     */
    public boolean isUploader() {
        return uploader;
    }

    /**
     * Returns <code>true</code> if this user is an author
     *
     * @return <code>true </code> if this user is an author;
     *         <code>false</code> otherwise
     */
    public boolean isAuthor() {
        return author;
    }

    /**
     * Returns <code>true</code> if this user is an admin
     *
     * @return <code>true </code> if this user is an admin;
     *         <code>false</code> otherwise
     */
    public boolean isAdmin() {
        return admin;
    }



    /**
     * Returns the String representation of this file
     *
     * @return the String representation of this file
     */
    @Override
    public String toString() {
        return this.username + " (" + this.id + ")";
    }
}
