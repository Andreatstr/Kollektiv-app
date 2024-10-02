package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.NewWashingTableName;
import model.NewWashingTableTask;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NewWashingPlanView {

    @FXML
    private Button BackFromNewWashingPlan;

    @FXML
    private Button addPersonButton;

    @FXML
    private TextField addPersonField;

    @FXML
    private Button addTaskButton;

    @FXML
    private TextField addTaskField;

    @FXML
    private TextField fromWeek;

    @FXML
    private TextField toWeek;

    @FXML
    private Button generateWashingPlan;

    @FXML
    private TableColumn<NewWashingTableName, String> listOfNamesForNewWashingPlan;

    @FXML
    private TableColumn<NewWashingTableTask, String> listOfTasksForNewWashingPlan;

    @FXML
    private TableView<NewWashingTableName> newWashingPlanNameTable;

    @FXML
    private TableView<NewWashingTableTask> newWashingPlanTaskTable;

    @FXML
    private Button HomeButton;

    @FXML
    void ButtonAddPerson(ActionEvent event) {
    }

    @FXML
    void ButtonAddTask(ActionEvent event) {

    }

    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonBackFromNewWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WashingPlanOverview.fxml");
    }

    @FXML
    void ButtonGenerateWashingPlan(ActionEvent event) throws IOException {
        //TODO: generere vaskeplan-logikk
        SceneSwitcher.switchToScene(event, "WashingPlan.fxml");
    }

    public void initialize (){
        Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
        
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(66);  
        imageView.setFitHeight(63); 
        imageView.setPreserveRatio(true);

        HomeButton.setGraphic(imageView);
    }

}
