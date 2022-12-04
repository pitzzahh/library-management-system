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
        optionalDatePicker.ifPresentOrElse(this::addBook, () -> { // TODO: analyze flow
            Tooltip tooltip  = initToolTip("Cannot add book, Please Select a return date", null, errorToolTipStyle(), returnDate);
            returnDate.setTooltip(tooltip);
            returnDate.getTooltip().show(getParent("borrow_books_window").getScene().getWindow());
        });
    }

    private void addBook(LocalDate date) {
        ObservableList<Book> selectedItems = availableBooks
                .getSelectionModel()
                .getSelectedItems()
                .stream()
                .peek(book -> {
                        book.setDateBorrowed(LocalDate.now());
                        book.setDateReturned(date);
                })
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        boolean error = selectedItems.stream()
                .noneMatch(books -> table.getItems().stream().noneMatch(book -> book.equals(books)));

        System.out.println("error = " + error);

        if (error) {
            Tooltip tooltip  = initToolTip("Cannot add book, Book is already added", null, errorToolTipStyle(), table);
            availableBooks.setTooltip(tooltip);
            availableBooks.getTooltip().show(getParent("borrow_books_window").getScene().getWindow());
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
        showToolTipOnHover("Remove a book to borrow listed on the right", mouseEvent, remove);
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
        saveAllBorrowedBooks();
        getBorrowedBooksDataSource().clear();
        table.setItems(getBorrowedBooksDataSource());
    }

    @FXML
    public void onHoverBorrowAll(MouseEvent mouseEvent) {
        showToolTipOnHover("Borrow all the books listed on the right", mouseEvent, borrowAll);
    }

    @FXML
    public void onChooseCategory(ActionEvent ignoredActionEvent) {
        setAvailableBooksData(availableBooks, choiceBox);
    }

}
