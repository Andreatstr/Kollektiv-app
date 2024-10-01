package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class ShoppingListOverviewView {

    @FXML
    private Button ActiveShoppingList;

    @FXML
    private Button BackFromShoppingListOverview;

    @FXML
    private Button ShoppingListHistory;

    @FXML
    void ButtonActiveShoppingList(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ShoppingList.fxml");
    }

    @FXML
    void ButtonBackFromShoppingListOverview(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

    @FXML
    void ButtonShoppingListHistory(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ShoppingListHistory.fxml");
    }

}
