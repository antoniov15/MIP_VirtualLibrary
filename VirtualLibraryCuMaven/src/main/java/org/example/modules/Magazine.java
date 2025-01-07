package org.example.modules;

import org.json.JSONObject;

public class Magazine extends Item{
    private String issue;

    public Magazine(String title, String author, double price, String issue) {
        super(title, author, price);
        this.issue = issue;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("type", "Magazine");
        json.put("title", title);
        json.put("author", author);
        json.put("price", price);
        json.put("issue", issue);
        return json;
    }

    public void edit(String newTitle, String newAuthor, double newPrice, String newIssue) {
        super.edit(newTitle, newAuthor, newPrice);
        this.issue = newIssue;
    }
}
