package org.example.modules;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testConstructor() {
        Book book = new Book("Test Title", "Test Author", 19.99, 300);

        assertEquals("Test Title", book.toJSON().getString("title"));
        assertEquals("Test Author", book.toJSON().getString("author"));
        assertEquals(19.99, book.toJSON().getDouble("price"), 0.01);
        assertEquals(300, book.toJSON().getInt("pages"));
    }

    @Test
    void testEdit() {
        Book book = new Book("Old Title", "Old Author", 15.99, 250);
        book.edit("New Title", "New Author", 20.99, 300);

        assertEquals("New Title", book.toJSON().getString("title"));
        assertEquals("New Author", book.toJSON().getString("author"));
        assertEquals(20.99, book.toJSON().getDouble("price"), 0.01);
        assertEquals(300, book.toJSON().getInt("pages"));
    }

    @Test
    void testToJSON() {
        Book book = new Book("JSON Title", "JSON Author", 25.50, 400);
        JSONObject json = book.toJSON();

        assertEquals("JSON Title", json.getString("title"));
        assertEquals("JSON Author", json.getString("author"));
        assertEquals(25.50, json.getDouble("price"), 0.01);
        assertEquals(400, json.getInt("pages"));
    }

    @Test
    void testToString() {
        Book book = new Book("String Title", "String Author", 19.99, 350);
        String expected = "String Title by String Author (19.99)";
        assertEquals(expected, book.toString());
    }
}
