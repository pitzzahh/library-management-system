package io.github.pitzzahh.libraryManagementSystem.controllers;

import io.github.pitzzahh.libraryManagementSystem.util.ToolTipUtil;
import io.github.pitzzahh.libraryManagementSystem.util.WindowUtil;
import io.github.pitzzahh.libraryManagementSystem.entity.Page;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;

/**
 * FXML Controller class for Admin page
 */
public class AdminController {

    @FXML
    public Button addStudents;

    @FXML
    private Button addBooks;

    @FXML
    private Button manageStudents;

    @FXML
    private Button manageBooks;

    @FXML
    private Button logout;

    /**
     * Shows a tooltip when the mouse is hovered over the add clients button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEnteredAddStudentsButton(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Add Students", mouseEvent, addStudents);
    }

    /**
     * Shows a tooltip when the mouse is hovered over the remove clients button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEnteredAddBooksButton(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Add Books", mouseEvent, addBooks);
    }

    /**
     * Shows a tooltip when the mouse is hovered over the view clients button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEnteredManageStudentsButton(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Manage Students Records", mouseEvent, manageStudents);
    }

    /**
     * Shows a tooltip when the mouse is hovered over the manage account loans button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEnteredManageBooksButton(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Manage Borrowed Books", mouseEvent, manageBooks);
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
    public void onMouseEnteredLogout(MouseEvent mouseEvent) {
        ToolTipUtil.showToolTipOnHover("Logout Session", mouseEvent, logout);
    }

    @FXML
    public void onAddStudents(ActionEvent actionEvent) {
        actionEvent.consume();
        WindowUtil.setPage(Page.ADD_STUDENTS);
        WindowUtil.loadPage("admin_window", "add_students_window");
    }

    @FXML
    public void onAddBooks(ActionEvent actionEvent) {
        actionEvent.consume();
        WindowUtil.setPage(Page.ADD_BOOKS);
        WindowUtil.loadPage("admin_window", "add_books_window");
    }

}

