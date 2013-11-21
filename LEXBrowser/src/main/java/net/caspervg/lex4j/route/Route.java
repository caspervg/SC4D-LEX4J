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
    ALLUSER("/user/all"),
    DOWNLOAD_LIST("/user/download-list"),
    DOWNLOAD_HISTORY("/user/download-history"),
    REGISTER("/user/register"),
    ACTIVATE("/user/activate"),

    // Lot Routes
    LOT("/lot/%s"),
    ALLLOT("/lot/all"),
    DOWNLOAD_LOT("/lot/%s/download");

    private static final String base = "http://sc4devotion.com/csxlex/api/";
    private static final String version = "v1";
    private String url;

    Route(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url();
    }

    public String url() {
        return (this.base + this.version + this.url);
    }

    public String url(int id) {
        return String.format(url(), id);
    }

    public static void addParameters(Reference ref, Map<String,Object> param) {
        for (String key : param.keySet()) {
            Object value = param.get(key);
            if (value != null) {
                ref.addQueryParameter(key, String.valueOf(value));
            }
        }
    }
}
