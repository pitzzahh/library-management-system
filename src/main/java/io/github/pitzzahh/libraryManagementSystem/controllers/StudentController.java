package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StudentController {


    @FXML
    private Button logout;

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
    public void onMouseEnteredLogout(MouseEvent mouseEvent) {
        showToolTipOnHover("Logout Session", mouseEvent, logout);
    }
}
