package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import data.HistoryShoppingListTable;
import data.Item;
import data.Task;
import viewmodel.MenueViewModel;
import viewmodel.ShoppingListViewModel;
import data.WashingTable;

public class ModelTest {

    @Test
    public void TestBuyItem() {
        String itemName = "Pasta";
        String itemCount = "5";
        ShoppingListViewModel shoppingListViewModel = ShoppingListViewModel.getInstance();
        shoppingListViewModel.addItem(itemName, itemCount);
        for (Item item : shoppingListViewModel.getShoppingList()) {
            if (itemName.equals(itemName))
                ;
            item.setActive(true);
        }
        shoppingListViewModel.buyItems();

        for (HistoryShoppingListTable item : shoppingListViewModel.getShoppingListHistory()) {
            if (item.getItem().equals(itemName))
                assertEquals(itemCount, String.valueOf(item.getCount()));
        }
    }

    @Test
    public void CreateNewHouse() {
        HouseManager houseManager = HouseManager.getInstance();
        String id = houseManager.getNewId();
        houseManager.CreateHouse(id);
        assertEquals(houseManager.setHouse(id), true);
    }

    @Test
    public void SetHouseToNonExisting() {
        String id = "fffff";
        HouseManager houseManager = HouseManager.getInstance();
        assertEquals(houseManager.setHouse(id), false);
    }

    @Test
    public void MenueViewModelGenerateHouseTest() {
        MenueViewModel menueViewModel = MenueViewModel.getInstance();
        String id = menueViewModel.getProposedHouseId();
        menueViewModel.generateHouse();
        menueViewModel.setCollective(id);
        assertEquals(HouseManager.getInstance().getHouse().getId(), id);
    }

    // Test for default constructor and parameterized constructor in Item
    @Test
    public void testItemConstructors() {
        // Testing the default constructor
        Item defaultItem = new Item();
        assertEquals(null, defaultItem.getItemName());
        assertEquals(0, defaultItem.getItemCount());

        // Testing the parameterized constructor
        Item parameterizedItem = new Item("Såpe", 3);
        assertEquals("Såpe", parameterizedItem.getItemName());
        assertEquals(3, parameterizedItem.getItemCount());
    }

    // Test for constructors in HistoryShoppingListTable
    @Test
    public void testHistoryShoppingListTableConstructors() {
        // Testing the default constructor
        HistoryShoppingListTable defaultTable = new HistoryShoppingListTable();
        assertEquals(null, defaultTable.getItem());
        assertEquals(0, defaultTable.getCount());
        assertEquals(null, defaultTable.getWhen());

        // Testing the parameterized constructor
        HistoryShoppingListTable parameterizedTable = new HistoryShoppingListTable("Såpe", 3, "2024-10-09");
        assertEquals("Såpe", parameterizedTable.getItem());
        assertEquals(3, parameterizedTable.getCount());
        assertEquals("2024-10-09", parameterizedTable.getWhen());
    }

    // Test for constructors in WashingTable
    @Test
    public void testWashingTableConstructors() {
        // Testing the default constructor
        WashingTable table = new WashingTable();
        assertEquals(0, table.getWashingPlans().size());
    }

    // Test for constructors in NewWashingTableTask
    @Test
    public void testNewWashingTableTaskConstructors() {
        // Testing the default constructor
        Task task = new Task();
        assertEquals(null, task.getTask());

        // Testing setting values after construction
        task.setTask("Vaske bad");
        assertEquals("Vaske bad", task.getTask());
    }
}
