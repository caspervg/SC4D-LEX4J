package net.caspervg.lex4j.route;

import net.caspervg.lex4j.auth.Auth;
import org.restlet.data.Reference;
import org.restlet.resource.ClientResource;

import java.util.Map;

/**
 * Provides the various LEX API endpoints supported by the LEX4J library
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
    DEPENDENCY_STRING("/lot/%s/dependency-string"),

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
    private static final String version = "v4";
    private String url;

    /**
     * Constructs a new route based on the endpoint. Use <code>%s</code> if a parameter has be filled in.
     *
     * @param url the endpoint for this route
     */
    Route(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url();
    }

    /**
     * Returns the url for this Route
     *
     * @return the URL for this Route
     */
    public String url() {
        return (base + version + this.url);
    }

    /**
     * Returns the url for this Route, with the ID filled in
     *
     * @param id the ID to be filled into the endpoint
     * @return the url for this Route, with the ID filled in
     */
    public String url(int id) {
        return String.format(url(), id);
    }

    /**
     * Convenience method that helps filling in query parameters
     *
     * @param ref reference to use for the generation
     * @param param parameters and their values to add to the request
     */
    static void addParameters(Reference ref, Map<String,Object> param) {
        for (String key : param.keySet()) {
            Object value = param.get(key);
            if (value != null) {
                ref.addQueryParameter(key, String.valueOf(value));
            }
        }
    }

    static void handleExtraInfo(ClientResource res, ExtraLotInfo extra, Auth auth) {
        Reference ref = res.getReference();

        if (extra.withComments()) {
            ref.addQueryParameter("comments", "true");
        }

        if (extra.withDependencies()) {
            ref.addQueryParameter("dependencies", "true");
        }

        if (extra.withVotes()) {
            ref.addQueryParameter("votes", "true");
        }

        if (extra.withUser()) {
            if (auth == null) {
                throw new IllegalStateException("To be able to retrieve user-related information (e.g. last_downloaded), " +
                        "you need to supply an Auth object to the route");
            }
            ref.addQueryParameter("user", "true");
            res.setChallengeResponse(auth.toChallenge());
        }
    }
}
