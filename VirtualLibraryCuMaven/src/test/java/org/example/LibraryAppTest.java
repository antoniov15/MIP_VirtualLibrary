package org.example;

import org.example.modules.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

class LibraryAppTest {

    @Test
    public void testAddAndListBooks() {
        LibraryManager libraryManager = new LibraryManager();

        // Adaugă o carte
        libraryManager.addItem(new Book("Test Title", "Test Author", 10.99, 300));

        // Verifică dacă a fost adăugată corect
        assertEquals(1, libraryManager.getItems().size());
        assertTrue(libraryManager.getItems().get(0) instanceof Book);

        // Testează listarea item-urilor
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        libraryManager.listItems();

        String expectedOutput = "Test Title by Test Author (10.99)";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testSaveAndLoadLibrary() throws Exception {
        LibraryManager libraryManager = new LibraryManager();
        libraryManager.addItem(new Book("Test Title", "Test Author", 10.99, 300));

        String testFileName = "testLibrary.json";

        // Salvează biblioteca
        libraryManager.saveToFile(testFileName);

        // Creează o instanță nouă și încarcă datele salvate
        LibraryManager newLibraryManager = new LibraryManager();
        newLibraryManager.loadFromFile(testFileName);

        // Verifică dacă datele au fost încărcate corect
        assertEquals(1, newLibraryManager.getItems().size());
        Item loadedItem = newLibraryManager.getItems().get(0);

        assertTrue(loadedItem instanceof Book);
        assertEquals("Test Title", ((Book) loadedItem).toJSON().getString("title"));
    }
}