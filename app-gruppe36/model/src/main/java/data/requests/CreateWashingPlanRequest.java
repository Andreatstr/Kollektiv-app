package data.requests;

import data.Person;
import data.Task;
import java.util.List;
public class CreateWashingPlanRequest {

    public List<Person> persons;

    public List<Task> tasks;

    public int fromWeek;

    public int toWeek;

    public String id;



    public CreateWashingPlanRequest() {
    }

    

    public CreateWashingPlanRequest(List<Person> persons, List<Task> tasks, int fromWeek, int toWeek, String id) {
        this.persons = persons;
        this.tasks = tasks;
        this.fromWeek = fromWeek;
        this.toWeek = toWeek;
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
