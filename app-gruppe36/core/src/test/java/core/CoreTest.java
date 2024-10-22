package core;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import data.*;
import json.JsonFileManager;
import core.HouseController;
import java.util.List;

public class CoreTest {


    //@Test
    public void CreateNewHouse()
    {
        String id = "fffff";
        HouseController houseController = HouseController.getInstance();
        houseController.CreateHouse(id);
        assertEquals(houseController.getHouse(id).getId(),id);
        new JsonFileManager().deleteHouse(id);
    }

}
