module net.kender.lt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires org.fusesource.jansi;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.controlsEmpty;
    requires minecraft;
    requires transitive javafx.graphics;

    opens net.kender.lt to javafx.fxml;
    exports net.kender.lt;
    exports net.kender.lt.controlers;
    opens net.kender.lt.controlers to javafx.fxml;
    exports net.kender.lt.controlers.config;
    opens net.kender.lt.controlers.config to javafx.fxml;
    exports net.kender.lt.controlers.config.tabs;
    opens net.kender.lt.controlers.config.tabs to javafx.fxml;

}