package com.github.desktop.demo.controller;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.extern.log4j.Log4j2;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Log4j2
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
                log.debug("Start running other task");
            }

            log.debug("Old State: {}", oldState.toString());
            log.debug("New State: {}", newState.toString());
        });

        webEngine.load("https://medium.com/");
        webEngine.setUserAgent("Demo Web Browser 1.0");
    }
}
