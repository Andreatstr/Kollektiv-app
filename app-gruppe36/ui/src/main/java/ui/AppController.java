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
import javafx.scene.control.TextFormatter;
import javafx.collections.ListChangeListener;


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
        String vareNavn = VareNavnFelt.getText();
        String antallText = AntallInput.getText();

        Vare newTestVare = new Vare("Test Vare", 5);
        list.add(newTestVare);
        System.out.println("Added test item: " + newTestVare.getVareNavn());

        if (!vareNavn.isEmpty() && !antallText.isEmpty()) {
            try {
                int antall = Integer.parseInt(antallText);
    
                Vare newVare = new Vare(vareNavn, antall);
                list.add(newVare);
                System.out.println("Added new item: " + newVare.getVareNavn() + ", Antall: " + newVare.getAntallAvVare());
                System.out.println(list);

                VareNavnFelt.clear();
                AntallInput.clear();

                //table.refresh();
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number entered in AntallInput: " + antallText);
            }
        } else {
            // Handle case where one or both fields are empty
            System.out.println("Varenavn or Antall input is empty");
        }
    }

    ObservableList<Vare> list = FXCollections.observableArrayList(

    // new Vare("Poteter", 2),
    // new Vare("Monster", 3),
    // new Vare("Pepsi max", 1)

    );

    public void initialize() {
        VareKolonne.setCellValueFactory(new PropertyValueFactory<>("vareNavn")); // Adjusted for JavaFX property
        AntallKolonne.setCellValueFactory(new PropertyValueFactory<>("antallAvVare")); // Adjusted for JavaFX property

        table.setItems(list);
        System.out.println("Initialized table with items: " + list);

        list.addListener((ListChangeListener<Vare>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("Items added: " + c.getAddedSubList());
                }
            }
        });
        AntallInput.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) { // Allow only digits
                return change; // Accept change
            }
            return null; // Reject change
        }));
    }
}
