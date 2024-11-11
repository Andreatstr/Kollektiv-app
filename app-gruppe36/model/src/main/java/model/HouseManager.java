package model;

// import java.util.Collection;
import data.House;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import data.House;
import restapi.*;

public class HouseManager {

    public RestApi api;
 
    private House selectedHouse;

    private static HouseManager instance;

    private List<UpdateEvent> subscriptions = new ArrayList<UpdateEvent>();

    private HouseManager() {
        api = new ServerApi();
    }

    public void setTestApi()
    {
        api = new DummyApi();
    }

    public void setServerApi()
    {
        api = new ServerApi();
    }

    public static HouseManager getInstance() {
        if (instance == null)
            instance = new HouseManager();
        return instance;
    }

    public boolean setHouse(String houseId) {
        api.GetHouse(houseId);
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
            House house = api.CreateNewHouse(id);
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
        return api.getNewValidId();
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
