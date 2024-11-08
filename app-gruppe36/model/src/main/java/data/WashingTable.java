package data;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WashingTable {
    private List<WashingPlan> washingPlans= new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

  public WashingTable() {
  }

    public WashingTable(List<Person> persons,List<Task> tasks ) {
        this.tasks = tasks;
        this.persons = persons;
    }

    public void addWashingPlan(WashingPlan plan) {
        washingPlans.add(plan);
    }

  public List<WashingPlan> getWashingPlans() {
    return washingPlans;
  }

  public void clearWashingPlans() {
    washingPlans.clear();
  }

    public void setWashingPlans(List<WashingPlan> washingPlans) {
        this.washingPlans = washingPlans;
    }

    public int getLowestWeek() {
        if (washingPlans == null) return 0;
        if (washingPlans.size() == 0) return 0;
        return washingPlans.get(0).getWeekNumber();
    }

    public int getHighestWeek() {
        if (washingPlans == null) return 0;
        if (washingPlans.size() == 0) return 0;
        return washingPlans.get(washingPlans.size()-1).getWeekNumber();
    }

    public WashingPlan getWashingPlanOfWeek(Integer week) {
        if (washingPlans == null) return null;
        for (WashingPlan plan : washingPlans)
        {
            if (plan.getWeekNumber() == week)
            {
                return plan;
            }
        }
        return null;
    }

    public List<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
