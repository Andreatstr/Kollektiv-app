package data.requests;

import data.Item;
import java.util.List;

/**
 * Represents a request containing a list of items and an associated identifier.
 * This class provides methods to get and set the list of items and the identifier.
 */
public class ItemListRequest {

    private List<Item> items;
    private String id;

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