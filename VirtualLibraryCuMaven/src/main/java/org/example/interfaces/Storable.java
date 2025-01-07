package org.example.interfaces;

public interface Storable {
    void saveToFile(String filename) throws java.io.IOException;
    void loadFromFile(String filename) throws java.io.IOException;
}
