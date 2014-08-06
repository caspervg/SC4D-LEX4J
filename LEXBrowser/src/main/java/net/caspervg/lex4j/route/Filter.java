package net.caspervg.lex4j.route;

/**
 * Provides several filters used to send search requests to the LEX
 *
 * @author Casper Van Gheluwe
 */
public enum Filter {
    /**
     * Result to start at. Requires an Integer parameter.
     */
    START("start", Integer.class),
    /**
     * Number of results to return. Requires an Integer parameter.
     */
    AMOUNT("amount", Integer.class),
    /**
     * Ordering of the results. Requires a String parameter.
     * <ul>
     *     <li><code>"download"</code> - order by number of downloads</li>
     *     <li><code>"popular"</code> - order by number of downloads</li>
     *     <li><code>"update"</code> - order by latest update</li>
     *     <li><code>"recent"</code> - order by release date</li>
     * </ul>
     */
    ORDER_BY("order_by", String.class),
    /**
     * Ascending or descending. Requires a String parameter.
     * <ul>
     *     <li><code>"asc"</code> - order ascending</li>
     *     <li><code>"desc"</code> - order descending</li>
     * </ul>
     */
    ORDER("order", String.class),
    /**
     * Only return ID and name, no other information. Requires a Boolean parameter.
     * <ul>
     *     <li><code>true</code> - return concise information (ID and name only)</li>
     *     <li><code>false</code> - return all information</li>
     * </ul>
     */
    CONCISE("concise", Boolean.class),
    /**
     * Filter by author of the file. Requires an Integer parameter (user id of the author).
     */
    CREATOR("creator", Integer.class),
    /**
     * Filter by broad category of the file. Requires a String parameter (name of the broad category).
     */
    BROAD_CATEGORY("broad_category", String.class),
    /**
     * Filter by LEX category of the file. Requires an Integer parameter (category id of the category).
     */
    LEX_CATEGORY("lex_category", Integer.class),
    /**
     * Filter by LEX type of the file. Requires an Integer parameter (type id of the category).
     */
    LEX_TYPE("lex_type", Integer.class),
    /**
     * Filter by broad type of the file (a very rough approach). Requires a String parameter.
     * <ul>
     *     <li><code>"lotbat"</code> - LOTs and BATs</li>
     *     <li><code>"dependency"</code> - Dependencies</li>
     *     <li><code>"map"</code> - Maps</li>
     *     <li><code>"mod"</code> - Mods</li>
     *     <li><code>"other"</code> - Files, Tools, Models, etc.</li>
     * </ul>
     */
    BROAD_TYPE("broad_type", String.class),
    /**
     * Filter by lot group of the file. Requires an Integer parameter (group id of the group).
     */
    GROUP("group", Integer.class),
    /**
     * Filter by (a part of) the title of the file. Requires a String parameter (text to search).
     */
    TITLE("query", String.class),
    /**
     * Exclude results that are not LEX Certified. Requires a Boolean parameter.
     * <ul>
     *     <li><code>true</code> - only return LEX Certified files</li>
     *     <li><code>false</code> - return all files</li>
     * </ul>
     */
    EXCLUDE_NOT_CERTIFIED("exclude_notcert", Boolean.class),
    /**
     * Exclude results that are locked (not downloadable). Requires a Boolean parameter.
     * <ul>
     *     <li><code>true</code> - only return active (not locked) files</li>
     *     <li><code>false</code> - return all files</li>
     * </ul>
     */
    EXCLUDE_LOCKED("exclude_locked", Boolean.class);

    private String representation;
    private Class clazz;

    /**
     * Constructs a new filter with it's string representation
     *
     * @param representation String representation
     */
    Filter(String representation, Class clazz) {
        this.representation = representation;
        this.clazz = clazz;
    }

    /**
     * Returns the String representation of this Filter
     *
     * @return The String representation of this Filter
     */
    protected String repr() {
        return this.representation;
    }

    /**
     * Returns the Parameter Class of this Filter
     *
     * @return the Parameter Class of this Filter
     */
    public Class getParameterClass() {
        return this.clazz;
    }
}
