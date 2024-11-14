package data.requests;

import data.Item;

/**
 * Represents a request containing a single item and an associated identifier.
 * This class provides methods to get and set the item and its associated identifier.
 */
public class ItemRequest {
    
    private Item item;
    private String id;

    public ItemRequest() {
    }

    public ItemRequest(Item item, String id) {
        this.id = id;
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}