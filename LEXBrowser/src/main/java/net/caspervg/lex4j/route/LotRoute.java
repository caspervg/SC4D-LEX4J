package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Comment;
import net.caspervg.lex4j.bean.DependencyList;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.log.LEX4JLogger;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;

/**
 * Routing for the Lot Endpoint.
 * <b>Attention: </b> methods can throw ResourceExceptions.
 * @see <a href="http://restlet.org/learn/javadocs/2.1/jse/api/org/restlet/data/Status.html">Restlet Status API Javadoc</a>
 * @see <a href="https://github.com/caspervg/SC4Devotion-LEX-API/blob/master/Lot.md">LEX API Overview on Github</a>
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 19:11
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
        ClientResource resource = new ClientResource(Route.LOT.url(id));

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), Lot.class);
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
        ClientResource resource = new ClientResource(Route.ALLLOT.url());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Lot>>() {
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
     * @param fos FileOutputStream where the file should be downloaded
     * @custom.require Authentication
     * @return Download succeeded
     */
    public boolean getLotDownload(int id, FileOutputStream fos) {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        try {
            Representation repr = resource.get(MediaType.APPLICATION_ZIP);
            repr.write(fos);
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
     * Retrieves the list of comments for a lot/file
     * @param id ID of the lot/file
     * @return List of comments
     */
    public List<Comment> getComment(int id) {
        ClientResource resource = new ClientResource(Route.GET_COMMENT.url(id));

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Comment>>() {
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
     * @param id ID of the lot/file
     * @return DependencyList
     */
    public DependencyList getDependency(int id) {
        ClientResource resource = new ClientResource(Route.DEPENDENCY.url(id));

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), DependencyList.class);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve lot dependencies correctly!");
            return null;
        }
    }

}
