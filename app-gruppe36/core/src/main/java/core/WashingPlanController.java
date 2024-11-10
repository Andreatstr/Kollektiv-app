package core;

import data.House;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import java.util.List;

public class WashingPlanController {

    public int currentWeek = 1;
    private static WashingPlanController washingPlanModel = null;

    private WashingPlanController() {
    }

    private void storeToFile() {
        HouseController.getInstance().saveHouse();
    }

    public static WashingPlanController getInstance() {
        if (washingPlanModel != null) {
            return washingPlanModel;
        }
        washingPlanModel = new WashingPlanController();
        return washingPlanModel;
    }

    public House generateWashingPlan(List<Person> per, List<Task> tsk, int fw, int tw, String id) {
        House house = HouseController.getInstance().getHouse(id);
        WashingTable washingTable = new WashingTable(per, tsk);
        if (house == null) {
            return null;
        }

        List<Person> names = per;
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
        storeToFile();
        return house;

    }

}