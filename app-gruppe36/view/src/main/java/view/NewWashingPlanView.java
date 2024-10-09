package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import data.Item;
import viewmodel.WashingPlanViewModel;

import java.io.IOException;
import java.util.List;

import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NewWashingPlanView {
    
    private WashingPlanViewModel washingPlanViewModel;

    public NewWashingPlanView() {
        washingPlanViewModel = WashingPlanViewModel.getInstance();
    }

    @FXML
    private Button BackFromNewWashingPlan;

    @FXML
    private Button addPersonButton;

    @FXML
    private TextField addPersonField;

    @FXML
    private Button addTaskButton;

    @FXML
    private TextField addTaskField;

    @FXML
    private TextField fromWeek;

    @FXML
    private TextField toWeek;

    @FXML
    private Button generateWashingPlan;

    @FXML
    private TableColumn<Person, String> listOfNamesForNewWashingPlan;

    @FXML
    private TableColumn<Task, String> listOfTasksForNewWashingPlan;

    @FXML
    private TableView<Person> newWashingPlanNameTable;

    @FXML
    private TableView<Task> newWashingPlanTaskTable;

    @FXML
    private Button HomeButton;

    @FXML
    private void ButtonAddPerson(ActionEvent event) {
        washingPlanViewModel.addPerson(addPersonField.getText());
        addPersonField.setText("");
    }

    @FXML
    private void ButtonAddTask(ActionEvent event) {
        washingPlanViewModel.addTask(addTaskField.getText());
        addTaskField.setText("");
    }

    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonBackFromNewWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WashingPlanOverview.fxml");
    }

    @FXML
    void ButtonGenerateWashingPlan(ActionEvent event) throws IOException {
        System.out.println("Button Pressed"); // debug comment
        String fromWeekInput = fromWeek.getText();
        String toWeekInput = toWeek.getText();

        int start = Integer.parseInt(fromWeekInput);
        int end = Integer.parseInt(toWeekInput);

        if (start > end) {
            washingPlanViewModel.setStartWeek(toWeekInput);
            washingPlanViewModel.setEndWeek(fromWeekInput);
        }
        else {
        washingPlanViewModel.setStartWeek(fromWeekInput);
        washingPlanViewModel.setEndWeek(toWeekInput);
        }

        int fromWeek = washingPlanViewModel.getStartWeek();
        int toWeek = washingPlanViewModel.getEndWeek();

        if (fromWeek > toWeek) {
            System.out.println("From Week cannot be greater than To Week."); // TODO: include plans that go over new
                                                                             // years
            return;
        }

        washingPlanViewModel.setCurrentWeek(fromWeek);
        washingPlanViewModel.generateWashingPlan(fromWeek, toWeek);
        SceneSwitcher.switchToScene(event, "WashingPlan.fxml");
    }

    public void initialize() {
        listOfNamesForNewWashingPlan.setCellValueFactory(new PropertyValueFactory<>("name"));
        listOfTasksForNewWashingPlan.setCellValueFactory(new PropertyValueFactory<>("task"));

        newWashingPlanNameTable.setItems(washingPlanViewModel.getWashingPlanPersons());
        newWashingPlanTaskTable.setItems(washingPlanViewModel.getWashingPlanTasks());

        // ---//
        Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(66);
        imageView.setFitHeight(63);
        imageView.setPreserveRatio(true);
        HomeButton.setGraphic(imageView);

    }

}
