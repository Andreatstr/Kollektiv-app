package core;

import data.House;
import data.Item;
import java.util.List;

/**
 * The `ShoppingListController` class manages shopping list operations by
 * connecting to a REST API
 * through the `HouseController` class and storing data using a
 * `JsonFileManager`.
 */
public class ShoppingListController {

    private static final ShoppingListController shoppingListModel = new ShoppingListController();
    private HouseController controller;

    private ShoppingListController() {
        controller = HouseController.getInstance();
    }

    /**
     * The getInstance method ensures that only one instance of
     * ShoppingListController is created and
     * returned.
     *
     * @return An instance of the `ShoppingListController` class is being returned.
     */
    public static ShoppingListController getInstance() {
        return shoppingListModel;
    }

    private void storeToFile() {
        controller.saveHouse();
    }

    /**
     * This Java function adds a new item to a house's shopping list, updating the
     * item count if the
     * item already exists in the list.
     *
     * @param newItem The `newItem` parameter is an object of type `Item` that
     *                represents the item
     *                to be added to the shopping list of a house in the system. It
     *                contains information such as the
     *                item's name and count.
     *
     * @param id      The `id` parameter in the `addItem` method is used to identify
     *                the specific House to
     *                which the new item will be added. It is used to retrieve the
     *                House object from the controller
     *                in order to update its shopping list with the new item.
     *
     * @return The `House` object is being returned from the `addItem` method.
     */
public House addItem(Item newItem, String id) {
    House house = controller.getHouse(id);
    if (house == null) {
        return null;
    }
    
    List<Item> shoppingList = house.getShoppingList();
    
    for (Item item : shoppingList) {
        if (item.getItemName().equals(newItem.getItemName())) {
            item.setItemCount(item.getItemCount() + newItem.getItemCount());
            storeToFile();
            return house;
        }
    }
    
    shoppingList.add(newItem);
    storeToFile();
    return house;
}

    /**
     * This Java function removes items from a house's shopping list based on their
     * item names and then
     * stores the updated list to a file.
     *
     * @param items A list of items that need to be removed from the shopping list
     *              of the house.
     *
     * @param id    The `id` parameter in the `removeItem` method is a unique
     *              identifier that is used to
     *              connect the house to a REST API. It is used to retrieve the
     *              specific house object from the
     *              controller based on this identifier.
     *
     * @return The `removeItem` method returns a `House` object.
     */
    public House removeItem(List<Item> items, String id) { // Connects house to REST API
        House house = controller.getHouse(id);
        if (house == null) {
            return null;
        }
        List<Item> shoppingList = controller.getHouse(id).getShoppingList();
        for (Item itemToRemove : items) {
            shoppingList.removeIf(b -> b.getItemName().equals(itemToRemove.getItemName()));
        }
        storeToFile();
        return house;
    }

    /**
     * The function buys items for a house by updating its shopping list history and
     * storing the changes to a file.
     *
     * @param items A list of items that the user wants to buy.
     * @param id    The `id` parameter is a unique identifier that is used to
     *              connect to a
     *              specific house in the REST API. It is used to retrieve the house
     *              object and perform
     *              operations related to that specific house, such as updating its
     *              shopping list history
     *              with new items.
     * @return The method `buyItems` is returning a `House` object.
     */
    public House buyItems(List<Item> items, String id) { // Connects house to REST API
        House house = controller.getHouse(id);
        if (house == null) {
            return null;
        }
        List<Item> shoppingListHistory = controller.getHouse(id).getShoppingListHistory();
        removeItem(items, id);
        for (Item item : items) {
            item.setBoughtDate();
        }
        shoppingListHistory.addAll(items);
        storeToFile();
        return house;
    }
}
