package model;

import java.util.ArrayList;
import java.util.List;

public class WashingTable {
    private List<WashingPlan> washingPlans= new ArrayList<>();     //list to store washing plans for each week

    public WashingTable() {
    }

    public void addWashingPlanEntry(WashingPlan entry) {
        washingPlans.add(entry);
    }

    public List<WashingPlan> getWashingPlans() {
        return washingPlans;
    }

    public void clearWashingPlans() {
        washingPlans.clear();
    }

    public void setWashingPlans(List<WashingPlan> washingPlans)
    {
        this.washingPlans = washingPlans;
    }
}
