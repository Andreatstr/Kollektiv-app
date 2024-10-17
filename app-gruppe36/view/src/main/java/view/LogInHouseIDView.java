package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
import viewmodel.MenueViewModel;

public class LogInHouseIDView {

  MenueViewModel viewModel;

  @FXML
  private Button backFromLogInHouseID;

  @FXML
  private Button loginHouseIDButton;

  // @FXML
  // private Button HomeButton;

  @FXML
  private TextField loginHouseIDField;

  // @FXML
  // void ButtonHome(ActionEvent event) throws IOException {
  // SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  // }

  @FXML
  void handleBackFromLogInHouseID(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "MainMenu.fxml");
  }

  @FXML
  void handleLoginHouseID(ActionEvent event) throws IOException {
    if (viewModel.setCollective(loginHouseIDField.getText())) {
      SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }
  }

  public void initialize() {
    viewModel = MenueViewModel.getInstance();
  }

}
