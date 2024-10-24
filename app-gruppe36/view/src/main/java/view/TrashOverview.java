package view;

import data.Trash;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrashOverview {

  @FXML
  private ImageView houseImage;

  @FXML
  private Button backFromTrash;

  @FXML
  private Button homeButton;

  @FXML
  private TableView<Trash> table;

  @FXML
  private ImageView trashPhoto;

  @FXML
  private TableColumn<Trash, String> wasteColumn;

  @FXML
  private TableColumn<Trash, Integer> weekColumn;

  @FXML
  void buttonHome(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  void handleBackFromTrash(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  public void initialize() {
    //house photo
    Image imageHouse = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
    ImageView imageViewHouse = new ImageView(imageHouse);
    imageViewHouse.setFitWidth(30);
    imageViewHouse.setFitHeight(30);
    imageViewHouse.setPreserveRatio(true);
    homeButton.setGraphic(imageViewHouse);

    //trash photo
    Image imageTrash = new Image(getClass().getResource("/view/img/trash.png").toExternalForm());
    trashPhoto.setFitWidth(200);
    trashPhoto.setFitHeight(200);
    trashPhoto.setPreserveRatio(true);
    trashPhoto.setImage(imageTrash);
  }
}