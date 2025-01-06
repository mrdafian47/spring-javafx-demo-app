package com.github.desktop.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView
public class DashboardView {

    @Autowired
    private FxWeaver fxWeaver;

    @FXML
    public Button buttonSignIn;

    @FXML
    public Button buttonSignUp;

    @FXML
    public Button buttonDataUser;

    @FXML
    public Button buttonDataBook;

    @FXML
    public Button buttonSignOut;

    @FXML
    public VBox container;

    @FXML
    public void initialize() {

    }

    @FXML
    public void onNavigateToBook(ActionEvent actionEvent) {
        System.out.println("Button Data Book Clicked!");
        AnchorPane node = fxWeaver.loadView(ListBookView.class);
        container.getChildren().clear();
        container.getChildren().add(node);
    }
}
