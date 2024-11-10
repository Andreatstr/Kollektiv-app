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

/**
 * The `DataTest` class contains test methods for the data classes in the model/data-folder.
 */
public class DataTest {

    @Test
    public void testHistoryShoppingListTable() {
        HistoryShoppingListTable historyItem = new HistoryShoppingListTable("Salt", 4, "2024-11-10");
        assertEquals("Salt", historyItem.getItem());
        assertEquals(4, historyItem.getCount());
        assertEquals("2024-11-10", historyItem.getWhen());

        historyItem.setItem("Pepper");
        historyItem.setCount(3);
        historyItem.setWhen("2024-11-11");

        assertEquals("Pepper", historyItem.getItem());
        assertEquals(3, historyItem.getCount());
        assertEquals("2024-11-11", historyItem.getWhen());
    }

    @Test
    public void testHouse() {
        House house = new House("HouseID1");
        assertEquals("HouseID1", house.getId());

        house.setId("HouseID2");
        assertEquals("HouseID2", house.getId());
    }

    @Test
    public void testItem() {
        Item item = new Item("Vaskemiddel", 2);
        assertEquals("Vaskemiddel", item.getItemName());
        assertEquals(2, item.getItemCount());

        item.setItemName("Dopapir");
        item.setItemCount(5);
        assertEquals("Dopapir", item.getItemName());
        assertEquals(5, item.getItemCount());

        item.setActive(true);
        assertTrue(item.getActive());
    }

    @Test
    public void testGetBoughtDate() {
        Item item = new Item("Poser", 2);
        assertEquals("?", item.getBoughtDate());

        item.setBoughtDate("2024-11-01");
        assertEquals("2024-11-01", item.getBoughtDate());
    }

    @Test
    public void testSetBoughtDate() {
        Item item = new Item("Krydder", 3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = formatter.format(LocalDate.now());

        item.setBoughtDate();
        assertEquals(today, item.getBoughtDate());

        item.setBoughtDate("2024-11-01");
        assertEquals("2024-11-01", item.getBoughtDate());
    }

    @Test
    public void testActiveProperty() {
        Item item = new Item("Ost", 4);
        assertFalse(item.activeProperty().get());

        item.setActive(true);
        assertTrue(item.activeProperty().get());
    }

    @Test
    public void testItemNoArgConstructor() {
        Item item = new Item();
        assertNull(item.getItemName());
        assertEquals(0, item.getItemCount());
        assertFalse(item.getActive());
        assertEquals("?", item.getBoughtDate());
    }

    @Test
    public void testTimePassed() {
        Item item = new Item("Melk", 1);
        LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        item.setBoughtDate(tenDaysAgo.format(formatter));

        assertTrue(item.timePassed(5));
        assertFalse(item.timePassed(10));
        assertFalse(item.timePassed(15));

        Item itemWithoutDate = new Item("Juice", 1);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            itemWithoutDate.timePassed(5);
        });
        assertEquals("The date for this object has not been set, boughtDate is null", exception.getMessage());
    }

    @Test
    public void testPerson() {
        Person person = new Person("Andrea");
        assertEquals("Andrea", person.getName());

        person.setName("Eva");
        assertEquals("Eva", person.getName());
    }

    @Test
    public void testTask() {
        Task task = new Task("Rydde");
        assertEquals("Rydde", task.getTask());

        task.setTask("Vaske");
        assertEquals("Vaske", task.getTask());
    }

    @Test
    public void testTaskNoArgConstructor() {
        Task task = new Task();
        assertNull(task.getTask());
    }

    @Test
    public void testTaskToString() {
        Task task = new Task("Støvsuge");
        assertEquals("Støvsuge", task.toString());
    }

    @Test
    public void testWashingPlan() {
        WashingPlan plan = new WashingPlan(2);
        WashingPlanEntry entry = new WashingPlanEntry(new Person("Kristian"), new Task("Sweep"));
        plan.addEntry(entry);

        assertEquals(2, plan.getWeekNumber());
        assertEquals(1, plan.getEntries().size());
        assertEquals("Kristian", plan.getEntries().get(0).getPerson().getName());
    }

    @Test
    public void testWashingPlanNoArgConstructor() {
        WashingPlan plan = new WashingPlan();
        assertEquals(0, plan.getWeekNumber());

        if (plan.getEntries() != null) {
            assertTrue(plan.getEntries().isEmpty());
        }
    }

    @Test
    public void testSetWeekNumber() {
        WashingPlan plan = new WashingPlan();
        plan.setWeekNumber(5);
        assertEquals(5, plan.getWeekNumber());
    }

    @Test
    public void testSetEntries() {
        WashingPlan plan = new WashingPlan();
        List<WashingPlanEntry> entries = new ArrayList<>();
        entries.add(new WashingPlanEntry(new Person("Lars"), new Task("Vaske")));
        entries.add(new WashingPlanEntry(new Person("Kristian"), new Task("Tørke støv")));

        plan.setEntries(entries);
        assertEquals(2, plan.getEntries().size());
        assertEquals("Lars", plan.getEntries().get(0).getPerson().getName());
        assertEquals("Vaske", plan.getEntries().get(0).getTask().getTask());
        assertEquals("Kristian", plan.getEntries().get(1).getPerson().getName());
        assertEquals("Tørke støv", plan.getEntries().get(1).getTask().getTask());
    }

    @Test
    public void testWashingPlanEntry() {
        Person person = new Person("Hanne");
        Task task = new Task("Støvsuge");
        WashingPlanEntry entry = new WashingPlanEntry(person, task);

        assertEquals("Hanne", entry.getPerson().getName());
        assertEquals("Støvsuge", entry.getTask().getTask());

        Person newPerson = new Person("Ada");
        entry.setPerson(newPerson);
        assertEquals("Ada", entry.getPerson().getName());
    }

    @Test
    public void testWashingPlanEntryNoArgConstructor() {
        WashingPlanEntry entry = new WashingPlanEntry();
        assertNull(entry.getPerson());
        assertNull(entry.getTask());
    }

    @Test
    public void testSetTask() {
        Person person = new Person("Emma");
        Task initialTask = new Task("Vaske do");
        WashingPlanEntry entry = new WashingPlanEntry(person, initialTask);

        assertEquals("Vaske do", entry.getTask().getTask());

        Task newTask = new Task("Støvsuge");
        entry.setTask(newTask);
        assertEquals("Støvsuge", entry.getTask().getTask());
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
    public void testWashingTableAddWashingPlan() {
        WashingTable table = new WashingTable();
        WashingPlan plan = new WashingPlan(1);

        table.addWashingPlan(plan);
        assertEquals(1, table.getWashingPlans().size());
        assertEquals(plan, table.getWashingPlans().get(0));
    }

    @Test
    public void testWashingTableClearWashingPlans() {
        WashingTable table = new WashingTable();
        WashingPlan plan1 = new WashingPlan(1);
        WashingPlan plan2 = new WashingPlan(2);

        table.addWashingPlan(plan1);
        table.addWashingPlan(plan2);
        assertEquals(2, table.getWashingPlans().size());

        table.clearWashingPlans();
        assertEquals(0, table.getWashingPlans().size());
    }

    @Test
    public void testWashingTableGetLowestWeek() {
        WashingTable table = new WashingTable();
        WashingPlan plan1 = new WashingPlan(1);
        WashingPlan plan2 = new WashingPlan(3);
        table.addWashingPlan(plan1);
        table.addWashingPlan(plan2);

        assertEquals(1, table.getLowestWeek());
    }

    @Test
    public void testWashingTableGetHighestWeek() {
        WashingTable table = new WashingTable();
        WashingPlan plan1 = new WashingPlan(3);
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
        assertNull(table.getWashingPlanOfWeek(3));
    }

    @Test
    public void testWashingTableManagePersons() {
        WashingTable table = new WashingTable();
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Anna"));
        persons.add(new Person("Bernt"));

        table.setPersons(persons);
        assertEquals(2, table.getPersons().size());
        assertEquals("Anna", table.getPersons().get(0).getName());
        assertEquals("Bernt", table.getPersons().get(1).getName());
    }

    @Test
    public void testWashingTableManageTasks() {
        WashingTable table = new WashingTable();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Støvsuge"));
        tasks.add(new Task("Tørke støv"));

        table.setTasks(tasks);
        assertEquals(2, table.getTasks().size());
        assertEquals("Støvsuge", table.getTasks().get(0).getTask());
        assertEquals("Tørke støv", table.getTasks().get(1).getTask());
    }

    @Test
    public void testWashingTableConstructorWithPersonsAndTasks() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Eva"));
        persons.add(new Person("Bernt"));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Vaske"));
        tasks.add(new Task("Rydde"));

        WashingTable table = new WashingTable(persons, tasks);

        assertEquals(2, table.getPersons().size());
        assertEquals("Eva", table.getPersons().get(0).getName());
        assertEquals("Bernt", table.getPersons().get(1).getName());

        assertEquals(2, table.getTasks().size());
        assertEquals("Vaske", table.getTasks().get(0).getTask());
        assertEquals("Rydde", table.getTasks().get(1).getTask());
    }

    @Test
    public void testWaste() {
        Waste waste = new Waste(5, "Plast");
        assertEquals(5, waste.getWeek());
        assertEquals("Plast", waste.getWasteType());

        waste.weekProperty().set(10);
        assertEquals(10, waste.getWeek());
    }
}
