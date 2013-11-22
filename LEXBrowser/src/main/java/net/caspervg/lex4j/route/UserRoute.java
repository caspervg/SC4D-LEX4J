package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.DownloadHistoryItem;
import net.caspervg.lex4j.bean.DownloadListItem;
import net.caspervg.lex4j.bean.User;
import net.caspervg.lex4j.log.LEX4JLogger;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * <b>Attention: </b> methods can throw ResourceExceptions.
 * @see <a href="http://restlet.org/learn/javadocs/2.1/jse/api/org/restlet/data/Status.html">Restlet Status API Javadoc</a>
 * @see <a href="https://github.com/caspervg/SC4Devotion-LEX-API/blob/master/User.md">LEX API Overview on Github</a>
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 12:10
 */
public class UserRoute {

    private Auth auth;

    /**
     * Create a new LotRoute, without Authorisation token. Some functions may not work.
     */
    public UserRoute() {
        this(null);
    }

    /**
     * Create a new LotRoute, with Authorisation token.
     * @param auth Authentication token
     */
    public UserRoute(Auth auth) {
        this.auth = auth;
    }

    /**
     * Retrieve the user that has been authenticated in the constructor
     * @return User's profile
     * @custom.require Authentication
     */
    public User getUser() {
        ClientResource resource = new ClientResource(Route.ME.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), User.class);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve user (me) correctly!");
            return null;
        }
    }

    /**
     * Retrieve the user with requested ID
     * @param id ID of the User
     * @return Requested User's profile
     * @custom.require Authentication and Administrator
     */
    public User getUser(int id) {
        ClientResource resource = new ClientResource(Route.USER.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), User.class);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve user correctly!");
            return null;
        }
    }

    /**
     * Retrieves a list of all Users
     * @param concise true: only ID and Username (the rest will be null) - false: all data
     * @param start start number of results
     * @param amount number of results to return
     * @return List of Users
     * @custom.require Authentication and Administrator
     */
    public List<User> getUserList(boolean concise, int start, int amount) {
        ClientResource resource = new ClientResource(Route.ALLUSER.url());
        Reference ref = resource.getReference();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("concise", concise);
        param.put("start", start);
        param.put("amount", amount);

        Route.addParameters(ref, param);

        resource.setChallengeResponse(this.auth.toChallenge());
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve user list correctly!");
            return null;
        }
    }

    /**
     * Retrieves the download list for the User authenticated in the constructor
     * @return List of DownloadListItems
     * @custom.require Authentication
     */
    public List<DownloadListItem> getDownloadList() {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LIST.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<DownloadListItem>>() {
        }.getType();

        try {
            Representation repr = resource.get();
            return gson.fromJson(repr.getText(), listType);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve download list correctly!");
            return null;
        }
    }

    /**
     * Retrieves the download history for the User authenticated in the constructor
     * @return List of DownloadHistoryItems
     * @custom.require Authentication
     */
    public List<DownloadHistoryItem> getDownloadHistory() {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_HISTORY.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<DownloadHistoryItem>>() {
        }.getType();

        try {
            String result = resource.get().getText();
            return gson.fromJson(result, listType);
        } catch (IOException e) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve download history correctly!");
            return null;
        }
    }

    /**
     * Registers a new user for the SC4D LEX
     * @param username the username
     * @param password the password
     * @param email the e-mail
     * @param fullname the fullname (can be blank)
     */
    public void postRegistration(String username, String password, String email, String fullname) {
        ClientResource resource = new ClientResource(Route.REGISTER.url());

        Form form = new Form();
        form.add("username", username);
        form.add("password_1", password);
        form.add("password_2", password);
        form.add("email", email);
        form.add("fullname", fullname);

        resource.post(form);
    }

    /**
     * Activates a user on the SC4D LEX
     * @param key the activation key
     */
    public void getActivation(String key) {
        ClientResource resource = new ClientResource(Route.ACTIVATE.url());
        Reference ref = resource.getReference();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("activation_key", key);

        Route.addParameters(ref, param);

        resource.get();
    }

}
