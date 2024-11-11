package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewmodel.MenueViewModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class SceneSwitcherTest extends ApplicationTest {

      @BeforeAll
      static public void Initialize()
    {
        MenueViewModel.getInstance().setTestApi();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingList.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Help method to simulate click and wait
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
    int waitTime = 2;

        clickAndWait("#homeButton", waitTime);

        // Washinglist button test
        clickAndWait("#openWashingPlanOverviewButton", waitTime);
        clickAndWait("#createNewWashingPlanButton", waitTime);
        clickAndWait("#backFromNewWashingPlan", waitTime);
        clickAndWait("#openWashingPlanButton", waitTime);
        clickAndWait("#leftArrowButton", waitTime);
        clickAndWait("#rightArrowButton", waitTime);
        clickAndWait("#editWashingPlan", waitTime);
        clickAndWait("#backFromNewWashingPlan", waitTime);
        clickAndWait("#backFromWashingPlan", waitTime);

        // Shopping list button test
        clickAndWait("#openShoppingListOverviewButton", waitTime);
        clickAndWait("#shoppingListHistory", waitTime);
        clickAndWait("#backFromShoppingListHistory", waitTime);
        clickAndWait("#activeShoppingList", waitTime);
        clickAndWait("#backFromShoppingList", waitTime);
        clickAndWait("#backFromShoppingListOverview", waitTime);

        // Waste test
        clickAndWait("#openTrash", waitTime);
        clickAndWait("#backFromTrash", waitTime);

        // Choice screen button test
        clickAndWait("#backFromChoiceScreen", waitTime);
        clickAndWait("#openCreateButton", waitTime);
        clickAndWait("#backButton", waitTime);
        clickAndWait("#openLoginButton", waitTime);
    }
}