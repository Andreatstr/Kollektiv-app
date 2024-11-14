package core;

import data.House;
import java.security.SecureRandom;
import java.util.List;
import json.JsonFileManager;

/**
 * The {@link HouseController} class manages the collection of houses and
 * provides methods for creating new houses, generating unique IDs for houses, and interacting
 * with the house data stored in a JSON file.
 * It ensures that houses are stored persistently and provides functionality for
 * retrieving houses by their ID.
 */
public class HouseController {

    private List<House> houses;

    private String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static HouseController instance;

    JsonFileManager jsonFileManager;

    private HouseController() {
        jsonFileManager = JsonFileManager.getInstance();
        houses = jsonFileManager.getHouses();
    }

    /**
     * Returns the singleton instance of {@link HouseController}.
     * This method ensures that only one instance of {@link HouseController} exists
     * throughout the application.
     *
     * @return the singleton instance of {@link HouseController}.
     */
    public static synchronized HouseController getInstance() {
        if (instance == null) {
            instance = new HouseController();
        }
        return instance;
    }

    /**
     * Retrieves a house by its unique ID.
     * This method searches through the list of houses and returns the house with
     * the specified ID.
     * If no house with the ID is found, it returns {@code null}.
     *
     * @param id the ID of the house to retrieve.
     * @return the house with the specified ID, or {@code null} if no such house
     *         exists.
     */
    public House getHouse(String id) {
        for (House house : houses) {
            if (house.getId().equals(id)) {
                return house;
            }
        }
        return null;
    }

    public void saveHouse() {
        jsonFileManager.saveToFile(houses);
    }

    /**
     * Creates a new house with the specified ID and adds it to the list of houses.
     * <p>
     * This method creates a new {@link House} object, adds it to the list of
     * houses, and saves the updated list.
     * </p>
     *
     * @param id the ID of the new house to create.
     * @return the newly created {@link House} object.
     */
    public House createHouse(String id) {
        House newHouse = new House(id);
        houses.add(newHouse);
        saveHouse();
        return newHouse;
    }

    /**
     * Generates a new unique ID for a house.
     * This method generates a random 5-character string and ensures that the ID
     * does not already exist in the list of houses.
     * If a duplicate ID is generated, the method will attempt to generate a new
     * one, up to a maximum of 100 tries.
     * If a unique ID is found, it is returned; otherwise, {@code null} is returned.
     *
     * @return a unique 5-character ID for a house, or {@code null} if a unique ID
     *         cannot be generated after 100 tries.
     */
    public String getNewId() {
        int maxTries = 100;
        int i = 0;
        while (i < maxTries) {
            boolean validId = true;
            i++;
            String randomId = generateRandomId(5);
            for (House house : houses) {
                if (house.getId().equals(randomId)) {
                    validId = false;
                }
            }
            if (validId) {
                return randomId;
            }
        }
        return null;
    }

    /**
     * Generates a new random Id based on length given 
     * @return a string of given length
     */
    private String generateRandomId(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(index));
        }

        return stringBuilder.toString();
    }
}
