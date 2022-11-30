package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getLogger;
import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.entity.Course;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.fxml.FXML;

public class AddStudentsPageController {

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

    @FXML
    public void onAddStudent(ActionEvent actionEvent) {
        actionEvent.consume();
        dataSource.add(new Student(generateRandomAccountNumber(), "John", "Doe", Course.BSIT));
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
}
