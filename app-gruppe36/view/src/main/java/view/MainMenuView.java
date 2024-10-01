package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class MainMenuView {

    @FXML
    private Button openLoginHouseIDButton;

    @FXML
    private Button openCreateHouseIDButton;

    @FXML
    void ButtonToLoginHouseID(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "LoginHouseID.fxml");
    }

    @FXML
    void ButtonToCreateHouseID(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "CreateHouseID.fxml");
    }

}
