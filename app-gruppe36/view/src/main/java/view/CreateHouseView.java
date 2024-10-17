package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
import viewmodel.MenueViewModel;

public class CreateHouseView {

  MenueViewModel menueViewModel;

  @FXML
  private Button backButton;

  @FXML
  private Button generateHouseIdButton;

  @FXML
  private Label generatedHouseIdLabel;

  @FXML
  private Button loginButton;

  // @FXML
  // private Button HomeButton;

  // @FXML
  // void handleHome(ActionEvent event) throws IOException {
  // SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  // }

  @FXML
  void handleBack(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "MainMenu.fxml");
  }

  @FXML
  void handleGenerateNewHouseId(ActionEvent event) throws IOException {
    generatedHouseIdLabel.setText(menueViewModel.getProposedHouseId());
  }

  @FXML
  void handleGenerateNewHouse(ActionEvent event) throws IOException {
    menueViewModel.generateHouse();
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  void handleLoginFromCreateHouseId(ActionEvent event) throws IOException {
    menueViewModel.generateHouse();
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  public void initialize() {
    menueViewModel = MenueViewModel.getInstance();
    generatedHouseIdLabel.setText(menueViewModel.getProposedHouseId());
  }

}
