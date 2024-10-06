package model.model;
import model.JsonFileManager;
import model.Item;
import model.Collective;
import java.util.ArrayList; 
import java.util.List;

public class ShoppingListModel
{
    private static ShoppingListModel shoppingListModel = null;

    private Collective collective;
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
        JsonFileManager fileManager = new JsonFileManager();
        collective = fileManager.getSavedCollective();

        if (collective == null) collective = new Collective();

        shoppingList = collective.getShoppingList();
        shoppingListHistory = collective.getShoppingListHistory();
    }

    private void resetItemActivation()
    {
        for (Item item : shoppingList) item.setActive(false);
    }

    private void storeToFile()
    {
        JsonFileManager fileManager = new JsonFileManager();
        collective.setShoppingList(shoppingList);
        collective.setShoppingListHistory(shoppingListHistory);
        fileManager.storeObject(collective);
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