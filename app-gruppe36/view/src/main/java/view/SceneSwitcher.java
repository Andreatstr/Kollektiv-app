package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The `SceneSwitcher` class in Java provides a method to switch scenes in a JavaFX application by
 * loading a new FXML file and setting it as the root of the stage.
 */
public class SceneSwitcher {

    /**
     * The function `switchToScene` loads a new FXML file and switches the current scene in a JavaFX
     * application.

     * @param event
     * The `event` parameter in the `switchToScene` method is of type `ActionEvent`. It is
     * typically used to represent an event that occurred, such as a button click or menu selection,
     * triggering the scene switch operation.

     * @param fxmlFile
     *  The `fxmlFile` parameter in the `switchToScene` method is a `String` that
     * represents the file path or name of the FXML file that contains the layout information
     * for the scene you want to switch to. This FXML file typically describes the structure
     * of the user interface using XML
     */
    public static void switchToScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlFile));
        Parent root = loader.load();

        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
}
