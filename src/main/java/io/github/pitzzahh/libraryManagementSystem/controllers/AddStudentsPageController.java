package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getLogger;
import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.entity.Course;
import io.github.pitzzahh.util.utilities.Print;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
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
        studentTable.setItems(dataSource);
        getLogger().debug("Added student to table");
        getLogger().debug("Student table size: " + studentTable.getItems().size());
        studentTable.getItems().forEach(Print::println);
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
