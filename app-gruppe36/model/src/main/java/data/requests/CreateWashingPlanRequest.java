package data.requests;

import data.Person;
import data.Task;
import java.util.List;

/**
 * The `CreateWashingPlanRequest` class in Java represents a request object containing lists
 * of persons and tasks, along with week range and an ID.
 */
public class CreateWashingPlanRequest {

    private List<Person> persons;
    private List<Task> tasks;
    private int fromWeek;
    private int toWeek;
    private String id;

    public CreateWashingPlanRequest() {
    }

    /**
     * Constructs a new `CreateWashingPlanRequest` with specified lists of persons and tasks, 
     * a range of weeks, and an identifier.
     *
     * @param prsn  the list of `Person` objects involved in the washing plan
     * @param task  the list of `Task` objects to be scheduled in the washing plan
     * @param fw    the starting week number for the plan
     * @param tw    the ending week number for the plan
     * @param id    a unique identifier for this washing plan request
     */
    public CreateWashingPlanRequest(List<Person> prsn, List<Task> task, int fw, int tw, String id) {
        this.persons = prsn;
        this.tasks = task;
        this.fromWeek = fw;
        this.toWeek = tw;
        this.id = id;
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

    public int getFromWeek() {
        return this.fromWeek;
    }

    public void setFromWeek(int fromWeek) {
        this.fromWeek = fromWeek;
    }

    public int getToWeek() {
        return this.toWeek;
    }

    public void setToWeek(int toWeek) {
        this.toWeek = toWeek;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
