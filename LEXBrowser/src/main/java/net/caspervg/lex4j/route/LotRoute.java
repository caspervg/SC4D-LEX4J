package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.error.LEX4JLogger;
import net.caspervg.lex4j.error.LEX4JStatusException;
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
            throw new LEX4JStatusException("lot", "lotlist", status.getCode());
        }
    }

    public boolean getLotDownload(int id, FileOutputStream zos) throws LEX4JStatusException {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Status status = resource.getResponse().getStatus();
        if (status.isSuccess()) {
            try {
                resource.get(MediaType.APPLICATION_ZIP).write(zos);
                return true;
            } catch (IOException ex) {
                LEX4JLogger.log(Level.WARNING, "Could not write download to the FileOutputStream!");
                return false;
            }
        } else {
            throw new LEX4JStatusException("lot", "download", status.getCode());
        }
    }
}
