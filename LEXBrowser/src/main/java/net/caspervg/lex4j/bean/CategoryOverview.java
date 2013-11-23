package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 22/11/13
 * Time: 10:17
 */
public class CategoryOverview {

    @SerializedName("broad_category")
    private List<BroadCategory> broadCategories;

    @SerializedName("lex_category")
    private List<Category> lexCategories;

    @SerializedName("lex_type")
    private List<TypeCategory> lexTypeCategories;

    @SerializedName("group")
    private List<Category> lotGroups;

    @SerializedName("author")
    private List<Category> lotAuthors;

    public List<BroadCategory> getBroadCategories() {
        return broadCategories;
    }

    public List<Category> getLEXCategories() {
        return lexCategories;
    }

    public List<TypeCategory> getLEXTypes() {
        return lexTypeCategories;
    }

    public List<Category> getLotGroups() {
        return lotGroups;
    }

    public List<Category> getLotAuthors() {
        return lotAuthors;
    }
}
