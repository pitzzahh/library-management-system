package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;

import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class BorrowBookController {

    @FXML
    public ChoiceBox<Category> choiceBox;

    @FXML
    public Button add;

    @FXML
    public Button remove;

    @FXML
    public Button removeAll;

    @FXML
    public Button saveAll;

    @FXML
    public TableView<Book> availableBooks;

    @FXML
    public TableView<Book> table;

    @FXML
    public void onAdd(MouseEvent mouseEvent) {

    }

    @FXML
    public void onHoverAdd(MouseEvent mouseEvent) {
        showToolTipOnHover("Add a book", mouseEvent, add);
    }

    @FXML
    public void onRemove(MouseEvent mouseEvent) {

    }

    @FXML
    public void onHoverRemove(MouseEvent mouseEvent) {
        showToolTipOnHover("Remove a book", mouseEvent, remove);
    }

    @FXML
    public void onRemoveAll(MouseEvent mouseEvent) {

    }

    @FXML
    public void onHoverRemoveAll(MouseEvent mouseEvent) {
        showToolTipOnHover("Remove all books", mouseEvent, remove);
    }

    @FXML
    public void onSaveAll(MouseEvent mouseEvent) {

    }

    @FXML
    public void onHoverSaveAll(MouseEvent mouseEvent) {
        showToolTipOnHover("Save all books", mouseEvent, remove);
    }

}
