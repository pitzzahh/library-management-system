package io.github.pitzzahh.libraryManagementSystem.controllers;

import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import io.github.pitzzahh.libraryManagementSystem.util.*;
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
        event.consume();
        Optional<LocalDate> optionalDatePicker = Optional.ofNullable(returnDate.getValue());
        optionalDatePicker.ifPresentOrElse(this::addBook, () -> { // TODO: analyze flow
            Tooltip tooltip  = ToolTipUtil.initToolTip("Cannot add book, Please Select a return date", null, Style.errorToolTipStyle(), returnDate);
            returnDate.setTooltip(tooltip);
            returnDate.getTooltip().show(WindowUtil.getParent("borrow_books_window").getScene().getWindow());
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
            Tooltip tooltip  = ToolTipUtil.initToolTip("Cannot add book, Book is already added", null, Style.errorToolTipStyle(), availableBooks);
            availableBooks.setTooltip(tooltip);
            availableBooks.getTooltip().show(WindowUtil.getParent("borrow_books_window").getScene().getWindow());
        } else {
            DataUtil.getBorrowedBooksDataSource().addAll(selectedItems);

            ComponentUtil.initTableColumns(table, new String[]{"bookId", "title", "author", "category", "dateBorrowed", "dateReturned"});

            table.setItems(DataUtil.getBorrowedBooksDataSource());
            availableBooks.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void onHoverAdd(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Add a book to borrow", mouseEvent, add);
    }

    @FXML
    public void onRemove(MouseEvent mouseEvent) {
        mouseEvent.consume();
        Optional<Object> optional = Optional.ofNullable(table.getSelectionModel().getSelectedItem());
        optional.ifPresent(item -> {
            DataUtil.getBorrowedBooksDataSource().remove(item);
            table.setItems(DataUtil.getBorrowedBooksDataSource());
            table.getSelectionModel().clearSelection();
        });
    }

    @FXML
    public void onHoverRemove(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Remove a book to borrow listed on the right", mouseEvent, remove);
    }

    @FXML
    public void onRemoveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        DataUtil.getAllBorrowedBooks().clear();
        DataUtil.getBorrowedBooksDataSource().clear();
        table.setItems(DataUtil.getBorrowedBooksDataSource());
    }

    @FXML
    public void onHoverRemoveAll(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Remove all books to borrow listed on the right", mouseEvent, removeAll);
    }

    @FXML
    public void onBorrowAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        DataUtil.saveAllBorrowedBooks();
        DataUtil.getBorrowedBooksDataSource().clear();
        table.getItems().clear();
    }

    @FXML
    public void onHoverBorrowAll(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Borrow all the books listed on the right", mouseEvent, borrowAll);
    }

    @FXML
    public void onChooseCategory(ActionEvent actionEvent) {
        actionEvent.consume();
        DataUtil.setAvailableBooksData(availableBooks, choiceBox);
    }

}
