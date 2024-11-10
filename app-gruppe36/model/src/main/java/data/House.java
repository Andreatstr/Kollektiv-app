package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The `House` class represents a house with properties such as id, shopping lists,
 * washing table, and waste plan.
 */
public class House {

    private String id;
    private List<Item> shoppingList = new ArrayList<Item>();
    private List<Item> shoppingListHistory = new ArrayList<Item>();
    private WashingTable washingTable = new WashingTable();
    private Map<Integer, List<String>> wastePlan = new HashMap<>();

    public House() {
    }

    public House(String id) {
        this.id = id;
    }

    public List<Item> getShoppingList() {
        return shoppingList;
    }

    public List<Item> getShoppingListHistory() {
        return shoppingListHistory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Map<Integer, List<String>> getWastePlan() {
        return wastePlan;
    }

    public void setWastePlan(Map<Integer, List<String>> wastePlan) {
        this.wastePlan = wastePlan;
    }
}