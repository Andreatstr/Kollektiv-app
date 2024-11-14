package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import data.Item;
import java.util.List;
import data.Person;
import data.Task;
import data.WashingTable;
import json.JsonFileManager;

public class CoreTest {
    HouseController houseController;

    @BeforeAll
    public static void initialize()
    {
        String id = "test";
        HouseController.getInstance().createHouse(id);
    }

    @Test
    public void CreateNewHouse()
    {
        String id = "fffff";
        JsonFileManager.getInstance().deleteHouse(id);
        HouseController houseController = HouseController.getInstance();
        houseController.createHouse(id);
        assertEquals(houseController.getHouse(id).getId(), id);
        JsonFileManager.getInstance().deleteHouse(id);
    }

    @Test
    public void TestNewId()
    {
        HouseController houseController = HouseController.getInstance();
        String id = houseController.getNewId();
        assertNotNull(id);
    }

    @Test
    public void TestItemFunctions()
    {
        ShoppingListController shoppingListController = ShoppingListController.getInstance();
        HouseController houseController = HouseController.getInstance();
        shoppingListController.addItem(new Item("fisk",1), "test");
        shoppingListController.addItem(new Item("ost",1), "test");
        assertEquals(houseController.getHouse("test").getShoppingList().get(0).getItemName(),"fisk");
        assertEquals(houseController.getHouse("test").getShoppingList().get(1).getItemName(),"ost");

        shoppingListController.buyItems(List.of(new Item("fisk",1)),"test");
        assertEquals(houseController.getHouse("test").getShoppingListHistory().get(0).getItemName(),"fisk");

        shoppingListController.removeItem(List.of(new Item("ost",1)),"test");
        assertEquals(houseController.getHouse("test").getShoppingList().size(),0);
    }

    @Test
    public void testCreateWashingPlan()
    {
        WashingPlanController washingPlanController = WashingPlanController.getInstance();
        HouseController houseController = HouseController.getInstance();
        washingPlanController.generateWashingPlan(List.of(new Person("Lars")), List.of(new Task("dass")), 1, 20, "test");
        WashingTable table = houseController.getHouse("test").getWashingTable();
        assertEquals(table.getLowestWeek(),1);
        assertEquals(table.getHighestWeek(),20);
    }

    @AfterAll
    public static void end()
    {
        JsonFileManager.getInstance().deleteHouse("test");
    }

}
