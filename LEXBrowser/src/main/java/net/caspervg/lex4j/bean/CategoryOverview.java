package net.caspervg.lex4j.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class provides an overview of all types of categories
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

    /**
     * Returns the broad categories
     *
     * @return the broad categories
     */
    public List<BroadCategory> getBroadCategories() {
        return broadCategories;
    }

    /**
     * Returns the LEX categories
     *
     * @return the LEX categories
     */
    public List<Category> getLEXCategories() {
        return lexCategories;
    }

    /**
     * Returns the LEX types
     *
     * @return the LEX types
     */
    public List<TypeCategory> getLEXTypes() {
        return lexTypeCategories;
    }

    /**
     * Returns the lot groups
     *
     * @return the lot groups
     */
    public List<Category> getLotGroups() {
        return lotGroups;
    }

    /**
     * Returns the lot authors
     *
     * @return the lot authors
     */
    public List<Category> getLotAuthors() {
        return lotAuthors;
    }
}
