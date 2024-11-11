// /*package view;


// import org.junit.jupiter.api.BeforeAll;
// // import static org.junit.jupiter.api.Assertions.assertEquals;
// // import javafx.collections.ObservableList;
// import org.junit.jupiter.api.Test;
// import static org.testfx.api.FxAssert.verifyThat;
// import org.testfx.framework.junit5.ApplicationTest;
// import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
// import javafx.collections.ObservableList;
// import javafx.scene.control.TableView;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import viewmodel.MenueViewModel;

// public class ShoppingListTest extends ApplicationTest {


//     @BeforeAll
//     static public void Initialize()
//     {
//         MenueViewModel.getInstance().setTestApi();
//     }

//     private TableView<?> table;
//     private int antallTing;

//     @Override
//     public void start(Stage stage) throws Exception {
//         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingList.fxml"));
//         Parent root = fxmlLoader.load();
//         stage.setScene(new Scene(root));
//         stage.show();
//         // Check how many elements are in the list
//         table = lookup("#table").queryTableView();
//     }

//     /*@Test
//     public void testItemNavnFeltInput() {
//         ObservableList<?> items = table.getItems();
//         antallTing = items.size();
//         // Simulate writing "Epler" in ItemNavnFelt
//         clickOn("#itemNameInput").write("Vaskemiddel");
//         clickOn("#itemCountInput").write("1");
//         clickOn("#addButton");

//         //Nødvendig?
//         try {
//             Thread.sleep(50);
//         }

//         catch (InterruptedException e) {
//             System.out.println("Kunne ikke pause!");
//         }

//         // Verify that input fields are empty
//         verifyThat("#itemNameInput", hasText(""));
//         verifyThat("#itemCountInput", hasText(""));

//         // Check that new element is added to list
//         // assertEquals((antallTing + 1), items.size(), "Elementet ble ikke lagt til i
//         // listen.");

//     }
// }*/
