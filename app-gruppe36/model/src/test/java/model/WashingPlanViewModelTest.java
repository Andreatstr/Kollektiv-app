package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import data.House;
import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import viewmodel.WashingPlanViewModel;
import restapi.DummyApi;

public class WashingPlanViewModelTest {

    //private HouseManager houseManager;
    private static WashingPlanViewModel washingPlanViewModel;
    private static WashingPlanModel mockWashingPlanModel;
    private WashingPlanModel washingPlanModel;

    @BeforeAll
    public static void setUpClass() {
        HouseManager.getInstance().api = new DummyApi();
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        washingPlanViewModel = WashingPlanViewModel.getInstance();
        mockWashingPlanModel = mock(WashingPlanModel.class);
        setWashingPlanModel(washingPlanViewModel, mockWashingPlanModel);

        when(mockWashingPlanModel.getWashingPlanPersons()).thenReturn(FXCollections.observableArrayList());
        when(mockWashingPlanModel.getWashingPlanTasks()).thenReturn(FXCollections.observableArrayList());

        washingPlanModel = WashingPlanModel.getInstance();
        washingPlanModel.reset();
    }

    @Test
    public void testAddPerson() {
        Person person = new Person("John Doe");
        washingPlanViewModel.addPerson("John Doe");

        when(mockWashingPlanModel.getWashingPlanPersons()).thenReturn(FXCollections.observableArrayList(person));
        washingPlanViewModel.updateWashingPlanPersons();

        ObservableList<Person> persons = washingPlanViewModel.getWashingPlanPersons();
        assertEquals(1, persons.size());
        assertEquals("John Doe", persons.get(0).getName());
    }

    @Test
    public void testAddTask() {
        Task task = new Task("Dishes");
        washingPlanViewModel.addTask("Dishes");

        when(mockWashingPlanModel.getWashingPlanTasks()).thenReturn(FXCollections.observableArrayList(task));
        washingPlanViewModel.updateWashingPlanTasks();

        ObservableList<Task> tasks = washingPlanViewModel.getWashingPlanTasks();
        assertEquals(1, tasks.size());
        assertEquals("Dishes", tasks.get(0).getTask());
    }

    @Test
    public void testNextWeek() {
        when(mockWashingPlanModel.getCurrentWeek()).thenReturn(2);

        washingPlanViewModel.nextWeek();

        verify(mockWashingPlanModel, times(1)).setCurrentWeek(3);
    }

    @Test
    public void testPreviousWeek() {
        when(mockWashingPlanModel.getCurrentWeek()).thenReturn(2);

        washingPlanViewModel.previousWeek();

        verify(mockWashingPlanModel, times(1)).setCurrentWeek(1);
    }

    @Test
    public void testSetCurrentWeek() {
        washingPlanViewModel.setCurrentWeek(5);

        verify(mockWashingPlanModel, times(1)).setCurrentWeek(5);
    }

    @Test
    public void testReset() {
        washingPlanViewModel.reset();

        assertEquals(0, washingPlanViewModel.getWashingPlanPersons().size());
        assertEquals(0, washingPlanViewModel.getWashingPlanTasks().size());
        verify(mockWashingPlanModel, times(1)).reset();
    }

    private static void setWashingPlanModel(WashingPlanViewModel washingPlanViewModel,
            WashingPlanModel mockWashingPlanModel) {
        try {
            var field = WashingPlanViewModel.class.getDeclaredField("washingPlanModel");
            field.setAccessible(true);
            field.set(washingPlanViewModel, mockWashingPlanModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSetStartWeek() {
        washingPlanViewModel.setStartWeek("5");

        assertEquals(5, washingPlanViewModel.getStartWeek());
    }

    @Test
    public void testSetStartWeek_InvalidInput() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            washingPlanViewModel.setStartWeek("invalid");
        });

        assertEquals("To Week is not a valid integer.", thrown.getMessage());
    }

    @Test
    public void testSetEndWeek() {
        washingPlanViewModel.setEndWeek("10");

        assertEquals(10, washingPlanViewModel.getEndWeek());
    }

    @Test
    public void testSetEndWeek_InvalidInput() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            washingPlanViewModel.setEndWeek("invalid");
        });

        assertEquals("To Week is not a valid integer.", thrown.getMessage());
    }

    @Test
    public void testGetWashingPlanEntries() {
        WashingPlan mockWashingPlan = mock(WashingPlan.class);
        List<WashingPlanEntry> mockEntries = new ArrayList<>();
        mockEntries.add(new WashingPlanEntry(new Person("John Doe"), new Task("Dishes")));
        when(mockWashingPlan.getEntries()).thenReturn(mockEntries);
        when(mockWashingPlanModel.getPlanForWeek()).thenReturn(mockWashingPlan);

        ObservableList<WashingPlanEntry> entries = washingPlanViewModel.getWashingPlanEntries();

        assertEquals(1, entries.size());
        assertEquals("John Doe", entries.get(0).getPerson().getName());
        assertEquals("Dishes", entries.get(0).getTask().getTask());
    }

    @Test
    public void testEditWashingPlan() {
        washingPlanViewModel.editWashingPlan();

        verify(mockWashingPlanModel, times(1)).editWashingPlan();
    }

    @Test
    public void testGetCurrentWeek() {
        when(mockWashingPlanModel.getCurrentWeek()).thenReturn(3);

        int currentWeek = washingPlanViewModel.getCurrentWeek();

        assertEquals(3, currentWeek);
    }

    @Test
    public void testUpdateWashingPlans_EntriesNotNull() {
        WashingPlan mockWashingPlan = mock(WashingPlan.class);
        WashingPlanEntry entry = new WashingPlanEntry(new Person("John Doe"), new Task("Dishes"));
        List<WashingPlanEntry> weekPlans = new ArrayList<>();
        weekPlans.add(entry);

        when(mockWashingPlanModel.getPlanForWeek()).thenReturn(mockWashingPlan);
        when(mockWashingPlan.getEntries()).thenReturn(weekPlans);

        washingPlanViewModel.updateWashingPlans();

        assertEquals(1, washingPlanViewModel.getWashingPlanEntries().size());
        assertEquals("John Doe", washingPlanViewModel.getWashingPlanEntries().get(0).getPerson().getName());
        assertEquals("Dishes", washingPlanViewModel.getWashingPlanEntries().get(0).getTask().getTask());
    }

    @Test
    public void testUpdateWashingPlans_EntriesNull() {
        WashingPlan mockWashingPlan = mock(WashingPlan.class);

        when(mockWashingPlanModel.getPlanForWeek()).thenReturn(mockWashingPlan);
        when(mockWashingPlan.getEntries()).thenReturn(null);

        washingPlanViewModel.updateWashingPlans();

        assertEquals(0, washingPlanViewModel.getWashingPlanEntries().size());
    }

    @Test
    public void testGenerateWashingPlan_ListRange() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John Doe"));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Laundry"));

        washingPlanViewModel.generateWashingPlan(persons, tasks, 1, 4);

        verify(mockWashingPlanModel, times(1)).generateWashingPlan(persons, tasks, 1, 4);
    }

    @Test
    public void testAddPersonModel() {
        Person person = new Person("John Doe");
        washingPlanModel.addPerson(person);

        assertEquals(1, washingPlanModel.getWashingPlanPersons().size());
        assertEquals("John Doe", washingPlanModel.getWashingPlanPersons().get(0).getName());
    }

    @Test
    public void testAddTaskModel() {
        Task task = new Task("Dishes");
        washingPlanModel.addTask(task);

        assertEquals(1, washingPlanModel.getWashingPlanTasks().size());
        assertEquals("Dishes", washingPlanModel.getWashingPlanTasks().get(0).getTask());
    }
}
