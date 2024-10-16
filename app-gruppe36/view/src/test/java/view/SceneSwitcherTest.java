package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;

public class SceneSwitcherTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingList.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Hjelpemetode for å simulere klikk og vente
    private void clickAndWait(String buttonId, int waitTime) {
        clickOn(buttonId);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            System.out.println("Kunne ikke pause!");
        }
    }

    @Test
    public void HomeButtonTest() {
        int waitTime = 1;

        clickAndWait("#HomeButton", waitTime);

        // Washinglist button test
        clickAndWait("#openWashingPlanOverviewButton", waitTime);
        clickAndWait("#createNewWashingPlanButton", waitTime);
        clickAndWait("#BackFromNewWashingPlan", waitTime);
        clickAndWait("#openWashingPlanButton", waitTime);
        clickAndWait("#leftArrowButton", waitTime);
        clickAndWait("#rightArrowButton", waitTime);
        clickAndWait("#editWashingPlan", waitTime);
        clickAndWait("#BackFromNewWashingPlan", waitTime);
        clickAndWait("#BackFromWashingPlan", waitTime);

        // Shopping list button test
        clickAndWait("#openShoppingListOverviewButton", waitTime);
        clickAndWait("#ShoppingListHistory", waitTime);
        clickAndWait("#BackFromShoppingListHistory", waitTime);
        clickAndWait("#ActiveShoppingList", waitTime);
        clickAndWait("#BackFromShoppingList", waitTime);
        clickAndWait("#BackFromShoppingListOverview", waitTime);

        // Choice screen button test
        clickAndWait("#backFromChoiceScreen", waitTime);
        clickAndWait("#openCreateHouseIDButton", waitTime);
        clickAndWait("#backFromCreateHouseID", waitTime);
        clickAndWait("#openLoginHouseIDButton", waitTime);

    }
}