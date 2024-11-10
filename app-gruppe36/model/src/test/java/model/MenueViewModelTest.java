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
        HouseManager.getInstance().api = new DummyApi();
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
        assertTrue(HouseManager.getInstance().api instanceof DummyApi, "The API should be set to DummyApi for testing");
    }

    @Test
    public void testSetCollectiveWithValidId() {
        String validId = "12345";
        Boolean result = menueViewModel.setCollective(validId);
        assertTrue(result, "setCollective() should return true for a valid ID");
    }

    @Test
    public void testSetCollectiveWithNullId() {
        Boolean result = menueViewModel.setCollective(null);
        assertFalse(result, "setCollective() should return false for a null ID");
    }

    @Test
    public void testSetCollectiveWithEmptyId() {
        Boolean result = menueViewModel.setCollective("");
        assertFalse(result, "setCollective() should return false for an empty ID");
    }

    @Test
    public void testGetProposedHouseId() {
        String proposedId = menueViewModel.getProposedHouseId();
        assertNotNull(proposedId, "getProposedHouseId() should return a non-null ID");
        assertEquals("fffff", proposedId, "The proposed ID should match the DummyApi ID 'fffff'");
    }

    @Test
    public void testGenerateHouseWithValidId() {
        menueViewModel.getProposedHouseId();
        menueViewModel.generateHouse();
        HouseManager houseManager = HouseManager.getInstance();
        assertNotNull(houseManager.getHouse(), "The house should be created and stored in the HouseManager");
        assertEquals("fffff", houseManager.getHouse().getId(), "The created house ID should be 'fffff'");
    }

    @Test
    public void testLogOut() {
        HouseManager houseManager = HouseManager.getInstance();
        houseManager.subscribeToEvents(new TestUpdateEvent());
        menueViewModel.logOut();
        assertTrue(TestUpdateEvent.logoutCalled, "Subscribers should be notified of logout");
    }

    private static class TestUpdateEvent implements UpdateEvent {
        static boolean logoutCalled = false;

        @Override
        public void updateEvent() {
        }

        @Override
        public void logoutEvent() {
            logoutCalled = true;
        }
    }
}
