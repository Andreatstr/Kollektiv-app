package view;

import data.HistoryShoppingListTable;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewmodel.ShoppingListViewModel;

public class ShoppingListHistoryView {

  private ShoppingListViewModel shoppingListViewModel;

  private ObservableList<HistoryShoppingListTable> historyShoppingList;

  @FXML
  private Button backFromShoppingListHistory;

  @FXML
  private Button homeButton;

  @FXML
  private TableView<HistoryShoppingListTable> shoppingListTable;

  @FXML
  private TableColumn<HistoryShoppingListTable, Integer> countColumnHistory;

  @FXML
  private TableColumn<HistoryShoppingListTable, String> itemColumnHistory;

  @FXML
  private TableColumn<HistoryShoppingListTable, String> whenColumnHistory;

  @FXML
  void buttonHome(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  void buttonBackFromShoppingListHistory(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ShoppingListOverview.fxml");
  }

  public void initialize() {
    Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());

    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(30);
    imageView.setFitHeight(30);
    imageView.setPreserveRatio(true);

    homeButton.setGraphic(imageView);

    shoppingListViewModel = ShoppingListViewModel.getInstance();
    historyShoppingList = shoppingListViewModel.getShoppingListHistory();

    countColumnHistory.setCellValueFactory(new PropertyValueFactory<>("count"));
    itemColumnHistory.setCellValueFactory(new PropertyValueFactory<>("item"));
    whenColumnHistory.setCellValueFactory(new PropertyValueFactory<>("when"));

    shoppingListTable.setItems(historyShoppingList);
  }
}
