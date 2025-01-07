package com.github.desktop.demo.controller;

import com.github.desktop.demo.dto.UserDTO;
import com.github.desktop.demo.model.UserModel;
import com.github.desktop.demo.service.UserService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView
public class ListUserView {

    private final ObservableList<UserModel> entityObservableList = FXCollections.observableArrayList();

    private final List<UserDTO> userDTOList = new ArrayList<>();

    @Autowired
    private UserService service;

    @Autowired
    private FxControllerAndView<DetailUserView, ScrollPane> detailUserView;

    @FXML
    public TableView<UserModel> userTableView;

    @FXML
    public TableColumn<UserModel, String> nameColumn;

    @FXML
    public TableColumn<UserModel, String> emailColumn;

    @FXML
    public TableColumn<UserModel, Button> viewColumn;

    @FXML
    public void initialize() {

        userTableView.setItems(entityObservableList);

        viewColumn.setStyle("-fx-alignment: CENTER;");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        viewColumn.setCellValueFactory(param -> {
            UserModel userModel = param.getValue();
            UserDTO userDTO = findByEmail(userModel.getEmail());

            Button button = new Button("View");
            button.setOnAction(event -> {
                System.out.println("Button View Clicked!");
                detailUserView.getController().show(userDTO);
            });

            return new SimpleObjectProperty<>(button);
        });

        loadData();
    }

    private void loadData() {

        List<UserDTO> userDTOList = service.fetchUserAll(1, 10);
        this.userDTOList.clear();
        this.userDTOList.addAll(userDTOList);

        List<UserModel> userModelList = new ArrayList<>(userDTOList.size());
        userDTOList.forEach(dto -> userModelList.add(UserModel.create(dto)));

        entityObservableList.addAll(userModelList);
    }

    private UserDTO findByEmail(String email) {
        return userDTOList.stream()
                .filter(dto -> dto.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(new UserDTO());
    }
}
