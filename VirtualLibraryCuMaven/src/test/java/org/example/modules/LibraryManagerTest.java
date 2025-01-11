package org.example.modules;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LibraryManagerTest {

    private LibraryManager libraryManager;
    private final String testFile = "testLibrary.json";

    @BeforeEach
    void setUp() {
        libraryManager = new LibraryManager();
    }

    @AfterEach
    void cleanUp() {
        File file = new File(testFile);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddItem() {
        Book book = new Book("Test Title", "Test Author", 19.99, 300);
        libraryManager.addItem(book);

        assertEquals(1, libraryManager.getItems().size());
        assertEquals("Test Title", libraryManager.getItems().get(0).toJSON().getString("title"));
    }

    @Test
    void testSaveToFile() throws IOException {
        Book book = new Book("Test Save", "Author Save", 15.99, 250);
        libraryManager.addItem(book);
        libraryManager.saveToFile(testFile);

        File file = new File(testFile);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    void testLoadFromFile() throws IOException {
        // Creează un fișier curat
        libraryManager.addItem(new Book("Test Load", "Author Load", 12.99, 150));
        libraryManager.saveToFile(testFile);

        // Încarcă datele dintr-o instanță nouă
        LibraryManager newLibraryManager = new LibraryManager();
        newLibraryManager.loadFromFile(testFile);

        assertEquals(1, newLibraryManager.getItems().size());
        Item loadedItem = newLibraryManager.getItems().get(0);

        assertTrue(loadedItem instanceof Book);
        assertEquals("Test Load", ((Book) loadedItem).toJSON().getString("title"));
    }

    @Test
    void testCreateFile() throws IOException {
        libraryManager.createFile(testFile);

        File file = new File(testFile);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    void testDeleteFile() throws IOException {
        libraryManager.createFile(testFile);
        File file = new File(testFile);
        assertTrue(file.exists());

        libraryManager.deleteFile(testFile);
        assertFalse(file.exists());
    }

    @Test
    void testGetItemByIndex() {
        Book book = new Book("Indexed Book", "Author Indexed", 9.99, 100);
        libraryManager.addItem(book);

        Item item = libraryManager.getItemByIndex(0);
        assertNotNull(item);
        assertEquals("Indexed Book", item.toJSON().getString("title"));

        assertThrows(IndexOutOfBoundsException.class, () -> libraryManager.getItemByIndex(1));
    }
}
