package view;

// import javafx.scene.input.KeyCode;
import data.Person;
import data.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import viewmodel.WashingPlanViewModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

public class NewWashingPlanTest extends ApplicationTest {

    private TableView<Person> personTable;
    private TableView<Task> taskTable;
    private WashingPlanViewModel viewModel;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NewWashingPlan.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

        personTable = lookup("#newWashingPlanNameTable").queryTableView();
        taskTable = lookup("#newWashingPlanTaskTable").queryTableView();

        viewModel = WashingPlanViewModel.getInstance();
    }

    @BeforeEach
    public void setup() {
        viewModel.reset();
    }

    @Test
    public void testAddPerson() {
        clickOn("#addPersonField").clickOn().write("Lars");
        clickOn("#addPersonButton");

        assertEquals(1, personTable.getItems().size(), "Person was not added to the table.");
        assertEquals(1, viewModel.getWashingPlanPersons().size(), "Person was not added to the ViewModel.");
        verifyThat("#addPersonField", hasText("")); // Check if the input field is cleared
    }

    @Test
    public void testAddTask() {
        clickOn("#addTaskField").clickOn().write("Vaske do");
        clickOn("#addTaskButton");

        assertEquals(1, taskTable.getItems().size(), "Task was not added to the table.");
        assertEquals(1, viewModel.getWashingPlanTasks().size(), "Task was not added to the ViewModel.");
        verifyThat("#addTaskField", hasText("")); // Check if the input field is cleared
    }

    @Test
    public void testSetWeekRange() {
        clickOn("#fromWeek").write("1");
        clickOn("#toWeek").write("5");

        clickOn("#generateWashingPlan");

        assertEquals(1, viewModel.getCurrentWeek(), "The current week should be set to the start week.");
        assertEquals(5, viewModel.getEndWeek(), "The end week should be set correctly.");
    }

    @Test
    public void testWeekRangeValidation() {
        clickOn("#fromWeek").write("5");
        clickOn("#toWeek").write("1");

        clickOn("#generateWashingPlan");

        assertEquals(1, viewModel.getCurrentWeek(), "Current week should not change with invalid range.");
    }

    @Test
    public void testAddingMultiplePersons() {
        clickOn("#addPersonField").clickOn().write("Lars");
        clickOn("#addPersonButton");

        clickOn("#addPersonField").clickOn().write("Ola");
        clickOn("#addPersonButton");

        assertEquals(2, personTable.getItems().size(), "Not all persons were added to the table.");
        assertEquals(2, viewModel.getWashingPlanPersons().size(), "Not all persons were added to the ViewModel.");
    }

    @Test
    public void testAddingMultipleTasks() {
        clickOn("#addTaskField").clickOn().write("Vaske do");
        clickOn("#addTaskButton");

        clickOn("#addTaskField").clickOn().clickOn().write("Rengjøre kjøkkenet");
        clickOn("#addTaskButton");

        assertEquals(2, taskTable.getItems().size(), "Not all tasks were added to the table.");
        assertEquals(2, viewModel.getWashingPlanTasks().size(), "Not all tasks were added to the ViewModel.");
    }

    @Test
    public void testEmptyPersonField() {
        clickOn("#addPersonField").write("");
        clickOn("#addPersonButton");

        assertEquals(0, personTable.getItems().size(), "Person should not have been added with empty input.");
        assertEquals(0, viewModel.getWashingPlanPersons().size(),
                "Person should not have been added to the ViewModel.");
    }

    @Test
    public void testEmptyTaskField() {
        clickOn("#addTaskField").write("");
        clickOn("#addTaskButton");

        assertEquals(0, taskTable.getItems().size(), "Task should not have been added with empty input.");
        assertEquals(0, viewModel.getWashingPlanTasks().size(), "Task should not have been added to the ViewModel.");
    }

    @Test
    public void testSettingWeekRangeEdgeCases() {
        clickOn("#fromWeek").write("1");
        clickOn("#toWeek").write("52");

        clickOn("#generateWashingPlan");

        assertEquals(1, viewModel.getCurrentWeek(), "The current week should be set to the start week.");
        assertEquals(52, viewModel.getEndWeek(), "The end week should be set correctly.");
        clickOn("#homeButton");
    }
}
