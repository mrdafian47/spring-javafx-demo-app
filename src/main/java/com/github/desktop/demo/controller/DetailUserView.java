package com.github.desktop.demo.controller;

import com.github.desktop.demo.dto.*;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@FxmlView
public class DetailUserView {

    @FXML
    public ScrollPane userDetailDialog;

    @FXML
    public ImageView userImageView;

    @FXML
    public Label userTitleLabel;

    @FXML
    public Label userFirstLabel;

    @FXML
    public Label userLastLabel;

    @FXML
    public Label userEmailLabel;

    @FXML
    public Label userPhoneLabel;

    @FXML
    public Label userAddressLabel;

    @FXML
    public Label userCityLabel;

    @FXML
    public Label userStateLabel;

    @FXML
    public Label userCountryLabel;

    @FXML
    public Label userPostCodeLabel;

    @FXML
    public WebView userMapWebView;

    private Stage stage;
    private WebEngine webEngine;

    @FXML
    public void initialize() {
        this.stage = new Stage();
        stage.setScene(new Scene(userDetailDialog));

        webEngine = userMapWebView.getEngine();
        webEngine.setUserAgent("Demo Web Browser 1.0");
        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.RUNNING) {
                log.debug("Start running other task");
            }

            log.debug("Old State: {}", oldState.toString());
            log.debug("New State: {}", newState.toString());
        });
    }

    public void show(UserDTO userDTO) {
        displayData(userDTO);
        stage.setTitle("Detail User");
        stage.show();
    }

    private void displayData(UserDTO userDTO) {
        userEmailLabel.setText(userDTO.getEmail());
        userPhoneLabel.setText(userDTO.getPhone());

        UserNameDTO nameDTO = userDTO.getNameDTO();
        userTitleLabel.setText(nameDTO.getTitle());
        userFirstLabel.setText(nameDTO.getFirst());
        userLastLabel.setText(nameDTO.getLast());

        UserPictureDTO pictureDTO = userDTO.getPictureDTO();
        Image userPictureImage = new Image(pictureDTO.getLarge());
        userImageView.setImage(userPictureImage);

        UserLocationDTO locationDTO = userDTO.getLocationDTO();
        userCityLabel.setText(locationDTO.getCity());
        userStateLabel.setText(locationDTO.getState());
        userCountryLabel.setText(locationDTO.getCountry());
        userPostCodeLabel.setText(locationDTO.getPostCode());

        UserLocationStreetDTO streetDTO = locationDTO.getStreetDTO();
        userAddressLabel.setText(streetDTO.getName() + " No." + streetDTO.getNumber());

        UserLocationCoordinateDTO coordinateDTO = locationDTO.getCoordinateDTO();
        Double latitude = Double.parseDouble(coordinateDTO.getLatitude());
        Double longitude = Double.parseDouble(coordinateDTO.getLongitude());
        String userLocationUrl = generateLocationUrlByCoordinate(latitude, longitude);
        log.debug("User Location URL: {}", userLocationUrl);
        webEngine.load(userLocationUrl);
    }

    private String generateLocationUrlByCoordinate(Double latitude, Double longitude) {
        // https://www.openstreetmap.org/?mlat=-6.2607&mlon=106.7816#map=16/-6.26070/106.78160
        StringBuilder locationUrlBuilder = new StringBuilder();
        locationUrlBuilder.append("https://www.openstreetmap.org/?mlat=");
        locationUrlBuilder.append(latitude);
        locationUrlBuilder.append("&mlon=");
        locationUrlBuilder.append(longitude);
        locationUrlBuilder.append("#map=16/");
        locationUrlBuilder.append(latitude);
        locationUrlBuilder.append("/");
        locationUrlBuilder.append(longitude);
        return locationUrlBuilder.toString();
    }
}
