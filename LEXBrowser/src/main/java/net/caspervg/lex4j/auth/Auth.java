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

    /**
     * Create a new Authenication token
     * @param username Username
     * @param password Password
     */
    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the Username
     * @return the Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new Username
     * @param username the Username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the Password
     * @return the Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets a new Password
     * @param password the Password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Helper method to create the Authentication token for Restlet
     * @return the ChallengeResponse for HTTP Basic Authentication
     */
    public ChallengeResponse toChallenge() {
        return new ChallengeResponse(ChallengeScheme.HTTP_BASIC, this.username, this.password);
    }
}
