package model;
import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileManager
{

    private String filePath = "shoppinglist.json";
    private String washingPath = "washingplan.json";

    public void storeObject(Collective objectToSave)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(new File(filePath), objectToSave);
            String jsonString = mapper.writeValueAsString(objectToSave);
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public Collective getSavedCollective()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            
            return mapper.readValue(new File(filePath),Collective.class);
        }
        catch (IOException e) 
        {
            System.out.println("Could not find exisitng collective");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void storeWashingObject(Object objectToSave)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(new File(washingPath), objectToSave);
            String jsonString = mapper.writeValueAsString(objectToSave);
        }
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Collective getSavedWashingCollective()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.readValue(new File(washingPath),Collective.class);
        }
        catch (IOException e) 
        {
            return null;
        }
    }
}