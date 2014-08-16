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
     * Result to start at.
     *
     * Acceptable parameter types: Integer
     */
    START("start", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return (o instanceof Integer);
        }
    }),
    /**
     * Number of results to return.
     *
     * Acceptable parameter types: Integer
     */
    AMOUNT("amount", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return (o instanceof Integer);
        }
    }),
    /**
     * Ordering of the results.
     *
     * Acceptable parameter types: String
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
     * Ascending or descending.
     *
     * Acceptable parameter types: String
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
     * Only return ID and name, no other information.
     *
     * Acceptable parameter types: Boolean
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
     * Filter by author of the file.
     *
     * Acceptable parameter types: Integer, {@link net.caspervg.lex4j.bean.User} and {@link net.caspervg.lex4j.bean.Category}
     */
    CREATOR("creator", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Integer || o instanceof User || o instanceof Category;
        }
    }, new ParameterHandler() {
        @Override
        public Object handleParameter(Object o) {
            if (o instanceof Integer) return o;
            if (o instanceof User) return ((User) o).getId();
            if (o instanceof Category) return ((Category) o).getId();

            // This shouldn't happen, parameters are validated first!
            return null;
        }
    }),
    /**
     * Filter by broad category of the file.
     *
     * Acceptable parameter types: Integer and {@link net.caspervg.lex4j.bean.BroadCategory}
     */
    BROAD_CATEGORY("broad_category", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof String || o instanceof BroadCategory;
        }
    }, new ParameterHandler() {
        @Override
        public Object handleParameter(Object o) {
            if (o instanceof String) return o;
            if (o instanceof BroadCategory) return ((BroadCategory) o).getImage();

            // This shouldn't happen, parameters are validated first
            return null;
        }
    }),
    /**
     * Filter by LEX category of the file.
     *
     * Acceptable parameter types: Integer and {@link net.caspervg.lex4j.bean.Category}
     */
    LEX_CATEGORY("lex_category", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Integer || o instanceof Category;
        }
    }, new ParameterHandler() {
        @Override
        public Object handleParameter(Object o) {
            if (o instanceof Integer) return o;
            if (o instanceof Category) return ((Category) o).getId();

            // This shouldn't happen, parameters are validated first!
            return null;
        }
    }),
    /**
     * Filter by LEX type of the file.
     *
     * Acceptable parameter types: Integer and {@link net.caspervg.lex4j.bean.TypeCategory}
     */
    LEX_TYPE("lex_type", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Integer || o instanceof TypeCategory;
        }
    }, new ParameterHandler() {
        @Override
        public Object handleParameter(Object o) {
            if (o instanceof Integer) return o;
            if (o instanceof Category) return ((TypeCategory) o).getId();

            // This shouldn't happen, parameters are validated first!
            return null;
        }
    }),
    /**
     * Filter by broad type of the file (a very rough approach).
     *
     * Acceptable parameter types: String
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
     * Filter by lot group of the file.
     *
     * Acceptable parameter types: Integer, {@link net.caspervg.lex4j.bean.Category}
     */
    GROUP("group", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof Integer || o instanceof Category;
        }
    }, new ParameterHandler() {
        @Override
        public Object handleParameter(Object o) {
            if (o instanceof Integer) return o;
            if (o instanceof Category) return ((Category) o).getId();

            // This shouldn't happen, parameters are validated first!
            return null;
        }
    }),
    /**
     * Filter by (a part of) the title of the file.
     *
     * Acceptable parameter types: String
     */
    TITLE("query", new FilterValidator() {
        @Override
        public boolean validateParameter(Object o) {
            return o instanceof String;
        }
    }),
    /**
     * Add lists of dependencies to each search result. By default not active.
     *
     * Acceptable parameter types: String
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
     * Exclude results that are not LEX Certified.
     *
     * Acceptable parameter types: Boolean
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
     * Exclude results that are locked (not downloadable).
     *
     * Acceptable parameter types: Boolean
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
    private ParameterHandler handler;

    /**
     * Constructs a new filter with a string representation and a parameter validator
     *
     * @param representation String representation
     * @param validator parameter validator
     */
    Filter(String representation, FilterValidator validator) {
        this.representation = representation;
        this.validator = validator;

        // Use default handler if none is supplied. Basically this just returns the input
        this.handler = new ParameterHandler() {
            @Override
            public Object handleParameter(Object o) {
                return o;
            }
        };
    }

    /**
     * Constructs a new filter with a string representation, a parameter validator and a parameter handler
     *
     * @param representation String representation
     * @param validator parameter validator
     */
    Filter(String representation, FilterValidator validator, ParameterHandler handler) {
        this(representation, validator);
        this.handler = handler;
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
    protected boolean validateParameter(Object o) {
        return validator.validateParameter(o);
    }

    /**
     * Handles a certain parameter to produce the correct input date for this Filter
     *
     * @param o Object to handle
     * @return the handled parameter
     */
    protected Object handleParameter(Object o) {
        return handler.handleParameter(o);
    }
}
