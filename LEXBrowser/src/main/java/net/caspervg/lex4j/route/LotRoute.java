package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Comment;
import net.caspervg.lex4j.bean.DependencyList;
import net.caspervg.lex4j.bean.DependencyString;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.log.LEX4JLogger;
import net.caspervg.lex4j.serializer.LEXDateSerializer;
import org.restlet.data.Disposition;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 * Provides routing options for the <i>Lot</i> endpoint
 * @see <a href="http://restlet.org/learn/javadocs/2.1/jse/api/org/restlet/data/Status.html">Restlet Status API Javadoc</a>
 * @see <a href="https://github.com/caspervg/SC4Devotion-LEX-API/blob/master/Lot.md">LEX API Overview on Github</a>
 */
public class LotRoute {

    private Auth auth;

    /**
     * Creates a new LotRoute, without Authorisation token. Some functions may not work!
     */
    public LotRoute() {
        this(null);
    }

    /**
     * Creates a new LotRoute, with Authorisation token.
     * @param auth Authentication token
     */
    public LotRoute(Auth auth) {
        this.auth = auth;
    }

    /**
     * Retrieve the lot/file
     * @param id ID of the lot to be retrieved
     * @return Lot
     */
    public Lot getLot(int id) {
        return getLot(id, Lot.class);
    }

    /**
     * Retrieve the lot/file
     * @param id ID of the lot to be retrieved
     * @param clazz Class to return
     * @param <T> Type to return
     * @return Lot
     */
    public <T extends Lot> T getLot(int id, Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.LOT.url(id));

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LEXDateSerializer()).create();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), clazz);
        } catch (IOException e) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve lot correctly!");
            return null;
        }
    }

    /**
     * Retrieve a list of lots/files
     * @return List of lots/files
     */
    public List<Lot> getLotList() {
       return getLotList(Lot.class);
    }

    /**
     * Retrieve a list of lots/files
     * @param clazz Class to return
     * @param <T> Type to return
     * @return List of lots/files
     */
    public <T extends Lot> List<T> getLotList(final Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.ALL_LOT.url());

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LEXDateSerializer()).create();
        Type listType = new TypeToken<List<T>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve lot list correctly!");
            return null;
        }
    }

    /**
     * Downloads a lot/file
     * @param id ID of the lot/file
     * @param directory Directory where the file should be downloaded
     * @custom.require Authentication
     * @return Download succeeded
     */
    public boolean getLotDownload(int id, File directory) {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        try {
            Representation repr = resource.get(MediaType.APPLICATION_ZIP);
            Disposition disposition = repr.getDisposition();

            File file = new File(directory, disposition.getFilename());
            FileOutputStream fos = new FileOutputStream(file);
            repr.write(fos);

            // Flush remaining buffer to output and close the stream
            fos.flush();
            fos.close();

            return true;
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not write download to the FileOutputStream!");
            return false;
        }
    }

    /**
     * Adds a file to the user's download list
     * @param id : ID of the lot/file
     * @custom.require Authentication
     */
    public void putLotDownloadList(int id) {
        ClientResource resource = new ClientResource(Route.DOWNLOADLIST_LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        resource.get();
    }

    /**
     * Removes a file from the user's download list
     * @param id : ID of the lot/file
     * @custom.require Authentication
     */
    public void deleteLotDownloadList(int id) {
        ClientResource resource = new ClientResource(Route.DOWNLOADLIST_LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        resource.delete();
    }

    /**
     * Retrieves the list of comments for a lot/file
     * @param id ID of the lot/file
     * @return List of comments
     */
    public List<Comment> getComment(int id) {
        return getComment(id, Comment.class);
    }

    /**
     * Retries the list of comments for a lot/file
     * @param id ID of the lot/file
     * @param clazz Class to return
     * @param <T> Type to return
     * @return List of comments
     */
    public <T extends Comment> List<T> getComment(int id, Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.GET_COMMENT.url(id));

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LEXDateSerializer()).create();
        Type listType = new TypeToken<List<T>>() {
        }.getType();

        try {
            String result = resource.get().getText();
            return gson.fromJson(result, listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve lot comments correctly!");
            return null;
        }
    }

    /**
     * Adds a comment and/or rating to a lot/file
     * @param id ID of the lot/file
     * @param rating rating for the lot/file (can be null)
     * @param comment comment for the lot/file (can be null)
     * @custom.require Authentication
     */
    public void postComment(int id, int rating, String comment) {
        ClientResource resource = new ClientResource(Route.ADD_COMMENT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Form form = new Form();
        if (id > 0)
            form.add("rating", String.valueOf(rating));
        if (comment != null && comment.length() > 0)
            form.add("comment", comment);

        resource.post(form);
    }

    /**
     * Retrieves the dependency list for a lot/file
     * @deprecated use {@link LotRoute#getDependencyList} instead
     * @param id ID of the lot/file
     * @return DependencyList
     */
    public DependencyList getDependency(int id) {
        return getDependencyList(id);
    }

    /**
     * Retrieves the dependency list for a lot/file
     * @param id ID of the lot/file
     * @return DependencyList
     */
    public DependencyList getDependencyList(int id) {
        return getDependencyList(id, DependencyList.class);
    }

    public <T extends DependencyList> T getDependencyList(int id, Class<T> clazz) {
        ClientResource resource = new ClientResource(Route.DEPENDENCY.url(id));

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LEXDateSerializer()).create();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), clazz);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve lot dependencies correctly!");
            return null;
        }
    }

    /**
     * Updates the dependency string for a lot/file
     * @param id ID of the lot/file
     * @param dep new dependency string
     * @custom.require Authentication and Administration
     */
    public void updateDependencyString(int id, String dep) {
        ClientResource resource = new ClientResource(Route.DEPENDENCY_STRING.url(id));
        Reference ref = resource.getReference();

        Form form = new Form();
        form.add("string", dep);

        resource.setChallengeResponse(this.auth.toChallenge());
        resource.put(form);
    }

    /**
     * Retrieves the dependency string for a lot/file
     * @param id ID of the lot/file
     * @return the dependency string
     */
    public String getDependencyString(int id) {
        ClientResource resource = new ClientResource(Route.DEPENDENCY_STRING.url(id));

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new LEXDateSerializer()).create();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), DependencyString.class).getDependency();
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve lot dependency string correctly!");
            return null;
        }
    }

}
