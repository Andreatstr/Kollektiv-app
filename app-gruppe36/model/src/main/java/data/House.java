package data;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;

public class House
{


    private String id;
    private List<Item> shoppingList = new ArrayList<Item>();
    private List<Item> shoppingListHistory = new ArrayList<Item>();
    private WashingTable washingTable = new WashingTable();  

    public House(){
    }

    public House(String id){
    this.id = id;
    }

    public List<Item>  getShoppingList() {
        return shoppingList;
    }
    
    public List<Item> getShoppingListHistory() {
        return shoppingListHistory;
    }
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    public void setShoppingList(List<Item> shoppingList) {
        this.shoppingList = shoppingList;
    }
    
    public void setShoppingListHistory(List<Item> shoppingListHistory) {
        this.shoppingListHistory = shoppingListHistory;
    }
    
    public WashingTable getWashingTable() {
        return washingTable;
    }
    
    public void setWashingTable(WashingTable washingTable) {
        this.washingTable = washingTable;
    }


}