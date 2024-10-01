package model;

public class NewWashingTableTask {
    private String task;

    public NewWashingTableTask(){}

    public NewWashingTableTask(String newTask) {
        this.task = newTask;
    }

    public String getTask() {
        return task;
    }

    public void setName(String newTask) { //repetition?
        this.task = newTask;
    }
}

