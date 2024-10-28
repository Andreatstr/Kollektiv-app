package model;
import org.springframework.web.client.RestTemplate;

public class Client
{
    private RestTemplate restTemplate;

    private String url = "http://localhost:8080/";

    public Client()
    {
        restTemplate = new RestTemplate();
        System.out.println(restTemplate.getForObject(url + "health-check", String.class));
        //System.out.println(restTemplate.postForObject( + "health-check", this, responseType));
    }
}