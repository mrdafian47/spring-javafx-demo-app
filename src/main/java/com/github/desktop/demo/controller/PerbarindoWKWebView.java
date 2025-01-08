package com.github.desktop.demo.controller;

import com.github.desktop.demo.library.CocoaLibrary;
import com.sun.jna.Pointer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@FxmlView
public class PerbarindoWKWebView {

    @FXML
    public Pane rootPane;

    private Pointer wkWebView;

//    private Stage stage;

    @FXML
    public void initialize() {

       try {
//           this.stage = new Stage();
//           stage.setScene(new Scene(rootPane));

           // Ensure the macOS environment is initialized
           boolean result = CocoaLibrary.INSTANCE.NSApplicationLoad();
           log.debug("Result CocoaLibrary {}", result);

           // Create the WKWebView and embed it into the rootPane
           double width = 400;
//           double width = rootPane.getWidth();
           double height = 400.0;
//           double height = rootPane.getHeight();

           log.debug("Width Pane {}", width);
           log.debug("Height Pane {}", height);

           wkWebView = CocoaLibrary.INSTANCE.createWKWebView(0, 0, width, height);

           log.debug("Create WKWebView");

           // Load a URL
           CocoaLibrary.INSTANCE.loadURL(wkWebView, "https://demo.asliri.id/liveness");
//           CocoaLibrary.INSTANCE.loadURL(wkWebView, "https://dev-perbarindo-web.mypoc.id");

           // Resize listener to adjust the WKWebView size
           rootPane.widthProperty().addListener((obs, oldWidth, newWidth) ->
                   CocoaLibrary.INSTANCE.createWKWebView(0, 0, newWidth.doubleValue(), height)
           );
           rootPane.heightProperty().addListener((obs, oldHeight, newHeight) ->
                   CocoaLibrary.INSTANCE.createWKWebView(0, 0, width, newHeight.doubleValue())
           );
       } catch (Exception e) {
           log.error(e);
       }
    }

    public void show() {
//        stage.setOnCloseRequest(event -> {
//            cleanup();
//        });
//        stage.show();
    }

    public void cleanup() {
        // Destroy WKWebView when closing the app
        if (wkWebView != null) {
            CocoaLibrary.INSTANCE.destroyWKWebView(wkWebView);
        }
    }
}
