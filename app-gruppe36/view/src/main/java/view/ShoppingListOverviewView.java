package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShoppingListOverviewView {

  @FXML
  private Button activeShoppingList;

  @FXML
  private Button backFromShoppingListOverview;

  @FXML
  private Button shoppingListHistory;

  @FXML
  private Button homeButton;

  @FXML
  void buttonHome(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  void buttonActiveShoppingList(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ShoppingList.fxml");
  }

  @FXML
  void buttonBackFromShoppingListOverview(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  void buttonShoppingListHistory(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ShoppingListHistory.fxml");
  }

  public void initialize() {
    Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());

    ImageView imageView = new ImageView(image);
    // imageView.setFitWidth(66);
    // imageView.setFitHeight(63);
    imageView.setPreserveRatio(true);

    homeButton.setGraphic(imageView);
  }

}
