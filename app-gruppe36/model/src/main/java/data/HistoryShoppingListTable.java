package data;

/**
 * The `HistoryShoppingListTable` class represents an item in a shopping list with details
 * such as item name, count, and when it was added.
 */
public class HistoryShoppingListTable {
    private String item;
    private int count;
    private String when;

    public HistoryShoppingListTable() {
    }

    /**
     * Constructs a new `HistoryShoppingListTable` instance with the specified item name,
     * quantity, and date added.
     *
     * @param item  the name of the item to be added to the shopping list
     * @param count the quantity of the item
     * @param when  the date or time when the item was added to the shopping list
     */
    public HistoryShoppingListTable(String item, int count, String when) {
        this.item = item;
        this.count = count;
        this.when = when;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }
}
