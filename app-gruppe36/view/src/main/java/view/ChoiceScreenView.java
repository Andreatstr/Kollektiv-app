package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import viewmodel.MenueViewModel;

/**
 * The `ChoiceScreenView` class contains methods for handling button actions to switch scenes in a
 * JavaFX application.
 */
public class ChoiceScreenView {

    @FXML
    private Button backFromChoiceScreen;

    @FXML
    private Button openTrash;

    @FXML
    private Button openShoppingListOverviewButton;

    @FXML
    private Button openWashingPlanOverviewButton;

    @FXML
    void handleBackFromChoiceScreen(ActionEvent event) throws IOException {
        MenueViewModel.getInstance().logOut();
        SceneSwitcher.switchToScene(event, "MainMenu.fxml");
    }

    @FXML
    void handleOpenTrash(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WasteOverview.fxml");
    }

    @FXML
    void handleOpenShoppingListOverview(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ShoppingListOverview.fxml");
    }

    @FXML
    void handleOpenWashingPlanOverview(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WashingPlanOverview.fxml");
    }
}
