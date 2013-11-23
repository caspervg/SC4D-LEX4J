package net.caspervg.lex4j.route;

import org.restlet.data.Reference;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 12:20
 */
public enum Route {

    // User Routes
    ME("/user"),
    USER("/user/%s"),
    ALL_USER("/user/all"),
    DOWNLOAD_LIST("/user/download-list"),
    DOWNLOAD_HISTORY("/user/download-history"),
    REGISTER("/user/register"),
    ACTIVATE("/user/activate"),

    // Lot Routes
    LOT("/lot/%s"),
    ALL_LOT("/lot/all"),
    DOWNLOAD_LOT("/lot/%s/download"),
    DOWNLOADLIST_LOT("/lot/%s/download-list"),
    ADD_COMMENT("/lot/%s/comment"),
    GET_COMMENT("/lot/%s/comment"),
    DEPENDENCY("/lot/%s/dependency"),

    // Search Routes
    SEARCH("/search"),

    // Category Routes
    BROAD_CATEGORY("/category/broad-category"),
    LEX_CATEGORY("/category/lex-category"),
    LEX_TYPE("/category/lex-type"),
    LOTGROUP("/category/group"),
    AUTHORS("/category/author"),
    ALL_CATEGORY("/category/all");


    private static final String base = "http://sc4devotion.com/csxlex/api/";
    private static final String version = "v1";
    private String url;

    /**
     * Creates a new Route
     * @param url Endpoint for the route, uses %s in case an ID has to be filled in
     */
    Route(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url();
    }

    /**
     * @return a full URL for this Route
     */
    public String url() {
        return (this.base + this.version + this.url);
    }

    /**
     * @param id The ID to be filled into the Endpoint
     * @return a full URL for this Route with given ID
     */
    public String url(int id) {
        return String.format(url(), id);
    }

    /**
     * Static function to make adding GET Parameters easier for myself
     * @param ref Reference (part of Restlet library)
     * @param param Map of Strings (key) and Objects (values)
     */
    protected static void addParameters(Reference ref, Map<String,Object> param) {
        for (String key : param.keySet()) {
            Object value = param.get(key);
            if (value != null) {
                ref.addQueryParameter(key, String.valueOf(value));
            }
        }
    }
}
