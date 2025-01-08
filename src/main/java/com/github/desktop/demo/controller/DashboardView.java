package com.github.desktop.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@FxmlView
public class DashboardView {

    @Autowired
    private FxWeaver fxWeaver;

    @Autowired
    private FxControllerAndView<PerbarindoWebView, AnchorPane> perbarindoWebView;

    @Autowired
    private FxControllerAndView<PerbarindoWKWebView, Pane> perbarindoWKWebView;

    @FXML
    public Button buttonSignIn;

    @FXML
    public Button buttonSignUp;

    @FXML
    public Button buttonDataUser;

    @FXML
    public Button buttonDataBook;

    @FXML
    public Button buttonDataWeb;

    @FXML
    public Button buttonDataWebPerbarindo;

    @FXML
    public Button buttonSignOut;

    @FXML
    public VBox container;

    @FXML
    public void initialize() {

        String currentOS = System.getProperty("os.name").toLowerCase();
        Label currentLabel = new Label(currentOS);
        currentLabel.setFont(new Font(24.0));

        VBox vBox = new VBox(16.0, currentLabel);
        vBox.setPadding(new Insets(16.0));
        vBox.setAlignment(Pos.CENTER_LEFT);
        container.getChildren().add(vBox);
    }

    @FXML
    public void onNavigateToUser(ActionEvent actionEvent) {
        log.debug("Button Data User Clicked!");
        setTitle("Data User");
        Node node = fxWeaver.loadView(ListUserView.class);
        container.getChildren().clear();
        container.getChildren().add(node);
    }

    @FXML
    public void onNavigateToBook(ActionEvent actionEvent) {
        log.debug("Button Data Book Clicked!");
        setTitle("Data Book");
        Node node = fxWeaver.loadView(ListBookView.class);
        container.getChildren().clear();
        container.getChildren().add(node);
    }

    @FXML
    public void onNavigateToWeb(ActionEvent actionEvent) {
        log.debug("Button Data Web Clicked!");
        setTitle("Data Web");
        Node node = fxWeaver.loadView(SomeWebView.class);
        container.getChildren().clear();
        container.getChildren().add(node);
    }

    @FXML
    public void onNavigateToPerbarindo(ActionEvent actionEvent) {
        log.debug("Button Data Web Perbarindo Clicked!");
        boolean usingWK = true;
        if (usingWK) {
            Node node = fxWeaver.loadView(PerbarindoWKWebView.class);
            container.getChildren().clear();
            container.getChildren().add(node);

//            Stage stage = (Stage) container.getScene().getWindow();
//            perbarindoWKWebView.getController().show();
        } else {
            perbarindoWebView.getController().show();
        }

    }

    private void setTitle(String title) {
        Stage stage = (Stage) container.getScene().getWindow();
        stage.setTitle(title);
    }
}
