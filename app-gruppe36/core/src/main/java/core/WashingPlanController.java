package core;

import java.util.ArrayList;
import java.util.List;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import data.House;
import data.Item;

public class WashingPlanController {

    public int currentWeek = 1;
    private static WashingPlanController washingPlanModel = null;

    private House collective;

    private WashingPlanController() {
    }

    private void storeToFile() {
    }

    public static WashingPlanController getInstance() {
        if (washingPlanModel != null)
            return washingPlanModel;
        washingPlanModel = new WashingPlanController();
        return washingPlanModel;
    }

    public List<WashingTable> getWashingTables() {
    //    return washingTables;
    return null;
    }

    public void addPerson(Person newPerson) {

    }

    public void addTask(Task newTask) {

    }

    public void generateWashingPlan(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek) {
        /*washingTables.clear();
        List<Person> names = persons;
        int numPeople = names.size();
        int numTasks = tasks.size();

        for (int week = fromWeek; week <= toWeek; week++) {
            WashingPlan washingPlan = new WashingPlan(week);

            for (int i = 0; i < numTasks; i++) {
                Task task = tasks.get(i);
                Person assignedPerson = names.get((i + (week - fromWeek)) % numPeople);
                WashingPlanEntry entry = new WashingPlanEntry(assignedPerson, task, week);
                washingPlan.addEntry(entry);
            }
            WashingTable washingTable = new WashingTable();
            washingTable.addWashingPlanEntry(washingPlan);
            washingTables.add(washingTable);
        }
        storeToFile();*/
    }

    public List<WashingPlan> getWashingTableForWeek(int weekNumber) {
        //if (weekNumber < 1 || weekNumber > washingTables.size()) {
        //    return new ArrayList<>(); // returns empty if week is out of range
        //}
        //return washingTables.get(weekNumber - 1).getWashingPlans();
        return null;
    }

}