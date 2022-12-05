package io.github.pitzzahh.libraryManagementSystem.controllers;

import io.github.pitzzahh.libraryManagementSystem.util.ComponentUtil;
import io.github.pitzzahh.libraryManagementSystem.util.ToolTipUtil;
import io.github.pitzzahh.libraryManagementSystem.util.WindowUtil;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.util.DataUtil;
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
        WindowUtil.setPage(Page.BORROW_BOOK);
        WindowUtil.loadPage("student_window", "borrow_books_window");

        Parent parent = WindowUtil.getParent("borrow_books_window");

        ComponentUtil.getTable("borrow_books_window", "availableBooks")
                .ifPresent(tableView -> {
                    Optional<ChoiceBox<?>> choiceBox = ComponentUtil.getChoiceBox(parent, "choiceBox");
                    choiceBox.ifPresent(objectChoiceBox -> DataUtil.setAvailableBooksData(tableView, (ChoiceBox<Category>) objectChoiceBox));
                });
    }

    @FXML
    public void onHoverBorrowBook(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Borrow a Book", mouseEvent, borrowBook);
    }

    @FXML
    public void onReturnBook(ActionEvent actionEvent) {
        actionEvent.consume();
        WindowUtil.setPage(Page.RETURN_BOOK);
    }

    @FXML
    public void onHoverReturnBook(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Return a book", mouseEvent, returnBook);
    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onViewBooks(ActionEvent actionEvent) {
        actionEvent.consume();
        WindowUtil.setPage(Page.VIEW_BORROWED_BOOKS);
        WindowUtil.loadPage("student_window", "list_of_borrowed_books_window");
        Optional<TableView<?>> table = ComponentUtil.getTable("list_of_borrowed_books_window", "table");
        table.map(e ->  (TableView<Book> )e)
                .ifPresent(this::setItemsToViewBooksTable);
    }

    private void setItemsToViewBooksTable(TableView<Book> t) {
        DataUtil.getBorrowedBooksDataSource().clear();
        DataUtil.getBorrowedBooksDataSource().addAll(DataUtil.getAllBorrowedBooks());
        ComponentUtil.initTableColumns(t, new String[]{"bookId", "title", "author", "category", "dateBorrowed", "dateReturned"});
        t.setItems(DataUtil.getBorrowedBooksDataSource());
    }

    @FXML
    public void onHoverViewBooks(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("View borrowed Books", mouseEvent, viewBorrowedBooks);
    }

    /**
     * Logs out the current session.
     * Returns to the main page.
     * @param actionEvent the action event.
     */
    @FXML
    public void onLogout(ActionEvent actionEvent) {
        actionEvent.consume();
        WindowUtil.logoutSession();
    }

    /**
     * Shows a tooltip when mouse is hovered over the logout button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onHoverLogout(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Logout Session", mouseEvent, logout);
    }
}
