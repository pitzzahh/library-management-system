package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getLogger;
import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.fxml.FXML;

public class AddStudBookController {

    @FXML
    public TextField id;

    @FXML
    public TextField firstInput;

    @FXML
    public TextField secondInput;

    @FXML
    public ChoiceBox<?> choiceBox;

    @FXML
    private Button add;

    @FXML
    private Button remove;

    @FXML
    private Button removeAll;

    @FXML
    private Button saveAll;


    @FXML
    @SuppressWarnings("rawtypes")
    public TableView table;

    private boolean passed;

    @FXML
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void onAdd(MouseEvent event) {
        event.consume();
        getLogger().debug("Passed: {}", passed);
        if (passed) {
            if (!isStudentAlreadyAdded(id.getText().trim())) {
                if (getPage().equals(Page.ADD_STUDENTS)) {
                    getStudentsDataSource().add(new Student(
                            id.getText().trim(),
                            firstInput.getText().trim(),
                            secondInput.getText().trim(),
                            (Course) choiceBox.getSelectionModel().getSelectedItem()
                    ));
                    TableColumn studentNumberColumn = (TableColumn) table.getColumns().get(0);
                    studentNumberColumn.setStyle("-fx-alignment: CENTER;");
                    studentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));

                    TableColumn firstNameColumn = (TableColumn) table.getColumns().get(1);
                    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

                    TableColumn lastNameColumn = (TableColumn) table.getColumns().get(2);
                    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

                    TableColumn studentCourseColumn = (TableColumn) table.getColumns().get(3);
                    studentCourseColumn.setStyle("-fx-alignment: CENTER;");
                    studentCourseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

                    table.setItems(getStudentsDataSource());
                    resetInputs(
                            id,
                            firstInput,
                            secondInput,
                            choiceBox
                    );
                }
                else if (getPage().equals(Page.ADD_BOOKS)){
                    getBooksDataSource().add(new Book(
                            id.getText().trim(),
                            firstInput.getText().trim(),
                            secondInput.getText().trim(),
                            (Category) choiceBox.getSelectionModel().getSelectedItem(),
                            null,
                            null,
                            null
                    ));
                    TableColumn bookNumberColumn = (TableColumn) table.getColumns().get(0);
                    bookNumberColumn.setStyle("-fx-alignment: CENTER;");
                    bookNumberColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

                    TableColumn firstNameColumn = (TableColumn) table.getColumns().get(1);
                    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

                    TableColumn lastNameColumn = (TableColumn) table.getColumns().get(2);
                    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

                    TableColumn studentCourseColumn = (TableColumn) table.getColumns().get(3);
                    studentCourseColumn.setStyle("-fx-alignment: CENTER;");
                    studentCourseColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

                    table.setItems(getBooksDataSource());
                    resetInputs(
                            id,
                            firstInput,
                            secondInput,
                            choiceBox
                    );
                }

            }
            else {
                String message = switch (getPage()) {
                    case ADD_STUDENTS -> "Cannot add student, Student with student id already added";
                    case ADD_BOOKS -> "Cannot add book, Book with book id already added";
                    case MANAGE_STUDENTS, MANAGE_BOOKS -> "No Message";
                };
                Tooltip tooltip  = new Tooltip(message);
                tooltip.setStyle(errorToolTipStyle());
                tooltip.setAutoHide(true);
                tooltip.setShowDuration(Duration.seconds(3));
                id.setTooltip(tooltip);
                String window = switch (getPage()) {
                    case ADD_STUDENTS -> "add_students_window";
                    case ADD_BOOKS -> "add_books_window";
                    case MANAGE_STUDENTS, MANAGE_BOOKS -> "";
                };
                id.getTooltip().show(getParent(window).getScene().getWindow());
            }
        }
    }

    @FXML
    public void onHoverAdd(MouseEvent event) {
        passed = checkInputs(
                add,
                event,
                id.getText().trim(),
                firstInput.getText().trim(),
                secondInput.getText().trim()
        );
        if (passed) {
            Tooltip tooltip = initToolTip(
                    switch (getPage()) {
                        case ADD_STUDENTS -> "Add Student";
                        case ADD_BOOKS -> "Add Book";
                        case MANAGE_STUDENTS, MANAGE_BOOKS -> "No Message";
                    },
                    event,
                    adminButtonFunctionsToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            add.setTooltip(tooltip);
        }
    }

    /**
     * Removes a student record from the table
     * @param mouseEvent the mouse event
     */
    @FXML
    @SuppressWarnings({"unchecked", "SuspiciousMethodCalls"})
    public void onRemove(MouseEvent mouseEvent) {
        mouseEvent.consume();
        getStudentsDataSource().remove(table.getSelectionModel().getSelectedItem());
        table.setItems(getStudentsDataSource());
    }

    @FXML
    public void onHoverRemove(MouseEvent event) {
        passed = checkInputs(
                add,
                event,
                id.getText().trim(),
                firstInput.getText().trim(),
                secondInput.getText().trim()
        );
        if (passed) {
            Tooltip tooltip = initToolTip(
                    switch (getPage()) {
                        case ADD_STUDENTS -> "Remove Student";
                        case ADD_BOOKS -> "Remove Book";
                        case MANAGE_STUDENTS, MANAGE_BOOKS -> "No Message";
                    },
                    event,
                    adminButtonFunctionsToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            remove.setTooltip(tooltip);
        }
    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onRemoveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        getAllStudents().clear();
        getStudentsDataSource().clear();
        table.setItems(getStudentsDataSource());
        resetInputs(
                id,
                firstInput,
                secondInput,
                choiceBox
        );
    }

    @FXML
    public void onHoverRemoveAll(MouseEvent event) {
        passed = checkInputs(
                add,
                event,
                id.getText().trim(),
                firstInput.getText().trim(),
                secondInput.getText().trim()
        );
        if (passed) {
            Tooltip tooltip = initToolTip(
                    switch (getPage()) {
                        case ADD_STUDENTS -> "Remove All Students records from the table";
                        case ADD_BOOKS -> "Remove All Books records from the table";
                        case MANAGE_STUDENTS, MANAGE_BOOKS -> "No Message";
                    },
                    event,
                    adminButtonFunctionsToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            removeAll.setTooltip(tooltip);
        }
    }

    @FXML
    public void onSaveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        saveAllStudents();
        resetInputs(
                id,
                firstInput,
                secondInput,
                choiceBox
        );
        onRemoveAll(mouseEvent);
    }

    @FXML
    public void onHoverSaveAll(MouseEvent event) {
        passed = checkInputs(
                add,
                event,
                id.getText().trim(),
                firstInput.getText().trim(),
                secondInput.getText().trim()
        );
        if (passed) {
            Tooltip tooltip = initToolTip(
                    switch (getPage()) {
                        case ADD_STUDENTS -> "Save All Students records to the database";
                        case ADD_BOOKS -> "Save All Books records to the database";
                        case MANAGE_STUDENTS, MANAGE_BOOKS -> "No Message";
                    },
                    event,
                    adminButtonFunctionsToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            saveAll.setTooltip(tooltip);
        }
    }
}
