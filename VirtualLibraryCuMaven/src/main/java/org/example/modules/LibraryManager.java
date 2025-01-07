package org.example.modules;

import org.example.interfaces.Storable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager implements Storable {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void listItems() {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public Item getItemByIndex(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid item index.");
        }
    }

    @Override
    public void saveToFile(String filename) throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (Item item : items) {
            jsonArray.put(item.toJSON());
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonArray.toString(4));
        }
    }

    @Override
    public void loadFromFile(String filename) throws IOException {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray jsonArray = new JSONArray(content);
            items.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                String type = json.getString("type");
                switch (type) {
                    case "Book":
                        items.add(new Book(
                                json.getString("title"),
                                json.getString("author"),
                                json.getDouble("price"),
                                json.getInt("pages")
                        ));
                        break;
                    case "Magazine":
                        items.add(new Magazine(
                                json.getString("title"),
                                json.getString("author"),
                                json.getDouble("price"),
                                json.getString("issue")
                        ));
                        break;
                    case "Comic":
                        items.add(new Comic(
                                json.getString("title"),
                                json.getString("author"),
                                json.getDouble("price"),
                                json.getString("artist")
                        ));
                        break;
                    default:
                        System.out.println("Unknown type: " + type);
                }
            }
        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage());
            throw e;
        }
    }

    public void createFile(String filename) throws IOException {
        try (FileWriter file = new FileWriter(filename)) {
            file.write("[]"); // Creează un fișier gol JSON cu un array vid
        }
    }

    public void deleteFile(String filename) throws IOException {
        File file = new File(filename);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                throw new IOException("Failed to delete the file.");
            }
        } else {
            throw new IOException("File not found.");
        }
    }
}