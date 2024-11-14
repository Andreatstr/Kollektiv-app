package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The `App` class extends `Application` in Java and overrides the `start` method to load a FXML 
 * file for the main menu.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Application APP starting...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}