package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getLogger;
import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getStage;
import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.entity.Course;
import javafx.scene.control.cell.PropertyValueFactory;
import io.github.pitzzahh.util.utilities.Print;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.fxml.FXML;

/**
 * FXML Controller class for Admin page
 */
public class AdminController {


    @FXML
    public Button addStudents;

    @FXML
    public TableView<Student> studentTable;

    @FXML
    public TextField studentId;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public ChoiceBox<Course> course;

    @FXML
    private Button addBooks;

    @FXML
    private Button manageStudents;

    @FXML
    private Button manageBooks;

    @FXML
    private Button logout;

    @FXML
    private Button addStudentTable;

    @FXML
    private Button removeStudentsTable;

    @FXML
    private Button removeAllStudentsTable;

    @FXML
    private Button saveALlStudentsTable;

    private final ObservableList<Student> dataSource = FXCollections.observableArrayList();

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
        getStage().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.F11.equals(event.getCode())) getStage().setFullScreen(false);
        });
        getLogger().debug("Loading main window");
        stage.show();
    }

    @FXML
    public void onAddStudents(ActionEvent actionEvent) {
        setCenterScreenOfBorderPane(actionEvent, "add_students_window");
    }

    @FXML
    public void onAddBooks(ActionEvent actionEvent) {
        setCenterScreenOfBorderPane(actionEvent, "add_books_window");
    }

    @FXML
    public void onHoverAddStudent(MouseEvent event) {
        var tooltip = initToolTip(
                "Add Student",
                event,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        addStudentTable.setTooltip(tooltip);
    }

    @FXML
    public void onHoverRemoveStudent(MouseEvent event) {
        var tooltip = initToolTip(
                "Remove Student",
                event,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        removeStudentsTable.setTooltip(tooltip);
    }

    @FXML
    public void onHoverRemoveAll(MouseEvent event) {
        var tooltip = initToolTip(
                "Remove All Students records from the table",
                event,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        removeAllStudentsTable.setTooltip(tooltip);
    }

    @FXML
    public void onHoverSaveAll(MouseEvent event) {
        var tooltip = initToolTip(
                "Save all students records from the table to the database",
                event,
                adminButtonFunctionsToolTipStyle()
        );
        tooltip.setShowDuration(Duration.seconds(3));
        saveALlStudentsTable.setTooltip(tooltip);
    }

    @FXML
    public void onAddStudent(ActionEvent actionEvent) {
        dataSource.add(new Student(generateRandomAccountNumber(), "John", "Doe", Course.BSIT));
        studentTable.setItems(dataSource);
        getLogger().debug("Added student to table");
        getLogger().debug("Student table size: " + studentTable.getItems().size());
        studentTable.getItems().forEach(Print::println);
    }


}

