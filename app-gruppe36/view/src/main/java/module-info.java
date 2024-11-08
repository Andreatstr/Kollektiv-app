module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires model;
    requires org.jsoup;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires java.net.http;
    requires javafx.graphics;

    opens view to javafx.fxml; // Open 'view' for JavaFX for FXML

    exports view; // Export 'view'-package
}