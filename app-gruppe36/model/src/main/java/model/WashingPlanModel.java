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

public class WashingPlanModel implements UpdateEvent {

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
        washingTables = collective.getWashingTable();
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
    }

    public void addTask(Task newTask) {
        for (Task task : washingPlanTasks) {
            if (task.getTask().equals(newTask.getTask())) {
                System.out.println("Task already in list");
                return;
            }
        }
        washingPlanTasks.add(newTask);
    }

    public void generateWashingPlan(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek) {

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

    @Override
    public void updateEvent() {

    }
}