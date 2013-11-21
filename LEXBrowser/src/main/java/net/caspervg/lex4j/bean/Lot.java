package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 15:46
 */
public class Lot {

    private int id;
    private String name;
    private String version;
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
    private String uploaded;
    @SerializedName("update_date")
    private String updated;

}
