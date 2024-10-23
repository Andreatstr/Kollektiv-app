package core;
import java.security.SecureRandom;
import java.util.List;

import data.House;
import json.JsonFileManager;

public class HouseController {

    private List<House> houses;

    private String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static HouseController instance;

    JsonFileManager jsonFileManager;

    private HouseController(){
        jsonFileManager = JsonFileManager.getInstance();
    }

    public static HouseController getInstance()
    {
        if (instance == null) instance = new HouseController();
        return instance;
    }

    //api 
    public House getHouse(String id)
    {
        for (House house : houses)
        {
            if (house.getId().equals(id))
            return house;
        }
        return null;
    }

    public void saveHouse()
    {
        jsonFileManager.saveToFile(houses);
    }

    //api 
    public void CreateHouse(String id)
    {
        House newHouse = new House(id);
        houses.add(newHouse);
        saveHouse();
    }

    public String getNewId()
    {
        return generateRandomId(5);
    }

    private  String generateRandomId(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }

        return stringBuilder.toString();
    }
}
