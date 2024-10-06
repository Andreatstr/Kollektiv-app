package model;
import java.util.ArrayList;
import java.util.List;

public class Collective
{
    private Integer id;

    private List<Item> shoppingList = new ArrayList<Item>();
    private List<Item> shoppingListHistory = new ArrayList<Item>();

    private List<Person> washingPlanPerson = new ArrayList<Person>();
    private List<Task> washingPlanTask = new ArrayList<Task>();
    private List<WashingTable> washingTable = new ArrayList<WashingTable>();  

    public Collective(){}

    public List<Item>  getShoppingList() {
        return shoppingList;
    }
    
    public List<Item> getShoppingListHistory() {
        return shoppingListHistory;
    }
    
    public void setShoppingList(List<Item> shoppingList) {
        this.shoppingList = shoppingList;
    }
    
    public void setShoppingListHistory(List<Item> shoppingListHistory) {
        this.shoppingListHistory = shoppingListHistory;
    }




    public List<Person>  getWashingPlanPerson() {
        return washingPlanPerson;
    }

    public List<Task>  getWashingPlanTask() {
        return washingPlanTask;
    }
    
    public List<WashingTable> getWashingTable() {
        return washingTable;
    }
    
    public void setWashingPlanPerson(List<Person> newPersonList) {
        this.washingPlanPerson = newPersonList;
    }

    public void setWashingPlanTask(List<Task> newTaskList) {
        this.washingPlanTask = newTaskList;
    }
    
    public void setWashingTable(List<WashingTable> newWashingTable) {
        this.washingTable = newWashingTable;
    }


}