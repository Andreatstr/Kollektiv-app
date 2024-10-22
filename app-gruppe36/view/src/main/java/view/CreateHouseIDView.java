package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import viewmodel.MenueViewModel;

public class CreateHouseIDView {

    MenueViewModel menueViewModel;

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
        generatedHouseIDText.setText(menueViewModel.getProposedHouseId());
    }

    @FXML
    void ButtonGenerateNewHouse(ActionEvent event) throws IOException {
        menueViewModel.generateHouse();
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonLoginFromCreateHouseID(ActionEvent event) throws IOException {
        menueViewModel.generateHouse();
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    public void initialize() {
        menueViewModel = MenueViewModel.getInstance();
        generatedHouseIDText.setText(menueViewModel.getProposedHouseId());
    }

}
