package net.caspervg.lex4j.route;

/**
 * Provides several filters used to send search requests to the LEX
 *
 * @author Casper Van Gheluwe
 */
public enum Filter {
    /**
     * Result to start at
     */
    START("start"),
    /**
     * Number of results to return
     */
    AMOUNT("amount"),
    /**
     * Ordering of the results
     */
    ORDER_BY("order_by"),
    /**
     * Ascending or descending
     */
    ORDER("order"),
    /**
     * Only return ID and name, no other information
     */
    CONCISE("concise"),
    /**
     * Filter by author of the file
     */
    CREATOR("creator"),
    /**
     * Filter by broad category of the file
     */
    BROAD_CATEGORY("broad_category"),
    /**
     * Filter by LEX category of the file
     */
    LEX_CATEGORY("lex_category"),
    /**
     * Filter by LEX type of the file
     */
    LEX_TYPE("lex_type"),
    /**
     * Filter by broad type of the file (a very rough approach)
     */
    BROAD_TYPE("broad_type"),
    /**
     * Filter by lot group of the file
     */
    GROUP("group"),
    /**
     * Exclude results that are not LEX certified
     */
    EXCLUDE_NOT_CERTIFIED("exclude_notcert"),
    /**
     * Exclude results that are locked (not downloadable)
     */
    EXCLUDE_LOCKED("exclude_locked");

    private String representation;

    /**
     * Constructs a new filter with it's string representation
     *
     * @param representation String representation
     */
    Filter(String representation) {
        this.representation = representation;
    }

    /**
     * Returns the String representation of this Filter
     *
     * @return The String representation of this Filter
     */
    protected String repr() {
        return this.representation;
    }
}
