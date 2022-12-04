package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.entity.Page.ADD_STUDENTS;
import static io.github.pitzzahh.libraryManagementSystem.entity.Page.ADD_BOOKS;
import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import java.util.Optional;
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
            if (isStudentAlreadyAdded(id.getText().trim()) && isBookAlreadyAdded(id.getText().trim())) {
                if (getPage().equals(Page.ADD_STUDENTS)) {
                    getStudentsDataSource().add(new Student(
                            id.getText().trim(),
                            firstInput.getText().trim(),
                            secondInput.getText().trim(),
                            (Course) choiceBox.getSelectionModel().getSelectedItem()
                    ));
                    initTableColumns(table, new String[]{"studentNumber", "firstName", "lastName", "course"});

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
                    initTableColumns(table, new String[] {"bookId", "title", "author", "category"});

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
                String message = getPage().equals(ADD_STUDENTS) ? "Cannot add student, Student with student id already added" :
                        getPage().equals(ADD_BOOKS) ? "Cannot add book, Book with book id already added" : "No Message";
                Tooltip tooltip  = initToolTip(message, null, errorToolTipStyle(), id);
                id.setTooltip(tooltip);
                String window = getPage().equals(ADD_STUDENTS) ? "add_students_window" :
                        getPage().equals(ADD_BOOKS) ? "add_books_window" : "no window";
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
            String message = getPage().equals(ADD_STUDENTS) ? "Add Student" :
                    getPage().equals(ADD_BOOKS) ? "Add Book" : "No Message";
            onHoverButtons(message, event, add);
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
        Optional<Object> optional = Optional.ofNullable(table.getSelectionModel().getSelectedItem());
        optional.ifPresent(item -> {
            if (getPage().equals(ADD_STUDENTS)) {
                getStudentsDataSource().remove(item);
                table.setItems(getStudentsDataSource());
            } else if (getPage().equals(ADD_BOOKS)) {
                getBooksDataSource().remove(item);
                table.setItems(getBooksDataSource());
            }
        });
    }

    @FXML
    public void onHoverRemove(MouseEvent event) {
        String message = getPage().equals(ADD_STUDENTS) ? "Remove Student" :
                getPage().equals(ADD_BOOKS) ? "Remove Book" : "No Message";
        onHoverButtons(message, event, remove);

    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onRemoveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        if (getPage().equals(ADD_STUDENTS)) {
            getAllStudents().clear();
            getStudentsDataSource().clear();
            table.setItems(getStudentsDataSource());
        } else if (getPage().equals(ADD_BOOKS)) {
            getAllBooks().clear();
            getBooksDataSource().clear();
            table.setItems(getBooksDataSource());
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
        String message = getPage().equals(ADD_STUDENTS) ? "Remove All Students records from the table" :
                getPage().equals(ADD_BOOKS) ? "Remove All Books records from the table" : "No Message";
        onHoverButtons(message, event, removeAll);
    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onSaveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        if (getPage().equals(ADD_STUDENTS)) {
            saveAllStudents();
            getStudentsDataSource().clear();
            table.setItems(getStudentsDataSource());
        } else if (getPage().equals(ADD_BOOKS)) {
            saveAllBooks();
            getBooksDataSource().clear();
            table.setItems(getBooksDataSource());
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
        String message = getPage().equals(ADD_STUDENTS) ? "Save All Students records to the database" :
                getPage().equals(ADD_BOOKS) ? "Save All Books records to the database" : "No Message";
        onHoverButtons(message, event, saveAll);
    }
}
