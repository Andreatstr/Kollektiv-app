package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a washing table, which contains a list of washing plans, persons, and tasks.
 * This class provides methods to manage the washing plans, retrieve the plans for specific weeks,
 * and manage the persons and tasks associated with the washing table.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WashingTable {
    private List<WashingPlan> washingPlans = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    public WashingTable() {
    }

    public WashingTable(List<Person> persons, List<Task> tasks) {
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

    /**
     * Retrieves the week number of the earliest washing plan.
     * If no plans exist, returns 0.
     *
     * @return the week number of the earliest washing plan, or 0 if no plans exist.
     */
    public int getLowestWeek() {
        if (washingPlans == null) {
            return 0;
        }
        if (washingPlans.size() == 0) {
            return 0;
        }
        return washingPlans.get(0).getWeekNumber();
    }

    /**
     * Retrieves the week number of the latest washing plan.
     * If no plans exist, returns 0.
     *
     * @return the week number of the latest washing plan, or 0 if no plans exist.
     */
    public int getHighestWeek() {
        if (washingPlans == null) {
            return 0;
        }
        if (washingPlans.size() == 0) {
            return 0;
        }
        return washingPlans.get(washingPlans.size() - 1).getWeekNumber();
    }

    /**
     * Retrieves the washing plan for a specific week.
     * If no plan exists for that week, returns null.
     *
     * @param week the week number to retrieve the washing plan for.
     * @return the {@link WashingPlan} for the given week, or null if no plan exists.
     */
    public WashingPlan getWashingPlanOfWeek(Integer week) {
        if (washingPlans == null) {
            return null;
        }
        for (WashingPlan plan : washingPlans) {
            if (plan.getWeekNumber() == week) {
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
