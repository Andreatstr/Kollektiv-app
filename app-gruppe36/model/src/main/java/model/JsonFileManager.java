package model;
import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

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

    public List<Item> getSavedList()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.readValue(new File(filePath),mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
}