package restapi;

import data.House;
import data.Item;
import data.Person;
import data.Task;
import java.util.List;

/**
 * Interface for interacting with a REST API that manages houses, shopping
 * lists, and washing plans.
 * This interface defines methods for performing various operations related to
 * managing a house's shopping list,
 * generating washing plans, and interacting with house-related data.
 * Implementations of this interface provide
 * the actual functionality for these operations, which can be used by different
 * parts of the application to
 * manipulate house data, manage items, and schedule tasks.
 */
public interface RestApi {
    public String getNewValidId();

    public House addItem(Item item, String id);

    public House createNewHouse(String id);

    public House getHouse(String id);

    public House buyItem(List<Item> items, String id);

    public House deleteItems(List<Item> items, String id);

    public House generateWashingplan(List<Person> persons, List<Task> tasks,
            int fromWeek, int toWeek, String houseId);

    public String type();
}
