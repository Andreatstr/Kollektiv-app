package ui;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.Label;
// import javafx.scene.control.Labeled;
// import javafx.scene.control.ListView;
// import javafx.scene.control.TextField;
// import javafx.scene.control.Button;
// import javafx.scene.control.CheckBox;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.cell.PropertyValueFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class AppController {

    public AppController() {

    }

    // @FXML
    // void initialize() {

    // }

    // @FXML
    // private TextField nameField;

    // @FXML
    // private Label example;

    // @FXML
    // private void handleButtonAction() {
    //     String text = nameField.getText();
    //     example.setText(text);
        
    // }

    @FXML
    private TextField AntallInput;

    @FXML
    private TableColumn<Vare, Integer> AntallKolonne;

    @FXML
    private Button LeggTilKnapp;

    @FXML
    private CheckBox MarkerAlle;

    //@FXML
    //private TableColumn<?, ?> SjekkKnapper;

    @FXML
    private TableColumn<Vare, String> VareKolonne;

    @FXML
    private TextField VareNavnFelt;
    
    @FXML
    private TableView<Vare> table;

    @FXML
    private void LeggTilVareOgAntall(ActionEvent event) {
        // legge til vare og antall
    }

    ObservableList<Vare> list = FXCollections.observableArrayList(

    new Vare("Poteter", 2),
    new Vare("Monster", 3),
    new Vare("Pepsi max", 1)

    );

    public void initialize() {
        VareKolonne.setCellValueFactory(new PropertyValueFactory<Vare, String>("VareNavn"));
        AntallKolonne.setCellValueFactory(new PropertyValueFactory<Vare, Integer>("AntallAvVare"));

        table.setItems(list);
    }
}
