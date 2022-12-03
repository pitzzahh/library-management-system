package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
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
    public Button borrowAll;

    @FXML
    public TableView<Book> availableBooks;

    @FXML
    public TableView<Book> table;

    @FXML
    public void onAdd(MouseEvent mouseEvent) {
        mouseEvent.consume();
        ObservableList<Book> selectedItems = availableBooks.getSelectionModel().getSelectedItems();

        getBorrowedBooksDataSource().addAll(selectedItems);

        initTableColumns(
                table,
                "bookId",
                "title",
                "author",
                "category"
        );

        table.setItems(getBorrowedBooksDataSource());
    }

    @FXML
    public void onHoverAdd(MouseEvent mouseEvent) {
        showToolTipOnHover("Add a book to borrow", mouseEvent, add);
    }

    @FXML
    public void onRemove(MouseEvent mouseEvent) {

    }

    @FXML
    public void onHoverRemove(MouseEvent mouseEvent) {
        showToolTipOnHover("Remove a book to borrow from the right", mouseEvent, remove);
    }

    @FXML
    public void onRemoveAll(MouseEvent mouseEvent) {

    }

    @FXML
    public void onHoverRemoveAll(MouseEvent mouseEvent) {
        showToolTipOnHover("Remove all books to borrow listed on the right", mouseEvent, removeAll);
    }

    @FXML
    public void onSaveAll(MouseEvent mouseEvent) {

    }

    @FXML
    public void onHoverSaveAll(MouseEvent mouseEvent) {
        showToolTipOnHover("Borrow all the books listed on the right", mouseEvent, borrowAll);
    }

    @FXML
    public void onChooseCategory(ActionEvent actionEvent) {
        actionEvent.consume();
        getAvailableBooksDataSource().clear();
        Category selectedItem = choiceBox.getSelectionModel().getSelectedItem();

        availableBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        getAvailableBooksDataSource().addAll(getBooksByCategory(selectedItem));

        initTableColumns(
                availableBooks,
                "bookId",
                "title",
                "author",
                "category"
        );

        availableBooks.setItems(getAvailableBooksDataSource());
    }
}
