package model;

import data.House;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class HouseManager {

    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/";
    private House selectedHouse;
    private static HouseManager instance;
    private List<UpdateEvent> subscriptions = new ArrayList<UpdateEvent>();

    private HouseManager() {
        restTemplate = new RestTemplate();
    }

    public static HouseManager getInstance() {
        if (instance == null)
            instance = new HouseManager();
        return instance;
    }

    public boolean setHouse(String houseId) {
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
        {
            System.out.print("using empty house!");
            selectedHouse = new House();
        }
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
        for (UpdateEvent subscriber : subscriptions)
        {
            subscriber.updateEvent();
        }

    }

    public String getNewId() {
        return restTemplate.getForObject(url + "newvalidid", String.class);
    }

    public void subscribeToEvents(UpdateEvent subscriber)
    {
        subscriptions.add(subscriber);
    }

    public void logOut()
    {
        for (UpdateEvent subscriber : subscriptions)
        {
            subscriber.logoutEvent();
        }
    }
}
