package io.github.pitzzahh.libraryManagementSystem.controllers;

import org.controlsfx.control.textfield.TextFields;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getLogger;
import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getStage;
import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;

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

    private boolean isOk;

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
     * @param actionEvent the action event.
     */
    @FXML
    public void onLogout(ActionEvent actionEvent) {
        getLogger().info("Logging out...");
        var parent = (AnchorPane) ((((Button) actionEvent.getSource()).getParent().getParent())).getParent();
        var stage = (Stage) parent.getScene().getWindow();
        stage.close();

        var mainWindow = getParent("main_window");
        getMessageLabel(mainWindow).ifPresent(label -> label.setText(""));
        getMainProgressBar(mainWindow).ifPresent(pb -> pb.setVisible(false));
        getStage().setFullScreen(false);
        stage.setTitle("ATM");
        stage.setFullScreen(false);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(mainWindow.getScene());
        getLogger().debug("Loading main window");
        stage.show();
    }

}

