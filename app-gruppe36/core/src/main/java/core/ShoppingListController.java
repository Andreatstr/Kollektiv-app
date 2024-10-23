package core;
import json.JsonFileManager;
import data.Item;
import data.House;
import java.util.ArrayList; 
import java.util.List;

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

    public void addItem(Item newItem, String id)
    {
        List<Item> shoppingList = controller.getHouse(id).getShoppingList();
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

    public void removeItem(List<Item> items, String id)
    {
        List<Item> shoppingList = controller.getHouse(id).getShoppingList();
        for (Item itemToRemove : items) shoppingList.removeIf(b->b.getItemName().equals(itemToRemove.getItemName()));
        storeToFile();
    }
    
    public void buyItems(List<Item> items, String id)
    {
        List<Item> shoppingListHistory = controller.getHouse(id).getShoppingListHistory();
        removeItem(items,id);
        for (Item item : items) item.setBoughtDate();
        shoppingListHistory.addAll(items);
        storeToFile();
    }
}
