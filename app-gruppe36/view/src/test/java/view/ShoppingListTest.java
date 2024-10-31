package view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShoppingListTest extends ApplicationTest {

    private TableView<?> table;
    private int antallTing;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingList.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
        // Sjekke hvor mange elementer som er i listen
        table = lookup("#table").queryTableView();
    }

    @Test
    public void testItemNavnFeltInput() {
        ObservableList<?> items = table.getItems();
        antallTing = items.size();
        // Simulerer å skrive "Epler" i ItemNavnFelt
        clickOn("#itemNameInput").write("Vaskemiddel");
        clickOn("#itemCountInput").write("1");
        clickOn("#AddButton");

        //Nødvendig?
        try {
            Thread.sleep(50);
        }

        catch (InterruptedException e) {
            System.out.println("Kunne ikke pause!");
        }

        // Verifiserer at input feltene tømte seg
        verifyThat("#itemNameInput", hasText(""));
        verifyThat("#itemCountInput", hasText(""));

        // Sjekker at et nytt element er lagt til i listen:
        // assertEquals((antallTing + 1), items.size(), "Elementet ble ikke lagt til i
        // listen.");

    }
}
