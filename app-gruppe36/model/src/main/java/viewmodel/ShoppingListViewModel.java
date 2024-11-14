package viewmodel;

import data.HistoryShoppingListTable;
import data.Item;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ShoppingListModel;

/**
 * The `ShoppingListViewModel` class manages the shopping list data and provides methods for adding,
 * buying, removing items, and updating the shopping list.
 */
public class ShoppingListViewModel {

    private static final ShoppingListViewModel shoppingListViewModel = new ShoppingListViewModel();
    private ShoppingListModel shoppingListModel;
    private ObservableList<Item> shoppingList = FXCollections.observableArrayList();
    private ObservableList<HistoryShoppingListTable> shopHist = FXCollections.observableArrayList();

    private ShoppingListViewModel() {
        shoppingListModel = ShoppingListModel.getInstance();
    }

    /**
     * The function getInstance() returns a single instance of ShoppingListViewModel using the
     * singleton pattern.
     *
     * @return The `ShoppingListViewModel` instance is being returned.
     */
    public static ShoppingListViewModel getInstance() {
        return shoppingListViewModel;
    }

    /**
    * The addItem function takes in an item name and item count as parameters,
    * creates a new Item object, adds it to a shopping list model, and updates
    * the shopping list.
    *
    * @param itemName The `itemName` parameter is a String that represents the
    * name of the item to be added to the shopping list.
    *
    * @param itemCountString The `itemCountString` parameter is a string representing
    * the quantity or count of the item being added to the shopping list. It should be
    * a numerical value that can be parsed into an integer using `Integer.parseInt()`
    * in the `addItem` method.
    */
    public void addItem(String itemName, String itemCountString) {
        if (itemName.isEmpty() || itemCountString.isEmpty()) {
            return;
        }
        try {
            int intemCount = Integer.parseInt(itemCountString);
            Item newItem = new Item(itemName, intemCount);
            shoppingListModel.addItem(newItem);
            updateShoppingList();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for item count: " + itemCountString);
        }
        updateShoppingList();
    }

    /**
     * The `buyItems` function checks for active items, buys them, and updates the shopping list
     * accordingly.
     */
    public void buyItems() {
        List<Item> activeItems = getActiveItems();
        if (activeItems.size() == 0) {
            return;
        }
        shoppingListModel.buyItems(activeItems);
        updateShoppingList();
    }

    /**
     * The `removeItems` function removes active items from the shopping 
     * list and updates the shopping list if there are active items present.
     */
    public void removeItems() {
        List<Item> activeItems = getActiveItems();
        if (activeItems.size() == 0) {
            return;
        }
        shoppingListModel.removeItem(activeItems);
        updateShoppingList();
    }

    private List<Item> getActiveItems() {
        List<Item> activeList = new ArrayList<Item>();
        for (Item item : shoppingList) {
            if (item.getActive()) {
                activeList.add(item);
            }
        }
        return activeList;
    }

    public ObservableList<Item> getShoppingList() {
        updateShoppingList();
        return shoppingList;
    }

    public ObservableList<HistoryShoppingListTable> getShoppingListHistory() {
        updateShoppingListHistory();
        return shopHist;
    }

    /**
     * The `updateShoppingList` function updates the shopping list by clearing the 
     * current list and adding items from a new shopping list obtained from the model.
     */
    public void updateShoppingList() {
        List<Item> newShoppingList = shoppingListModel.getShoppingList();
        shoppingList.clear();
        shoppingList.addAll(newShoppingList);
    }

    /**
     * The `updateShoppingListHistory` function updates the shopping list 
     * history by creating a new list of history items based on the current shopping list.
     */
    public void updateShoppingListHistory() {
        List<Item> newShoppingList = shoppingListModel.getShoppingListHistory();
        shopHist.clear();
        for (Item item : newShoppingList) {
            String name = item.getItemName();
            int count = item.getItemCount();
            String date = item.getBoughtDate();
            shopHist.add(new HistoryShoppingListTable(name, count, date));
        }
    }

    /**
     * Updates the selection status of all items in the shopping list.
     * Sets each item's active state to the specified selection state.
     *
     * @param selected the new active state to apply to all items in the shopping list;
     * {@code true} to mark all items as active, {@code false} to mark all as inactive.
     */
    public void selectAllCheckBoxChanged(Boolean selected) {
        for (Item item : shoppingList) {
            item.setActive(selected);
        }
    }
}
