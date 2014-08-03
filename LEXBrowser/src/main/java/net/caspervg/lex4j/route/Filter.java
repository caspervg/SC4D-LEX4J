package net.caspervg.lex4j.route;

/**
 * Provides several filters used to send search requests to the LEX
 *
 * @author Casper Van Gheluwe
 */
public enum Filter {
    START("start"),
    AMOUNT("amount"),
    ORDER_BY("order_by"),
    ORDER("order"),
    CONCISE("concise"),
    CREATOR("creator"),
    BROAD_CATEGORY("broad_category"),
    LEX_CATEGORY("lex_category"),
    LEX_TYPE("lex_type"),
    BROAD_TYPE("broad_type"),
    GROUP("group"),
    EXCLUDE_NOT_CERTIFIED("exclude_notcert"),
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
