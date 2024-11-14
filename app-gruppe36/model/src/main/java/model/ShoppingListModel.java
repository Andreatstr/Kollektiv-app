package model;

import data.House;
import data.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import viewmodel.ShoppingListViewModel;

/**
 * Manages the shopping list and shopping list history for a specific house.
 * This class handles the operations for adding, removing, and buying items within the shopping
 * list. It also interacts with the {@link HouseManager} to update the house and communicates with
 * the server through REST API calls to reflect changes in the shopping list.
 */
public class ShoppingListModel implements UpdateEvent {

    private static final ShoppingListModel shoppingListModel = new ShoppingListModel();
    private House house;
    private List<Item> shoppingList = new ArrayList<Item>();
    private List<Item> shoppingListHistory = new ArrayList<Item>();
    private HouseManager houseManager;

    private ShoppingListModel() {
        houseManager = HouseManager.getInstance();
        houseManager.subscribeToEvents(this);
        setShoppingLists();
    }

    public static ShoppingListModel getInstance() {
        return shoppingListModel;
    }

    private void setShoppingLists() {
        house = HouseManager.getInstance().getHouse();
        shoppingList = house.getShoppingList();
        shoppingListHistory = house.getShoppingListHistory();
        resetItemActivation();
    }

    private void resetItemActivation() {
        for (Item item : shoppingList) {
            item.setActive(false);
        }
    }

    public List<Item> getShoppingList() {
        return Collections.unmodifiableList(shoppingList);
    }


    public List<Item> getShoppingListHistory() {
        return Collections.unmodifiableList(shoppingListHistory);
    }

    // Methods sending to server
    public void addItem(Item newItem) {
        house = houseManager.getApi().addItem(newItem, house.getId());
        houseManager.updateHouse(house);
    }

    public void removeItem(List<Item> items) {
        house = houseManager.getApi().deleteItems(items, house.getId());
        houseManager.updateHouse(house);
    }

    public void buyItems(List<Item> items) {
        house = houseManager.getApi().buyItem(items, house.getId());
        houseManager.updateHouse(house);
    }

    @Override
    public void updateEvent() {
        ShoppingListViewModel viewmodel = ShoppingListViewModel.getInstance();
        setShoppingLists();
        viewmodel.updateShoppingList();
        viewmodel.updateShoppingListHistory();

    }

    @Override
    public void logoutEvent() {
        house = null;
        shoppingList = new ArrayList<>();
        shoppingListHistory = new ArrayList<>();
    }

    /**
     * Retrieves the ID of the currently selected house.
     *
     * @return the ID of the house, or null if no house is selected.
     */
    public String getHouseId() {
        if (house == null) {
            return null;
        }
        return house.getId();
    }
}