package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.GeneratedWashingTable;

public class GeneratedWashingPlanView {

    // legge til den lagrede tabell informasjonen

    @FXML
    private Button BackFromWashingPlan;

    @FXML
    private Button editWashingPlan;

    @FXML
    private TableView<GeneratedWashingPlanView> generatedWashingPlanTable;

    @FXML
    private Button leftArrowButton;

    @FXML
    private TableColumn<GeneratedWashingPlanView, String> listOfNamesForWashingPlan;

    @FXML
    private TableColumn<GeneratedWashingPlanView, String> listOfTasksForWashingPlan;

    @FXML
    private Button rightArrowButton;

    @FXML
    private Label weekNumberField;

    @FXML
    void ButtonBackFromWashingPlan(ActionEvent event) {

    }

    @FXML
    void ButtonEditWashingPlan(ActionEvent event) {

    }

    @FXML
    void ButtonLeftArrow(ActionEvent event) {

    }

    @FXML
    void ButtonRightArrow(ActionEvent event) {

    }

}
