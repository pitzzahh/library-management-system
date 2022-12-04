package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.Optional;
import javafx.fxml.FXML;

public class BorrowBookController {

    @FXML
    public ChoiceBox<Category> choiceBox;

    @FXML
    public DatePicker returnDate;

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
    public Button separateWindowButton;

    @FXML
    public void onAdd(MouseEvent event) {
        Optional<LocalDate> optionalDatePicker = Optional.ofNullable(returnDate.getValue());
        optionalDatePicker.ifPresentOrElse(e -> addBook(e, event), () -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Return date is required");
            alert.setContentText("Please select a return date");
            alert.showAndWait();
        });
    }

    private void addBook(LocalDate date, MouseEvent event) {
        ObservableList<Book> selectedItems = availableBooks
                .getSelectionModel()
                .getSelectedItems()
                .stream()
                .peek(book -> {
                        book.setDateBorrowed(LocalDate.now());
                        book.setDateReturned(date);
                })
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        boolean b = selectedItems.stream()
                .anyMatch(books -> table.getItems().stream().anyMatch(book -> book.equals(books)));

        if (b) {
            Tooltip tooltip  = initToolTip("Cannot add book, Book is already added", event, errorToolTipStyle());
            availableBooks.setTooltip(tooltip);
            tooltip.show(availableBooks, event.getScreenX(), event.getScreenY());
        } else {
            getBorrowedBooksDataSource().addAll(selectedItems);

            initTableColumns(table, new String[]{"bookId", "title", "author", "category", "dateBorrowed", "dateReturned"});

            table.setItems(getBorrowedBooksDataSource());
            availableBooks.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void onHoverAdd(MouseEvent mouseEvent) {
        showToolTipOnHover("Add a book to borrow", mouseEvent, add);
    }

    @FXML
    public void onRemove(MouseEvent ignoredMouseEvent) {
        Optional<Object> optional = Optional.ofNullable(table.getSelectionModel().getSelectedItem());
        optional.ifPresent(item -> {
            getBorrowedBooksDataSource().remove(item);
            table.setItems(getBorrowedBooksDataSource());
            table.getSelectionModel().clearSelection();
        });
    }

    @FXML
    public void onHoverRemove(MouseEvent mouseEvent) {
        showToolTipOnHover("Remove a book to borrow from the right", mouseEvent, remove);
    }

    @FXML
    public void onRemoveAll(MouseEvent ignoredMouseEvent) {
        getAllBorrowedBooks().clear();
        getBorrowedBooksDataSource().clear();
        table.setItems(getBorrowedBooksDataSource());
    }

    @FXML
    public void onHoverRemoveAll(MouseEvent mouseEvent) {
        showToolTipOnHover("Remove all books to borrow listed on the right", mouseEvent, removeAll);
    }

    @FXML
    public void onBorrowAll(MouseEvent ignoredMouseEvent) {

    }

    @FXML
    public void onHoverBorrowAll(MouseEvent mouseEvent) {
        showToolTipOnHover("Borrow all the books listed on the right", mouseEvent, borrowAll);
    }

    @FXML
    public void onChooseCategory(ActionEvent ignoredActionEvent) {
        getAvailableBooksDataSource().clear();
        Category selectedItem = choiceBox.getSelectionModel().getSelectedItem();

        availableBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        getAvailableBooksDataSource().addAll(getBooksByCategory(selectedItem));

        initTableColumns(availableBooks, new String[]{"bookId", "title", "author", "category"});

        availableBooks.setItems(getAvailableBooksDataSource());
    }

}
