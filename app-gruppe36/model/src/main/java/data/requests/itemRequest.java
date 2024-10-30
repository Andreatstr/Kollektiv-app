package data.requests;

import data.Item;

public class itemRequest {
    private Item item;

    private String id;

    public itemRequest() {
    }

    public itemRequest(Item item, String id) {
        this.id = id;
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getString() {
        return this.id;
    }

    public void setString(String id) {
        this.id = id;
    }

}