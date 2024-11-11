package restapi;

import java.util.ArrayList;
import java.util.List;
import data.House;
import data.Item;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;

public class DummyApi implements RestApi  {

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
    public House CreateNewHouse(String id) {
        house = new House(id);
        return house;
    }

    @Override
    public House GetHouse(String id) {
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
    public House generateWashingplan(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek, String houseId) {
        WashingTable washingTable = new WashingTable(persons,tasks);
          List<Person> names = persons;
          int numPeople = names.size();
          int numTasks = tasks.size();
          
          for (int week = fromWeek; week <= toWeek; week++) {
            WashingPlan washingPlan = new WashingPlan(week);
            for (int i = 0; i < numTasks; i++) {
                Task task = tasks.get(i);
                Person assignedPerson = names.get((i + (week - fromWeek)) % numPeople);
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