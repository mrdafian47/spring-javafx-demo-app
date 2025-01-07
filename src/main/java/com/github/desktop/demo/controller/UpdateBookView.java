package com.github.desktop.demo.controller;

import com.github.desktop.demo.entity.BookEntity;
import com.github.desktop.demo.event.StageBookEvent;
import com.github.desktop.demo.service.BookService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
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

import java.util.Optional;

@Log4j2
@Component
@FxmlView
public class UpdateBookView {

    private final LongProperty idProperty = new SimpleLongProperty();
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
    public TextField idTextField;

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
    public AnchorPane updateBookDialog;

    private Stage stage;
    private Long bookId;

    @FXML
    public void initialize() {
        this.stage = new Stage();
        stage.setScene(new Scene(updateBookDialog));

        Bindings.bindBidirectional(idTextField.textProperty(), idProperty, new NumberStringConverter());
        Bindings.bindBidirectional(titleTextField.textProperty(), titleProperty);
        Bindings.bindBidirectional(descriptionTextArea.textProperty(), descriptionProperty);
        Bindings.bindBidirectional(authorTextField.textProperty(), authorProperty);
        Bindings.bindBidirectional(genreTextField.textProperty(), genreProperty);
        Bindings.bindBidirectional(publishYearTextField.textProperty(), publishYearProperty, new NumberStringConverter());
    }

    @FXML
    public void onUpdateButton(ActionEvent actionEvent) {
        log.debug("Button Update Clicked!");
        BookEntity resultEntity = service.updateBook(
                BookEntity.builder()
                        .id(bookId)
                        .title(titleProperty.get())
                        .description(descriptionProperty.get())
                        .author(authorProperty.get())
                        .genre(genreProperty.get())
                        .publishYear(publishYearProperty.get())
                        .build(),
                bookId
        );

        context.publishEvent(new StageBookEvent(resultEntity));
        stage.close();
    }

    @FXML
    public void onCancelButton(ActionEvent actionEvent) {
        log.debug("Button Cancel Clicked!");
        stage.close();
    }

    public void show(Long bookId) {
        loadBookById(bookId);
        stage.setTitle("Edit Book: #" + bookId);
        stage.show();
    }

    private void loadBookById(Long bookId) {
        this.bookId = bookId;
        Optional<BookEntity> entityOpt = service.getBookById(bookId);
        if (entityOpt.isPresent()) {
            BookEntity entity = entityOpt.get();

            idProperty.set(entity.getId());
            titleProperty.set(entity.getTitle());
            descriptionProperty.set(entity.getDescription());
            authorProperty.set(entity.getAuthor());
            genreProperty.set(entity.getGenre());
            publishYearProperty.set(entity.getPublishYear());
        }
    }
}
