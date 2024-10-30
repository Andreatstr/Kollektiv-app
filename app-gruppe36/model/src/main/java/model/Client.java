package model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import data.House;

public class Client
{
    private RestTemplate restTemplate;

    private String url = "http://localhost:8080/";

    public Client()
    {
        restTemplate = new RestTemplate();
        System.out.println(restTemplate.getForObject(url + "health-check", String.class));
        System.out.println(getHouse("fffff").getId());
        //System.out.println(restTemplate.postForObject( + "health-check", this, responseType));


    }

    public House getHouse(String id)
    {
         return restTemplate.postForObject(url + "gethouse", id, House.class);
    }
}