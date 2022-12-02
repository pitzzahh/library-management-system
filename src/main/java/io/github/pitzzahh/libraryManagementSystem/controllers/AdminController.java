package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getLogger;
import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getStage;
import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
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
        var tooltip = initToolTip(
                "Add Students",
                mouseEvent,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        addStudents.setTooltip(tooltip);
    }

    /**
     * Shows a tooltip when the mouse is hovered over the remove clients button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEnteredAddBooksButton(MouseEvent mouseEvent) {
        var tooltip = initToolTip(
                "Add Books",
                mouseEvent,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        addBooks.setTooltip(tooltip);
    }

    /**
     * Shows a tooltip when the mouse is hovered over the view clients button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEnteredManageStudentsButton(MouseEvent mouseEvent) {
        var tooltip = initToolTip(
                "Manage Students Records",
                mouseEvent,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        manageStudents.setTooltip(tooltip);
    }

    /**
     * Shows a tooltip when the mouse is hovered over the manage account loans button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEnteredManageBooksButton(MouseEvent mouseEvent) {
        var tooltip = initToolTip(
                "Manage Locked Accounts",
                mouseEvent,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        manageBooks.setTooltip(tooltip);
    }

    /**
     * Shows a tooltip when mouse is hovered over the logout button.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEnteredLogout(MouseEvent mouseEvent) {
        var tooltip = initToolTip(
                "Logout Session",
                mouseEvent,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        logout.setTooltip(tooltip);
    }


    /**
     * Logs out the current session.
     * Returns to the main page.
     * @param ignoredActionEvent the action event.
     */
    @FXML
    public void onLogout(ActionEvent ignoredActionEvent) {
        getLogger().info("Logging out...");
        getStage().removeEventHandler(KeyEvent.KEY_PRESSED, getToggleFullScreenEvent());
        getStage().close();
        var mainWindow = getParent("main_window");
        getMessageLabel(mainWindow).ifPresent(label -> label.setText(""));
        getMainProgressBar(mainWindow).ifPresent(pb -> pb.setVisible(false));
        getStage().setTitle("Library Management System");
        getStage().centerOnScreen();
        getStage().setScene(mainWindow.getScene());
        getLogger().debug("Loading main window");
        getStage().show();
    }

    @FXML
    public void onAddStudents(ActionEvent actionEvent) {
        setCenterScreenOfBorderPane(actionEvent, "add_students_window");
    }

    @FXML
    public void onAddBooks(ActionEvent actionEvent) {
        setCenterScreenOfBorderPane(actionEvent, "add_books_window");
    }

}

