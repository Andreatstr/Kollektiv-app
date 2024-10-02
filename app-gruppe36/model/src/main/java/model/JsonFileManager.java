package model;
import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileManager
{

    private String filePath = "shoppinglist.json";


    public void storeObject(Object objectToSave)
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
            return null;
        }
    }
}