package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class WashingPlanOverviewView {

    @FXML
    private Button BackFromWashingPlan;

    @FXML
    private Button createNewWashingPlanButton;

    @FXML
    private Button openWashingPlanButton;

    @FXML
    private void handleButtonBackFromWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    private void handleButtonCreateNewWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "NewWashingPlan.fxml");
    }

    @FXML
    private void handleButtonOpenWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WashingPlan.fxml");
    }
}
