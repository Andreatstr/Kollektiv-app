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
    private Button BackFromLogInHouseID;

    @FXML
    private Button loginHouseIDButton;

    @FXML
    private Button HomeButton;

    @FXML
    private TextField loginHouseIDField;    // må legge til sjekk at ID finnes
    
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
            //Vise at id ikke finnes
        }
    }
    public void initialize() {
    viewModel = MenueViewModel.getInstance();
    }

}
