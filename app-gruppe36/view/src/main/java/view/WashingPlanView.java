package view;

// import javafx.application.Platform;
// import javafx.beans.property.SimpleStringProperty;
// import data.Person;
// import data.Task;
// import data.WashingPlan;
// import data.WashingTable;
import data.WashingPlanEntry;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewmodel.WashingPlanViewModel;

public class WashingPlanView {

  private WashingPlanViewModel washingPlanViewModel;

  ObservableList<WashingPlanEntry> observableList = FXCollections.observableArrayList();

  public WashingPlanView() {
    washingPlanViewModel = WashingPlanViewModel.getInstance();
  }

  @FXML
  private Button backFromWashingPlan;

  @FXML
  private Button editWashingPlan;

  @FXML
  private TableView<WashingPlanEntry> newWashingPlanTable;

  @FXML
  private Button leftArrowButton;

  @FXML
  private TableColumn<WashingPlanEntry, String> listOfNamesForWashingPlan;

  @FXML
  private TableColumn<WashingPlanEntry, String> listOfTasksForWashingPlan;

  @FXML
  private Button rightArrowButton;

  @FXML
  private Button homeButton;

  @FXML
  private Label weekNumberField;

  @FXML
  void buttonHome(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
  }

  @FXML
  void buttonBackFromWashingPlan(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "WashingPlanOverview.fxml");
  }

  @FXML
  void buttonEditWashingPlan(ActionEvent event) throws IOException {
    SceneSwitcher.switchToScene(event, "NewWashingPlan.fxml");
  }

  @FXML
  void buttonLeftArrow(ActionEvent event) {
    int currentWeek = washingPlanViewModel.getCurrentWeek();

    if (washingPlanViewModel.isThisTheFirstWeek(currentWeek)) {
      System.out.println("Already at the first week.");
      return;
    }

    washingPlanViewModel.previousWeek();
    weekNumberField.setText(String.valueOf(washingPlanViewModel.getCurrentWeek()));
    updateWashingPlanTable();
  }

  @FXML
  void buttonRightArrow(ActionEvent event) {
    int currentWeek = washingPlanViewModel.getCurrentWeek();

    if (washingPlanViewModel.isThisTheLastWeek(currentWeek)) {
      System.out.println("Already at the last week.");
      return;
    }

    washingPlanViewModel.nextWeek();
    weekNumberField.setText(String.valueOf(washingPlanViewModel.getCurrentWeek()));
    updateWashingPlanTable();
  }

  public void initialize() {
    Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(30);
    imageView.setFitHeight(30);
    imageView.setPreserveRatio(true);
    homeButton.setGraphic(imageView);
    
    listOfNamesForWashingPlan.setCellValueFactory(new PropertyValueFactory<>("person"));
    listOfTasksForWashingPlan.setCellValueFactory(new PropertyValueFactory<>("task"));
    String startNumber = String.valueOf(washingPlanViewModel.getCurrentWeek());
    weekNumberField.setText(startNumber);
    newWashingPlanTable.setItems(observableList);
    updateWashingPlanTable();
  }

  private void updateWashingPlanTable() {
    WashingPlanViewModel wpvm = washingPlanViewModel;
    List<WashingPlanEntry> entriesForCurrentWeek = wpvm.getWashingPlanEntriesForCurrentWeek();

    for (WashingPlanEntry entry : entriesForCurrentWeek) {
      System.out.println(entry.getPerson() + "  " + entry.getTask());
    }

    if (entriesForCurrentWeek != null && !entriesForCurrentWeek.isEmpty()) {
      observableList.clear();
      observableList.addAll(entriesForCurrentWeek);
    }
  }
}
