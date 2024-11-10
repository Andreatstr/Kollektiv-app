package restserver;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.House;
import data.Item;
import data.Person;
import data.Task;
import data.requests.CreateWashingPlanRequest;
import data.requests.ItemListRequest;
import data.requests.ItemRequest;

public class ApiIntegrationTest {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper(); // For å konvertere objekter til JSON

    @Test
    public void testGetEndpoint() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/health"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Response status should be 200");
        assertEquals("yehaw", response.body(), "Response body should match expected");
    }

    @Test
    public void testAddItem() throws Exception {
        Item item = new Item(); // Opprett et Item-objekt med riktige verdier
        ItemRequest requestObj = new ItemRequest(item, "houseId");
        String requestBody = mapper.writeValueAsString(requestObj);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/additem"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Response status should be 200");

        // Only deserialize if response body is not empty
        if (!response.body().isEmpty()) {
            House house = mapper.readValue(response.body(), House.class);
            assertNotNull(house, "House should not be null");
        }
    }

    @Test
    public void testCreateNewHouse() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/createnewhouse"))
            .POST(HttpRequest.BodyPublishers.ofString("\"houseId\""))
            .header("Content-Type", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Response status should be 200");

        if (!response.body().isEmpty()) {
            House house = mapper.readValue(response.body(), House.class);
            assertNotNull(house, "House should not be null");
        }
    }

    @Test
    public void testGetHouse() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/gethouse"))
            .POST(HttpRequest.BodyPublishers.ofString("\"houseId\""))
            .header("Content-Type", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Response status should be 200");

        if (!response.body().isEmpty()) {
            House house = mapper.readValue(response.body(), House.class);
            assertNotNull(house, "House should not be null");
        }
    }

    @Test
    public void testBuyItem() throws Exception {
        List<Item> items = List.of(new Item()); // Opprett en liste med Item-objekter
        ItemListRequest requestObj = new ItemListRequest(items, "houseId");
        String requestBody = mapper.writeValueAsString(requestObj);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/buyitems"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Response status should be 200");

        if (!response.body().isEmpty()) {
            House house = mapper.readValue(response.body(), House.class);
            assertNotNull(house, "House should not be null");
        }
    }

    @Test
    public void testDeleteItems() throws Exception {
        List<Item> items = List.of(new Item()); // Opprett en liste med Item-objekter
        ItemListRequest requestObj = new ItemListRequest(items, "houseId");
        String requestBody = mapper.writeValueAsString(requestObj);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/removeitem"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Response status should be 200");

        if (!response.body().isEmpty()) {
            House house = mapper.readValue(response.body(), House.class);
            assertNotNull(house, "House should not be null");
        }
    }

    @Test
    public void testGenerateWashingplan() throws Exception {
        List<Person> persons = List.of(new Person()); // Opprett en liste med Person-objekter
        List<Task> tasks = List.of(new Task()); // Opprett en liste med Task-objekter
        CreateWashingPlanRequest requestObj = new CreateWashingPlanRequest(persons, tasks, 1, 5, "houseId");
        String requestBody = mapper.writeValueAsString(requestObj);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/generateWashingplan"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Response status should be 200");

        if (!response.body().isEmpty()) {
            House house = mapper.readValue(response.body(), House.class);
            assertNotNull(house, "House should not be null");
        }
    }

    @Test
    public void testGetNewValidId() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/newvalidid"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Response status should be 200");

        if (!response.body().isEmpty()) {
            String newId = response.body();
            assertNotNull(newId, "New valid ID should not be null");
        }
    }
}
