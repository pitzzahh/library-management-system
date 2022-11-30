package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.entity.Course;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
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

    // TODO: move to Util interface
    private final ObservableList<Student> dataSource = FXCollections.observableArrayList();

    private boolean passed;

    @FXML
    public void onAddStudent(MouseEvent event) {
        if (passed) {
            if (!isStudentAlreadyAdded(studentTable, studentId.getText().trim())) {
                dataSource.add(new Student(
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

                studentTable.setItems(dataSource);
            }
            else {
                Tooltip tooltip = initToolTip(
                        "Cannot add student, Student with student number already added",
                        event,
                        adminButtonFunctionsToolTipStyle()
                );
                tooltip.setAutoHide(true);
                tooltip.setShowDuration(Duration.seconds(3));
                addStudentTable.setTooltip(tooltip);
                addStudentTable.getTooltip().show(getParent("add_students_window").getScene().getWindow());
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
