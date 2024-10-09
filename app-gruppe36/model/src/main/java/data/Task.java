package data;

public class Task {
    private String task;

    public Task(){}

    public Task(String newTask) {
        this.task = newTask;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String newTask) {
        this.task = newTask;
    }

    @Override
    public String toString() {
        return this.task; 
    }
}

