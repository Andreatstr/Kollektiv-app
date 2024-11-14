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
        // Initialiserer HouseManager med test-api
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
        assertTrue(HouseManager.getInstance().getApi() instanceof DummyApi,
                "The API should be set to DummyApi for testing");
    }

    @Test
    public void testSetCollectiveWithNullId() {
        Boolean result = menueViewModel.setCollective(null);
        assertFalse(result, "setCollective() should return false for a null ID");
    }

    @Test
    public void EventTest() {
        // Initialiserer HouseManager og modellen
        HouseManager houseManager = HouseManager.getInstance();
        houseManager.setHouse("initialHouse");
        WashingPlanModel washingPlanModel = WashingPlanModel.getInstance();
        ShoppingListModel shoppingListModel = ShoppingListModel.getInstance();

        // Oppretter og oppdaterer house med "test" ID
        houseManager.createHouse("test");
        houseManager.updateHouse(houseManager.getHouse());

        // Validerer at house ID er satt korrekt
        assertEquals("test", washingPlanModel.getHouseId(), "House ID in WashingPlanModel should be 'test'");
        assertEquals("test", shoppingListModel.getHouseId(), "House ID in ShoppingListModel should be 'test'");

        // Logger ut og verifiserer at ID-er nullstilles
        houseManager.logOut();
        assertNull(washingPlanModel.getHouseId(), "House ID in WashingPlanModel should be null after logout");
        assertNull(shoppingListModel.getHouseId(), "House ID in ShoppingListModel should be null after logout");

        // Tilbakestiller for å unngå påvirkning på andre tester
        houseManager.createHouse("initialHouse");
        houseManager.setHouse("initialHouse");
    }
}
