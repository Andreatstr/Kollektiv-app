package viewmodel;

// import java.util.ArrayList;
// import data.WashingTable;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import java.util.List;
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
    private ObservableList<WashingPlanEntry> washingPlanEntry = FXCollections.observableArrayList();

  private WashingPlanViewModel() {
    washingPlanModel = WashingPlanModel.getInstance();
  }

  public static WashingPlanViewModel getInstance() {
    if (washingPlanViewModel != null) {
      return washingPlanViewModel;
    }
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
        updateWashingPlans();
    }

    public void nextWeek() {
        washingPlanModel.setCurrentWeek(washingPlanModel.getCurrentWeek()+1);
        updateWashingPlans();
    }

    public void previousWeek() {
        washingPlanModel.setCurrentWeek(washingPlanModel.getCurrentWeek()-1);
        updateWashingPlans();
    }

    public void setCurrentWeek(Integer week) {
        washingPlanModel.setCurrentWeek(week);
        updateWashingPlans();
    }

    public void generateWashingPlan(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek) {
        washingPlanModel.generateWashingPlan(persons, tasks, fromWeek, toWeek);
        updateWashingPlans();
    }

    public void updateWashingPlans() {
        washingPlanEntry.clear();
        currentWeek = washingPlanModel.getCurrentWeek();
        WashingPlan plan = washingPlanModel.getPlanForWeek();
        if (plan == null) return;
        List<WashingPlanEntry> weekPlans = plan.getEntries();
        if (weekPlans == null) return;
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

    public ObservableList<WashingPlanEntry> getWashingPlanEntries() {
        washingPlanEntry.clear();
        WashingPlan plansForCurrentWeek = washingPlanModel.getPlanForWeek();

        if (plansForCurrentWeek == null) return washingPlanEntry;
        if (plansForCurrentWeek.getEntries() == null) return washingPlanEntry;
        washingPlanEntry.addAll(plansForCurrentWeek.getEntries());

        return washingPlanEntry;
    }

    public void editWashingPlan()
    {
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

    public void setStartWeek(String toWeekInput) {
        if (!isInteger(toWeekInput)) {
            throw new IllegalArgumentException("To Week is not a valid integer.");
        }
        int newStartWeek = Integer.parseInt(toWeekInput);
        this.startWeek = newStartWeek;
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
        washingPlanPersons.clear();
        washingPlanTasks.clear();
        currentWeek = 1;
        washingPlanModel.reset();
    }
}
