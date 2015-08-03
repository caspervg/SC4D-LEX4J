package net.caspervg.lex4j.route;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Provides routing options for the <i>User</i> endpoint
 * @see <a href="http://restlet.org/learn/javadocs/2.1/jse/api/org/restlet/data/Status.html">Restlet Status API Javadoc</a>
 * @see <a href="https://github.com/caspervg/SC4Devotion-LEX-API/blob/master/User.md">LEX API Overview on Github</a>
 */
public class UserRoute {

    private Auth auth;
    private ObjectMapper mapper;

    /**
     * Constructs a new UserRoute, without authentication.
     *
     * <p>This is useful for {@link #postRegistration} and {@link #getActivation}</p>
     */
    public UserRoute() {
        this(null);
    }

    /**
     * Constructs a new UserRoute with an authentication token.
     *
     * @param auth the authentication token to use
     */
    public UserRoute(Auth auth) {
        this.auth = auth;
        this.mapper = new ObjectMapper();
    }

    /**
     * Returns the current user
     *
     * @return the current user
     * @custom.require Authentication
     */
    public User getUser() {
        ClientResource resource = new ClientResource(Route.ME.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        try {
            Representation repr = resource.get();
            return mapper.readValue(repr.getText(), User.class);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve user (me) correctly!");
            return null;
        }
    }

    /**
     * Returns the user with the primary key
     *
     * @param id the primary key of the user
     * @return the user
     * @custom.require Authentication and Administrator
     */
    public User getUser(int id) {
        ClientResource resource = new ClientResource(Route.USER.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        try {
            Representation repr = resource.get();
            return mapper.readValue(repr.getText(), User.class);
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve user correctly!");
            return null;
        }
    }

    /**
     * Returns all users
     *
     * @param concise <code>true</code> if you only want the primary key and the username;
     *                <code>false</code> otherwise
     * @param start start number of results
     * @param amount number of results to return
     * @return all users
     * @custom.require Authentication and Administrator
     */
    public List<User> getUserList(boolean concise, int start, int amount) {
        ClientResource resource = new ClientResource(Route.ALL_USER.url());
        Reference ref = resource.getReference();

        Map<String, Object> param = new HashMap<>();
        param.put("concise", concise);
        param.put("start", start);
        param.put("amount", amount);

        Route.addParameters(ref, param);

        resource.setChallengeResponse(this.auth.toChallenge());

        try {
            Representation repr = resource.get();
            return mapper.readValue(repr.getText(), new TypeReference<List<User>>() {});
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve user list correctly!");
            return null;
        }
    }

    /**
     * Returns the download list of the current user
     *
     * @return the download list of the current user
     * @custom.require Authentication
     */
    public List<DownloadListItem> getDownloadList() {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LIST.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        try {
            Representation repr = resource.get();
            return mapper.readValue(repr.getText(), new TypeReference<List<DownloadListItem>>() {});
        } catch (IOException ex) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve download list correctly!");
            return null;
        }
    }

    /**
     * Returns the download history of the current user
     *
     * @return the download list of the current user
     * @custom.require Authentication
     */
    public List<DownloadHistoryItem> getDownloadHistory() {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_HISTORY.url());
        resource.setChallengeResponse(this.auth.toChallenge());


        try {
            String result = resource.get().getText();
            return mapper.readValue(result, new TypeReference<List<DownloadHistoryItem>>() {});
        } catch (IOException e) {
            LEX4JLogger.log(Level.WARNING, "Could not retrieve download history correctly!");
            return null;
        }
    }

    /**
     * Registers a new user for the SC4D LEX
     *
     * @param username the username for the new user
     * @param password the password for the new user
     * @param email the e-mail for the new user
     * @param fullname the name for the new user (can be blank)
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
     *
     * @param key the activation key to use
     */
    public void getActivation(String key) {
        ClientResource resource = new ClientResource(Route.ACTIVATE.url());
        Reference ref = resource.getReference();

        Map<String, Object> param = new HashMap<>();
        param.put("activation_key", key);

        Route.addParameters(ref, param);

        resource.get();
    }

}
