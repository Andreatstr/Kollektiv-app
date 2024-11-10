package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.House;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonFileManager {

    private static JsonFileManager instance;
    private String path = "data.json";

    private JsonFileManager() {
    }

    public static synchronized JsonFileManager getInstance() {
        if (instance == null) {
            instance = new JsonFileManager();
        }
        return instance;
    }

    public void saveHouse(House house) {
        List<House> houses = getNewHouseList(house);
        saveToFile(houses);
    }

    public void deleteHouse(String id) {
        List<House> houses = getHouses();
        House houseToDelete = null;
        for (House house : houses) {
            if (house.getId().equals(id)) {
                houseToDelete = house;
                break;
            }
        }
        if (houseToDelete == null) {
            return;
        }
        houses.remove(houseToDelete);
        saveToFile(houses);
    }

    public void saveToFile(List<House> houses) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(getPath(), houses);
            String jsonString = mapper.writeValueAsString(houses);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving data    " + e.getMessage());
        }
    }

    public List<House> getHouses() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<House> houses = mapper.readValue(getPath(),
                    mapper.getTypeFactory().constructCollectionType(List.class, House.class));
                return Collections.unmodifiableList(houses);
        } catch (IOException e) {
            System.out.println("An error occurred while loading data    " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public File getPath() {
        return new File(path);
    }

    private List<House> getNewHouseList(House house) {
        List<House> houses = new ArrayList<>(getHouses());
        for (int i = 0; i < houses.size(); i++) {
            if (houses.get(i).getId().equals(house.getId())) {
                houses.set(i, house);
                return houses;
            }
        }
        houses.add(house);
        return houses;
    }

    public House getSavedHouse(String id) {
        List<House> houses = getHouses();
        if (houses == null) {
            return null;
        }
        if (houses.size() == 0) {
            return null;
        }
        for (House house : houses) {
            if (house.getId().equals(id)) {
                return house;
            }
        }
        return null;
    }
}