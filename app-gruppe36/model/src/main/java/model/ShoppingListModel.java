package model;

import json.JsonFileManager;
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
        setShoppingLists();
        resetItemActivation();
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
        itemRequest request = new itemRequest(newItem, house.getId());
        house = restTemplate.postForObject(url + "additem", request, House.class);
        houseManager.updateHouse(house);
    }

    public void removeItem(List<Item> items) {
        ItemListRequest request = new ItemListRequest(items, house.getId());
        house = restTemplate.postForObject(url + "removeitem", request, House.class);
        houseManager.updateHouse(house);
    }

    public void buyItems(List<Item> items) {
        ItemListRequest request = new ItemListRequest(items, house.getId());
        house = restTemplate.postForObject(url + "buyitems", items, House.class);
        houseManager.updateHouse(house);
    }

    @Override
    public void updateEvent() {
        ShoppingListViewModel viewmodel = ShoppingListViewModel.getInstance();
        setShoppingLists();
        viewmodel.updateShoppingList();
        viewmodel.updateShoppingListHistory();

    }

}