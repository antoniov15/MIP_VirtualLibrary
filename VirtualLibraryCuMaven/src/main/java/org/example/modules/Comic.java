package org.example.modules;

import org.json.JSONObject;

public class Comic extends Item{
    private String artist;

    public Comic(String title, String author, double price, String artist) {
        super(title, author, price);
        this.artist = artist;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("type", "Comic");
        json.put("title", title);
        json.put("author", author);
        json.put("price", price);
        json.put("artist", artist);
        return json;
    }

    public void edit(String newTitle, String newAuthor, double newPrice, String newArtist) {
        super.edit(newTitle, newAuthor, newPrice);
        this.artist = newArtist;
    }
}
