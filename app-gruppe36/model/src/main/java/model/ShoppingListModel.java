package model;

import data.House;
import data.Item;
import data.requests.ItemListRequest;
import data.requests.ItemRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import viewmodel.ShoppingListViewModel;

public class ShoppingListModel implements UpdateEvent {

    private static final ShoppingListModel shoppingListModel = new ShoppingListModel();
    private House house;
    private List<Item> shoppingList = new ArrayList<Item>();
    private List<Item> shoppingListHistory = new ArrayList<Item>();
    private HouseManager houseManager;
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/";

    private ShoppingListModel() {
        houseManager = HouseManager.getInstance();
        restTemplate = new RestTemplate();
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

    public List<Item> getshoppingListHistory() {
        return Collections.unmodifiableList(shoppingListHistory);
    }

    // Methods sending to server
    public void addItem(Item newItem) {
        ItemRequest request = new ItemRequest(newItem, house.getId());
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
        house = restTemplate.postForObject(url + "buyitems", request, House.class);
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