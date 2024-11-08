package data;

public class WashingPlanEntry {
  private Person person;
  private Task task;

  public WashingPlanEntry() {
  }

  public WashingPlanEntry(Person person, Task task) {
    this.person = person;
    this.task = task;
  }

  public Person getPerson() {
    return person;
  }

  public Task getTask() {
    return task;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public void setTask(Task task) {
    this.task = task;
  }
}
