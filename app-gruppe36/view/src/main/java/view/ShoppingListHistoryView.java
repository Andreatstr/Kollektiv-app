package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import data.HistoryShoppingListTable;
import viewmodel.ShoppingListViewModel;

import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShoppingListHistoryView {

    private ShoppingListViewModel shoppingListViewModel;

    private ObservableList<HistoryShoppingListTable> historyShoppingList;

    @FXML
    private Button BackFromShoppingListHistory;
    
    @FXML
    private Button HomeButton;

    @FXML
    private TableView<HistoryShoppingListTable> ShoppingListTable;

    @FXML
    private TableColumn<HistoryShoppingListTable, Integer> countColumnHistory;

    @FXML
    private TableColumn<HistoryShoppingListTable, String> itemColumnHistory;

    @FXML
    private TableColumn<HistoryShoppingListTable, String> whenColumnHistory;

    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonBackFromShoppingListHistory(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ShoppingListOverview.fxml");
    }
    public void initialize (){
        Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
        
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(66);  
        imageView.setFitHeight(63); 
        imageView.setPreserveRatio(true);

        HomeButton.setGraphic(imageView);

        shoppingListViewModel = ShoppingListViewModel.getInstance();
        historyShoppingList= shoppingListViewModel.getShoppingListHistory();

        countColumnHistory.setCellValueFactory(new PropertyValueFactory<>("count")); 
        itemColumnHistory.setCellValueFactory(new PropertyValueFactory<>("item")); 
        whenColumnHistory.setCellValueFactory(new PropertyValueFactory<>("when")); 

        ShoppingListTable.setItems(historyShoppingList);
    }

}
