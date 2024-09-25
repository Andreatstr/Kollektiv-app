package view;

import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingList.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testItemNavnFeltInput() {
        // Simulerer å skrive "Epler" i ItemNavnFelt
        clickOn("#itemNameInput").write("Vaskemiddel");
        clickOn("#itemCountInput").write("2");
        //clickOn("#LeggTilKnapp").clickOn("#LeggTilKnapp");
        try {
            Thread.sleep(1000);
        } 
        
        catch (InterruptedException e) {
            System.out.println("Kunne ikke pause!");
        }
        
        // Verifiserer at ItemNavnFelt inneholder "Epler"
        verifyThat("#itemNameInput", hasText("Vaskemiddel"));
        verifyThat("#itemCountInput", hasText("2"));
    }
}
