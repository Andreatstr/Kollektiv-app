package restserver;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ApiIntegrationTest {

    @Test
    public void testGetEndpoint() throws Exception {
        // Creates a new HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Builds a GET request to the API endpoint
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/health")) // Specifies the URI of the endpoint
            .GET() // Specifies that this is a GET request
            .build();

        // Sends the request and receives the response as a String
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verifies that the response status is 200 (OK)
        assertEquals(200, response.statusCode(), "Response status should be 200");

        // Verifies that the content of the response matches the expected value
        assertEquals("yehaw", response.body(), "Response body should match expected");
    }
}

