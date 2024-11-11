package model;

import restapi.DummyApi;
import viewmodel.MenueViewModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenueViewModelTest {

    private MenueViewModel menueViewModel;

    @BeforeAll
    public static void setUpClass() {
        HouseManager.getInstance().setTestApi();
    }

    @BeforeEach
    public void setUp() {
        menueViewModel = MenueViewModel.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        MenueViewModel instance1 = MenueViewModel.getInstance();
        MenueViewModel instance2 = MenueViewModel.getInstance();
        assertSame(instance1, instance2, "getInstance() should return the same instance");
    }

    @Test
    public void testSetTestApi() {
        menueViewModel.setTestApi();
        assertTrue(HouseManager.getInstance().getApi() instanceof DummyApi, "The API should be set to DummyApi for testing");
    }
    /*
     * @Test
     * public void testSetCollectiveWithValidId() {
     * String validId = "12345";
     * Boolean result = menueViewModel.setCollective(validId);
     * assertTrue(result, "setCollective() should return true for a valid ID");
     * }
     */

    @Test
    public void testSetCollectiveWithNullId() {
        Boolean result = menueViewModel.setCollective(null);
        assertFalse(result, "setCollective() should return false for a null ID");
    }

    @Test
    public void EventTest()
    {
        HouseManager houseManager = HouseManager.getInstance();
        houseManager.setHouse("fffff");
        WashingPlanModel washingPlanModel = WashingPlanModel.getInstance();
        ShoppingListModel shoppingListModel = ShoppingListModel.getInstance();
        houseManager.createHouse("test");
        houseManager.updateHouse(houseManager.getHouse());
        assertEquals(washingPlanModel.getHouseId(),"test");
        assertEquals(shoppingListModel.getHouseId(),"test");
        houseManager.logOut();
        assertNull(washingPlanModel.getHouseId());
        assertNull(shoppingListModel.getHouseId());

        // Reset so it does not crash on other tests
        houseManager.createHouse("fffff");
        houseManager.setHouse("fffff");
    }

}