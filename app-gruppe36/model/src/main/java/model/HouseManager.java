package model;

import java.security.SecureRandom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import data.House;

public class HouseManager {

    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/";

    private House selectedHouse;

    private static HouseManager instance;

    private HouseManager() {
        restTemplate = new RestTemplate();
    }

    public static HouseManager getInstance() {
        if (instance == null)
            instance = new HouseManager();
        return instance;
    }

    public boolean setHouse(String houseId) {
        System.out.println("Creating House");
        try {
            House house = restTemplate.postForObject(url + "gethouse", houseId, House.class);
            updateHouse(house);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public House getHouse() {
        if (selectedHouse == null)
            selectedHouse = new House();
        return selectedHouse;
    }

    public boolean CreateHouse(String id) {
        try {
            House house = restTemplate.postForObject(url + "createnewhouse", id, House.class);
            updateHouse(house);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void updateHouse(House house) {
        if (house == null)
            return;
        selectedHouse = house;
        ShoppingListModel.getInstance().updateEvent();

    }

    public String getNewId() {
        System.out.println("Getting id");
        return restTemplate.getForObject(url + "newvalidid", String.class);
    }
}
