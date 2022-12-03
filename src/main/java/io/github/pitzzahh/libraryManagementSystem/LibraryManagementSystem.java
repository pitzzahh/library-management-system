package io.github.pitzzahh.libraryManagementSystem;


import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Course;
import io.github.pitzzahh.libraryManagementSystem.util.Util;
import static java.util.Objects.requireNonNull;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.control.ChoiceBox;

import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import org.slf4j.LoggerFactory;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Arrays;
import org.slf4j.Logger;

public class LibraryManagementSystem extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryManagementSystem.class);
    private static Stage stage;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        initParents();
        var parent = getParent("main_window");
        var scene = new Scene(parent);
        LibraryManagementSystem.stage = primaryStage;
        var mainProgressBar = getMainProgressBar(parent);
        mainProgressBar.ifPresent(Util::hideProgressBar);
        getStage().initStyle(StageStyle.DECORATED);
        getStage().getIcons().add(new Image(requireNonNull(LibraryManagementSystem.class.getResourceAsStream("img/logo.png"), "logo not found")));
        getStage().setScene(scene);
        getStage().centerOnScreen();
        getStage().toFront();
        getStage().setTitle("Library Management System");

        Optional<ChoiceBox<Object>> courseChoiceBox = getChoiceBox(getParent("add_students_window"), "choiceBox");

        courseChoiceBox.ifPresentOrElse(e -> {
            e.getItems().addAll(FXCollections.observableArrayList(Arrays.stream(Course.values()).collect(Collectors.toList())));
            e.getSelectionModel().selectFirst();
        }, () -> LOGGER.error("Course choice box not found")
        );

        Optional<ChoiceBox<Object>> booksChoiceBox = getChoiceBox(getParent("add_books_window"), "choiceBox");

        booksChoiceBox.ifPresentOrElse(e -> {
            e.getItems().addAll(FXCollections.observableArrayList(Arrays.stream(Category.values()).collect(Collectors.toList())));
            e.getSelectionModel().selectFirst();
        }, () -> LOGGER.error("Category choice box not found")
        );

        getStage().show();
        LOGGER.info("Application started");
    }

    /**
     * main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Initializes the parents.
     * The window is loaded from the FXML file.
     * @throws IOException if the parent cannot be loaded.
     */
    private void initParents() throws IOException {
        var mainPage = (Parent) FXMLLoader.load(requireNonNull(LibraryManagementSystem.class.getResource("fxml/mainPage.fxml"), "Cannot find mainPage.fxml"));
        var adminPage = (Parent) FXMLLoader.load(requireNonNull(LibraryManagementSystem.class.getResource("fxml/admin/adminPage.fxml"), "Cannot find adminPage.fxml"));
        var addStudentsPage = (Parent) FXMLLoader.load(requireNonNull(LibraryManagementSystem.class.getResource("fxml/admin/addStudents/addStudents.fxml"), "Cannot find addStudents.fxml"));
        var addBooksPage = (Parent) FXMLLoader.load(requireNonNull(LibraryManagementSystem.class.getResource("fxml/admin/addBooks/addBooks.fxml"), "Cannot find addBooks.fxml"));
        var studentPage = (Parent) FXMLLoader.load(requireNonNull(LibraryManagementSystem.class.getResource("fxml/student/studentPage.fxml"), "Cannot find studentPage.fxml"));
        adminPage.setId("admin_window");
        mainPage.setId("main_window");
        addStudentsPage.setId("add_students_window");
        addBooksPage.setId("add_books_window");
        studentPage.setId("student_window");
        addParents.accept(List.of
                (
                mainPage,
                adminPage,
                addStudentsPage,
                addBooksPage,
                studentPage
                )
        );
    }

    /**
     * Gets the logger for atm.
     * @return the logger.
     */
    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Gets the stage for atm.
     * @return the stage.
     */
    public static Stage getStage() {
        return stage;
    }
}
