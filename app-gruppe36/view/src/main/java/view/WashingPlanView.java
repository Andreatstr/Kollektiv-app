package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GeneratedWashingTable;
import java.io.IOException;

public class WashingPlanView {

    // legge til den lagrede tabell informasjonen

    @FXML
    private Button BackFromWashingPlan;

    @FXML
    private Button editWashingPlan;

    @FXML
    private TableView<WashingPlanView> generatedWashingPlanTable;

    @FXML
    private Button leftArrowButton;

    @FXML
    private TableColumn<WashingPlanView, String> listOfNamesForWashingPlan;

    @FXML
    private TableColumn<WashingPlanView, String> listOfTasksForWashingPlan;

    @FXML
    private Button rightArrowButton;

    @FXML
    private Button HomeButton;

    @FXML
    private Label weekNumberField;

    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonBackFromWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "WashingPlanOverview.fxml");
    }

    @FXML
    void ButtonEditWashingPlan(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "NewWashingPlan.fxml");
    }

    @FXML
    void ButtonLeftArrow(ActionEvent event) {
        //TODO: switch to previous week
    }

    @FXML
    void ButtonRightArrow(ActionEvent event) {
        //TODO: switch to next week
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
