package core;

import data.House;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import java.util.List;

/**
 * The {@link WashingPlanController} class is responsible for generating washing
 * plans for a specified period
 * and storing them in the corresponding house's {@link WashingTable}.
 * This class ensures that a washing plan can be generated, assigned to people
 * and tasks, and saved to the house data.
 */
public class WashingPlanController {

    private static WashingPlanController washingPlanModel = null;

    private WashingPlanController() {
    }

    private void storeToFile() {
        HouseController.getInstance().saveHouse();
    }

    /**
     * Returns the singleton instance of {@link WashingPlanController}.
     * This method ensures that only one instance of the
     * {@link WashingPlanController} exists throughout the application.
     *
     * @return the singleton instance of {@link WashingPlanController}.
     */
    public static synchronized WashingPlanController getInstance() {
        if (washingPlanModel != null) {
            return washingPlanModel;
        }
        washingPlanModel = new WashingPlanController();
        return washingPlanModel;
    }

    /**
     * Generates a washing plan for a specified range of weeks and stores it in the
     * house's {@link WashingTable}.
     * This method takes a list of people and tasks, assigns them to each week in
     * the given range, and stores the generated
     * washing plan in the house's {@link WashingTable}.
     *
     * @param per the list of people to be assigned tasks.
     * @param tsk the list of tasks to be assigned.
     * @param fw  the starting week for the washing plan.
     * @param tw  the ending week for the washing plan.
     * @param id  the ID of the house where the washing plan will be stored.
     * @return the updated {@link House} object with the newly generated washing
     *         plan, or {@code null} if the house is not found.
     */
    public House generateWashingPlan(List<Person> per, List<Task> tsk, int fw, int tw, String id) {
        House house = HouseController.getInstance().getHouse(id);
        WashingTable washingTable = new WashingTable(per, tsk);
        if (house == null) {
            return null;
        }

        List<Person> names = per;
        int numPeople = names.size();
        int numTasks = tsk.size();

        for (int week = fw; week <= tw; week++) {
            WashingPlan washingPlan = new WashingPlan(week);
            for (int i = 0; i < numTasks; i++) {
                Task task = tsk.get(i);
                Person assignedPerson = names.get((i + (week - fw)) % numPeople);
                WashingPlanEntry entry = new WashingPlanEntry(assignedPerson, task);
                washingPlan.addEntry(entry);
            }
            washingTable.addWashingPlan(washingPlan);
        }
        house.setWashingTable(washingTable);
        storeToFile();
        return house;

    }

}