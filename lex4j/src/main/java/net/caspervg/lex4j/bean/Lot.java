package net.caspervg.lex4j.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides a file/lot
 */
public class Lot {

    protected int id;
    protected String name;
    protected String version;
    @JsonProperty("num_downloads")
    protected int downloads;
    protected String author;
    @JsonProperty("is_exclusive")
    protected boolean exclusive;
    @JsonProperty("maxis_category")
    protected String broadCategory;
    protected String description;
    protected HashMap<String,String> images;
    protected String link;
    @JsonProperty("is_certified")
    protected boolean certified;
    @JsonProperty("is_active")
    protected boolean active;
    @JsonProperty("upload_date")
    protected Date uploaded;
    @JsonProperty("update_date")
    protected Date updated;
    protected String filesize;
    @JsonProperty("dependencies")
    protected DependencyList dependencyList;
    protected List<Comment> comments;
    @JsonProperty("last_downloaded")
    protected Date lastDownloaded;
    protected Map<Integer, Integer> votes;

    /**
     * Returns the ID of this file
     *
     * @return the ID of this file
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of this file
     *
     * @return the name of this file
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the version of this file
     *
     * @return the version of this file
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the number of downloads of this file
     *
     * @return the number of downloads of this file
     */
    public int getDownloads() {
        return downloads;
    }

    /**
     * Returns the username of the author of this file
     *
     * @return the username of the author of this file
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns <code>true</code> if this file is a LEX Exclusive (only uploaded to the SC4D LEX)
     *
     * @return <code>true</code> if this file is a LEX Exclusive;
     *         <code>false</code> otherwise
     */
    public boolean isExclusive() {
        return exclusive;
    }

    /**
     * Returns the broad category of this file
     *
     * @return the broad category of this file
     */
    public String getBroadCategory() {
        return broadCategory;
    }

    /**
     * Returns the description of this file
     *
     * @return the description of this file
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the images associated with this file. Keys can be one of
     * <ul>
     *     <li><code>"primary"</code></li>
     *     <li><code>"secondary"</code></li>
     *     <li><code>"extra"</code></li>
     * </ul>
     *
     * @return the images associated with this file
     */
    public HashMap<String, String> getImages() {
        return images;
    }

    /**
     * Returns the link of this file
     *
     * @return the link of this file
     */
    public String getLink() {
        return link;
    }

    /**
     * Returns <code>true</code> if this file is LEX Certified
     *
     * @return <code>true</code> if this file is LEX Certified;
     *         <code>false</code> otherwise
     */
    public boolean isCertified() {
        return certified;
    }

    /**
     * Returns <code>true</code> if this file is active (not locked or deleted)
     *
     * @return <code>true</code> if this file is active;
     *         <code>false</code> otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the upload date of this file
     *
     * @return the upload date of this file
     */
    public Date getUploaded() {
        return uploaded;
    }

    /**
     * Returns the last update date of this file
     *
     * @return the last update date of this file
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * Returns the filesize of this file
     * The size is presented in a rounded, human-readable form.
     * For example, instead of <code>"1.300.128 bytes"</code>, it will return <code>"1.30 MB".</code>
     *
     * @return the filesize of this file
     */
    public String getFilesize() {
        return filesize;
    }

    /**
     * Returns the {@link DependencyList} of this file
     *
     * @return the dependencies of this file
     */
    public DependencyList getDependencyList() {
        return dependencyList;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Date getLastDownloaded() {
        return lastDownloaded;
    }

    public Map<Integer, Integer> getVotes() {
        return votes;
    }

    /**
     * Returns the String representation of this file
     *
     * @return the String representation of this file
     */
    @Override
    public String toString() {
        return this.name + " (" + this.id + ")";
    }
}
