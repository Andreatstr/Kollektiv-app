package model;

import data.House;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingTable;
import data.requests.CreateWashingPlanRequest;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import org.springframework.web.client.RestTemplate;
import viewmodel.WashingPlanViewModel;

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
        if (washingTable == null)
            return;
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

    public void generateWashingPlan(List<Person> person, List<Task> task, int fw, int tw) {
        house = houseManager.api.generateWashingplan(person, task, fw, tw, house.getId());
        houseManager.updateHouse(house);
    }

    public WashingPlan getPlanForWeek() {
        if (washingTable == null)
            return null;
        return washingTable.getWashingPlanOfWeek(currentWeek);
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int week) {
        if (washingTable == null)
            return;
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

    public void editWashingPlan() {
        if (washingTable == null)
            return;
        washingPlanPersons = washingTable.getPersons();
        washingPlanTasks = washingTable.getTasks();
        WashingPlanViewModel washingPlanViewModel = WashingPlanViewModel.getInstance();
        washingPlanViewModel.updateWashingPlanPersons();
        washingPlanViewModel.updateWashingPlanTasks();
    }

    public void setWashingTable(WashingTable washingTable) {
        this.washingTable = washingTable;
    }

    public void reset() {
        washingPlanPersons.clear();
        washingPlanTasks.clear();
        washingTable = null;
        currentWeek = 0;
    }
}