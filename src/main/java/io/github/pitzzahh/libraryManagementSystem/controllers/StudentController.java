package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import io.github.pitzzahh.libraryManagementSystem.entity.Page;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import java.util.Optional;
import javafx.fxml.FXML;

public class StudentController {

    @FXML
    public Button borrowBook;

    @FXML
    public Button returnBook;

    @FXML
    public Button viewBorrowedBooks;

    @FXML
    private Button logout;

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onBorrowBook(ActionEvent actionEvent) {
        actionEvent.consume();
        setPage(Page.BORROW_BOOK);
        loadPage("student_window", "borrow_books_window");

        Parent parent = getParent("borrow_books_window");

        getTable("borrow_books_window", "availableBooks")
                .ifPresent(tableView -> {
                    Optional<ChoiceBox<?>> choiceBox = getChoiceBox(parent, "choiceBox");
                    choiceBox.ifPresent(objectChoiceBox -> setAvailableBooksData(tableView, (ChoiceBox<Category>) objectChoiceBox));
                });
    }

    @FXML
    public void onHoverBorrowBook(MouseEvent mouseEvent) {
        showToolTipOnHover("Borrow a Book", mouseEvent, borrowBook);
    }

    @FXML
    public void onReturnBook(ActionEvent actionEvent) {
        actionEvent.consume();
        setPage(Page.RETURN_BOOK);
    }

    @FXML
    public void onHoverReturnBook(MouseEvent mouseEvent) {
        showToolTipOnHover("Return a book", mouseEvent, returnBook);
    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onViewBooks(ActionEvent actionEvent) {
        actionEvent.consume();
        setPage(Page.VIEW_BORROWED_BOOKS);
        loadPage("student_window", "list_of_borrowed_books_window");
        Optional<TableView<?>> table = getTable("list_of_borrowed_books_window", "table");
        table.map(e ->  (TableView<Book> )e)
                .ifPresent(this::setItemsToViewBooksTable);
    }

    private void setItemsToViewBooksTable(TableView<Book> t) {
        getBorrowedBooksDataSource().clear();
        getBorrowedBooksDataSource().addAll(getAllBorrowedBooks());
        initTableColumns(t, new String[]{"bookId", "title", "author", "category", "dateBorrowed", "dateReturned"});
        t.setItems(getBorrowedBooksDataSource());
    }

    @FXML
    public void onHoverViewBooks(MouseEvent mouseEvent) {
        showToolTipOnHover("View borrowed Books", mouseEvent, viewBorrowedBooks);
    }

    /**
     * Logs out the current session.
     * Returns to the main page.
     * @param actionEvent the action event.
     */
    @FXML
    public void onLogout(ActionEvent actionEvent) {
        logoutSession(actionEvent);
    }

    /**
     * Shows a tooltip when mouse is hovered over the logout button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onHoverLogout(MouseEvent mouseEvent) {
        showToolTipOnHover("Logout Session", mouseEvent, logout);
    }
}
