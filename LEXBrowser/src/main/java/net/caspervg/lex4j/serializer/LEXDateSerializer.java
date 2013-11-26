package net.caspervg.lex4j.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Casper
 * Date: 26/11/13
 * Time: 8:58
 */
public class LEXDateSerializer implements JsonDeserializer<Date> {

    private static final String[] DATE_FORMATS = new String[] {
            "yyyyMMddHHmmss",
            "yyyyMMdd",
    };

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        for (String format : DATE_FORMATS) {
            try {
                return new SimpleDateFormat(format).parse(jsonElement.getAsString());
            } catch (ParseException ex) {

            }
        }
        throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }
}
