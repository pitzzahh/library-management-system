package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.*;
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
    @SuppressWarnings({"unchecked"})
    public void onAdd(MouseEvent event) {
        event.consume();
        System.out.printf("Passed: %s%n", passed);
        if (passed) {
            if (!isStudentAlreadyAdded(id.getText().trim())) {
                if (getPage().equals(Page.ADD_STUDENTS)) {
                    getStudentsDataSource().add(new Student(
                            id.getText().trim(),
                            firstInput.getText().trim(),
                            secondInput.getText().trim(),
                            (Course) choiceBox.getSelectionModel().getSelectedItem()
                    ));
                    initTableColumns(
                            table,
                            "studentNumber",
                            "firstName",
                            "lastName",
                            "course"
                    );

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
                    initTableColumns(
                            table,
                            "bookId",
                            "title",
                            "author",
                            "category"
                    );

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
                    default -> "No Message";
                };
                Tooltip tooltip  = new Tooltip(message);
                tooltip.setStyle(errorToolTipStyle());
                tooltip.setAutoHide(true);
                tooltip.setShowDuration(Duration.seconds(3));
                id.setTooltip(tooltip);
                String window = switch (getPage()) {
                    case ADD_STUDENTS -> "add_students_window";
                    case ADD_BOOKS -> "add_books_window";
                    default -> "No Message";
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
            onHoverButtons(switch (getPage()) {
                case ADD_STUDENTS -> "Add Student";
                case ADD_BOOKS -> "Add Book";
                default -> "No Message";
            }, event, add);
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
        switch (getPage()) {
            case ADD_STUDENTS -> {
                getStudentsDataSource().remove(table.getSelectionModel().getSelectedItem());
                table.setItems(getStudentsDataSource());
            }
            case ADD_BOOKS -> {
                getBooksDataSource().remove(table.getSelectionModel().getSelectedItem());
                table.setItems(getBooksDataSource());
            }
        }
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
            onHoverButtons(switch (getPage()) {
                case ADD_STUDENTS -> "Remove Student";
                case ADD_BOOKS -> "Remove Book";
                default -> "No Message";
            }, event, remove);
        }
    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onRemoveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        switch (getPage()) {
            case ADD_STUDENTS -> {
                getAllStudents().clear();
                getStudentsDataSource().clear();
                table.setItems(getStudentsDataSource());
            }
            case ADD_BOOKS -> {
                getAllBooks().clear();
                getBooksDataSource().clear();
                table.setItems(getBooksDataSource());
            }
        }
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
            onHoverButtons(switch (getPage()) {
                case ADD_STUDENTS -> "Remove All Students records from the table";
                case ADD_BOOKS -> "Remove All Books records from the table";
                default -> "No Message";
            }, event, removeAll);
        }
    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onSaveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        switch (getPage()) {
            case ADD_STUDENTS -> {
                saveAllStudents();
                getStudentsDataSource().clear();
                table.setItems(getStudentsDataSource());
            }
            case ADD_BOOKS -> {
                saveAllBooks();
                getBooksDataSource().clear();
                table.setItems(getBooksDataSource());
            }
        }
        resetInputs(
                id,
                firstInput,
                secondInput,
                choiceBox
        );
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
            onHoverButtons(switch (getPage()) {
                case ADD_STUDENTS -> "Save All Students records to the database";
                case ADD_BOOKS -> "Save All Books records to the database";
                default -> "No Message";
            }, event, saveAll);
        }
    }
}
