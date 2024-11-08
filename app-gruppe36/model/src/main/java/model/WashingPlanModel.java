package model;

import java.util.List;
import org.springframework.web.client.RestTemplate;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingTable;
import javafx.collections.FXCollections;
import viewmodel.WashingPlanViewModel;
import data.requests.CreateWashingPlanRequest;
import data.House;

public class WashingPlanModel implements UpdateEvent {

    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/";
    private int currentWeek = 1;
    private static WashingPlanModel washingPlanModel = null;
    private House house;
    private HouseManager houseManager;
    private List<Person> washingPlanPersons = FXCollections.observableArrayList();
    private List<Task> washingPlanTasks = FXCollections.observableArrayList();
    private WashingTable washingTable = new WashingTable();

    private WashingPlanModel() {
        restTemplate = new RestTemplate();
        houseManager = HouseManager.getInstance();
        houseManager.subscribeToEvents(this);
        setWashingPlan();
    }

    private void setWashingPlan() {
        house = houseManager.getHouse();
        washingTable = house.getWashingTable();
        currentWeek = washingTable.getLowestWeek();
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

    public void generateWashingPlan(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek) {
        CreateWashingPlanRequest request = new CreateWashingPlanRequest(persons, tasks, fromWeek, toWeek, house.getId());
        house = restTemplate.postForObject(url + "generateWashingplan", request, House.class);
        houseManager.updateHouse(house);
    }

    public WashingPlan getPlanForWeek() {
        return washingTable.getWashingPlanOfWeek(currentWeek);
    }

  public int getCurrentWeek() {
    return currentWeek;
  }

    public void setCurrentWeek(int week) {
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
        washingPlanPersons = washingTable.getPersons();
        washingPlanTasks = washingTable.getTasks();
        WashingPlanViewModel washingPlanViewModel = WashingPlanViewModel.getInstance();
        washingPlanViewModel.updateWashingPlanPersons();
        washingPlanViewModel.updateWashingPlanTasks();
    }

    public void reset() {
        washingPlanPersons.clear();
        washingPlanTasks.clear();
        washingTable = null;
        currentWeek = 0;
    }
}