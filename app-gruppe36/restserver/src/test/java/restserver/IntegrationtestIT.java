package restserver;

import data.Item;
import data.Person;
import data.Task;
import model.HouseManager;
import model.ShoppingListModel;
import model.WashingPlanModel;
import restapi.ServerApi;
import viewmodel.ShoppingListViewModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import json.JsonFileManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, properties = {
        "server.port=8080"
})
public class IntegrationtestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    ServerApi serverApi = new ServerApi();

    @BeforeEach
    private void initialize() {
        serverApi.setRestTemplate(restTemplate.getRestTemplate());
        HouseManager.getInstance().setApi(serverApi);

    }

    @Test
    public void testGetEndpoint() throws Exception {
        String data = restTemplate.getForObject("/health", String.class);
        assertEquals("yehaw", data);
    }

    @Test
    public void TestCreationOfHouse() {
        HouseManager houseManager = HouseManager.getInstance();
        String id = houseManager.getNewId();
        assertNotNull(id);
        assertEquals(houseManager.createHouse(id), true);
        assertEquals(houseManager.setHouse(id), true);
        assertEquals(houseManager.getHouse().getId(), id);
        JsonFileManager.getInstance().deleteHouse(id);
    }

    @Test
    public void TestShoppingList() {
        HouseManager houseManager = HouseManager.getInstance();
        String id = houseManager.getNewId();
        assertNotNull(id);
        assertEquals(houseManager.createHouse(id), true);
        assertEquals(houseManager.setHouse(id), true);
        assertEquals(houseManager.getHouse().getId(), id);
        ShoppingListModel shoppingListModel = ShoppingListModel.getInstance();
        ShoppingListViewModel shoppingListViewModel = ShoppingListViewModel.getInstance();
        shoppingListViewModel.addItem("dopapir", "1");
        shoppingListViewModel.addItem("ost", "1");
        assertEquals(shoppingListModel.getShoppingList().get(0).getItemName(), "dopapir");
        assertEquals(shoppingListModel.getShoppingList().get(1).getItemName(), "ost");
        shoppingListModel.buyItems(List.of(new Item("dopapir", 1)));
        assertEquals(shoppingListModel.getShoppingListHistory().get(0).getItemName(), "dopapir");
        JsonFileManager.getInstance().deleteHouse(id);
    }

    @Test
    public void TestWashingPlan() {
        HouseManager houseManager = HouseManager.getInstance();
        String id = houseManager.getNewId();
        assertNotNull(id);
        assertEquals(houseManager.createHouse(id), true);
        assertEquals(houseManager.setHouse(id), true);
        assertEquals(houseManager.getHouse().getId(), id);
        WashingPlanModel controller = WashingPlanModel.getInstance();
        controller.generateWashingPlan(List.of(new Person("lars")), List.of(new Task("Lars")), 1, 50);
        assertNotNull(controller.getWashingTables());
        assertEquals(controller.getWashingTables().getHighestWeek(), 50);
    }
}
