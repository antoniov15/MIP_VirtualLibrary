package org.example.modules;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagazineTest {

    @Test
    void testConstructor() {
        Magazine magazine = new Magazine("Magazine Title", "Magazine Author", 5.99, "Issue 1");

        assertEquals("Magazine Title", magazine.toJSON().getString("title"));
        assertEquals("Magazine Author", magazine.toJSON().getString("author"));
        assertEquals(5.99, magazine.toJSON().getDouble("price"), 0.01);
        assertEquals("Issue 1", magazine.toJSON().getString("issue"));
    }

    @Test
    void testEdit() {
        Magazine magazine = new Magazine("Old Title", "Old Author", 4.99, "Old Issue");
        magazine.edit("New Title", "New Author", 6.99, "New Issue");

        assertEquals("New Title", magazine.toJSON().getString("title"));
        assertEquals("New Author", magazine.toJSON().getString("author"));
        assertEquals(6.99, magazine.toJSON().getDouble("price"), 0.01);
        assertEquals("New Issue", magazine.toJSON().getString("issue"));
    }

    @Test
    void testToJSON() {
        Magazine magazine = new Magazine("JSON Title", "JSON Author", 3.50, "Issue JSON");
        JSONObject json = magazine.toJSON();

        assertEquals("Magazine", json.getString("type"));
        assertEquals("JSON Title", json.getString("title"));
        assertEquals("JSON Author", json.getString("author"));
        assertEquals(3.50, json.getDouble("price"), 0.01);
        assertEquals("Issue JSON", json.getString("issue"));
    }

    @Test
    void testToString() {
        Magazine magazine = new Magazine("String Title", "String Author", 7.99, "Issue 42");
        String expected = "String Title by String Author (7.99)";
        assertEquals(expected, magazine.toString());
    }
}
