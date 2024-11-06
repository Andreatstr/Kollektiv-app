package view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewmodel.WashingPlanViewModel;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewmodel.WashingPlanViewModel;

import java.io.IOException;
import java.util.List;

import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;
import java.util.List;

import data.Person;
import data.Task;
import data.WashingPlan;
import data.WashingPlanEntry;
import data.WashingTable;

public class WashingPlanView {

    private WashingPlanViewModel washingPlanViewModel;

    public WashingPlanView() {
        washingPlanViewModel = WashingPlanViewModel.getInstance();
    }
    
    @FXML
    private Button BackFromWashingPlan;

    @FXML
    private Button editWashingPlan;

    @FXML
    private TableView<WashingPlanEntry> newWashingPlanTable;
    private TableView<WashingPlanEntry> newWashingPlanTable;

    @FXML
    private Button leftArrowButton;

    @FXML
    private TableColumn<WashingPlanEntry, String> listOfNamesForWashingPlan;
    private TableColumn<WashingPlanEntry, String> listOfNamesForWashingPlan;

    @FXML
    private TableColumn<WashingPlanEntry, String> listOfTasksForWashingPlan;
    private TableColumn<WashingPlanEntry, String> listOfTasksForWashingPlan;

    @FXML
    private Button rightArrowButton;

    @FXML
    private Button HomeButton;

    @FXML
    private Button HomeButton;

    @FXML
    private Label weekNumberField;

    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonBackFromWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WashingPlanOverview.fxml");
    }

    @FXML
    void ButtonEditWashingPlan(ActionEvent event) throws IOException {
        washingPlanViewModel.editWashingPlan();
        SceneSwitcher.switchToScene(event, "NewWashingPlan.fxml");
    }

    @FXML
    void ButtonLeftArrow(ActionEvent event) {
        washingPlanViewModel.previousWeek(); 
        weekNumberField.setText(String.valueOf(washingPlanViewModel.getCurrentWeek()));
    }

    @FXML
    void ButtonRightArrow(ActionEvent event) {
        washingPlanViewModel.nextWeek(); 
        weekNumberField.setText(String.valueOf(washingPlanViewModel.getCurrentWeek()));
    }

    public void initialize () {
        Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(66);  
        imageView.setFitHeight(63); 
        imageView.setPreserveRatio(true);

        HomeButton.setGraphic(imageView);
        
        listOfNamesForWashingPlan.setCellValueFactory(new PropertyValueFactory<>("person"));
        listOfTasksForWashingPlan.setCellValueFactory(new PropertyValueFactory<>("task"));

        String startNumber = String.valueOf(washingPlanViewModel.getCurrentWeek());

        weekNumberField.setText(startNumber);
        newWashingPlanTable.setItems(washingPlanViewModel.getWashingPlanEntries());
    }
}
