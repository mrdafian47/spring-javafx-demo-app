package com.github.desktop.demo.controller;

import com.github.desktop.demo.service.RandomService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView
public class SomeDialog {

    private Stage stage;

    @FXML
    public Label randomLabel;

    @FXML
    public Button closeButton;

    @FXML
    public VBox dialog;

    @Autowired
    private RandomService service;

    @FXML
    public void initialize() {
        this.stage = new Stage();
        stage.setScene(new Scene(dialog));

        randomLabel.setText(service.getRandomWord());
        closeButton.setOnAction(actionEvent -> stage.close());
    }

    public void show() {
        stage.setTitle("Dialog");
        stage.show();
    }
}
