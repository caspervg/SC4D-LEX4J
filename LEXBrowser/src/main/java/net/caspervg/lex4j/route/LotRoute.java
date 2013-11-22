package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Comment;
import net.caspervg.lex4j.bean.DependencyList;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.error.LEX4JLogger;
import net.caspervg.lex4j.error.LEX4JStatusException;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 19:11
 */
public class LotRoute {

    private Auth auth;

    public LotRoute(Auth auth) {
        this.auth = auth;
    }

    public Lot getLot(int id) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, Lot.class);
            } catch (IOException e) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve lot correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("lot", "get", status.getCode());
        }
    }

    public List<Lot> getLotList() throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.ALLLOT.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Lot>>() {
        }.getType();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, listType);
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve lot list correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("lot", "list", status.getCode());
        }
    }

    public boolean getLotDownload(int id, FileOutputStream fos) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                resource.get(MediaType.APPLICATION_ZIP).write(fos);
                return true;
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not write download to the FileOutputStream!");
                return false;
            }
        } else {
            throw new LEX4JStatusException("lot", "download", status.getCode());
        }
    }

    public boolean getLotDownload(Lot lot, FileOutputStream fos) throws LEX4JStatusException {
        return getLotDownload(lot.getId(), fos);
    }

    public boolean putLotDownloadList(int id) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.DOWNLOADLIST_LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        resource.get();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            return true;
        } else {
            throw new LEX4JStatusException("lot", "download-list", status.getCode());
        }
    }

    public boolean putLotDownloadList(Lot lot) throws LEX4JStatusException {
        return putLotDownloadList(lot.getId());
    }

    public List<Comment> getComment(int id) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.GET_COMMENT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Comment>>() {
        }.getType();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, listType);
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve lot comments correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("lot", "get-comment", status.getCode());
        }
    }

    public boolean postComment(int id, int rating, String comment) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.ADD_COMMENT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Form form = new Form();
        if (id > 0)
            form.add("rating", String.valueOf(rating));
        if (comment.length() > 0)
            form.add("comment", comment);

        resource.post(form);

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            return true;
        } else {
            throw new LEX4JStatusException("lot","post-comment", status.getCode());
        }
    }

    public DependencyList getDependency(int id) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.DEPENDENCY.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                String result = resource.get().getText();
                return gson.fromJson(result, DependencyList.class);
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not retrieve lot dependencies correctly!");
                return null;
            }
        } else {
            throw new LEX4JStatusException("lot", "dependency", status.getCode());
        }
    }

}
