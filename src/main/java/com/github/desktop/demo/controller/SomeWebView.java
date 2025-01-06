package com.github.desktop.demo.controller;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView
public class SomeWebView {

    @FXML
    public WebView contentWeb;

    @FXML
    public void initialize() {

        WebEngine webEngine = contentWeb.getEngine();

        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.RUNNING) {
                System.out.println("Start running other task");
            }
            System.out.println("Old State: " + oldState.toString());
            System.out.println("New State: " + newState.toString());
        });

        webEngine.load("https://medium.com/");
        webEngine.setUserAgent("Demo Web Browser 1.0");
    }
}
