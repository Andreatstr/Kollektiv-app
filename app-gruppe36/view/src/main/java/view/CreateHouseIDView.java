package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
import viewmodel.MenueViewModel;

public class CreateHouseIDView {

    MenueViewModel menueViewModel;

    @FXML
    private Button backFromCreateHouseID;

    @FXML
    private Button generateNewHouseIDButton;

    @FXML
    private Label generatedHouseIDText;

    @FXML
    private Button loginFromCreateHouseIDButton;

    // @FXML
    // private Button HomeButton;

    // @FXML
    // void handleHome(ActionEvent event) throws IOException {
    //     SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    // }

    @FXML
    void handleBackFromCreateHouseID(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "MainMenu.fxml");
    }

    @FXML
    void handleGenerateNewHouseID(ActionEvent event) throws IOException {
        generatedHouseIDText.setText(menueViewModel.getProposedHouseId());
    }

    @FXML
    void handleGenerateNewHouse(ActionEvent event) throws IOException {
        menueViewModel.generateHouse();
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void handleLoginFromCreateHouseID(ActionEvent event) throws IOException {
        menueViewModel.generateHouse();
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    public void initialize() {
        menueViewModel = MenueViewModel.getInstance();
        generatedHouseIDText.setText(menueViewModel.getProposedHouseId());
    }

}
