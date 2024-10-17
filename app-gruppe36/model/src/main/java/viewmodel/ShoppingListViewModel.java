package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ShoppingListModel;
import data.HistoryShoppingListTable;
import data.Item;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListViewModel {
  public static ShoppingListViewModel shoppingListViewModel;

  private ShoppingListModel shoppingListModel;

  private ObservableList<Item> shoppingList = FXCollections.observableArrayList();
  private ObservableList<HistoryShoppingListTable> shoppingListHistory = FXCollections.observableArrayList();;

  private ShoppingListViewModel() {
    shoppingListModel = ShoppingListModel.getInstance();
  }

  public static ShoppingListViewModel getInstance() {
    if (shoppingListViewModel != null)
      return shoppingListViewModel;
    shoppingListViewModel = new ShoppingListViewModel();
    return shoppingListViewModel;
  }

  public void addItem(String itemName, String itemCountString) {
    if (itemName.isEmpty() || itemCountString.isEmpty())
      return;
    try {
      int intemCount = Integer.parseInt(itemCountString);
      Item newItem = new Item(itemName, intemCount);
      shoppingListModel.addItem(newItem);
      updateShoppingList();
    } catch (NumberFormatException e) { // specify the exception type
      System.out.println("Invalid number format for item count: " + itemCountString);
    }
    updateShoppingList();
  }

  public void buyItems() {
    List<Item> activeItems = getActiveItems();
    if (activeItems.size() == 0)
      return;
    shoppingListModel.buyItems(activeItems);
    updateShoppingList();

  }

  public void removeItems() {
    List<Item> activeItems = getActiveItems();
    if (activeItems.size() == 0)
      return;
    shoppingListModel.removeItem(activeItems);
    updateShoppingList();

  }

  private List<Item> getActiveItems() {
    List<Item> activeList = new ArrayList<Item>();
    for (Item item : shoppingList) {
      if (item.getActive())
        activeList.add(item);
    }
    return activeList;
  }

  public ObservableList<Item> getShoppingList() {
    updateShoppingList();
    return shoppingList;
  }

  public ObservableList<HistoryShoppingListTable> getShoppingListHistory() {
    updateShoppingListHistory();
    return shoppingListHistory;
  }

  public void updateShoppingList() {
    List<Item> newShoppingList = shoppingListModel.getShoppingList();
    shoppingList.clear();
    shoppingList.addAll(newShoppingList);
  }

  public void updateShoppingListHistory() {
    List<Item> newShoppingList = shoppingListModel.getshoppingListHistory();
    shoppingListHistory.clear();
    for (Item item : newShoppingList) {
      shoppingListHistory
          .add(new HistoryShoppingListTable(item.getItemName(), item.getItemCount(), item.getBoughtDate()));
    }
  }

  public void selectAllCheckBoxChanged(Boolean selected) {
    for (Item item : shoppingList)
      item.setActive(selected);
  }
}
