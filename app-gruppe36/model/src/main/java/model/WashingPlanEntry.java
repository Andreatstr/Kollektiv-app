package model;

public class WashingPlanEntry {
    private Person person;
    private Task task;
    private int week;

    public WashingPlanEntry(){}

    public WashingPlanEntry(Person person, Task task, int week) {
        this.person = person;
        this.task = task;
        this.week = week;
    }

    public Person getPerson() {
        return person;
    }

    public Task getTask() {
        return task;
    }

    public int getWeek() {
        return week;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public void setTask(Task task)
    {
        this.task = task;
    }

    public void setWeek(int week)
    {
        this.week = week;
    }
}
