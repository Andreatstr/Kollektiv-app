package model;

import java.security.SecureRandom;

import data.House;
import json.JsonFileManager;

public class HouseManager {

    private String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    private House selectedHouse;

    private static HouseManager instance;

    JsonFileManager jsonFileManager;

    private HouseManager(){
        jsonFileManager = new JsonFileManager();
        Client client = new Client();
    }

    public static HouseManager getInstance()
    {
        if (instance == null) instance = new HouseManager();
        return instance;
    }


    public boolean setHouse(String houseId)
    {
        selectedHouse = jsonFileManager.getSavedHouse(houseId);
        if (selectedHouse == null) return false;
        return true;

    }

    public House getHouse()
    {
        if (selectedHouse == null) selectedHouse = new House();
        return selectedHouse;
    }

    public void saveHouse()
    {
        if (selectedHouse.getId() == null) return;
        jsonFileManager.saveHouse(selectedHouse);
    }

    public void CreateHouse(String id)
    {
        selectedHouse = new House(id);
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
