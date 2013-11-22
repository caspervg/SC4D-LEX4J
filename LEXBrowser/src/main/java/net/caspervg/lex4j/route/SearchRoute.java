package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.error.LEX4JLogger;
import net.caspervg.lex4j.error.LEX4JStatusException;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 22/11/13
 * Time: 11:10
 */
public class SearchRoute {

    private Auth auth;
    private Map<String, Object> parameters;

    /**
     * Create a new LotRoute, without Authorisation token. Search doesn't require Authentication.
     */
    public SearchRoute() {
        this.parameters = new HashMap<String, Object>();
    }

    /**
     * Adds a parameter to the search operation
     * @param filter the filter to be added
     * @param value value of the filter to be added. String.valueOf() of this Object is used for the search.
     */
    public void addFilter(Filter filter, Object value) {
        parameters.put(filter.repr(), value);
    }

    /**
     * Removes a parameter from the search operation
     * @param filter removes this filter from the parameters
     */
    public void removeFilter(Filter filter) {
        parameters.remove(filter.repr());
    }

    /**
     * Clears all parameters from the search operation
     */
    public void clearFilters() {
        this.parameters.clear();
    }

    /**
     * Performs the search operation based on filters that are currently active
     * @return List of Lots
     * @throws LEX4JStatusException if the server returns an error
     */
    public List<Lot> doSearch() throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.SEARCH.url());
        Reference reference = resource.getReference();

        Route.addParameters(reference, this.parameters);

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Lot>>() {
        }.getType();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, listType);
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve search results correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("lot", "list", status.getCode());
        }
    }
}
