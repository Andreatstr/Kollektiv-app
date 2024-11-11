package model;

import data.House;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestClientException;
import restapi.DummyApi;
import restapi.RestApi;
import restapi.ServerApi;

/**
 * Manages operations related to houses, including setting and updating house details,
 * subscribing to update events, and interacting with a remote API for house data.
 * This class provides methods for creating a new house, retrieving the currently selected house,
 * setting the API to either a dummy API for testing or a real server API, and subscribing to events
 * for updates related to house data.
 */
public class HouseManager {

    private RestApi api;
    private House selectedHouse;
    private static HouseManager instance = new HouseManager();
    private List<UpdateEvent> subscriptions = new ArrayList<UpdateEvent>();

    private HouseManager() {
        api = new ServerApi();
    }

    public void setTestApi() {
        api = new DummyApi();
    }

    public void setApi(RestApi api) {
        this.api = api;
    }

    public RestApi getApi() {
        return api;
    }

    public static HouseManager getInstance() {
        return instance;
    }

    /**
     * Sets the currently selected house by fetching it from the configured {@link RestApi}
     * using the provided house ID. If the house is not found, the method returns false.
     *
     * @param houseId the ID of the house to select.
     * @return true if the house was successfully retrieved and selected, false otherwise.
     */
    public boolean setHouse(String houseId) {
        House newHouse = api.getHouse(houseId);
        if (newHouse == null) {
            return false;
        }
        updateHouse(api.getHouse(houseId));
        return true;
    }

    /**
     * Retrieves the currently selected house.
     * If no house is selected, a new empty {@link House} object is returned.
     *
     * @return the currently selected {@link House}, or a new empty house if no house is selected.
     */
    public House getHouse() {
        if (selectedHouse == null) {
            selectedHouse = new House();
        }
        return selectedHouse;
    }

    /**
     * Creates a new house with the given ID by interacting with the remote API.
     * If successful, the new house is set as the selected house and subscribers are notified.
     *
     * @param id the ID of the new house to create.
     * @return true if the house was created successfully, false otherwise.
     */
    public boolean createHouse(String id) {
        House house = api.createNewHouse(id);
        if (house == null) {
            return false;
        }
        updateHouse(house);
        return true;
    }

    /**
     * Updates the currently selected house and notifies all subscribed update events.
     *
     * @param house the new {@link House} to set as the selected house.
     */
    public void updateHouse(House house) {
        if (house == null) {
            return;
        }
        selectedHouse = house;
        for (UpdateEvent subscriber : subscriptions) {
            subscriber.updateEvent();
        }
    }

    public String getNewId() {
        return api.getNewValidId();
    }

    public void subscribeToEvents(UpdateEvent subscriber) {
        subscriptions.add(subscriber);
    }

    /**
     * Logs out all subscribers by calling their logout events.
     */
    public void logOut() {
        for (UpdateEvent subscriber : subscriptions) {
            subscriber.logoutEvent();
        }
    }
}
