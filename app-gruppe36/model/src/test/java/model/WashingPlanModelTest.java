package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingTable;
import restapi.DummyApi;

public class WashingPlanModelTest {

    private WashingPlanModel model;

    @BeforeEach
    public void setUp() {
        model = WashingPlanModel.getInstance();
        HouseManager.getInstance().api = new DummyApi(); // Setter opp DummyApi som mock API for testing
        model.reset(); // Sikrer at modellen er resatt før hver test
    }

    @Test
    public void testAddPerson() {
        Person person1 = new Person("Alice");
        Person person2 = new Person("Bob");

        model.addPerson(person1);
        model.addPerson(person2);

        List<Person> persons = model.getWashingPlanPersons();
        assertEquals(2, persons.size(), "There should be 2 persons in the washing plan");
        assertEquals("Alice", persons.get(0).getName());
        assertEquals("Bob", persons.get(1).getName());
    }

    @Test
    public void testAddDuplicatePerson() {
        Person person = new Person("Charlie");

        model.addPerson(person);
        model.addPerson(person); // Prøver å legge til samme person igjen

        List<Person> persons = model.getWashingPlanPersons();
        assertEquals(1, persons.size(), "Duplicate person should not be added");
    }

    @Test
    public void testAddTask() {
        Task task1 = new Task("Clean Kitchen");
        Task task2 = new Task("Vacuum Living Room");

        model.addTask(task1);
        model.addTask(task2);

        List<Task> tasks = model.getWashingPlanTasks();
        assertEquals(2, tasks.size(), "There should be 2 tasks in the washing plan");
        assertEquals("Clean Kitchen", tasks.get(0).getTask());
        assertEquals("Vacuum Living Room", tasks.get(1).getTask());
    }

    @Test
    public void testAddDuplicateTask() {
        Task task = new Task("Mop Floor");

        model.addTask(task);
        model.addTask(task); // Prøver å legge til samme oppgave igjen

        List<Task> tasks = model.getWashingPlanTasks();
        assertEquals(1, tasks.size(), "Duplicate task should not be added");
    }

    @Test
    public void testGetPlanForWeek() {
        model.setCurrentWeek(2);
        WashingPlan plan = model.getPlanForWeek();

        if (model.getWashingTables() != null) {
            assertNotNull(plan, "WashingPlan should not be null if WashingTable exists");
        } else {
            assertNull(plan, "WashingPlan should be null if WashingTable is null");
        }
    }

    @Test
    public void testReset() {
        model.reset();
        assertTrue(model.getWashingPlanPersons().isEmpty(), "Washing plan persons should be cleared after reset");
        assertTrue(model.getWashingPlanTasks().isEmpty(), "Washing plan tasks should be cleared after reset");
        assertNull(model.getWashingTables(), "WashingTable should be null after reset");
        assertEquals(0, model.getCurrentWeek(), "Current week should be reset to 0");
    }
}
