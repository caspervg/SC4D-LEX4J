package net.caspervg.lex4j.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class provides an overview of all types of categories
 */
public class CategoryOverview {

    @JsonProperty("broad_category")
    protected List<BroadCategory> broadCategories;

    @JsonProperty("lex_category")
    protected List<Category> lexCategories;

    @JsonProperty("lex_type")
    protected List<TypeCategory> lexTypeCategories;

    @JsonProperty("group")
    protected List<GroupCategory> lotGroups;

    @JsonProperty("author")
    protected List<Category> lotAuthors;

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
    public List<GroupCategory> getLotGroups() {
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

    /**
     * Returns the String representation of this category overview
     *
     * @return the String representation of this category overview
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Broad Categories: ");
        builder.append(this.broadCategories);
        builder.append(System.lineSeparator());
        builder.append("LEX Categories: ");
        builder.append(this.lexCategories);
        builder.append(System.lineSeparator());
        builder.append("LEX Types: ");
        builder.append(this.lexTypeCategories);
        builder.append(System.lineSeparator());
        builder.append("Lot Groups: ");
        builder.append(this.lotGroups);
        builder.append(System.lineSeparator());
        builder.append("Lot Authors: ");
        builder.append(this.lotAuthors);
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}
