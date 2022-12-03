package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Page;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
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
    public void onBorrowBook(ActionEvent actionEvent) {
        actionEvent.consume();
        setPage(Page.BORROW_BOOK);
    }

    @FXML
    public void onHoverBorrowBook(MouseEvent mouseEvent) {
        showToolTipOnHover("Logout Session", mouseEvent, borrowBook);
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
    public void onViewBooks(ActionEvent actionEvent) {
        actionEvent.consume();
        setPage(Page.VIEW_BORROWED_BOOKS);
    }

    @FXML
    public void onHoverViewBooks(MouseEvent mouseEvent) {
        showToolTipOnHover("View borrowed books", mouseEvent, viewBorrowedBooks);
    }

    /**
     * Logs out the current session.
     * Returns to the main page.
     * @param ignoredActionEvent the action event.
     */
    @FXML
    public void onLogout(ActionEvent ignoredActionEvent) {
        logoutSession();
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
