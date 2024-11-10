module model {
    requires org.jsoup;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.webflux;
    requires javafx.base;
    exports viewmodel;
    exports data;
    exports data.requests;
}
