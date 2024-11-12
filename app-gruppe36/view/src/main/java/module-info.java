module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires model;
    requires java.net.http;
    // requires javafx.graphics;
    opens view to javafx.graphics, javafx.fxml; // Open 'view' for JavaFX for FXML
    exports view; // Export 'view'-package
}