package model;

import java.util.ArrayList;
import java.util.List;

public class WashingPlan {
    private List<WashingPlanEntry> entries;
    private int weekNumber;


    public WashingPlan(){}

    public WashingPlan(int weekNumber) {
        this.entries = new ArrayList<>();
        this.weekNumber = weekNumber;
    }

    public void addEntry(WashingPlanEntry entry) {
        entries.add(entry);
    }

    public List<WashingPlanEntry> getEntries() {
        return entries;
    }

    public int getWeekNumber() {
        return weekNumber;
    }


    //For at Json skal fungere
    public void setWeekNumber(int weekNumber)
    {
        this.weekNumber = weekNumber;
    }

    public void setEntries(List<WashingPlanEntry> entries)
    {
        this.entries = entries;
    }

}
