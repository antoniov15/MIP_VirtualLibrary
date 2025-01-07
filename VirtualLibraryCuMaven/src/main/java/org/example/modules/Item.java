package org.example.modules;

import org.json.JSONObject;

public abstract class Item {
    protected String title;
    protected String author;
    protected double price;

    public Item(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public abstract JSONObject toJSON();

    public void edit(String newTitle, String newAuthor, double newPrice) {
        this.title = newTitle;
        this.author = newAuthor;
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (%.2f)", title, author, price);
    }
}
