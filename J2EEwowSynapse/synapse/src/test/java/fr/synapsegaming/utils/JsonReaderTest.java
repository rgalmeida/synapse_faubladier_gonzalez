package fr.synapsegaming.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class JsonReaderTest {

    private static final String JSON_URL = "http://eu.battle.net/api/wow/character/krasus/XtremZ?fields=appearance";
    private static final String BAD_JSON_URL = "aa{a:a}]";
    private static final JsonReader jsonReader = new JsonReader();

    @Test
    public void testReadJsonFromUrl() throws IOException {
        assertNotNull(JsonReader.readJsonFromUrl(JSON_URL));
    }

    @SuppressWarnings("static-access")
    @Test
    public void testGetJsonResponseFromBadString() {
        assertTrue(jsonReader.getJsonResponse(BAD_JSON_URL).length() == 0);
    }
}
