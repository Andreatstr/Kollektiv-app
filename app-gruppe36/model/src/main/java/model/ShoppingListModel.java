package model;
import data.Item;
import data.House;
import data.requests.*;
import viewmodel.ShoppingListViewModel;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class ShoppingListModel implements UpdateEvent {
    private static ShoppingListModel shoppingListModel = null;

    private House house;
    private List<Item> shoppingList = new ArrayList<Item>();
    private List<Item> shoppingListHistory = new ArrayList<Item>();
    private HouseManager houseManager;

  private Integer daysInHistory = 14;

    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/";

    private ShoppingListModel() {
        houseManager = HouseManager.getInstance();
        restTemplate = new RestTemplate();
        houseManager.subscribeToEvents(this);
        setShoppingLists();
    }

    public static ShoppingListModel getInstance() {
        if (shoppingListModel != null)
            return shoppingListModel;
        shoppingListModel = new ShoppingListModel();
        return shoppingListModel;
    }

    private void setShoppingLists() {
        house = HouseManager.getInstance().getHouse();
        shoppingList = house.getShoppingList();
        shoppingListHistory = house.getShoppingListHistory();
        resetItemActivation(); 
    }

    private void resetItemActivation() {
        for (Item item : shoppingList)
            item.setActive(false);
    }

    public List<Item> getShoppingList() {
        return shoppingList;
    }

    public List<Item> getshoppingListHistory() {
        return shoppingListHistory;
    }

    /* Metoder som sender til server */
    public void addItem(Item newItem) {
        house = houseManager.api.addItem(newItem, house.getId());
        houseManager.updateHouse(house);
    }

    public void removeItem(List<Item> items) {
        house = houseManager.api.deleteItems(items,house.getId());
        houseManager.updateHouse(house);
    }

    public void buyItems(List<Item> items) {
        house = houseManager.api.buyItem(items,house.getId());
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
        shoppingList = new ArrayList<Item>();
        shoppingListHistory = new ArrayList<Item>();
    }

}