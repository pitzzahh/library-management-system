package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    public void onChooseCategory(ActionEvent actionEvent) {
        actionEvent.consume();
        getAvailableBooksDataSource().clear();
        Category selectedItem = choiceBox.getSelectionModel().getSelectedItem();

        availableBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        getAvailableBooksDataSource().addAll(getBooksByCategory(selectedItem));

        TableColumn<Book, ?> bookNumberColumn = availableBooks.getColumns().get(0);
        bookNumberColumn.setStyle("-fx-alignment: CENTER;");
        bookNumberColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, ?> bookTitleColumn = availableBooks.getColumns().get(1);
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, ?> bookAuthorColumn = availableBooks.getColumns().get(2);
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, ?> bookCategoryColumn = availableBooks.getColumns().get(3);
        bookCategoryColumn.setStyle("-fx-alignment: CENTER;");
        bookCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        availableBooks.setItems(getAvailableBooksDataSource());
    }
}
