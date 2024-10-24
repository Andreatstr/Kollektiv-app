package view;

// import javafx.collections.ListChangeListener;
// import javafx.collections.FXCollections;
import data.Item;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewmodel.ShoppingListViewModel;

public class ShoppingListView {

  private ObservableList<Item> list;
  private ShoppingListViewModel shoppingListViewModel;

  public ShoppingListView() {
    shoppingListViewModel = ShoppingListViewModel.getInstance();
  }

  @FXML
  private TextField itemCountInput;

  @FXML
  private TableColumn<Item, Integer> countColumn;

  @FXML
  private Button addButton;

  @FXML
  private Button buyButton;

  @FXML
  private Button deleteButton;

  @FXML
  private CheckBox checkAll;

  @FXML
  private TableColumn<Item, String> itemColumn;

  @FXML
  private TableColumn<Item, Boolean> checkButtonsColumn;

  @FXML
  private TextField itemNameInput;

  @FXML
  private TableView<Item> table;

  @FXML
  private void buyItem(ActionEvent event) {
    shoppingListViewModel.buyItems();
  }

  @FXML
  private Button backFromShoppingList;

  @FXML
  private Button homeButton;

  @FXML
  void buttonHome(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  void buttonBackFromShoppingList(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ShoppingListOverview.fxml");
  }

  @FXML
  private void deleteItem(ActionEvent event) {
    shoppingListViewModel.removeItems();
  }

  @FXML
  private void addItem(ActionEvent event) {
    shoppingListViewModel.addItem(itemNameInput.getText(), itemCountInput.getText());
    itemNameInput.clear();
    itemCountInput.clear();
  }

  @FXML
  private void checkBoxChanged(ActionEvent event) {
    shoppingListViewModel.selectAllCheckBoxChanged(checkAll.isSelected());
  }

  public void initialize() {
    list = shoppingListViewModel.getShoppingList();
    itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
    countColumn.setCellValueFactory(new PropertyValueFactory<>("itemCount"));

    checkButtonsColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkButtonsColumn));
    checkButtonsColumn.setCellValueFactory(cd -> cd.getValue().activeProperty());

    table.setEditable(true);
    table.setItems(list);

    itemCountInput.setTextFormatter(new TextFormatter<>(change -> {
      String newText = change.getControlNewText();
      if (newText.matches("\\d*")) { // Allow only digits
        return change; // Accept change
      }
      return null; // Reject change
    }));

    Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());

    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(30);
    imageView.setFitHeight(30);
    imageView.setPreserveRatio(true);

    homeButton.setGraphic(imageView);
  }
}
