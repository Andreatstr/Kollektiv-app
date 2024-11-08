package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.Person;
import data.Task;
import viewmodel.WashingPlanViewModel;
import javafx.collections.ObservableList;

public class WashingPlanViewModelTest {

    private WashingPlanViewModel viewModel;

    @BeforeEach
    public void setUp() {
        viewModel = WashingPlanViewModel.getInstance();
        viewModel.reset();
        viewModel.setStartWeek("1");
        viewModel.setEndWeek("4");
    }

    @Test
    public void testAddTask() {
        viewModel.addTask("");
        ObservableList<Task> tasks = viewModel.getWashingPlanTasks();
        assertTrue(tasks.isEmpty(), "Tasks should not be added for empty task names");

        String taskName = "Vaske kjøkken";
        viewModel.addTask(taskName);

        ObservableList<Task> tasks2 = viewModel.getWashingPlanTasks();
        assertTrue(tasks2.stream().anyMatch(t -> t.getTask().equals(taskName)),
                "Task should be added to washing plan tasks");
    }

    @Test
    public void testAddPerson() {
        viewModel.addPerson("");
        ObservableList<Person> persons = viewModel.getWashingPlanPersons();
        assertTrue(persons.isEmpty(), "Persons should not be added for empty names");

        String personName = "Lars";
        viewModel.addPerson(personName);

        ObservableList<Person> persons2 = viewModel.getWashingPlanPersons();
        assertTrue(persons2.stream().anyMatch(p -> p.getName().equals(personName)),
                "Person should be added to washing plan persons");
    }

    @Test
    public void testGenerateWashingPlan() {
        /* 
        viewModel.addPerson("Lars");
        viewModel.addPerson("Andrea");
        viewModel.addTask("Vaske bad");
        viewModel.addTask("Vaske stue");

        viewModel.generateWashingPlan(1, 4);

        ObservableList<WashingPlan> washingPlans = viewModel.getWashingPlans();
        assertFalse(washingPlans.isEmpty(), "Washing plans should be generated");
        assertEquals(1, washingPlans.get(0).getWeekNumber(), "First week's plan should be for week 1");*/
    }

    @Test
    public void testGenerateWashingPlanWithPersonsAndTasks() {
        /*
        viewModel.addPerson("Lars");
        viewModel.addPerson("Andrea");
        viewModel.addTask("Vaske bad");
        viewModel.addTask("Vaske stue");

        List<Person> persons = viewModel.getWashingPlanPersons();
        List<Task> tasks = viewModel.getWashingPlanTasks();

        viewModel.generateWashingPlan(persons, tasks, 1, 4);

        ObservableList<WashingPlan> washingPlans = viewModel.getWashingPlans();
        assertFalse(washingPlans.isEmpty(), "Washing plans should be generated with persons and tasks");
        assertEquals(1, washingPlans.get(0).getWeekNumber(), "First week's plan should be for week 1");*/
    }

    @Test
    public void testGetWashingPlansForCurrentWeek() {
        /* 
        viewModel.addPerson("Lars");
        viewModel.addPerson("Andrea");
        viewModel.addTask("Vaske bad");
        viewModel.addTask("Vaske stue");

        viewModel.generateWashingPlan(1, 4);
        viewModel.setCurrentWeek(1);
        List<WashingPlan> currentWeekPlans = viewModel.getWashingPlansForCurrentWeek();

        assertEquals(2, currentWeekPlans.get(0).getEntries().size(), "Two tasks should be assigned in week 1");
        assertEquals(1, currentWeekPlans.get(0).getWeekNumber(), "Week number should be 1");*/
    }

    @Test
    public void testNextWeek() {
        viewModel.setStartWeek("1");
        viewModel.setEndWeek("4");

        viewModel.nextWeek();
        assertEquals(2, viewModel.getCurrentWeek(), "Should move to week 2");

        viewModel.nextWeek();
        viewModel.nextWeek();
        assertEquals(4, viewModel.getCurrentWeek(), "Should be at the last week (week 4)");

        viewModel.nextWeek();
        assertEquals(4, viewModel.getCurrentWeek(), "Should remain at the last week");
    }

    @Test
    public void testPreviousWeek() {
        viewModel.setStartWeek("1");
        viewModel.setEndWeek("4");
        viewModel.setCurrentWeek(3);

        viewModel.previousWeek();
        assertEquals(2, viewModel.getCurrentWeek(), "Should move back to week 2");

        viewModel.previousWeek();
        viewModel.previousWeek();
        assertEquals(1, viewModel.getCurrentWeek(), "Should be at the first week (week 1)");

        viewModel.previousWeek();
        assertEquals(1, viewModel.getCurrentWeek(), "Should remain at the first week");
    }

    @Test
    public void testSetStartWeekWithInvalidInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viewModel.setStartWeek("notAnInteger");
        });
        assertEquals("From Week is not a valid integer", exception.getMessage());
    }

    @Test
    public void testSetEndWeekWithInvalidInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            viewModel.setEndWeek("notAnInteger");
        });
        assertEquals("To Week is not a valid integer.", exception.getMessage());
    }

    @Test
    public void testGetStartAndEndWeek() {
        assertEquals("1", String.valueOf(viewModel.getStartWeek()), "Start week should be 1");
        assertEquals("4", String.valueOf(viewModel.getEndWeek()), "End week should be 4");
    }

    @Test
    public void testIsInteger() {
        assertTrue(viewModel.isInteger("123"), "Input should be recognized as an integer");
        assertFalse(viewModel.isInteger("abc"), "Input should not be recognized as an integer");
        assertFalse(viewModel.isInteger("12.3"), "Decimal input should not be recognized as an integer");
    }

    @Test
    public void testUpdateWashingPlans() {
        /* 
        List<WashingPlan> generatedPlans = new ArrayList<>();
        WashingPlan plan = new WashingPlan(1);
        generatedPlans.add(plan);

        viewModel.addPerson("Lars");
        viewModel.addTask("Vaske bad");

        viewModel.generateWashingPlan(1, 1);
        ObservableList<WashingPlan> initialPlans = viewModel.getWashingPlans();

        viewModel.addTask("Vaske stue");
        viewModel.generateWashingPlan(1, 1);

        ObservableList<WashingPlan> updatedPlans = viewModel.getWashingPlans();
        assertEquals(initialPlans.size(), updatedPlans.size(), "Number of plans should be the same after update");
        assertEquals(2, updatedPlans.get(0).getEntries().size(),
                "There should be two entries for week 1 after adding a new task");*/
    }

    @Test
    public void testUpdateWashingPlansWithoutParameters() {
        /*
        viewModel.addPerson("Lars");
        viewModel.addTask("Vaske bad");
        viewModel.generateWashingPlan(1, 1);

        ObservableList<WashingPlan> initialPlans = viewModel.getWashingPlans();

        viewModel.updateWashingPlans();

        ObservableList<WashingPlan> updatedPlans = viewModel.getWashingPlans();
        assertEquals(initialPlans.size(), updatedPlans.size(),
                "Number of plans should be the same after update without changes");*/
    }


    @Test
    public void testGetWashingPlans() {
        /*viewModel.addPerson("Lars");
        viewModel.addTask("Vaske bad");
        viewModel.generateWashingPlan(1, 1);

        ObservableList<WashingPlan> washingPlans = viewModel.getWashingPlans();
        assertFalse(washingPlans.isEmpty(), "Washing plans should not be empty after generation");
        assertEquals(1, washingPlans.size(), "There should be one washing plan generated");
        */
    }

    @Test
    public void testGetWashingPlanTasks() {
        viewModel.addTask("Vaske bad");
        viewModel.addTask("Vaske stue");

        ObservableList<Task> tasks = viewModel.getWashingPlanTasks();
        assertEquals(2, tasks.size(), "There should be two tasks in the washing plan");
    }

    @Test
    public void testGetWashingPlanPersons() {
        viewModel.addPerson("Lars");
        viewModel.addPerson("Andrea");

        ObservableList<Person> persons = viewModel.getWashingPlanPersons();
        assertEquals(2, persons.size(), "There should be two persons in the washing plan");
    }

    @Test
    public void testGetWashingPlanEntriesForCurrentWeek() {
        /*viewModel.reset();
        viewModel.addPerson("Lars");
        viewModel.addPerson("Andrea");
        viewModel.addTask("Vaske bad");
        viewModel.addTask("Vaske stue");

        viewModel.generateWashingPlan(1, 4);
        ObservableList<WashingPlanEntry> entries = viewModel.getWashingPlanEntriesForCurrentWeek();

        assertFalse(entries.isEmpty(), "Entries should be generated for the current week");
        assertEquals(2, entries.size(), "There should be two entries for week 1");
        */
    }

    @Test
    public void testReset() {
        /*viewModel.addPerson("Lars");
        viewModel.addTask("Vaske bad");
        viewModel.generateWashingPlan(1, 1);

        assertFalse(viewModel.getWashingPlans().isEmpty(), "Washing plans should not be empty before reset");
        assertEquals(1, viewModel.getWashingPlanPersons().size(), "There should be one person before reset");

        viewModel.reset();

        assertTrue(viewModel.getWashingPlans().isEmpty(), "Washing plans should be empty after reset");
        assertTrue(viewModel.getWashingPlanPersons().isEmpty(), "Person list should be empty after reset");
        */
    }
}
