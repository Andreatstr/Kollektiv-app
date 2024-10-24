package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    SceneSwitcher.switchToScene(event, "MainMenu.fxml");
  }

  @FXML
    void handleOpenTrash(ActionEvent event) throws IOException {
      SceneSwitcher.switchToScene(event, "Trash.fxml");
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
