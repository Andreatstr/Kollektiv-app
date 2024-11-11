module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires model;
    requires org.jsoup;
    requires htmlunit;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires java.net.http;
    requires javafx.graphics;

    opens view to javafx.fxml; // Åpne 'view' for JavaFX for FXML

    exports view; // Eksportere 'view'-pakken
}