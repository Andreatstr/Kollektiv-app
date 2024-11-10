package model;

import org.junit.jupiter.api.Test;

import data.HistoryShoppingListTable;
import data.House;
import data.Item;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import data.Waste;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataTest {

    @Test
    public void testWaste() {
        Waste waste = new Waste(5, "Plastic");
        assertEquals(5, waste.getWeek());
        assertEquals("Plastic", waste.getWasteType());

        waste.weekProperty().set(10);
        assertEquals(10, waste.getWeek());
    }

    @Test
    public void testWashingTable() {
        WashingTable table = new WashingTable();
        WashingPlan plan = new WashingPlan(1);
        table.addWashingPlan(plan);

        assertEquals(1, table.getLowestWeek());
        assertEquals(1, table.getHighestWeek());
        assertNotNull(table.getWashingPlanOfWeek(1));
    }

    @Test
    public void testWashingPlanEntry() {
        Person person = new Person("Bob");
        Task task = new Task("Vacuum");
        WashingPlanEntry entry = new WashingPlanEntry(person, task);

        assertEquals("Bob", entry.getPerson().getName());
        assertEquals("Vacuum", entry.getTask().getTask());

        Person newPerson = new Person("Charlie");
        entry.setPerson(newPerson);
        assertEquals("Charlie", entry.getPerson().getName());
    }

    @Test
    public void testWashingPlan() {
        WashingPlan plan = new WashingPlan(2);
        WashingPlanEntry entry = new WashingPlanEntry(new Person("Alice"), new Task("Sweep"));
        plan.addEntry(entry);

        assertEquals(2, plan.getWeekNumber());
        assertEquals(1, plan.getEntries().size());
        assertEquals("Alice", plan.getEntries().get(0).getPerson().getName());
    }

    @Test
    public void testTask() {
        Task task = new Task("Dust");
        assertEquals("Dust", task.getTask());

        task.setTask("Polish");
        assertEquals("Polish", task.getTask());
    }

    @Test
    public void testPerson() {
        Person person = new Person("David");
        assertEquals("David", person.getName());

        person.setName("Eva");
        assertEquals("Eva", person.getName());
    }

    @Test
    public void testItem() {
        Item item = new Item("Soap", 2);
        assertEquals("Soap", item.getItemName());
        assertEquals(2, item.getItemCount());

        item.setItemName("Detergent");
        item.setItemCount(5);
        assertEquals("Detergent", item.getItemName());
        assertEquals(5, item.getItemCount());

        item.setActive(true);
        assertTrue(item.getActive());
    }


    @Test
    public void testGetBoughtDate() {
        Item item = new Item("Bread", 2);
        assertEquals("?", item.getBoughtDate());

        item.setBoughtDate("2024-11-01");
        assertEquals("2024-11-01", item.getBoughtDate());
    }

    @Test
    public void testSetBoughtDate() {
        Item item = new Item("Juice", 3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = formatter.format(LocalDate.now());

        item.setBoughtDate();
        assertEquals(today, item.getBoughtDate());

        item.setBoughtDate("2024-11-01");
        assertEquals("2024-11-01", item.getBoughtDate());
    }

    @Test
    public void testActiveProperty() {
        Item item = new Item("Cheese", 4);
        assertFalse(item.activeProperty().get());

        item.setActive(true);
        assertTrue(item.activeProperty().get());
    }

    @Test
    public void testHouse() {
        House house = new House("HouseID1");
        assertEquals("HouseID1", house.getId());

        house.setId("HouseID2");
        assertEquals("HouseID2", house.getId());
    }

    @Test
    public void testHistoryShoppingListTable() {
        HistoryShoppingListTable historyItem = new HistoryShoppingListTable("Apples", 4, "2024-11-10");
        assertEquals("Apples", historyItem.getItem());
        assertEquals(4, historyItem.getCount());
        assertEquals("2024-11-10", historyItem.getWhen());

        historyItem.setItem("Bananas");
        historyItem.setCount(3);
        historyItem.setWhen("2024-11-11");

        assertEquals("Bananas", historyItem.getItem());
        assertEquals(3, historyItem.getCount());
        assertEquals("2024-11-11", historyItem.getWhen());
    }

    @Test
    public void testWashingTableAddWashingPlan() {
        WashingTable table = new WashingTable();
        WashingPlan plan = new WashingPlan(1);
        
        // Adding a washing plan
        table.addWashingPlan(plan);
        assertEquals(1, table.getWashingPlans().size());
        assertEquals(plan, table.getWashingPlans().get(0));
    }

    @Test
    public void testWashingTableClearWashingPlans() {
        WashingTable table = new WashingTable();
        WashingPlan plan1 = new WashingPlan(1);
        WashingPlan plan2 = new WashingPlan(2);
        
        // Adding plans and then clearing
        table.addWashingPlan(plan1);
        table.addWashingPlan(plan2);
        assertEquals(2, table.getWashingPlans().size());
        
        table.clearWashingPlans();
        assertEquals(0, table.getWashingPlans().size());
    }

    @Test
    public void testWashingTableGetLowestWeek() {
        WashingTable table = new WashingTable();
        
        // Add plans in ascending order
        WashingPlan plan1 = new WashingPlan(1); // Lowest week
        WashingPlan plan2 = new WashingPlan(3);
        table.addWashingPlan(plan1);
        table.addWashingPlan(plan2);
        
        assertEquals(1, table.getLowestWeek());
    }

    @Test
    public void testWashingTableGetHighestWeek() {
        WashingTable table = new WashingTable();
        
        // Add plans in descending order
        WashingPlan plan1 = new WashingPlan(3); // Highest week
        WashingPlan plan2 = new WashingPlan(1);
        table.addWashingPlan(plan2);
        table.addWashingPlan(plan1);
        
        assertEquals(3, table.getHighestWeek());
    }

    @Test
    public void testWashingTableGetWashingPlanOfWeek() {
        WashingTable table = new WashingTable();
        WashingPlan plan1 = new WashingPlan(1);
        WashingPlan plan2 = new WashingPlan(2);
        
        table.addWashingPlan(plan1);
        table.addWashingPlan(plan2);
        
        assertEquals(plan1, table.getWashingPlanOfWeek(1));
        assertEquals(plan2, table.getWashingPlanOfWeek(2));
        assertNull(table.getWashingPlanOfWeek(3)); // No plan for week 3
    }

    @Test
    public void testWashingTableManagePersons() {
        WashingTable table = new WashingTable();
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice"));
        persons.add(new Person("Bob"));
        
        table.setPersons(persons);
        assertEquals(2, table.getPersons().size());
        assertEquals("Alice", table.getPersons().get(0).getName());
        assertEquals("Bob", table.getPersons().get(1).getName());
    }

      @Test
      public void testWashingTableManageTasks() {
          WashingTable table = new WashingTable();
          List<Task> tasks = new ArrayList<>();
          tasks.add(new Task("Vacuum"));
          tasks.add(new Task("Dusting"));
          
          table.setTasks(tasks);
          assertEquals(2, table.getTasks().size());
          assertEquals("Vacuum", table.getTasks().get(0).getTask());
          assertEquals("Dusting", table.getTasks().get(1).getTask());
      }

    @Test
    public void testWashingTableConstructorWithPersonsAndTasks() {
        // Create sample persons and tasks lists
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice"));
        persons.add(new Person("Bob"));
        
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Vacuum"));
        tasks.add(new Task("Mop"));
        
        // Initialize WashingTable with these lists
        WashingTable table = new WashingTable(persons, tasks);
        
        // Verify that persons and tasks were correctly set
        assertEquals(2, table.getPersons().size());
        assertEquals("Alice", table.getPersons().get(0).getName());
        assertEquals("Bob", table.getPersons().get(1).getName());
        
        assertEquals(2, table.getTasks().size());
        assertEquals("Vacuum", table.getTasks().get(0).getTask());
        assertEquals("Mop", table.getTasks().get(1).getTask());
    }

    @Test
    public void testItemNoArgConstructor() {
        // Create an Item using the no-argument constructor
        Item item = new Item();
        
        // Check default values
        assertNull(item.getItemName());        // Expect null as no name is set
        assertEquals(0, item.getItemCount());  // Expect 0 as no count is set
        assertFalse(item.getActive());         // Expect false as active is not set to true
        assertEquals("?", item.getBoughtDate()); // Expect "?" as boughtDate is not set
    }

    @Test
    public void testTimePassed() {
        Item item = new Item("Milk", 1);
        
        // Set a specific boughtDate, for example, 10 days ago
        LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        item.setBoughtDate(tenDaysAgo.format(formatter));
        
        // Check timePassed with days less than, equal to, and greater than 10
        assertTrue(item.timePassed(5), "Expected true for days = 5 as 10 days have passed since boughtDate");
        assertFalse(item.timePassed(10), "Expected false for days = 10 as exactly 10 days have passed");
        assertFalse(item.timePassed(15), "Expected false for days = 15 as only 10 days have passed");

        // Test with no boughtDate set to ensure it throws an exception
        Item itemWithoutDate = new Item("Juice", 1);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            itemWithoutDate.timePassed(5);
        });
        assertEquals("The date for this object has not been set, boughtDate is null", exception.getMessage());
    }

    @Test
    public void testWashingPlanNoArgConstructor() {
        // Test the no-argument constructor
        WashingPlan plan = new WashingPlan();
        assertEquals(0, plan.getWeekNumber(), "Expected default week number to be 0");

        // Check if entries is null, and if so, skip the empty check
        if (plan.getEntries() == null) {
            System.out.println("Entries list is null by default.");
        } else {
            // Only check isEmpty() if entries is not null
            assertTrue(plan.getEntries().isEmpty(), "Expected entries list to be empty by default");
        }
    }
    
    @Test
    public void testSetWeekNumber() {
        WashingPlan plan = new WashingPlan();
        plan.setWeekNumber(5);
        assertEquals(5, plan.getWeekNumber(), "Expected week number to be set to 5");
    }

    @Test
    public void testSetEntries() {
        WashingPlan plan = new WashingPlan();

        // Create a list of WashingPlanEntry objects
        List<WashingPlanEntry> entries = new ArrayList<>();
        entries.add(new WashingPlanEntry(new Person("Alice"), new Task("Sweep")));
        entries.add(new WashingPlanEntry(new Person("Bob"), new Task("Mop")));
        
        // Set entries in WashingPlan
        plan.setEntries(entries);
        
        // Verify entries were set correctly
        assertEquals(2, plan.getEntries().size(), "Expected entries list size to be 2");
        assertEquals("Alice", plan.getEntries().get(0).getPerson().getName());
        assertEquals("Sweep", plan.getEntries().get(0).getTask().getTask());
        assertEquals("Bob", plan.getEntries().get(1).getPerson().getName());
        assertEquals("Mop", plan.getEntries().get(1).getTask().getTask());
    }

    @Test
    public void testWashingPlanEntryNoArgConstructor() {
        // Create a WashingPlanEntry using the no-argument constructor
        WashingPlanEntry entry = new WashingPlanEntry();
        
        // Check that person and task are null by default
        assertNull(entry.getPerson(), "Expected person to be null by default");
        assertNull(entry.getTask(), "Expected task to be null by default");
    }

    @Test
    public void testSetTask() {
        // Create a WashingPlanEntry with a person and initial task
        Person person = new Person("Alice");
        Task initialTask = new Task("Sweep");
        WashingPlanEntry entry = new WashingPlanEntry(person, initialTask);
        
        // Verify initial task
        assertEquals("Sweep", entry.getTask().getTask(), "Expected initial task to be 'Sweep'");
        
        // Set a new task
        Task newTask = new Task("Mop");
        entry.setTask(newTask);
        
        // Verify that the task was updated
        assertEquals("Mop", entry.getTask().getTask(), "Expected updated task to be 'Mop'");
    }

    @Test
    public void testTaskNoArgConstructor() {
        // Create a Task using the no-argument constructor
        Task task = new Task();
        
        // Verify that task is null by default
        assertNull(task.getTask(), "Expected task to be null by default");
    }

    @Test
    public void testTaskToString() {
        // Create a Task with a specific name
        Task task = new Task("Clean");
        
        // Verify that toString() returns the task name
        assertEquals("Clean", task.toString(), "Expected toString() to return 'Clean'");
    }
}

