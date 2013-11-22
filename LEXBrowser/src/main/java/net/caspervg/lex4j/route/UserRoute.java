package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.DownloadHistoryItem;
import net.caspervg.lex4j.bean.DownloadListItem;
import net.caspervg.lex4j.bean.User;
import net.caspervg.lex4j.error.LEX4JLogger;
import net.caspervg.lex4j.error.LEX4JStatusException;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 12:10
 */
public class UserRoute {

    private Auth auth;

    public UserRoute(Auth auth) {
        this.auth = auth;
    }

    public User getUser() throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.ME.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, User.class);
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve user (me) correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("user", "get-me", status.getCode());
        }
    }

    public User getUser(int id) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.USER.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, User.class);
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve user correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("user", "get", status.getCode());
        }
    }

    public List<User> getUserList(boolean concise, int start, int amount) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.ALLUSER.url());
        Reference ref = resource.getReference();

        Map<String, Object> param = new HashMap<>();
        param.put("concise", concise);
        param.put("start", start);
        param.put("amount", amount);

        Route.addParameters(ref, param);

        resource.setChallengeResponse(this.auth.toChallenge());
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>() {
        }.getType();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, listType);
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve user list correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("user", "get-list", status.getCode());
        }
    }


    public List<DownloadListItem> getDownloadList() throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LIST.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<DownloadListItem>>() {
        }.getType();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, listType);
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve download list correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("user", "download-list", status.getCode());
        }
    }

    public List<DownloadHistoryItem> getDownloadHistory() throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_HISTORY.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<DownloadHistoryItem>>() {
        }.getType();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, listType);
            } catch (IOException e) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve download history correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("user", "download-history", status.getCode());
        }
    }

    public void postRegistration(String username, String password, String email, String fullname) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.REGISTER.url());

        Form form = new Form();
        form.add("username", username);
        form.add("password_1", password);
        form.add("password_2", password);
        form.add("email", email);
        form.add("fullname", fullname);

        resource.post(form);

        Status status = resource.getResponse().getStatus();
        if (!status.isSuccess()) {
            throw new LEX4JStatusException("user","register",status.getCode());
        }
    }

    public void getActivation(String key) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.ACTIVATE.url());
        Reference ref = resource.getReference();

        Map<String, Object> param = new HashMap<>();
        param.put("activation_key", key);

        Route.addParameters(ref, param);

        resource.get();

        Status status = resource.getResponse().getStatus();
        if (!status.isSuccess()) {
            throw new LEX4JStatusException("user","activate",status.getCode());
        }
    }

}
