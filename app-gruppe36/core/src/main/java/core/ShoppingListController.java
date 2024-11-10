package core;
import java.util.List;

import data.House;
import data.Item;
import json.JsonFileManager;

public class ShoppingListController
{
    private static ShoppingListController shoppingListModel = null;

    private Integer daysInHistory = 14;

    private JsonFileManager jsonFileManager;

    private HouseController controller;

    private ShoppingListController() 
    {
        jsonFileManager = JsonFileManager.getInstance();
        controller = HouseController.getInstance();
    }

    public static ShoppingListController getInstance()
    {   
        if (shoppingListModel != null)
            return shoppingListModel;
        shoppingListModel = new ShoppingListController();
        return shoppingListModel;
    }


    private void storeToFile()
    {
        controller.saveHouse();
    }
    //api 
    public House addItem(Item newItem, String id)
    {   
        House house = controller.getHouse(id);
        if (house == null) return null;
        List<Item> shoppingList = controller.getHouse(id).getShoppingList();
        for (Item item : shoppingList)
        {
            if (item.getItemName().equals(newItem.getItemName()))
            {
                item.setItemCount(item.getItemCount() + newItem.getItemCount());
                storeToFile();
                return house;
            }
        }
        shoppingList.add(newItem);
        storeToFile();
        return house;
    }
    //api 
    public House removeItem(List<Item> items, String id)
    {
        House house = controller.getHouse(id);
        if (house == null) return null;
        List<Item> shoppingList = controller.getHouse(id).getShoppingList();
        for (Item itemToRemove : items) shoppingList.removeIf(b->b.getItemName().equals(itemToRemove.getItemName()));
        storeToFile();
        return house;
    }
    //api 
    public House buyItems(List<Item> items, String id)
    {
        House house = controller.getHouse(id);
        if (house == null) return null;
        List<Item> shoppingListHistory = controller.getHouse(id).getShoppingListHistory();
        removeItem(items,id);
        for (Item item : items) item.setBoughtDate();
        shoppingListHistory.addAll(items);
        storeToFile();
        return house;
    }
}
