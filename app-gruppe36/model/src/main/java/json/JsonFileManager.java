package json;
import java.io.IOException;
import java.net.URL;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.House;

public class JsonFileManager
{



    public void saveHouse(House house)
    {
        List<House> houses = getNewHouseList(house);
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(getPath(), houses);
            String jsonString = mapper.writeValueAsString(houses);
        }
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("An error occured while saving data    " + e.getMessage());
        }
    }

    


    private List<House> getHouses()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.readValue(getPath() ,mapper.getTypeFactory().constructCollectionType(List.class, House.class));
        }
        catch (IOException e) 
        {
            System.out.println("An error occured while loading data    " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public File getPath() {
        URL resource = getClass().getClassLoader().getResource("data.json");
        if (resource != null) {
            return new File(resource.getFile());
        } else {
            File newFile = new File("src/main/resources/data.json");
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                System.err.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
            return newFile;
        }
    }
    


    private List<House> getNewHouseList(House house)
    {
        List<House> houses = getHouses();
        for (int i = 0; i < houses.size(); i++)
        {
            if (houses.get(i).getId().equals(house.getId()))
            {
                houses.set(i, house);
                return houses;
            }
        }
        houses.add(house);
        return houses;
    }

    public House getSavedHouse(String id)
    {
        List<House> houses = getHouses();
        if (houses == null) return null;
        if (houses.size() == 0) return null;
        for (House house : houses) 
            if (house.getId().equals(id)) return house;
        return null;
    }

    public House DebugGetFirstHouse()
    {
        List<House> houses = getHouses();
        if (houses == null) return null;
        if (houses.size() == 0) return null;
        return houses.get(0);
    }






}