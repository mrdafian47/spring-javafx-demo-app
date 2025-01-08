package com.github.desktop.demo.controller;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import net.rgielen.fxweaver.core.FxmlView;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Log4j2
@Component
@FxmlView
public class PerbarindoWebView {

    @FXML
    public Pane perbarindoContainer;

    @FXML
    public WebView perbarindoWebView;

    private Stage stage;
    private WebEngine webEngine;

    @FXML
    public void initialize() {

        this.stage = new Stage();
        stage.setScene(new Scene(perbarindoContainer));

        webEngine = perbarindoWebView.getEngine();
        webEngine.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                // Expose the Java object to JavaScript
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("app", new ConsoleLogger());

                // Override the console.log function
                webEngine.executeScript(
                        "console.log = (function (originalLog) {" +
                                "    return function (message) {" +
                                "        originalLog(message);" +
                                "        app.log(message);" + // Call the Java method
                                "    };" +
                                "})(console.log);"
                );
            }
        });

        webEngine.getLoadWorker().exceptionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                log.debug("Listen Load Worker Exception Property");
                log.error(newValue);
            }
        });

        webEngine.setOnAlert(event -> {
            log.debug("Listen Alert from WebView");
            log.warn(event.getData());
        });

        webEngine.setOnError(event -> {
            log.debug("Listen Error from WebView");
            log.debug(event.getMessage());
            log.error(event.getException());
        });

        webEngine.documentProperty().addListener((obs, oldDoc, newDoc) -> {
            if (newDoc != null) {
                inspectDocument(newDoc);
            }
        });
    }

    @FXML
    public void onReloadWeb(ActionEvent actionEvent) {
        log.debug("On Reload Web");
        webEngine.reload();
    }

    public void show() {
//        webEngine.load("https://dev-perbarindo-web.mypoc.id");
//        webEngine.load("http://172.17.84.17:3000");
//        webEngine.load("https://github.com/");
        webEngine.load("https://demo.asliri.id/liveness");
        stage.show();
    }

    private void inspectDocument(Document document) {
        String title = document.getElementsByTagName("title").item(0).getTextContent();
        stage.setTitle(title);
    }

    private static class ConsoleLogger {
        public void log(String message) {
            System.out.println("JavaScript Console: " + message);
        }
    }
}
