package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import viewmodel.MenueViewModel;

/**
 * The `LoginView` class in Java handles user login functionality and scene switching based on input
 * validation.
 */
public class LoginView {

    MenueViewModel viewModel;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "MainMenu.fxml");
    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        if (viewModel.setCollective(loginField.getText())) {
            SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
        }
    }

    public void initialize() {
        viewModel = MenueViewModel.getInstance();
    }
}
