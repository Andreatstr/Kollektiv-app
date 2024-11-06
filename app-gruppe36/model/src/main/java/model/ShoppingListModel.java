package model;
import json.JsonFileManager;
import data.Item;
import data.House;
import java.util.ArrayList; 
import java.util.List;

public class ShoppingListModel
{
    private static ShoppingListModel shoppingListModel = null;

    private House collective;
    private List<Item> shoppingList = new ArrayList<Item>();
    private List<Item> shoppingListHistory = new ArrayList<Item>();  


    private Integer daysInHistory = 14;

    private ShoppingListModel() 
    {
        readShoppingListFromFile();
        resetItemActivation();
    }

    public static ShoppingListModel getInstance()
    {   
        if (shoppingListModel != null)
            return shoppingListModel;
        shoppingListModel = new ShoppingListModel();
        return shoppingListModel;
    }

    private void readShoppingListFromFile()
    {
        collective = HouseManager.getInstance().getHouse();
        shoppingList = collective.getShoppingList();
        shoppingListHistory = collective.getShoppingListHistory();
    }

    private void resetItemActivation()
    {
        for (Item item : shoppingList) item.setActive(false);
    }

    private void storeToFile()
    {
        collective.setShoppingList(shoppingList);
        collective.setShoppingListHistory(shoppingListHistory);
        HouseManager.getInstance().saveHouse();
    }

    public List<Item> getShoppingList()
    {
        return shoppingList;
    }

    public List<Item> getshoppingListHistory()
    {
        boolean historyChanged = false;

        for (Item item : shoppingListHistory)
        {
            if (item.timePassed(daysInHistory))
            {
                shoppingListHistory.remove(item);
                historyChanged = true;
            }
        }

        if (historyChanged) storeToFile(); 
        return shoppingListHistory;
    }

    public void addItem(Item newItem)
    {
        //Sjekke at produktet ikke eksisterer allerede isåfall legg til Funker ikke
        for (Item item : shoppingList)
        {
            if (item.getItemName().equals(newItem.getItemName()))
            {
                item.setItemCount(item.getItemCount() + newItem.getItemCount());
                storeToFile();
                return;
            }
        }

        shoppingList.add(newItem);
        storeToFile();
    }

    public void removeItem(List<Item> items)
    {
        for (Item itemToRemove : items) shoppingList.removeIf(b->b.getItemName().equals(itemToRemove.getItemName()));
        storeToFile();
    }
    
    public void buyItems(List<Item> items)
    {
        removeItem(items);
        for (Item item : items) item.setBoughtDate();
        shoppingListHistory.addAll(items);
        storeToFile();
    }




}
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