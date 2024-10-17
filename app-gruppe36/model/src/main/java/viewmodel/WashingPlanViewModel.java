package viewmodel;

import java.util.List;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.WashingPlanModel;

public class WashingPlanViewModel {
    
    public static WashingPlanViewModel washingPlanViewModel;
    private WashingPlanModel washingPlanModel;
    private int currentWeek = 1;
    public int startWeek;
    public int endWeek;

    private ObservableList<Person> washingPlanPersons = FXCollections.observableArrayList();
    private ObservableList<Task> washingPlanTasks = FXCollections.observableArrayList();
    private ObservableList<WashingPlan> washingPlans = FXCollections.observableArrayList();

    ObservableList<WashingPlanEntry> washingPlanEntry = FXCollections.observableArrayList();

    private WashingPlanViewModel() {
        washingPlanModel = WashingPlanModel.getInstance();
    }

    public static WashingPlanViewModel getInstance() {
        if (washingPlanViewModel != null)
            return washingPlanViewModel;
        washingPlanViewModel = new WashingPlanViewModel();
        return washingPlanViewModel;
    }

    public void addTask(String task) {
        if (task == null || task.isEmpty()) {
            System.out.println("Task field is null or empty");
            return;
        } 
        Task newTask = new Task(task);
        washingPlanModel.addTask(newTask);
        updateWashingPlanTasks();
    }

    public void addPerson(String person) {
        if (person == null || person.isEmpty()) {
            System.out.println("Person field is null or empty");
            return;
        } 
        Person newPerson = new Person(person);
        washingPlanModel.addPerson(newPerson);
        updateWashingPlanPersons();
    }

    public void generateWashingPlan(int fromWeek, int toWeek) {
        washingPlanModel.generateWashingPlan(washingPlanPersons, washingPlanTasks, fromWeek, toWeek);
        currentWeek = fromWeek;
        updateWashingPlans();
    }

    public ObservableList<WashingPlan> getWashingPlansForCurrentWeek() {
        updateWashingPlans();
        return washingPlans;
    }

    public List<WashingPlan> getWashingPlansForCurrentWeek(int currentWeek) {
        List<WashingPlan> thisWeeksWashingPlan = washingPlanModel.getWashingTableForWeek(currentWeek);
        return thisWeeksWashingPlan;
    }

    public void nextWeek() {
        if (currentWeek < endWeek) {
            currentWeek++;
            washingPlanModel.setCurrentWeek(currentWeek); 
            System.out.println("Moving to next week: " + currentWeek);          //debug comment
            updateWashingPlans();
        }
    }

    public void previousWeek() {
        if (currentWeek > startWeek) {
            currentWeek--;
            washingPlanModel.setCurrentWeek(currentWeek); 
            System.out.println("Moving to previous week: " + currentWeek);      //debug comment
            updateWashingPlans();
        }
    }

    public void generateWashingPlan(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek) {
        washingPlanModel.generateWashingPlan(persons, tasks, fromWeek, toWeek);
        currentWeek = fromWeek;
        updateWashingPlans();
    }

    public void updateWashingPlans(List<WashingPlan> generatedPlans) {
        washingPlans.clear();
        washingPlans.addAll(generatedPlans);
    }

    public void updateWashingPlans() {
        washingPlans.clear();
        List<WashingPlan> weekPlans = washingPlanModel.getWashingTableForWeek(currentWeek);
        washingPlans.addAll(weekPlans);
    }

    public void updateWashingPlanPersons() {
        washingPlanPersons.clear();
        washingPlanPersons.addAll(washingPlanModel.getWashingPlanPersons());
    }

    public void updateWashingPlanTasks() {
        washingPlanTasks.clear();
        washingPlanTasks.addAll(washingPlanModel.getWashingPlanTasks());
    }

    public ObservableList<WashingPlanEntry> getWashingPlanEntriesForCurrentWeek() {
        List<WashingPlan> plansForCurrentWeek = washingPlanModel.getWashingTableForWeek(currentWeek);
        washingPlanEntry.clear();
        for (WashingPlan plan : plansForCurrentWeek) {
            washingPlanEntry.addAll(plan.getEntries());
        }
        
        return washingPlanEntry;
    }

    public ObservableList<WashingPlan> getWashingPlans() {
        //updateWashingPlans();     // works for tests
        return washingPlans;
    }

    public ObservableList<Task> getWashingPlanTasks() {
        return washingPlanTasks;
    }

    public ObservableList<Person> getWashingPlanPersons() {
        return washingPlanPersons;
    }

    public int getCurrentWeek() {
        // System.out.println("current week in wpVm: " + currentWeek);
        // System.out.println("current week in wpm: " + washingPlanModel.getCurrentWeek());
        return washingPlanModel.getCurrentWeek();
    }

    public void setCurrentWeek(int week) {
        currentWeek = week;
        washingPlanModel.setCurrentWeek(week);
        // System.out.println("current week in wpm: " + washingPlanModel.getCurrentWeek());
    }

    public boolean isThisTheFirstWeek(int thisWeek) {
        if (startWeek == thisWeek) {
            // System.out.println("true start Week: " + startWeek);
            // System.out.println("this Week: " + thisWeek);
            return true;
        }
        // System.out.println("false start Week: " + startWeek);
        // System.out.println("this Week: " + thisWeek);
        return false;
    }

    public boolean isThisTheLastWeek(int thisWeek) {
        if (endWeek == thisWeek) {
            // System.out.println("true end Week: " + endWeek);
            // System.out.println("this Week: " + thisWeek);
            return true;
        }
        // System.out.println("false end Week: " + endWeek);
        // System.out.println("this Week: " + thisWeek);
        return false;
    }

    public void setStartWeek(String fromWeekInput) {
        if (!isInteger(fromWeekInput)) {
            throw new IllegalArgumentException("From Week is not a valid integer");
        }
        int fromWeek = Integer.parseInt(fromWeekInput);
        this.startWeek = fromWeek;
        setCurrentWeek(fromWeek);
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setEndWeek(String toWeekInput) {
        if (!isInteger(toWeekInput)) {
            throw new IllegalArgumentException("To Week is not a valid integer.");
        }
        int toWeek = Integer.parseInt(toWeekInput);
        this.endWeek = toWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void reset() {
        // System.out.println("Resetting WashingPlanViewModel...");
        washingPlanPersons.clear();
        washingPlanTasks.clear();
        washingPlans.clear();
        currentWeek = 1;
        // System.out.println("Reset complete. Current week: " + currentWeek);
        // System.out.println("Persons: " + washingPlanPersons.size());
        // System.out.println("Tasks: " + washingPlanTasks.size());
        // System.out.println("Plans: " + washingPlans.size());
        washingPlanModel.reset();
    }
}
