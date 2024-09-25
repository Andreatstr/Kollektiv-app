module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires model;

    opens view to javafx.fxml; // Open the 'ui' package to JavaFX for FXML
    //opens ui to javafx.base; // Open the 'ui' package to javafx.base to allow property access
    exports view; // Export 'ui' package to allow access from javafx.graphics

}
