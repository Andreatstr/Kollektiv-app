package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginHouseIDView {

    @FXML
    private Button BackFromLogInHouseID;

    @FXML
    private Button loginHouseIDButton;

    @FXML
    private TextField loginHouseIDField;    // må legge til sjekk at ID finnes

    @FXML
    void ButtonBackFromLogInHouseID(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "MainMenu.fxml");
    }

    @FXML
    void ButtonLoginHouseID(ActionEvent event) throws IOException  {
        //TODO: ID-input = this.ID
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

}
