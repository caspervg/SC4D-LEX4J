package net.caspervg.lex4j.auth;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 12:12
 */
public class Auth {

    private String username;
    private String password;

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ChallengeResponse toChallenge() {
        return new ChallengeResponse(ChallengeScheme.HTTP_BASIC, this.username, this.password);
    }
}
