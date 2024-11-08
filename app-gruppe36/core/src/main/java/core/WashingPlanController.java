package core;

import java.util.List;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import data.House;

public class WashingPlanController {

    public int currentWeek = 1;
    private static WashingPlanController washingPlanModel = null;

    private WashingPlanController() {
    }

    private void storeToFile() {
        HouseController.getInstance().saveHouse();
    }

    public static WashingPlanController getInstance() {
        if (washingPlanModel != null)
            return washingPlanModel;
        washingPlanModel = new WashingPlanController();
        return washingPlanModel;
    }

    public House generateWashingPlan(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek,String id) {
        House house = HouseController.getInstance().getHouse(id);
        WashingTable washingTable = new WashingTable(persons,tasks);
        if (house == null) return null;

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
          storeToFile();
          return house;
         
    }

}