package com.github.desktop.demo.controller;

import com.github.desktop.demo.entity.BookEntity;
import com.github.desktop.demo.event.StageBookEvent;
import com.github.desktop.demo.service.BookService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.log4j.Log4j2;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@FxmlView
public class CreateBookView {

    private final StringProperty titleProperty = new SimpleStringProperty();
    private final StringProperty descriptionProperty = new SimpleStringProperty();
    private final StringProperty authorProperty = new SimpleStringProperty();
    private final StringProperty genreProperty = new SimpleStringProperty();
    private final IntegerProperty publishYearProperty = new SimpleIntegerProperty();

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private BookService service;

    @FXML
    public TextField titleTextField;

    @FXML
    public TextArea descriptionTextArea;

    @FXML
    public TextField authorTextField;

    @FXML
    public TextField genreTextField;

    @FXML
    public TextField publishYearTextField;

    @FXML
    public AnchorPane createBookDialog;

    private Stage stage;

    @FXML
    public void initialize() {
        this.stage = new Stage();
        stage.setScene(new Scene(createBookDialog));

        Bindings.bindBidirectional(titleTextField.textProperty(), titleProperty);
        Bindings.bindBidirectional(descriptionTextArea.textProperty(), descriptionProperty);
        Bindings.bindBidirectional(authorTextField.textProperty(), authorProperty);
        Bindings.bindBidirectional(genreTextField.textProperty(), genreProperty);
        Bindings.bindBidirectional(publishYearTextField.textProperty(), publishYearProperty, new NumberStringConverter());
    }

    @FXML
    public void onSubmitButton(ActionEvent actionEvent) {
        log.debug("Button Submit Clicked!");
        BookEntity resultEntity = service.createBook(
                BookEntity.builder()
                        .title(titleProperty.get())
                        .description(descriptionProperty.get())
                        .author(authorProperty.get())
                        .genre(genreProperty.get())
                        .publishYear(publishYearProperty.get())
                        .build()
        );

        context.publishEvent(new StageBookEvent(resultEntity));
        stage.close();
        clearData();
    }

    @FXML
    public void onCancelButton(ActionEvent actionEvent) {
        log.debug("Button Cancel Clicked!");
        stage.close();
    }

    public void show() {
        stage.setTitle("Create New Book");
        stage.show();
    }

    public void clearData() {
        titleProperty.set(null);
        descriptionProperty.set(null);
        authorProperty.set(null);
        genreProperty.set(null);
        publishYearProperty.set(0);
    }
}
