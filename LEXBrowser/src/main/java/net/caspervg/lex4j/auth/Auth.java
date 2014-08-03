package net.caspervg.lex4j.auth;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;

/**
 * This class is used to authenticate the user with the SC4Devotion LEX API.
 */
public class Auth {

    private String username;
    private String password;

    /**
     * Constructs a new authentication token with the username and password
     *
     * @param username Username
     * @param password Password
     */
    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of this authentication token
     *
     * @return the username of this authentication token
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this authentication token
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of this authentication token
     *
     * @return the password of this authentication token
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this authentication token
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Helper method to create the authentication token for HTTP Basic Authentication using Restlet
     * @see org.restlet.data.ChallengeResponse
     *
     * @return the ChallengeResponse for this authentication token
     */
    public ChallengeResponse toChallenge() {
        return new ChallengeResponse(ChallengeScheme.HTTP_BASIC, this.username, this.password);
    }
}
