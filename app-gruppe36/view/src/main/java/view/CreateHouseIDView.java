package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CreateHouseIDView {

    @FXML
    private Button BackFromCreateHouseID;

    @FXML
    private Button generateNewHouseIDButton;

    @FXML
    private Label generatedHouseIDText; //TODO: må lage model som genererer og lagrer HouseID

    @FXML
    private Button loginFromCreateHouseIDButton;

    @FXML
    private Button HomeButton;

    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonBackFromCreateHouseID(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "MainMenu.fxml");
    }

    @FXML
    void ButtonGenerateNewHouseID(ActionEvent event) throws IOException {
        //TODO: generere unik kollektiv-ID
    }

    @FXML
    void ButtonLoginFromCreateHouseID(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

}
