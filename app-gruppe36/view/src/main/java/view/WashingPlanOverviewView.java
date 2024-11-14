package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The class `WashingPlanOverviewView` in Java contains FXML annotations for buttons and methods to
 * handle button actions for navigating between different scenes, with an initialization method
 * to set a home button image.
 */
public class WashingPlanOverviewView {

    @FXML
    private Button backFromWashingPlan;

    @FXML
    private Button createNewWashingPlanButton;

    @FXML
    private Button openWashingPlanButton;

    @FXML
    private Button homeButton;

    @FXML
    void buttonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    private void buttonBackFromWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    private void buttonCreateNewWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "NewWashingPlan.fxml");
    }

    @FXML
    private void buttonOpenWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WashingPlan.fxml");
    }

    /**
     * The `initialize` function sets an image of a house on a button in a Java application.
     */
    public void initialize() {
        Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setPreserveRatio(true);
        homeButton.setGraphic(imageView);
    }
}
