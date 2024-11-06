package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import viewmodel.MenueViewModel;

public class LogInHouseIDView {

    MenueViewModel viewModel;

    @FXML
    private Button backFromLogInHouseID;

    @FXML
    private Button loginHouseIDButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextField loginHouseIDField;    //must add check to see if ID already exist
    
    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonBackFromLogInHouseID(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "MainMenu.fxml");
    }

    @FXML
    void ButtonLoginHouseID(ActionEvent event) throws IOException  {
        if (viewModel.setCollective(loginHouseIDField.getText()))
        {
            SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
        }
        else
        {
            //Show that ID does not exist
        }
    }
    public void initialize() {
    viewModel = MenueViewModel.getInstance();
    }

}
