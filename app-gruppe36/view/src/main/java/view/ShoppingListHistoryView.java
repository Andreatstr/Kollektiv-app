package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.HistoryShoppingListTable;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShoppingListHistoryView {

    @FXML
    private Button BackFromShoppingListHistory;
    
    @FXML
    private Button HomeButton;

    @FXML
    private TableView<HistoryShoppingListTable> HistoryShoppingListTable;

    @FXML
    private TableColumn<HistoryShoppingListTable, Integer> countColumnHistory;

    @FXML
    private TableColumn<HistoryShoppingListTable, String> itemColumnHistory;

    @FXML
    private TableColumn<HistoryShoppingListTable, Integer> whenColumnHistory;

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
    }

}
