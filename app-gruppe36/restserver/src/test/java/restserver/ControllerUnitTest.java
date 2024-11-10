package restserver;

import org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito.*;

import data.House;
import core.HouseController;
import core.ShoppingListController;
import core.WashingPlanController;
import data.requests.CreateWashingPlanRequest;
import data.requests.ItemListRequest;
import data.requests.ItemRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class ControllerUnitTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private HouseController houseController;

    @Mock
    private ShoppingListController shoppingListController;

    @Mock
    private WashingPlanController washingPlanController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNewValidId() {
        // Mock oppførselen til houseController.getNewId()
        when(houseController.getNewId()).thenReturn("test-id");

        // Kall metoden i Controller og verifiser resultatet
        String result = controller.getNewValidId();
        assertEquals("test-id", result);

        // Verifiser at houseController.getNewId() ble kalt én gang
        verify(houseController, times(1)).getNewId();
    }

    @Test
    public void testCreateNewHouse() {
        House mockHouse = new House();
        when(houseController.CreateHouse("test-id")).thenReturn(mockHouse);

        House result = controller.CreateNewHouse("test-id");
        assertEquals(mockHouse, result);

        verify(houseController, times(1)).CreateHouse("test-id");
    }

    @Test
    public void testGetHouse() {
        House mockHouse = new House();
        when(houseController.getHouse("test-id")).thenReturn(mockHouse);

        House result = controller.GetHouse("test-id");
        assertEquals(mockHouse, result);

        verify(houseController, times(1)).getHouse("test-id");
    }

    @Test
    public void testAddItem() {
        House mockHouse = new House();
        ItemRequest request = new ItemRequest("item1", "test-id");

        when(shoppingListController.addItem("item1", "test-id")).thenReturn(mockHouse);

        House result = controller.additem(request);
        assertEquals(mockHouse, result);

        verify(shoppingListController, times(1)).addItem("item1", "test-id");
    }

    @Test
    public void testBuyItems() {
        House mockHouse = new House();
        ItemListRequest request = new ItemListRequest(List.of("item1", "item2"), "test-id");

        when(shoppingListController.buyItems(request.getItems(), request.getId())).thenReturn(mockHouse);

        House result = controller.buyItem(request);
        assertEquals(mockHouse, result);

        verify(shoppingListController, times(1)).buyItems(request.getItems(), request.getId());
    }

    @Test
    public void testDeleteItems() {
        House mockHouse = new House();
        ItemListRequest request = new ItemListRequest(List.of("item1", "item2"), "test-id");

        when(shoppingListController.removeItem(request.getItems(), request.getId())).thenReturn(mockHouse);

        House result = controller.deleteItems(request);
        assertEquals(mockHouse, result);

        verify(shoppingListController, times(1)).removeItem(request.getItems(), request.getId());
    }

    @Test
    public void testGenerateWashingPlan() {
        House mockHouse = new House();
        CreateWashingPlanRequest request = new CreateWashingPlanRequest(
                List.of("person1", "person2"),
                List.of("task1", "task2"),
                1, 10, "test-id"
        );

        when(washingPlanController.generateWashingPlan(
                request.persons, request.tasks, request.fromWeek, request.toWeek, request.id))
                .thenReturn(mockHouse);

        House result = controller.generateWashingplan(request);
        assertEquals(mockHouse, result);

        verify(washingPlanController, times(1)).generateWashingPlan(
                request.persons, request.tasks, request.fromWeek, request.toWeek, request.id);
    }
}
