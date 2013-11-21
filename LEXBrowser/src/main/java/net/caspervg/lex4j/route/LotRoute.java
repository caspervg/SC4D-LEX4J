package net.caspervg.lex4j.route;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.caspervg.lex4j.auth.Auth;
import net.caspervg.lex4j.bean.Lot;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.List;

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

    public Lot getLot(int id) throws Throwable {
        ClientResource resource = new ClientResource(Route.LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();

        if (resource.getResponse().getStatus().isSuccess()) {
            String result = resource.get().getText();
            return gson.fromJson(result, Lot.class);
        } else {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }

    public List<Lot> getLotList() throws Throwable {
        ClientResource resource = new ClientResource(Route.ALLLOT.url());
        resource.setChallengeResponse(this.auth.toChallenge());

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Lot>>() {
        }.getType();

        if (resource.getResponse().getStatus().isSuccess()) {
            String result = resource.get().getText();
            return gson.fromJson(result, listType);
        } else {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }

    public void getLotDownload(int id, FileOutputStream zos) throws Throwable {
        ClientResource resource = new ClientResource(Route.DOWNLOAD_LOT.url(id));
        resource.setChallengeResponse(this.auth.toChallenge());

        if (resource.getResponse().getStatus().isSuccess()) {
            resource.get(MediaType.APPLICATION_ZIP).write(zos);
        } else {
            throw resource.getResponse().getStatus().getThrowable();
        }
    }
}
