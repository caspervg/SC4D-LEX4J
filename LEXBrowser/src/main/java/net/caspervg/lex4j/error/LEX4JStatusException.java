package net.caspervg.lex4j.error;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 22/11/13
 * Time: 8:13
 */
public class LEX4JStatusException extends Throwable {

    private static final Properties prop = new Properties();
    static {
        try {
            prop.load(LEX4JStatusException.class.getResourceAsStream("error.properties"));
        } catch (IOException e) {
            LEX4JLogger.log(Level.SEVERE, "Could not load error.properties");
        }
    }

    private String endpoint;
    private String method;
    private int status;

    public LEX4JStatusException(String endpoint, String method, int status) {
        this.endpoint = endpoint;
        this.method = method;
        this.status = status;
    }

    @Override
    public String getMessage() {
        String key = String.format("%s.%s.%s", endpoint, method, status);
        return prop.getProperty(key);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getMethod() {
        return method;
    }

    public int getStatus() {
        return status;
    }
}
