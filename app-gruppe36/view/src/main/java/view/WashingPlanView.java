package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.GeneratedWashingTable;
import java.io.IOException;

public class WashingPlanView {

    // legge til den lagrede tabell informasjonen

    @FXML
    private Button BackFromWashingPlan;

    @FXML
    private Button editWashingPlan;

    @FXML
    private TableView<WashingPlanView> generatedWashingPlanTable;

    @FXML
    private Button leftArrowButton;

    @FXML
    private TableColumn<WashingPlanView, String> listOfNamesForWashingPlan;

    @FXML
    private TableColumn<WashingPlanView, String> listOfTasksForWashingPlan;

    @FXML
    private Button rightArrowButton;

    @FXML
    private Label weekNumberField;

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
        //TODO: switch to previous week
    }

    @FXML
    void ButtonRightArrow(ActionEvent event) {
        //TODO: switch to next week
    }

}
