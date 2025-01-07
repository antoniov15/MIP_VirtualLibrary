package org.example.modules;

import org.json.JSONObject;

public class Book extends Item {
    private int pages;

    public Book(String title, String author, double price, int pages) {
        super(title, author, price);
        this.pages = pages;
    }

    @Override
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("type", "Book");
        json.put("title", title);
        json.put("author", author);
        json.put("price", price);
        json.put("pages", pages);
        return json;
    }

    public void edit(String newTitle, String newAuthor, double newPrice, int newPages) {
        super.edit(newTitle, newAuthor, newPrice);
        this.pages = newPages;
    }
}
