module model {
    requires org.jsoup;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires javafx.base;
    exports viewmodel;
    exports model;
    exports data;
    exports restapi;
    exports data.requests;
}
