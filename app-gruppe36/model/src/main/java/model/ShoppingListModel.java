package model;

import data.House;
import data.Item;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListModel {
  private static ShoppingListModel shoppingListModel = null;

  private House collective;
  private List<Item> shoppingList = new ArrayList<Item>();
  private List<Item> shoppingListHistory = new ArrayList<Item>();

  private Integer daysInHistory = 14;

  private ShoppingListModel() {
    readShoppingListFromFile();
    resetItemActivation();
  }

  public static ShoppingListModel getInstance() {
    if (shoppingListModel != null) {
      return shoppingListModel;
    }
    shoppingListModel = new ShoppingListModel();
    return shoppingListModel;
  }

  private void readShoppingListFromFile() {
    collective = HouseManager.getInstance().getHouse();
    shoppingList = collective.getShoppingList();
    shoppingListHistory = collective.getShoppingListHistory();
  }

  private void resetItemActivation() {
    for (Item item : shoppingList) {
      item.setActive(false);
    }
  }

  private void storeToFile() {
    collective.setShoppingList(shoppingList);
    collective.setShoppingListHistory(shoppingListHistory);
    HouseManager.getInstance().saveHouse();
  }

  public List<Item> getShoppingList() {
    return shoppingList;
  }

  public List<Item> getshoppingListHistory() {
    boolean historyChanged = false;

    for (Item item : shoppingListHistory) {
      if (item.timePassed(daysInHistory)) {
        shoppingListHistory.remove(item);
        historyChanged = true;
      }
    }

    if (historyChanged) {
      storeToFile();
    }
    return shoppingListHistory;
  }

  public void addItem(Item newItem) {
    for (Item item : shoppingList) {
      if (item.getItemName().equals(newItem.getItemName())) {
        item.setItemCount(item.getItemCount() + newItem.getItemCount());
        storeToFile();
        return;
      }
    }

    shoppingList.add(newItem);
    storeToFile();
  }

  public void removeItem(List<Item> items) {
    for (Item itemToRemove : items) {
      shoppingList.removeIf(b -> b.getItemName().equals(itemToRemove.getItemName()));
    }
    storeToFile();
  }

  public void buyItems(List<Item> items) {
    removeItem(items);
    for (Item item : items) {
      item.setBoughtDate();
    }
    shoppingListHistory.addAll(items);
    storeToFile();
  }
}