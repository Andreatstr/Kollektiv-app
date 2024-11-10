package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import viewmodel.MenueViewModel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.testfx.framework.junit5.ApplicationTest;

public class WasteOverviewTest extends ApplicationTest {

  private TableView<?> table;

    @BeforeAll
    static public void Initialize()
    {
        MenueViewModel.getInstance().setTestApi();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WasteOverview.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
        table = lookup("#table").queryTableView();
    }

    @Test
    public void testTableIsNotEmptyAfterScraping() {
        ObservableList<?> items = table.getItems();
        assertFalse(items.isEmpty(), "The table should not be empty after data scraping");
    }
}
