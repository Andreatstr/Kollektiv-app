package ui;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppController {

    public AppController() {

    }

    @FXML
    void initialize() {

    }

    @FXML
    private TextField nameField;

    @FXML
    private Label example;

    @FXML
    private void handleButtonAction() {
        String text = nameField.getText();
        example.setText(text);
        
    }
}
