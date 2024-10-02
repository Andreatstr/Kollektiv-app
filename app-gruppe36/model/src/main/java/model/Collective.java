package model;
import java.util.ArrayList;
import java.util.List;

public class Collective
{
    private Integer id;

    private List<Item> shoppingList = new ArrayList<Item>();
    private List<Item> shoppingListHistory = new ArrayList<Item>();  

    public Collective(){}

    public List<Item>  getShoppingList(){
        return shoppingList;
    }
    
    public List<Item> getShoppingListHistory()
    {
        return shoppingListHistory;
    }

    
    public void setShoppingList(List<Item> shoppingList){
        this.shoppingList = shoppingList;
    }
    
    public void setShoppingListHistory(List<Item> shoppingListHistory)
    {
        this.shoppingListHistory = shoppingListHistory;
    }


}