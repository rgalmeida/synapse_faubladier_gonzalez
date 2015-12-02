package fr.synapsegaming.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <b>JsonReader</b> is an helper for JSON uses
 * 
 * @author Meidi
 * 
 */
public class JsonReader {

    private static final Logger LOGGER = Logger.getLogger(JsonReader.class);

    /**
     * Read all the json contained in a file
     * 
     * @param rd
     *            : the reader
     * @return JSON String
     * @throws IOException
     */
    private static String readAll(final Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Read the json String from an URL source
     * 
     * @param url
     *            : the URL containing JSON String
     * @return JSON String
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException{
        InputStream is = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is,
                Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        return getJsonResponse(jsonText);
    }

    /**
     * Build the JSON response
     * @param jsonText : JSON String
     * @return JSONObject
     */
    public static JSONObject getJsonResponse(String jsonText) {
        try {
            return new JSONObject(jsonText);
        } catch (JSONException e) {
            LOGGER.warn(e);
            return new JSONObject();
        }
    }
}
