package core;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import data.*;
import json.JsonFileManager;
import core.HouseController;
import java.util.List;

public class CoreTest {


    //@Test
    public void CreateNewHouse() {
        String id = "fffff";
        HouseController houseController = HouseController.getInstance();
        houseController.createHouse(id);
        assertEquals(houseController.getHouse(id).getId(),id);
        JsonFileManager.getInstance().deleteHouse(id);
    }

}
