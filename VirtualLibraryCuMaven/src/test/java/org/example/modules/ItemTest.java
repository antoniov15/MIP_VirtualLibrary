package org.example.modules;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testToString() {
        Book book = new Book("Title", "Author", 19.99, 300);
        String expected = "Title by Author (19.99)";
        assertEquals(expected, book.toString());
    }

    @Test
    void testEdit() {
        Book book = new Book("Old Title", "Old Author", 15.99, 200);

        // Modificăm atributele
        book.edit("New Title", "New Author", 20.99);

        // Verificăm dacă modificările au fost aplicate corect
        assertEquals("New Title", book.title);
        assertEquals("New Author", book.author);
        assertEquals(20.99, book.price, 0.01);
    }

    @Test
    void testToJSON() {
        Book book = new Book("JSON Title", "JSON Author", 25.50, 400);
        JSONObject json = book.toJSON();

        // Verificăm structura JSON generată
        assertEquals("JSON Title", json.getString("title"));
        assertEquals("JSON Author", json.getString("author"));
        assertEquals(25.50, json.getDouble("price"), 0.01);
        assertEquals(400, json.getInt("pages"));
    }
}