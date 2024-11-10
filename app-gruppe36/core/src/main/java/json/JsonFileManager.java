package json;

import java.io.IOException;
import java.net.URL;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.House;

public class JsonFileManager {

    private static JsonFileManager instance;

    private JsonFileManager() {
    }

    public static JsonFileManager getInstance() {
        if (instance == null)
            instance = new JsonFileManager();
        return instance;
    }

    String path = "data.json";


    public void deleteHouse(String id) {
        List<House> houses = getHouses();
        House houseToDelete = null;
        for (House house : houses) {
            if (house.getId().equals(id)) {
                houseToDelete = house;
                break;
            }
        }
        if (houseToDelete == null)
            return;
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
            System.out.println("An error occured while saving data    " + e.getMessage());
        }
    }

    public List<House> getHouses() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(getPath(),
                    mapper.getTypeFactory().constructCollectionType(List.class, House.class));
        } catch (IOException e) {
            System.out.println("An error occured while loading data    " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public File getPath() {
        return new File(path);
    }

}