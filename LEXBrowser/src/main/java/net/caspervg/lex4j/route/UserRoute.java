package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.DownloadHistoryItem;
import net.caspervg.lex4j.bean.DownloadListItem;
import net.caspervg.lex4j.bean.User;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.resource.ClientResource;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public User getUser() throws Throwable {
        ClientResource resource = new ClientResource(Route.ME.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        if (resource.getResponse().getStatus().isSuccess()) {
            String result = resource.get().getText();
            return gson.fromJson(result, User.class);
        } else {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }

    public User getUser(int id) throws Throwable {
        ClientResource resource = new ClientResource(Route.USER.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        if (resource.getResponse().getStatus().isSuccess()) {
            String result = resource.get().getText();
            return gson.fromJson(result, User.class);
        } else {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }

    public List<User> getUserList(boolean concise, int start, int amount) throws Throwable {
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

        if (resource.getResponse().getStatus().isSuccess()) {
            String result = resource.get().getText();
            return gson.fromJson(result, listType);
        } else {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }


    public List<DownloadListItem> getDownloadList() throws Throwable {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LIST.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<DownloadListItem>>() {
        }.getType();

        if (resource.getResponse().getStatus().isSuccess()) {
            String result = resource.get().getText();
            return gson.fromJson(result, listType);
        } else {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }

    public List<DownloadHistoryItem> getDownloadHistory() throws Throwable {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_HISTORY.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<DownloadHistoryItem>>() {
        }.getType();

        if (resource.getResponse().getStatus().isSuccess()) {
            String result = resource.get().getText();
            return gson.fromJson(result, listType);
        } else {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }

    public void postRegistration(String username, String password, String email, String fullname) throws Throwable {
        ClientResource resource = new ClientResource(Route.REGISTER.url());

        Form form = new Form();
        form.add("username", username);
        form.add("password_1", password);
        form.add("password_2", password);
        form.add("email", email);
        form.add("fullname", fullname);

        resource.post(form);

        if (!resource.getResponse().getStatus().isSuccess()) {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }

    public void getActivation(String key) throws Throwable {
        ClientResource resource = new ClientResource(Route.ACTIVATE.url());
        Reference ref = resource.getReference();

        Map<String, Object> param = new HashMap<>();
        param.put("activation_key", key);

        Route.addParameters(ref, param);

        resource.get();

        if (!resource.getResponse().getStatus().isSuccess()) {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }

}
