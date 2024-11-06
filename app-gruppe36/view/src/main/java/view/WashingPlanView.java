package view;

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
import java.io.IOException;
import data.WashingPlanEntry;



public class WashingPlanView {

    private WashingPlanViewModel washingPlanViewModel;

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
        washingPlanViewModel.editWashingPlan();
    SceneSwitcher.switchToScene(event, "NewWashingPlan.fxml");
  }

    @FXML
    void buttonLeftArrow(ActionEvent event) {
        washingPlanViewModel.previousWeek(); 
        weekNumberField.setText(String.valueOf(washingPlanViewModel.getCurrentWeek()));
    }

    @FXML
    void buttonRightArrow(ActionEvent event) {
        washingPlanViewModel.nextWeek(); 
        weekNumberField.setText(String.valueOf(washingPlanViewModel.getCurrentWeek()));
    }

    public void initialize () {
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
        newWashingPlanTable.setItems(washingPlanViewModel.getWashingPlanEntries());
    }
}
