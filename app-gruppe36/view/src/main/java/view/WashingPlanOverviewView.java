package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WashingPlanOverviewView {

  @FXML
  private Button backFromWashingPlan;

  @FXML
  private Button createNewWashingPlanButton;

  @FXML
  private Button openWashingPlanButton;

  @FXML
  private Button homeButton;

  @FXML
  void buttonHome(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  private void buttonBackFromWashingPlan(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  private void buttonCreateNewWashingPlan(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "NewWashingPlan.fxml");
  }

  @FXML
  private void buttonOpenWashingPlan(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "WashingPlan.fxml");
  }

  public void initialize() {
    Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());

    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(30);
    imageView.setFitHeight(30);
    imageView.setPreserveRatio(true);
    homeButton.setGraphic(imageView);
  }
}
