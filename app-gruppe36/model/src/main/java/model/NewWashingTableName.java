package model;

public class NewWashingTableName {
    private String name;

    public NewWashingTableName(){}

    public NewWashingTableName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) { //repetition?
        this.name = newName;
    }
}
