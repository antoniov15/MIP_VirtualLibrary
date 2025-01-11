package org.example.modules;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComicTest {

    @Test
    void testConstructor() {
        Comic comic = new Comic("Comic Title", "Comic Author", 10.99, "Comic Artist");

        assertEquals("Comic Title", comic.toJSON().getString("title"));
        assertEquals("Comic Author", comic.toJSON().getString("author"));
        assertEquals(10.99, comic.toJSON().getDouble("price"), 0.01);
        assertEquals("Comic Artist", comic.toJSON().getString("artist"));
    }

    @Test
    void testEdit() {
        Comic comic = new Comic("Old Title", "Old Author", 8.99, "Old Artist");
        comic.edit("New Title", "New Author", 12.99, "New Artist");

        assertEquals("New Title", comic.toJSON().getString("title"));
        assertEquals("New Author", comic.toJSON().getString("author"));
        assertEquals(12.99, comic.toJSON().getDouble("price"), 0.01);
        assertEquals("New Artist", comic.toJSON().getString("artist"));
    }

    @Test
    void testToJSON() {
        Comic comic = new Comic("JSON Title", "JSON Author", 15.50, "JSON Artist");
        JSONObject json = comic.toJSON();

        assertEquals("Comic", json.getString("type"));
        assertEquals("JSON Title", json.getString("title"));
        assertEquals("JSON Author", json.getString("author"));
        assertEquals(15.50, json.getDouble("price"), 0.01);
        assertEquals("JSON Artist", json.getString("artist"));
    }

    @Test
    void testToString() {
        Comic comic = new Comic("String Title", "String Author", 11.99, "String Artist");
        String expected = "String Title by String Author (11.99)";
        assertEquals(expected, comic.toString());
    }
}
