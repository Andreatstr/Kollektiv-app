package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class ChoiceScreenView {

    @FXML
    private Button BackFromChoiceScreen;

    @FXML
    private Button openShoppingListOverviewButton;

    @FXML
    private Button openWashingPlanOverviewButton;

    @FXML
    void ButtonBackFromChoiceScreen(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "MainMenu.fxml");
    }

    @FXML
    void ButtonOpenShoppingListOverview(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ShoppingListOverview.fxml");
    }

    @FXML
    void ButtonOpenWashingPlanOverview(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WashingPlanOverview.fxml");
    }

}
