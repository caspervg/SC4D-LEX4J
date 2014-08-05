package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;

/**
 * This class provides a file/lot
 */
public class Lot {

    private int id;
    private String name;
    private String version;
    @SerializedName("num_downloads")
    private int downloads;
    private String author;
    @SerializedName("is_exclusive")
    private boolean exclusive;
    @SerializedName("maxis_category")
    private String broadCategory;
    private String description;
    private HashMap<String,String> images;
    private String link;
    @SerializedName("is_certified")
    private boolean certified;
    @SerializedName("is_active")
    private boolean active;
    @SerializedName("upload_date")
    private Date uploaded;
    @SerializedName("update_date")
    private Date updated;
    @SerializedName("dependencies")
    private DependencyList dependencyList;

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
     * Returns the {@link DependencyList} of this file
     *
     * @return the dependencies of this file
     */
    public DependencyList getDependencyList() {
        return dependencyList;
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
