package view;

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
import model.Person;
import model.Task;
import model.WashingTable;
import model.WashingPlan;
import model.WashingPlanEntry;
import model.viewmodel.WashingPlanViewModel;

import java.io.IOException;
import java.util.List;

public class WashingPlanView {

    private WashingPlanViewModel washingPlanViewModel;
    
    ObservableList<WashingPlanEntry> observableList = FXCollections.observableArrayList();

    public WashingPlanView() {
        washingPlanViewModel = WashingPlanViewModel.getInstance();
    }
    
    @FXML
    private Button BackFromWashingPlan;

    @FXML
    private Button editWashingPlan;

    @FXML
    private TableView<WashingPlanEntry> newWashingPlanTable;

    @FXML
    private Button leftArrowButton;

    @FXML
    private TableColumn<WashingPlanEntry, String> listOfNamesForWashingPlan;

    @FXML
    private TableColumn<WashingPlanEntry, String> listOfTasksForWashingPlan;

    @FXML
    private Button rightArrowButton;

    @FXML
    private Button HomeButton;

    @FXML
    private Label weekNumberField;

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
        SceneSwitcher.switchToScene(event, "NewWashingPlan.fxml");
    }

    @FXML
    void ButtonLeftArrow(ActionEvent event) {
        int currentWeek = washingPlanViewModel.getCurrentWeek();
        weekNumberField.setText(String.valueOf(currentWeek));
        if (washingPlanViewModel.isThisTheFirstWeek(currentWeek)) {
            System.out.println("Already at the first week.");
            return;
        } 
        washingPlanViewModel.previousWeek(); 
        updateWashingPlanTable(); 
    }

    @FXML
    void ButtonRightArrow(ActionEvent event) {
        int currentWeek = washingPlanViewModel.getCurrentWeek(); 
        weekNumberField.setText(String.valueOf(currentWeek)); 
        if (washingPlanViewModel.isThisTheLastWeek(currentWeek)) {
            System.out.println("Already at the last week.");
            return;
        } 
        washingPlanViewModel.nextWeek(); 
        updateWashingPlanTable();
    }

    public void initialize () {
        Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(66);  
        imageView.setFitHeight(63); 
        imageView.setPreserveRatio(true);

        HomeButton.setGraphic(imageView);
        //---//
        
        listOfNamesForWashingPlan.setCellValueFactory(new PropertyValueFactory<>("person"));
        listOfTasksForWashingPlan.setCellValueFactory(new PropertyValueFactory<>("task"));


        int fromWeek = washingPlanViewModel.getStartWeek();
        int toWeek = washingPlanViewModel.getEndWeek();
        washingPlanViewModel.generateWashingPlan(fromWeek, toWeek);
        
        newWashingPlanTable.setItems(observableList);

        updateWashingPlanTable();
    }

    private void updateWashingPlanTable() {
        List<WashingPlanEntry> entriesForCurrentWeek = washingPlanViewModel.getWashingPlanEntriesForCurrentWeek();
        for (WashingPlanEntry entry : entriesForCurrentWeek) System.out.println(entry.getPerson() + "  " + entry.getTask());
        if (entriesForCurrentWeek != null && !entriesForCurrentWeek.isEmpty()) 
        {
            observableList.clear();
            observableList.addAll(entriesForCurrentWeek);
        }
    }

}
