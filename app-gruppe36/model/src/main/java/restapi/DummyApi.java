package restapi;

import data.House;
import data.Item;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the RestApi interface used for testing and
 * development.
 * This class simulates interactions with a REST API, providing hardcoded
 * responses for various API calls. It allows for testing and debugging functionality
 * without needing an actual backend or server connection.
 */
public class DummyApi implements RestApi {

    House house = new House("fffff");

    @Override
    public String getNewValidId() {
        return "fffff";
    }

    @Override
    public House addItem(Item item, String id) {
        List<Item> items = new ArrayList();
        items.add(item);
        house.setShoppingList(items);
        return house;
    }

    @Override
    public House createNewHouse(String id) {
        house = new House(id);
        return house;
    }

    @Override
    public House getHouse(String id) {
        return house;
    }

    @Override
    public House buyItem(List<Item> items, String id) {
        house.setShoppingListHistory(items);
        return house;
    }

    @Override
    public House deleteItems(List<Item> items, String id) {
        house.setShoppingList(new ArrayList<>());
        return house;
    }

    @Override
    public House generateWashingplan(List<Person> psn, List<Task> tsk, int fw, int tw, String id) {
        WashingTable washingTable = new WashingTable(psn, tsk);
        List<Person> names = psn;
        int numPeople = names.size();
        int numTasks = tsk.size();

        for (int week = fw; week <= tw; week++) {
            WashingPlan washingPlan = new WashingPlan(week);
            for (int i = 0; i < numTasks; i++) {
                Task task = tsk.get(i);
                Person assignedPerson = names.get((i + (week - fw)) % numPeople);
                WashingPlanEntry entry = new WashingPlanEntry(assignedPerson, task);
                washingPlan.addEntry(entry);
            }
            washingTable.addWashingPlan(washingPlan);
        }
        house.setWashingTable(washingTable);
        return house;
    }

    @Override
    public String type() {
        return "Dummy-Api";
    }

}
