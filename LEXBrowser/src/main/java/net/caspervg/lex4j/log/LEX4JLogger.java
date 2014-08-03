package net.caspervg.lex4j.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides a static logger utility
 */
public class LEX4JLogger {

    /**
     * Convenience method for logging within the LEX4J library
     *
     * @param level Level of the log message
     * @param message Log message
     */
    public static void log(Level level, String message) {
        Logger.getLogger("LEX4J").log(level, message);
    }
}
