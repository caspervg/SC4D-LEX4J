package net.caspervg.lex4j.route;

import net.caspervg.lex4j.bean.BroadCategory;
import net.caspervg.lex4j.bean.Category;
import net.caspervg.lex4j.bean.TypeCategory;
import net.caspervg.lex4j.bean.User;

import java.util.Arrays;

/**
 * Provides several filters used to send search requests to the LEX
 *
 * @author Casper Van Gheluwe
 */
public enum Filter {
    /**
     * Result to start at. Requires an Integer parameter.
     */
    START("start", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return (o instanceof Integer);
        }
    }),
    /**
     * Number of results to return. Requires an Integer parameter.
     */
    AMOUNT("amount", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return (o instanceof Integer);
        }
    }),
    /**
     * Ordering of the results. Requires a String parameter.
     * <ul>
     *     <li><code>"download"</code> - order by number of downloads</li>
     *     <li><code>"popular"</code> - order by number of downloads</li>
     *     <li><code>"update"</code> - order by latest update</li>
     *     <li><code>"recent"</code> - order by release date</li>
     * </ul>
     */
    ORDER_BY("order_by", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            String[] possibilities = {"download", "popular", "update", "recent"};
            return o instanceof String && Arrays.asList(possibilities).contains(o);
        }
    }),
    /**
     * Ascending or descending. Requires a String parameter.
     * <ul>
     *     <li><code>"asc"</code> - order ascending</li>
     *     <li><code>"desc"</code> - order descending</li>
     * </ul>
     */
    ORDER("order", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            String[] possibilities = {"asc", "desc"};
            return o instanceof String && Arrays.asList(possibilities).contains(o);
        }
    }),
    /**
     * Only return ID and name, no other information. Requires a Boolean parameter.
     * <ul>
     *     <li><code>true</code> - return concise information (ID and name only)</li>
     *     <li><code>false</code> - return all information</li>
     * </ul>
     */
    CONCISE("concise", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Boolean;
        }
    }),
    /**
     * Filter by author of the file. Requires an Integer parameter (user id of the author).
     */
    CREATOR("creator", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Integer || o instanceof User;
        }
    }),
    /**
     * Filter by broad category of the file. Requires a String parameter (name of the broad category).
     */
    BROAD_CATEGORY("broad_category", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof String || o instanceof BroadCategory;
        }
    }),
    /**
     * Filter by LEX category of the file. Requires an Integer parameter (category id of the category).
     */
    LEX_CATEGORY("lex_category", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Integer || o instanceof Category;
        }
    }),
    /**
     * Filter by LEX type of the file. Requires an Integer parameter (type id of the category).
     */
    LEX_TYPE("lex_type", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Integer || o instanceof TypeCategory;
        }
    }),
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
    BROAD_TYPE("broad_type", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            String[] possibilities = {"lotbat", "dependency", "map", "mod", "other"};
            return o instanceof String && Arrays.asList(possibilities).contains(o);
        }
    }),
    /**
     * Filter by lot group of the file. Requires an Integer parameter (group id of the group).
     */
    GROUP("group", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Integer || o instanceof Category;
        }
    }),
    /**
     * Filter by (a part of) the title of the file. Requires a String parameter (text to search).
     */
    TITLE("query", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof String;
        }
    }),
    /**
     * Add lists of dependencies to each search result. By default not active. Requires a String parameter.
     * <ul>
     *     <li><code>"full"</code> - Full list of dependencies, similar to {@link LotRoute#getDependencyList(int)}</li>
     *     <li><code>"concise"</code> - Concise list of dependencies, only IDs for internal dependencies</li>
     * </ul>
     */
    DEPENDENCIES("dependencies", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            String[] possibilities = {"full", "concise"};
            return o instanceof String && Arrays.asList(possibilities).contains(o);
        }
    }),
    /**
     * Exclude results that are not LEX Certified. Requires a Boolean parameter.
     * <ul>
     *     <li><code>true</code> - only return LEX Certified files</li>
     *     <li><code>false</code> - return all files</li>
     * </ul>
     */
    EXCLUDE_NOT_CERTIFIED("exclude_notcert", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Boolean;
        }
    }),
    /**
     * Exclude results that are locked (not downloadable). Requires a Boolean parameter.
     * <ul>
     *     <li><code>true</code> - only return active (not locked) files</li>
     *     <li><code>false</code> - return all files</li>
     * </ul>
     */
    EXCLUDE_LOCKED("exclude_locked", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Boolean;
        }
    });

    private String representation;
    private FilterValidator validator;

    /**
     * Constructs a new filter with a string representation and a parameter validator
     *
     * @param representation String representation
     * @param validator parameter validator
     */
    Filter(String representation, FilterValidator validator) {
        this.representation = representation;
        this.validator = validator;
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
     * Validates a certain parameter for usage in this Filter
     *
     * @param o Object to validate
     * @return <code>true</code> if the object was successfully validated;
     *         <code>false</code> otherwise
     */
    public boolean validateParameter(Object o) {
        return validator.validateParameter(o);
    }
}
