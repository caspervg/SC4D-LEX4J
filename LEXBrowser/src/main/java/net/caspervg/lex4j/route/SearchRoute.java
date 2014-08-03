package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.log.LEX4JLogger;
import net.caspervg.lex4j.serializer.LEXDateSerializer;
import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Provides routing options for the <i>Search</i> endpoint
 * @see <a href="http://restlet.org/learn/javadocs/2.1/jse/api/org/restlet/data/Status.html">Restlet Status API Javadoc</a>
 * @see <a href="https://github.com/caspervg/SC4Devotion-LEX-API/blob/master/Search.md">LEX API Overview on Github</a>
 */
public class SearchRoute {

    private Map<String, Object> parameters;

    /**
     * Constructs a new SearchRoute. This does not require authentication.
     */
    public SearchRoute() {
        this.parameters = new HashMap<String, Object>();
    }

    /**
     * Adds a parameter to the search operation
     *
     * @param filter the filter to be added
     * @param value value of the filter to be added. String.valueOf() of this Object is used for the search.
     */
    public void addFilter(Filter filter, Object value) {
        parameters.put(filter.repr(), value);
    }

    /**
     * Removes a parameter from the search operation
     *
     * @param filter filter to be removed
     */
    public void removeFilter(Filter filter) {
        parameters.remove(filter.repr());
    }

    /**
     * Removes all parameters from the search operation
     */
    public void clearFilters() {
        this.parameters.clear();
    }

    /**
     * Performs the search operation based on filters that are currently active
     *
     * @return the files that were returned by the search operation
     */
    public List<Lot> doSearch() {
        ClientResource resource = new ClientResource(Route.SEARCH.url());
        Reference reference = resource.getReference();

        Route.addParameters(reference, this.parameters);

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LEXDateSerializer()).create();
        Type listType = new TypeToken<List<Lot>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve search results correctly!");
            return null;
        }
    }
}
