package viewmodel;

import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.WashingPlanModel;

/**
 * The `WashingPlanViewModel` class in Java represents the view model for a washing
 * plan application, providing methods to manage tasks, persons, weeks, and generate
 * washing plans.
 */
public class WashingPlanViewModel {

    private static WashingPlanViewModel washingPlanViewModel;
    private WashingPlanModel washingPlanModel;
    private int startWeek;
    private int endWeek;

    private ObservableList<Person> washingPlanPersons = FXCollections.observableArrayList();
    private ObservableList<Task> washingPlanTasks = FXCollections.observableArrayList();
    private ObservableList<WashingPlanEntry> washingPlanEntry = FXCollections.observableArrayList();

    private WashingPlanViewModel() {
        washingPlanModel = WashingPlanModel.getInstance();
    }

    /**
     * The function `getInstance` returns a singleton instance of `WashingPlanViewModel`.
     *
     * @return The `WashingPlanViewModel` instance is being returned.
     */
    public static WashingPlanViewModel getInstance() {
        if (washingPlanViewModel != null) {
            return washingPlanViewModel;
        }
        washingPlanViewModel = new WashingPlanViewModel();
        return washingPlanViewModel;
    }

    /**
     * The `addTask` function checks if the input task is null or empty, creates a new Task
     * object, adds it to the washing plan model, and updates the washing plan tasks.
     *
     * @param task The `addTask` method takes a `String` parameter named `task`, which 
     * represents the task to be added to the washing plan. If the `task` parameter is 
     * `null` or an empty string, the method prints a message indicating that the task 
     * field is null or empty and then returns
     */
    public void addTask(String task) {
        if (task == null || task.isEmpty()) {
            System.out.println("Task field is null or empty");
            return;
        }
        Task newTask = new Task(task);
        washingPlanModel.addTask(newTask);
        updateWashingPlanTasks();
    }

    /**
     * The `addPerson` function creates a new `Person` object with the given name, 
     * adds it to a `washingPlanModel`, and updates the washing plan persons.
     *
     * @param person The `addPerson` method takes a `String` parameter named `person`,
     * which represents the name of a person to be added to a washing plan. If the `person` parameter 
     * is `null` or an empty string, a message "Person field is null or empty" is printed to the
     */
    public void addPerson(String person) {
        if (person == null || person.isEmpty()) {
            System.out.println("Person field is null or empty");
            return;
        }
        Person newPerson = new Person(person);
        washingPlanModel.addPerson(newPerson);
        updateWashingPlanPersons();
    }

    public void nextWeek() {
        washingPlanModel.setCurrentWeek(washingPlanModel.getCurrentWeek() + 1);
        updateWashingPlans();
    }

    public void previousWeek() {
        washingPlanModel.setCurrentWeek(washingPlanModel.getCurrentWeek() - 1);
        updateWashingPlans();
    }

    public void setCurrentWeek(Integer week) {
        washingPlanModel.setCurrentWeek(week);
        updateWashingPlans();
    }

    public void generateWashingPlan(int fw, int tw) {
        washingPlanModel.generateWashingPlan(washingPlanPersons, washingPlanTasks, fw, tw);
        updateWashingPlans();
    }

    public void generateWashingPlan(List<Person> persons, List<Task> tasks, int fw, int tw) {
        washingPlanModel.generateWashingPlan(persons, tasks, fw, tw);
        updateWashingPlans();
    }

    /**
     * The `updateWashingPlans` method clears the existing washing plan entries, retrieves the current
     * week's washing plan, and adds the entries to the list if they are not null.
     */
    public void updateWashingPlans() {
        washingPlanEntry.clear();
        WashingPlan plan = washingPlanModel.getPlanForWeek();
        if (plan == null) {
            return;
        }
        List<WashingPlanEntry> weekPlans = plan.getEntries();
        if (weekPlans == null) {
            return;
        }
        washingPlanEntry.addAll(weekPlans);
    }

    public void updateWashingPlanPersons() {
        washingPlanPersons.clear();
        washingPlanPersons.addAll(washingPlanModel.getWashingPlanPersons());
    }

    public void updateWashingPlanTasks() {
        washingPlanTasks.clear();
        washingPlanTasks.addAll(washingPlanModel.getWashingPlanTasks());
    }

    /**
     * This function retrieves the washing plan entries for the current week from a washing plan model.
     *
     * @return The method `getWashingPlanEntries()` returns an `ObservableList` of `WashingPlanEntry`
     * objects.
     */
    public ObservableList<WashingPlanEntry> getWashingPlanEntries() {
        washingPlanEntry.clear();
        WashingPlan plansForCurrentWeek = washingPlanModel.getPlanForWeek();

        if (plansForCurrentWeek == null) {
            return washingPlanEntry;
        }
        if (plansForCurrentWeek.getEntries() == null) {
            return washingPlanEntry;
        }
        washingPlanEntry.addAll(plansForCurrentWeek.getEntries());

        return washingPlanEntry;
    }

    public void editWashingPlan() {
        washingPlanModel.editWashingPlan();
    }

    public ObservableList<Task> getWashingPlanTasks() {
        return washingPlanTasks;
    }

    public ObservableList<Person> getWashingPlanPersons() {
        return washingPlanPersons;
    }

    public int getCurrentWeek() {
        return washingPlanModel.getCurrentWeek();
    }

    public int getStartWeek() {
        return startWeek;
    }

    /**
     * The function `setStartWeek` sets the start week based on the input string after 
     * validating it as an integer.
     *
     * @param toWeekInput The `toWeekInput` parameter is a String representing the week 
     * number that you want to set as the start week.
     */
    public void setStartWeek(String toWeekInput) {
        if (!isInteger(toWeekInput)) {
            throw new IllegalArgumentException("To Week is not a valid integer.");
        }
        int newStartWeek = Integer.parseInt(toWeekInput);
        this.startWeek = newStartWeek;
    }

    /**
     * The function `setEndWeek` sets the end week value based on the input string after 
     * validating it as an integer.
     *
     * @param toWeekInput The `toWeekInput` parameter is a String representing the week 
     * number to set as the end week.
     */
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

    /**
     * The function checks if a given string can be parsed into an integer in Java.
     *
     * @param input The `isInteger` method checks if a given input string can be parsed into an 
     * integer. If the input can be successfully parsed without throwing a `NumberFormatException`,
     * the method returns `true`, indicating that the input is an integer. Otherwise, it returns 
     * `false`.
     *
     * @return The method `isInteger` returns a boolean value - `true` if the input string can 
     * be parsed into an integer using `Integer.parseInt`, and `false` if a `NumberFormatException`
     * is caught during
     * the parsing process.
     */
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * The `reset` function clears lists of persons and tasks, resets the current week to 1, 
     * and calls the `reset` method of a `washingPlanModel` object.
     */
    public void reset() {
        washingPlanPersons.clear();
        washingPlanTasks.clear();
        washingPlanModel.reset();
    }
}
