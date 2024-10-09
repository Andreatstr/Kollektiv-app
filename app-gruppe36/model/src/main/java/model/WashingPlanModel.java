package model;

import java.util.ArrayList;
import java.util.List;

import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import data.House;
import data.Item;

public class WashingPlanModel {

    public int currentWeek = 1;
    private static WashingPlanModel washingPlanModel = null;

    private House collective;

    private List<Person> washingPlanPersons = FXCollections.observableArrayList();
    private List<Task> washingPlanTasks = FXCollections.observableArrayList();
    private List<WashingTable> washingTables = new ArrayList<>();

    private WashingPlanModel() {
        readWashingPlanFromFile();
    }

    private void readWashingPlanFromFile() {
        collective = HouseManager.getInstance().getHouse();
        washingPlanPersons = collective.getWashingPlanPerson();
        washingPlanTasks = collective.getWashingPlanTask();
        washingTables = collective.getWashingTable();
    }

    private void storeToFile() {
        collective.setWashingTable(washingTables);
        collective.setWashingPlanPerson(washingPlanPersons);
        collective.setWashingPlanTask(washingPlanTasks);
        HouseManager.getInstance().saveHouse();
    }

    public static WashingPlanModel getInstance() {
        if (washingPlanModel != null)
            return washingPlanModel;
        washingPlanModel = new WashingPlanModel();
        return washingPlanModel;
    }

    public List<Task> getWashingPlanTasks() {
        return washingPlanTasks;
    }

    public List<Person> getWashingPlanPersons() {
        return washingPlanPersons;
    }

    public List<WashingTable> getWashingTables() {
        return washingTables;
    }

    public void addPerson(Person newPerson) {
        for (Person name : washingPlanPersons) {
            if (name.getName().equals(newPerson.getName())) {
                System.out.println("Name already in list");
                return;
            }
        }

        washingPlanPersons.add(newPerson);
        storeToFile();
    }

    public void addTask(Task newTask) {
        for (Task task : washingPlanTasks) {
            if (task.getTask().equals(newTask.getTask())) {
                System.out.println("Task already in list");
                return;
            }
        }
        washingPlanTasks.add(newTask);
        storeToFile();
    }

    public void generateWashingPlan(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek) {
        washingTables.clear();
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
        storeToFile();
    }

    public List<Person> rotateNames(List<Person> names) {
        if (names.size() > 1) {
            List<Person> rotatedNames = names;
            Person last = rotatedNames.remove(rotatedNames.size() - 1);
            rotatedNames.add(0, last);
            return rotatedNames;
        }
        return names;
    }

    public List<WashingPlan> getWashingTableForWeek(int weekNumber) {
        if (weekNumber < 1 || weekNumber > washingTables.size()) {
            return new ArrayList<>(); // returns empty if week is out of range
        }
        return washingTables.get(weekNumber - 1).getWashingPlans();
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int week) {
        this.currentWeek = week;
    }

    public void reset() {
        washingPlanPersons.clear();
        washingPlanTasks.clear();
        washingTables.clear();
        currentWeek = 1;
    }
}