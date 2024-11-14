package view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewmodel.MenueViewModel;

public class WashingPlanTest extends ApplicationTest {

    @BeforeAll
    static public void Initialize()
    {
        MenueViewModel.getInstance().setTestApi();
    }

    @Override
    public void start(Stage stage) throws Exception {
    Parent root =
    FXMLLoader.load(getClass().getResource("WashingPlanOverview.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    }
}
