package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.HistoryShoppingListTable;
import java.io.IOException;

public class ShoppingListHistoryView {

    @FXML
    private Button BackFromShoppingListHistory;

    @FXML
    private TableView<HistoryShoppingListTable> HistoryShoppingListTable;

    @FXML
    private TableColumn<HistoryShoppingListTable, Integer> countColumnHistory;

    @FXML
    private TableColumn<HistoryShoppingListTable, String> itemColumnHistory;

    @FXML
    private TableColumn<HistoryShoppingListTable, Integer> whenColumnHistory;

    @FXML
    void ButtonBackFromShoppingListHistory(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ShoppingListOverview.fxml");
    }

}
