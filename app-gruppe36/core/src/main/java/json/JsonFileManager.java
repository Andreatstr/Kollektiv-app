package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.House;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@link JsonFileManager} class is responsible for managing the saving and loading of
 * {@link House} objects to and from a JSON file. It provides methods for saving, retrieving,
 * and deleting house data stored in a file.
 * The class uses the Singleton design pattern to ensure there is only one instance of the manager
 * used throughout the application.
 */
public class JsonFileManager {

    private static JsonFileManager instance;
    private String path = "data.json";

    private JsonFileManager() {
    }

    /**
     * Returns the singleton instance of {@link JsonFileManager}.
     * Ensures that only one instance of the manager is created and used throughout
     * the application.
     *
     * @return the singleton instance of {@link JsonFileManager}.
     */
    public static synchronized JsonFileManager getInstance() {
        if (instance == null) {
            instance = new JsonFileManager();
        }
        return instance;
    }

    /**
     * Deletes a house with a given ID from the list of houses stored in the JSON file.
     * This method loads the list of houses, searches for the house with the specified ID,
     * removes it from the list, and then saves the updated list back to the file.
     *
     * @param id the ID of the house to delete.
     */
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

    /**
     * Saves a list of houses to a JSON file.
     * This method uses the {@link ObjectMapper} from the Jackson library to convert
     * the list of houses to JSON format
     * and writes it to the file specified by {@link #getPath()}.
     *
     * @param houses the list of houses to save.
     */
    public void saveToFile(List<House> houses) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(getPath(), houses);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving data    " + e.getMessage());
        }
    }

    /**
     * Loads and returns the list of houses from the JSON file.
     * This method reads the JSON file using the Jackson {@link ObjectMapper} and
     * converts the data into a list of
     * {@link House} objects. If an error occurs during reading, an empty list is
     * returned.
     *
     * @return a list of {@link House} objects loaded from the file, or an empty
     *         list if an error occurs.
     */
    public List<House> getHouses() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<House> houses = mapper.readValue(getPath(),
                    mapper.getTypeFactory().constructCollectionType(List.class, House.class));
            return houses;
        } catch (IOException e) {
            System.out.println("An error occurred while loading data    " + e.getMessage());
            return new ArrayList<House>();
        }
    }

    public File getPath() {
        return new File(path);
    }

}