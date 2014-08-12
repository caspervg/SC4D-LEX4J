package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import net.caspervg.lex4j.bean.BroadCategory;
import net.caspervg.lex4j.bean.Category;
import net.caspervg.lex4j.bean.CategoryOverview;
import net.caspervg.lex4j.bean.TypeCategory;
import net.caspervg.lex4j.log.LEX4JLogger;
import net.caspervg.lex4j.reflection.ParameterizedList;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

/**
 * Provides routing options for the <i>Category</i> endpoint
 * @see <a href="http://restlet.org/learn/javadocs/2.1/jse/api/org/restlet/data/Status.html">Restlet Status API Javadoc</a>
 * @see <a href="https://github.com/caspervg/SC4Devotion-LEX-API/blob/master/Category.md">LEX API Overview on Github</a>
 */
public class CategoryRoute {

    /**
     * Constructs a CategoryRoute. This does not require authentication.
     */
    public CategoryRoute() {
    }

    /**
     * Returns the broad categories
     *
     * @return the broad categories
     */
    public List<BroadCategory> getBroadCategories() {
        return getBroadCategories(BroadCategory.class);
    }

    /**
     * Returns the broad categories
     *
     * @param clazz Class to return
     * @param <T> Type to return
     * @return the broad categories
     */
    public <T extends Category> List<T> getBroadCategories(Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.BROAD_CATEGORY.url());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), new ParameterizedList<T>(clazz));
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve broad categories correctly!");
            return null;
        }
    }

    /**
     * Returns the LEX categories
     *
     * @return the LEX categories
     */
    public List<Category> getLEXCategories() {
        return getLEXCategories(Category.class);
    }

    /**
     * Returns the LEX categories
     *
     * @param clazz Class to return
     * @param <T> Type to return
     * @return the LEX categories
     */
    public <T extends Category> List<T> getLEXCategories(Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.LEX_CATEGORY.url());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), new ParameterizedList<T>(clazz));
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve LEX categories correctly!");
            return null;
        }
    }

    /**
     * Returns the LEX types
     *
     * @return the LEX types
     */
    public List<TypeCategory> getLEXTypes() {
        return getLEXTypes(TypeCategory.class);
    }

    /**
     * Returns the LEX types
     *
     * @param clazz Class to return
     * @param <T> Type to return
     * @return the LEX types
     */
    public <T extends Category> List<T> getLEXTypes(Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.LEX_TYPE.url());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), new ParameterizedList<T>(clazz));
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve LEX types correctly!");
            return null;
        }
    }

    /**
     * Returns the lot groups
     *
     * @return the lot groups
     */
    public List<Category> getLotGroups() {
        return getLotGroups(Category.class);
    }

    /**
     * Returns the lot groups
     *
     * @param clazz Class to return
     * @param <T> Type to return
     * @return the lot groups
     */
    public <T extends Category> List<T> getLotGroups(Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.LOTGROUP.url());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), new ParameterizedList<T>(clazz));
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve lot groups correctly!");
            return null;
        }
    }

    /**
     * Returns the lot authors (these are people who have uploaded at least one file)
     *
     * @return the lot authors
     */
    public List<Category> getLotAuthors() {
        return getLotAuthors(Category.class);
    }

    /**
     * Returns the lot authors (these are people who have uploaded at least one file)
     *
     * @param clazz Class to return
     * @param <T> Type to return
     * @return the lot authors
     */
    public <T extends Category> List<T> getLotAuthors(Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.AUTHORS.url());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), new ParameterizedList<T>(clazz));
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve broad categories correctly!");
            return null;
        }
    }

    /**
     * Returns all the categories
     *
     * @return all the categories
     */
    public CategoryOverview getCategories() {
        return getCategories(CategoryOverview.class);
    }

    /**
     * Returns all the categories
     *
     * @param clazz Class to return
     * @param <T> Type to return
     * @return all the categories
     */
    public <T extends CategoryOverview> T getCategories(Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.ALL_CATEGORY.url());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), clazz);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve the categories correctly!");
            return null;
        }
    }
}
