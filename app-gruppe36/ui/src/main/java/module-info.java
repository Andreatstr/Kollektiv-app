module calc.ui {
    requires javafx.controls;
    requires javafx.fxml;

    opens ui to javafx.fxml; // Open the 'ui' package to JavaFX for FXML
    //opens ui to javafx.base; // Open the 'ui' package to javafx.base to allow property access
    exports ui; // Export 'ui' package to allow access from javafx.graphics

}
