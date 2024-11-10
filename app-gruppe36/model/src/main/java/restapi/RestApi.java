package restapi;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import data.House;
import data.Item;
import data.requests.CreateWashingPlanRequest;
import data.requests.ItemListRequest;
import data.requests.ItemRequest;
import data.Person;
import data.Task;

public interface RestApi {
    public String getNewValidId();
    public House addItem(Item item, String id);
    public House CreateNewHouse(String id);
    public House GetHouse(String id);
    public House buyItem(List<Item> items, String id);
    public House deleteItems(List<Item> items, String id);
    public House generateWashingplan(List<Person> persons, List<Task> tasks, int fromWeek,int toWeek,String houseId); 
    


}
