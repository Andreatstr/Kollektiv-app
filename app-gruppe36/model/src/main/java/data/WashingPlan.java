package data;

import java.util.ArrayList;
import java.util.List;

/**
 * The `WashingPlan` class represents a plan for washing activities with entries for
 * each task and a week number.
 */
public class WashingPlan {
    private List<WashingPlanEntry> entries;
    private int weekNumber;

    public WashingPlan() {
    }

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

    // For Json to work
    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setEntries(List<WashingPlanEntry> entries) {
        this.entries = entries;
    }

}
