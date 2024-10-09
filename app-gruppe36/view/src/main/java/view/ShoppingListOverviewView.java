package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShoppingListOverviewView {

    @FXML
    private Button ActiveShoppingList;

    @FXML
    private Button BackFromShoppingListOverview;

    @FXML
    private Button ShoppingListHistory;

    @FXML
    private Button HomeButton;

    @FXML
    void ButtonHome(ActionEvent event) throws IOException {
        SceneSwitcher.switchToScene(event, "ChoiceScreen.fxml");
    }

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

    public void initialize (){
        Image image = new Image(getClass().getResource("/view/img/house.png").toExternalForm());
        
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(66);  
        imageView.setFitHeight(63); 
        imageView.setPreserveRatio(true);

        HomeButton.setGraphic(imageView);
    }

}
