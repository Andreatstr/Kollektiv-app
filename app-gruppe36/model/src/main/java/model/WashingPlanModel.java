package model;

import data.House;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingTable;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import viewmodel.WashingPlanViewModel;

/**
 * Manages the washing plans for a specific house.
 * This class is responsible for handling the washing plan, tasks, and persons in the plan.
 * It interacts with the {@link HouseManager} to update and retrieve house-related data and uses
 * a {@link WashingTable} to store the washing plans and related information. It also communicates
 * with the server to generate washing plans.
 */
public class WashingPlanModel implements UpdateEvent {

    private int currentWeek = 1;
    private static final WashingPlanModel washingPlanModel = new WashingPlanModel();

    private House house;
    private HouseManager houseManager;
    private List<Person> washingPlanPersons = FXCollections.observableArrayList();
    private List<Task> washingPlanTasks = FXCollections.observableArrayList();
    private WashingTable washingTable = new WashingTable();

    private WashingPlanModel() {
        houseManager = HouseManager.getInstance();
        houseManager.subscribeToEvents(this);
        setWashingPlan();
    }

    private void setWashingPlan() {
        house = houseManager.getHouse();
        washingTable = house.getWashingTable();
        if (washingTable == null) {
            return;
        }
        currentWeek = washingTable.getLowestWeek();
    }

    public static WashingPlanModel getInstance() {
        return washingPlanModel;
    }

    public List<Task> getWashingPlanTasks() {
        return Collections.unmodifiableList(washingPlanTasks);
    }

    public List<Person> getWashingPlanPersons() {
        return Collections.unmodifiableList(washingPlanPersons);
    }

    public WashingTable getWashingTables() {
        return washingTable;
    }

    /**
     * Adds a new person to the washing plan if the person is not already in the list.
     *
     * @param newPerson the person to add to the washing plan.
     */
    public void addPerson(Person newPerson) {
        for (Person name : washingPlanPersons) {
            if (name.getName().equals(newPerson.getName())) {
                System.out.println("Name already in list");
                return;
            }
        }
        washingPlanPersons.add(newPerson);
    }

    /**
     * Adds a new task to the washing plan if the task is not already in the list.
     *
     * @param newTask the task to add to the washing plan.
     */
    public void addTask(Task newTask) {
        for (Task task : washingPlanTasks) {
            if (task.getTask().equals(newTask.getTask())) {
                System.out.println("Task already in list");
                return;
            }
        }
        washingPlanTasks.add(newTask);
    }

    public void generateWashingPlan(List<Person> person, List<Task> task, int fw, int tw) {
        house = houseManager.getApi().generateWashingplan(person, task, fw, tw, house.getId());
        houseManager.updateHouse(house);
    }

    /**
     * Retrieves the washing plan for the current week.
     *
     * @return the washing plan for the current week, or null if no washing plan exists.
     */
    public WashingPlan getPlanForWeek() {
        if (washingTable == null) {
            return null;
        }
        return washingTable.getWashingPlanOfWeek(currentWeek);
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    /**
     * Sets the current week number, ensuring it's within the valid range.
     *
     * @param week the week number to set as the current week.
     */
    public void setCurrentWeek(int week) {
        if (washingTable == null) {
            return;
        }
        if (week < washingTable.getLowestWeek() || week > washingTable.getHighestWeek()) {
            return;
        }
        this.currentWeek = week;
    }

    @Override
    public void updateEvent() {
        setWashingPlan();
        WashingPlanViewModel.getInstance().updateWashingPlans();
    }

    @Override
    public void logoutEvent() {
        System.out.println("Logout");
        reset();
        WashingPlanViewModel washingPlanViewModel = WashingPlanViewModel.getInstance();
        washingPlanViewModel.updateWashingPlanPersons();
        washingPlanViewModel.updateWashingPlanTasks();
    }

    /**
     * Edits the current washing plan by setting the persons and tasks from the washing table.
     * It updates the corresponding view model.
     */
    public void editWashingPlan() {
        if (washingTable == null) {
            return;
        }
        washingPlanPersons = washingTable.getPersons();
        washingPlanTasks = washingTable.getTasks();
        WashingPlanViewModel washingPlanViewModel = WashingPlanViewModel.getInstance();
        washingPlanViewModel.updateWashingPlanPersons();
        washingPlanViewModel.updateWashingPlanTasks();
    }

    public void setWashingTable(WashingTable washingTable) {
        this.washingTable = washingTable;
    }

    /**
     * Resets the washing plan data by clearing persons, tasks, and washing table.
     */
    public void reset() {
        washingPlanPersons.clear();
        washingPlanTasks.clear();
        washingTable = null;
        house = null;
        currentWeek = 0;
    }

    /**
     * Retrieves the ID of the currently selected house.
     *
     * @return the ID of the house, or null if no house is selected.
     */
    public String getHouseId() {
        if (house == null) {
            return null;
        }
        return house.getId();
    }
}