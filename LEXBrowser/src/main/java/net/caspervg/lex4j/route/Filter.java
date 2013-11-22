package net.caspervg.lex4j.route;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 22/11/13
 * Time: 11:57
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
     * Creates a new Filter
     * @param representation String representation
     */
    Filter(String representation) {
        this.representation = representation;
    }

    /**
     * @return The String representation of this Filter for internal use
     */
    protected String repr() {
        return this.representation;
    }
}
