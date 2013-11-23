package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.bean.BroadCategory;
import net.caspervg.lex4j.bean.Category;
import net.caspervg.lex4j.bean.CategoryOverview;
import net.caspervg.lex4j.bean.TypeCategory;
import net.caspervg.lex4j.log.LEX4JLogger;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;

/**
 * Routing for the Category Endpoint.
 * <b>Attention: </b> methods can throw ResourceExceptions.
 * @see <a href="http://restlet.org/learn/javadocs/2.1/jse/api/org/restlet/data/Status.html">Restlet Status API Javadoc</a>
 * @see <a href="https://github.com/caspervg/SC4Devotion-LEX-API/blob/master/Category.md">LEX API Overview on Github</a>
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 19:11
 */
public class CategoryRoute {

    /**
     * Create a new CategoryRoute, without Authorisation token. Category doesn't require Authentication.
     */
    public CategoryRoute() {
    }

    /**
     * Fetch the LEX Broad Categories
     * @return List of BroadCategories
     */
    public List<BroadCategory> getBroadCategories() {
        ClientResource resource = new ClientResource(Route.BROAD_CATEGORY.url());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<BroadCategory>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve broad categories correctly!");
            return null;
        }
    }

    /**
     * Fetch the LEX Categories
     * @return List of Categories
     */
    public List<Category> getLEXCategories() {
        ClientResource resource = new ClientResource(Route.LEX_CATEGORY.url());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Category>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve LEX categories correctly!");
            return null;
        }
    }

    /**
     * Fetch the LEX Types
     * @return List of Types
     */
    public List<TypeCategory> getLEXTypes() {
        ClientResource resource = new ClientResource(Route.LEX_TYPE.url());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<TypeCategory>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve LEX types correctly!");
            return null;
        }
    }

    /**
     * Fetch the LEX Lot Groups
     * @return List of Categories (Groups)
     */
    public List<Category> getLotGroups() {
        ClientResource resource = new ClientResource(Route.LOTGROUP.url());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Category>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve lot groups correctly!");
            return null;
        }
    }

    /**
     * Fetch the LEX Authors (people who have uploaded at least one file)
     * @return List of Categories (Authors)
     */
    public List<Category> getLotAuthors() {
        ClientResource resource = new ClientResource(Route.AUTHORS.url());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Category>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve broad categories correctly!");
            return null;
        }
    }

    /**
     * Fetch all LEX Categories (BroadCategories, LEXCategories, LEXTypes, LotGroups, Authors)
     * @return CategoryOverview with the results
     */
    public CategoryOverview getCategories() {
        ClientResource resource = new ClientResource(Route.ALL_CATEGORY.url());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), CategoryOverview.class);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve the categories correctly!");
            return null;
        }
    }
}
