package model;

import java.util.ArrayList;
import java.util.List;


import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingTable;
import data.requests.ItemListRequest;
import javafx.collections.FXCollections;
import data.requests.CreateWashingPlanRequest;
import data.House;


public class WashingPlanModel implements UpdateEvent {

    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/";

    public int currentWeek = 1;
    private static WashingPlanModel washingPlanModel = null;

    private House house;

    private HouseManager houseManager;

    private List<Person> washingPlanPersons = FXCollections.observableArrayList();
    private List<Task> washingPlanTasks = FXCollections.observableArrayList();
    private List<WashingTable> washingTables = new ArrayList<>();

    private WashingPlanModel() {
        restTemplate = new RestTemplate();
        houseManager = HouseManager.getInstance();
        setWashingPlan();
    }

    private void setWashingPlan() {
        house = houseManager.getHouse();
        washingTables = house.getWashingTable();
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
        CreateWashingPlanRequest request = new CreateWashingPlanRequest(persons, tasks, fromWeek, toWeek, house.getId());
        house = restTemplate.postForObject(url + "generateWashingplan", request, House.class);
        houseManager.updateHouse(house);
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
        setWashingPlan();
    }

    public void reset()
    {
        washingPlanPersons.clear();
        washingPlanTasks.clear();
        washingTables.clear();
        currentWeek = 1;
    }
}