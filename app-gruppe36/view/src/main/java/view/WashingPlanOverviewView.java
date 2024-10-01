package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        switchToScene(event, "MainMenu.fxml");
    }

    @FXML
    private void handleButtonCreateNewWashingPlan(ActionEvent event) throws IOException {
        switchToScene(event, "NewWashingPlan.fxml");
    }

    @FXML
    private void handleButtonOpenWashingPlan(ActionEvent event) throws IOException {
        switchToScene(event, "WashingPlan.fxml");
    }

    private void switchToScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
}
