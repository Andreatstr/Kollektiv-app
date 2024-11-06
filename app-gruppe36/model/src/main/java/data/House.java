package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// import java.security.SecureRandom;
import java.util.Map;

public class House {

  private String id;
  private List<Item> shoppingList = new ArrayList<Item>();
  private List<Item> shoppingListHistory = new ArrayList<Item>();
  private List<Person> washingPlanPerson = new ArrayList<Person>();
  private List<Task> washingPlanTask = new ArrayList<Task>();
  private List<WashingTable> washingTable = new ArrayList<WashingTable>();
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

  public List<Person> getWashingPlanPerson() {
    return washingPlanPerson;
  }

  public List<Task> getWashingPlanTask() {
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

  public void setWashingTable(List<WashingTable> washingTable) {
    this.washingTable = washingTable;
  }

  public Map<Integer, List<String>> getWastePlan() {
    return wastePlan;
  }

  public void setWastePlan(Map<Integer, List<String>> wastePlan) {
    this.wastePlan = wastePlan;
  }
}