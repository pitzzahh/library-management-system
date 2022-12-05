package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.entity.Page.ADD_STUDENTS;
import static io.github.pitzzahh.libraryManagementSystem.entity.Page.ADD_BOOKS;
import io.github.pitzzahh.libraryManagementSystem.validator.Validator;
import io.github.pitzzahh.libraryManagementSystem.entity.*;
import io.github.pitzzahh.libraryManagementSystem.util.*;
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
            if (Validator.isStudentAlreadyAdded(id.getText().trim()) && Validator.isBookAlreadyAdded(id.getText().trim())) {
                if (WindowUtil.getPage().equals(Page.ADD_STUDENTS)) {
                    DataUtil.getStudentsDataSource().add(new Student(
                            id.getText().trim(),
                            firstInput.getText().trim(),
                            secondInput.getText().trim(),
                            (Course) choiceBox.getSelectionModel().getSelectedItem()
                    ));
                    ComponentUtil.initTableColumns(table, new String[]{"studentNumber", "firstName", "lastName", "course"});

                    table.setItems(DataUtil.getStudentsDataSource());
                    TextFieldUtil.resetInputs(
                            id,
                            firstInput,
                            secondInput,
                            choiceBox
                    );
                }
                else if (WindowUtil.getPage().equals(Page.ADD_BOOKS)){
                    DataUtil.getBooksDataSource().add(new Book(
                            id.getText().trim(),
                            firstInput.getText().trim(),
                            secondInput.getText().trim(),
                            (Category) choiceBox.getSelectionModel().getSelectedItem(),
                            null,
                            null,
                            null
                    ));
                    ComponentUtil.initTableColumns(table, new String[] {"bookId", "title", "author", "category"});

                    table.setItems(DataUtil.getBooksDataSource());
                    TextFieldUtil.resetInputs(
                            id,
                            firstInput,
                            secondInput,
                            choiceBox
                    );
                }

            }
            else {
                String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Cannot add student, Student with student id already added" :
                        WindowUtil.getPage().equals(ADD_BOOKS) ? "Cannot add book, Book with book id already added" : "No Message";
                Tooltip tooltip  = ToolTipUtil.initToolTip(message, null, Style.errorToolTipStyle(), id);
                id.setTooltip(tooltip);
                String window = WindowUtil.getPage().equals(ADD_STUDENTS) ? "add_students_window" :
                        WindowUtil.getPage().equals(ADD_BOOKS) ? "add_books_window" : "no window";
                id.getTooltip().show(WindowUtil.getParent(window).getScene().getWindow());
            }
        }
    }

    @FXML
    public void onHoverAdd(MouseEvent event) {
        passed = TextFieldUtil.checkInputs(
                add,
                event,
                id.getText().trim(),
                firstInput.getText().trim(),
                secondInput.getText().trim()
        );
        if (passed) {
            String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Add Student" :
                    WindowUtil.getPage().equals(ADD_BOOKS) ? "Add Book" : "No Message";
            ToolTipUtil.onHoverButtons(message, event, add);
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
            if (WindowUtil.getPage().equals(ADD_STUDENTS)) {
                DataUtil.getStudentsDataSource().remove(item);
                table.setItems(DataUtil.getStudentsDataSource());
            } else if (WindowUtil.getPage().equals(ADD_BOOKS)) {
                DataUtil.getBooksDataSource().remove(item);
                table.setItems(DataUtil.getBooksDataSource());
            }
        });
    }

    @FXML
    public void onHoverRemove(MouseEvent event) {
        String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Remove Student" :
                WindowUtil.getPage().equals(ADD_BOOKS) ? "Remove Book" : "No Message";
        ToolTipUtil.onHoverButtons(message, event, remove);

    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onRemoveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        if (WindowUtil.getPage().equals(ADD_STUDENTS)) {
            DataUtil.getAllStudents().clear();
            DataUtil.getStudentsDataSource().clear();
            table.setItems(DataUtil.getStudentsDataSource());
        } else if (WindowUtil.getPage().equals(ADD_BOOKS)) {
            DataUtil.getAllBooks().clear();
            DataUtil.getBooksDataSource().clear();
            table.setItems(DataUtil.getBooksDataSource());
        }
        TextFieldUtil.resetInputs(
                id,
                firstInput,
                secondInput,
                choiceBox
        );
    }

    @FXML
    public void onHoverRemoveAll(MouseEvent event) {
        String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Remove All Students records from the table" :
                WindowUtil.getPage().equals(ADD_BOOKS) ? "Remove All Books records from the table" : "No Message";
        ToolTipUtil.onHoverButtons(message, event, removeAll);
    }

    @FXML
    @SuppressWarnings({"unchecked"})
    public void onSaveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        if (WindowUtil.getPage().equals(ADD_STUDENTS)) {
            DataUtil.saveAllStudents();
            DataUtil.getStudentsDataSource().clear();
            table.setItems(DataUtil.getStudentsDataSource());
        } else if (WindowUtil.getPage().equals(ADD_BOOKS)) {
            DataUtil.saveAllBooks();
            DataUtil.getBooksDataSource().clear();
            table.setItems(DataUtil.getBooksDataSource());
        }
        TextFieldUtil.resetInputs(
                id,
                firstInput,
                secondInput,
                choiceBox
        );
    }

    @FXML
    public void onHoverSaveAll(MouseEvent event) {
        String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Save All Students records to the database" :
                WindowUtil.getPage().equals(ADD_BOOKS) ? "Save All Books records to the database" : "No Message";
        ToolTipUtil.onHoverButtons(message, event, saveAll);
    }
}
