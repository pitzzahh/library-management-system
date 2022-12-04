package io.github.pitzzahh.libraryManagementSystem;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Course;
import io.github.pitzzahh.libraryManagementSystem.util.Util;
import static java.util.Objects.requireNonNull;
import javafx.scene.control.ProgressBar;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.control.ChoiceBox;
import java.util.stream.Collectors;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;

public class LibraryManagementSystem extends Application {

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
        Parent parent = getParent("main_window");
        Scene scene = new Scene(parent);
        LibraryManagementSystem.stage = primaryStage;
        Optional<ProgressBar> mainProgressBar = getMainProgressBar(parent);
        mainProgressBar.ifPresent(p -> p.setVisible(false));
        getStage().initStyle(StageStyle.DECORATED);
        getStage().getIcons().add(new Image(requireNonNull(getClass().getResourceAsStream("img/logo.png"), "logo not found")));
        getStage().addEventHandler(KeyEvent.KEY_PRESSED, getToggleFullScreenEvent());
        getStage().setScene(scene);
        getStage().centerOnScreen();
        getStage().toFront();
        getStage().setTitle("Library Management System");

        getChoiceBox(getParent("add_students_window"), "choiceBox")
                .map(e -> (ChoiceBox<Course>) e)
                .ifPresentOrElse(e -> {
                    e.getItems().addAll(FXCollections.observableArrayList(Arrays.stream(Course.values()).collect(Collectors.toList())));
                    e.getSelectionModel().selectFirst();
                    }, () -> System.out.println("Course choice box not found")
                );

        getChoiceBox(getParent("add_books_window"), "choiceBox")
                .map(e -> (ChoiceBox<Object>) e)
                .ifPresent(LibraryManagementSystem::initCategory);

        getChoiceBox(getParent("borrow_books_window"), "choiceBox")
                .map(e -> (ChoiceBox<Object>) e)
                .ifPresent(LibraryManagementSystem::initCategory);

        getStage().show();
        System.out.println("Application started");
    }

    private static void initCategory(ChoiceBox<Object> borrowBookChoiceBox) {
        borrowBookChoiceBox.getItems().addAll(FXCollections.observableArrayList(Arrays.stream(Category.values()).collect(Collectors.toList())));
        borrowBookChoiceBox.getSelectionModel().selectFirst();
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
        Parent mainPage = FXMLLoader.load(requireNonNull(getClass().getResource("fxml/mainPage.fxml"), "Cannot find mainPage.fxml"));
        Parent adminPage = FXMLLoader.load(requireNonNull(getClass().getResource("fxml/admin/adminPage.fxml"), "Cannot find adminPage.fxml"));
        Parent addStudentsPage = FXMLLoader.load(requireNonNull(getClass().getResource("fxml/admin/addStudents/addStudents.fxml"), "Cannot find addStudents.fxml"));
        Parent addBooksPage = FXMLLoader.load(requireNonNull(getClass().getResource("fxml/admin/addBooks/addBooks.fxml"), "Cannot find addBooks.fxml"));
        Parent studentPage = FXMLLoader.load(requireNonNull(getClass().getResource("fxml/student/studentPage.fxml"), "Cannot find studentPage.fxml"));
        Parent borrowBookPage = FXMLLoader.load(requireNonNull(getClass().getResource("fxml/student/borrowBook/borrowBook.fxml"), "Cannot find borrowBook.fxml"));
        Parent listOfBorrowedBooksPage = FXMLLoader.load(requireNonNull(getClass().getResource("fxml/student/viewList/listOfBorrowedBooks.fxml"), "Cannot find listOfBorrowedBooks.fxml"));
        Parent promptPage = FXMLLoader.load(requireNonNull(getClass().getResource("fxml/prompt.fxml"), "Cannot find prompt.fxml"));

        adminPage.setId("admin_window");
        mainPage.setId("main_window");
        addStudentsPage.setId("add_students_window");
        addBooksPage.setId("add_books_window");
        studentPage.setId("student_window");
        borrowBookPage.setId("borrow_books_window");
        listOfBorrowedBooksPage.setId("list_of_borrowed_books_window");
        promptPage.setId("promptPage_page");

        addParents.accept(List.of
                (
                        mainPage,
                        adminPage,
                        addStudentsPage,
                        addBooksPage,
                        studentPage,
                        borrowBookPage,
                        listOfBorrowedBooksPage,
                        promptPage
                )
        );
    }

    /**
     * Gets the stage for atm.
     * @return the stage.
     */
    public static Stage getStage() {
        return stage;
    }
}
