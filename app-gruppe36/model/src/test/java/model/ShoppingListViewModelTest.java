package model;

import data.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restapi.DummyApi;
import viewmodel.ShoppingListViewModel;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListViewModelTest {

    private static ShoppingListViewModel viewModel;

    @BeforeAll
    public static void setUpClass() {
        HouseManager.getInstance().api = new DummyApi();
        viewModel = ShoppingListViewModel.getInstance();
    }

    @BeforeEach
    public void setUp() {
        viewModel.getShoppingList().clear();
        viewModel.getShoppingListHistory().clear();
    }

    @Test
    public void testAddItem() {
        viewModel.addItem("Milk", "2");
        assertEquals(1, viewModel.getShoppingList().size());
        assertEquals("Milk", viewModel.getShoppingList().get(0).getItemName());
        assertEquals(2, viewModel.getShoppingList().get(0).getItemCount());
    }

    @Test
    public void testAddItemInvalidCount() {
        int initialSize = viewModel.getShoppingList().size();

        // Try adding an item with an empty count
        viewModel.addItem("Bread", "");
        assertEquals(initialSize, viewModel.getShoppingList().size(), "Shopping list size should remain the same with an empty count");
    
        // Try adding an item with a non-numeric count
        viewModel.addItem("Bread", "abc");
        assertEquals(initialSize, viewModel.getShoppingList().size(), "Shopping list size should remain the same with a non-numeric count");
    }

    @Test
    public void testRemoveItems() {
        viewModel.addItem("Juice", "1");
        viewModel.getShoppingList().get(0).setActive(true);
        
        viewModel.removeItems();
        
        assertEquals(0, viewModel.getShoppingList().size(), "No items should remain");
    }

    @Test
    public void testSelectAllCheckBoxChanged() {
        viewModel.addItem("Apples", "5");
        viewModel.addItem("Bananas", "10");

        viewModel.selectAllCheckBoxChanged(true);

        for (Item item : viewModel.getShoppingList()) {
            assertTrue(item.getActive(), "All items should be active");
        }

        viewModel.selectAllCheckBoxChanged(false);

        for (Item item : viewModel.getShoppingList()) {
            assertFalse(item.getActive(), "All items should be inactive");
        }
    }

    @Test
    public void testUpdateShoppingListHistory() {
        viewModel.addItem("Pasta", "2");
        viewModel.getShoppingList().get(0).setActive(true);
        viewModel.buyItems();

        viewModel.updateShoppingListHistory();

        assertEquals(1, viewModel.getShoppingListHistory().size(), "History should update with bought items");
        assertEquals("Pasta", viewModel.getShoppingListHistory().get(0).getItem());
        assertEquals(2, viewModel.getShoppingListHistory().get(0).getCount());
    }
}
