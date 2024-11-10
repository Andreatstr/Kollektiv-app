package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The MainMenuView class contains methods for switching to the login and create house scenes in a
 * JavaFX application.
 */
public class MainMenuView {

    @FXML
    private Button openLoginButton;

    @FXML
    private Button openCreateButton;

    @FXML
    void buttonToLogin(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "LoginHouseID.fxml");
    }

    @FXML
    void buttonToCreate(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "CreateHouseID.fxml");
    }
}
