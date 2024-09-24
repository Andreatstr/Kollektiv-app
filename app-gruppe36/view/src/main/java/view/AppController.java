package view;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import model.JsonFileManager;
import model.Item;

import java.util.List;
import java.util.ArrayList;
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

    private JsonFileManager fileManager;

    public AppController() {
        fileManager = new JsonFileManager();
    }

    @FXML
    private TextField itemCountInput;

    @FXML
    private TableColumn<Item, Integer> countColumn;

    @FXML
    private Button AddButton;

    @FXML
    private CheckBox checkAll;

    @FXML
    private TableColumn<Item, String> itemColumn;

    @FXML
    private TextField ItemNameInput;
    
    @FXML
    private TableView<Item> table;

    @FXML
    private void addItem(ActionEvent event) {
        // legge til Item og antall
        String itemName = ItemNameInput.getText();
        String antallText = itemCountInput.getText();

        if (!itemName.isEmpty() && !antallText.isEmpty()) {
            try {
                int antall = Integer.parseInt(antallText);
    
                Item newItem = new Item(itemName, antall);
                
                list.add(newItem);
                storeData();
                System.out.println("Added new item: " + newItem.getItemName() + ", Antall: " + newItem.getItemCount());
                System.out.println(list);

                ItemNameInput.clear();
                itemCountInput.clear();
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number entered in itemCountInput: " + antallText);
            }
        } else {
            System.out.println("itemName or Antall input is empty");
        }
    }

    private ObservableList<Item> list = FXCollections.observableArrayList();

    public void initialize() {
        
        loadData();

        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName")); // Adjusted for JavaFX property
        countColumn.setCellValueFactory(new PropertyValueFactory<>("itemCount")); // Adjusted for JavaFX property

        table.setItems(list);

        list.addListener((ListChangeListener<Item>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    // System.out.println("Items added: " + c.getAddedSubList());
                }
            }
        });
        itemCountInput.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) { // Allow only digits
                return change; // Accept change
            }
            return null; // Reject change
        }));
        
    }

    private void loadData()
    {
        List<Item> storedData = fileManager.getSavedList();
        if (storedData == null || storedData.size() == 0) return;
        for (Item Item : storedData)
        {
            list.add(Item);
        }
    }

    private void storeData()
    {
        fileManager.storeObject(list);
    }
}
