package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.entity.Course;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.fxml.FXML;

public class AddStudentsController {

    @FXML
    public TextField studentId;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public ChoiceBox<Course> course;

    @FXML
    private Button addStudentTable;

    @FXML
    private Button removeStudentsTable;

    @FXML
    private Button removeAllStudentsTable;

    @FXML
    private Button saveALlStudentsTable;

    @FXML
    public TableView<Student> studentTable;

    private boolean passed;

    @FXML
    public void onAddStudent(MouseEvent event) {
        event.consume();
        if (passed) {
            if (!isStudentAlreadyAdded(studentId.getText().trim())) {
                getStudentsDataSource().add(new Student(
                        studentId.getText().trim(),
                        firstName.getText().trim(),
                        lastName.getText().trim(),
                        course.getSelectionModel().getSelectedItem()
                ));

                TableColumn<Student, ?> studentNumberColumn = studentTable.getColumns().get(0);
                studentNumberColumn.setStyle("-fx-alignment: CENTER;");
                studentNumberColumn.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));

                studentTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));

                studentTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastName"));

                TableColumn<Student, ?> studentCourseColumn = studentTable.getColumns().get(3);
                studentCourseColumn.setStyle("-fx-alignment: CENTER;");
                studentCourseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

                studentTable.setItems(getStudentsDataSource());
                resetAddStudentFields(
                        studentId,
                        firstName,
                        lastName,
                        course
                );
            }
            else {
                Tooltip tooltip  = new Tooltip("Cannot add student, Student with student number already added");
                tooltip.setStyle(errorToolTipStyle());
                tooltip.setAutoHide(true);
                tooltip.setShowDuration(Duration.seconds(3));
                studentId.setTooltip(tooltip);
                studentId.getTooltip().show(getParent("add_students_window").getScene().getWindow());
            }
        }
    }

    @FXML
    public void onHoverAddStudent(MouseEvent event) {
        passed = checkInputs(
                addStudentTable,
                event,
                studentId.getText().trim(),
                firstName.getText().trim(),
                lastName.getText().trim()
        );
        if (passed) {
            Tooltip tooltip = initToolTip(
                    "Add Student",
                    event,
                    adminButtonFunctionsToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            addStudentTable.setTooltip(tooltip);
        }
    }

    /**
     * Removes a student record from the table
     * @param mouseEvent the mouse event
     */
    @FXML
    public void onRemoveStudent(MouseEvent mouseEvent) {
        mouseEvent.consume();
        getStudentsDataSource().remove(studentTable.getSelectionModel().getSelectedItem());
        studentTable.setItems(getStudentsDataSource());
    }

    @FXML
    public void onHoverRemoveStudent(MouseEvent event) {
        passed = checkInputs(
                addStudentTable,
                event,
                studentId.getText().trim(),
                firstName.getText().trim(),
                lastName.getText().trim()
        );
        if (passed) {
            Tooltip tooltip = initToolTip(
                    "Remove Student",
                    event,
                    adminButtonFunctionsToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            removeStudentsTable.setTooltip(tooltip);
        }
    }

    @FXML
    public void onRemoveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        getAllStudents().clear();
        getStudentsDataSource().clear();
        studentTable.setItems(getStudentsDataSource());
        resetAddStudentFields(
                studentId,
                firstName,
                lastName,
                course
        );
    }

    @FXML
    public void onHoverRemoveAll(MouseEvent event) {
        passed = checkInputs(
                addStudentTable,
                event,
                studentId.getText().trim(),
                firstName.getText().trim(),
                lastName.getText().trim()
        );
        if (passed) {
            Tooltip tooltip = initToolTip(
                    "Remove All Students records from the table",
                    event,
                    adminButtonFunctionsToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            removeAllStudentsTable.setTooltip(tooltip);
        }
    }

    @FXML
    public void onSaveAll(MouseEvent mouseEvent) {
        mouseEvent.consume();
        saveAllStudents();
        resetAddStudentFields(
                studentId,
                firstName,
                lastName,
                course
        );
        onRemoveAll(mouseEvent);
    }

    @FXML
    public void onHoverSaveAll(MouseEvent event) {
        passed = checkInputs(
                addStudentTable,
                event,
                studentId.getText().trim(),
                firstName.getText().trim(),
                lastName.getText().trim()
        );
        if (passed) {
            Tooltip tooltip = initToolTip(
                    "Save all students records from the table to the database",
                    event,
                    adminButtonFunctionsToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            saveALlStudentsTable.setTooltip(tooltip);
        }
    }

}
