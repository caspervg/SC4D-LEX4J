package net.caspervg.lex4j.error;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 21/11/13
 * Time: 13:31
 */
public class LEX4JError {

    public static void log(Level level, String message) {
        Logger.getLogger("LEX4J").log(level, message);
    }
}
