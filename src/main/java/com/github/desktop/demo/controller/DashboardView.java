package com.github.desktop.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView
public class DashboardView {

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

        buttonSignIn.setOnAction(event -> {
            System.out.println("Button Sign In Clicked!");
        });

        buttonSignUp.setOnAction(event -> {
            System.out.println("Button Sign Up Clicked!");
        });

        buttonDataUser.setOnAction(event -> {
            System.out.println("Button Data User Clicked!");
        });

        buttonDataBook.setOnAction(event -> {
            System.out.println("Button Data Book Clicked!");
        });

        buttonSignOut.setOnAction(event -> {
            System.out.println("Button Sign Out Clicked!");
        });
    }
}
