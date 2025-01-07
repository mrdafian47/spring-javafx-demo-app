package com.github.desktop.demo.controller;

import com.github.desktop.demo.entity.BookEntity;
import com.github.desktop.demo.event.StageBookEvent;
import com.github.desktop.demo.service.BookService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lombok.extern.log4j.Log4j2;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@FxmlView
public class ListBookView implements ApplicationListener<StageBookEvent> {

    private final ObservableList<BookEntity> entityObservableList = FXCollections.observableArrayList();

    @Autowired
    private BookService service;

    @Autowired
    private FxControllerAndView<CreateBookView, AnchorPane> createBookView;

    @Autowired
    private FxControllerAndView<UpdateBookView, AnchorPane> updateBookView;

    @FXML
    public TableView<BookEntity> bookTableView;

    @FXML
    public TableColumn<BookEntity, Long> idColumn;

    @FXML
    public TableColumn<BookEntity, String> titleColumn;

    @FXML
    public TableColumn<BookEntity, String> authorColumn;

    @FXML
    public TableColumn<BookEntity, Button> editColumn;

    @FXML
    public TableColumn<BookEntity, Button> deleteColumn;

    @FXML
    public void initialize() {

        bookTableView.setItems(entityObservableList);

        idColumn.setStyle("-fx-alignment: CENTER;");
        editColumn.setStyle("-fx-alignment: CENTER;");
        deleteColumn.setStyle("-fx-alignment: CENTER;");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        editColumn.setCellValueFactory(param -> {
            Button button = new Button("Edit");
            BookEntity entity = param.getValue();

            button.setOnAction(event -> {
                log.debug("Button Edit Clicked!");
                log.debug("Edit Book with ID: {}", entity.getId());
                updateBookView.getController().show(entity.getId());
            });

            return new SimpleObjectProperty<>(button);
        });

        deleteColumn.setCellValueFactory(param -> {
            Button button = new Button("Delete");
            BookEntity entity = param.getValue();

            button.setOnAction(event -> {
                log.debug("Button Delete Clicked!");
                log.debug("Delete Book with ID: {}", entity.getId());

                Alert alert = new Alert(
                        Alert.AlertType.WARNING,
                        "Do you want to delete this book?",
                        ButtonType.YES,
                        ButtonType.NO
                );

                Optional<ButtonType> resultOpt = alert.showAndWait();
                if (resultOpt.isPresent()) {
                    ButtonType resultButtonType = resultOpt.get();
                    if (resultButtonType == ButtonType.YES) {
                        service.deleteBook(entity.getId());
                        loadData();
                        alert.close();
                    } else if (resultButtonType == ButtonType.NO) {
                        alert.close();
                    }
                }
            });

            return new SimpleObjectProperty<>(button);
        });

        loadData();
    }

    @FXML
    public void onCreateButton(ActionEvent actionEvent) {
        log.debug("Button Create New Clicked!");
        createBookView.getController().show();
    }

    @Override
    public void onApplicationEvent(StageBookEvent event) {
        log.debug("Success Create or Update Book. Reload Data");
        loadData();
    }

    private void loadData() {

        List<BookEntity> entityList = service.getBookAll();
        if (!entityList.isEmpty()) {
            entityObservableList.clear();
            entityObservableList.addAll(entityList);
        }
    }
}
