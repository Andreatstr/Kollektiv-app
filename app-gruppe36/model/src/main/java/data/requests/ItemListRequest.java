package data.requests;

import java.util.List;
import data.Item;

public class ItemListRequest {

    public List<Item> items;
    public String id;

    public ItemListRequest() {
    }

    public ItemListRequest(List<Item> items, String id) {
        this.items = items;
        this.id = id;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}