package net.caspervg.lex4j.route;

/**
 * RuntimeException thrown when an unexpected {@link Filter} parameter was supplied.
 */
public class FilterParameterException extends RuntimeException {
    /**
     * Constructs a new FilterParameterException with a message
     *
     * @param message message of this exception
     */
    public FilterParameterException(String message) {
        super(message);
    }
}
