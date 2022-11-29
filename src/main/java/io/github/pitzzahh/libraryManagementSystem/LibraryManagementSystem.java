package io.github.pitzzahh.libraryManagementSystem;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import static java.util.Objects.requireNonNull;

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
        mainProgressBar
                .ifPresent(pb -> {
                    pb.setVisible(false);
                    pb.setStyle("-fx-accent: cyan;");
                });
        getStage().setResizable(false);
        getStage().initStyle(StageStyle.DECORATED);
        getStage().getIcons().add(new Image(requireNonNull(LibraryManagementSystem.class.getResourceAsStream("img/adminPage/logo_100px.png"), "logo not found")));
        moveWindow(parent);
        getStage().setScene(scene);
        getStage().centerOnScreen();
        getStage().toFront();
        getStage().setTitle("Library Management System");
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
        var addStudentsPage = (Parent) FXMLLoader.load(requireNonNull(LibraryManagementSystem.class.getResource("fxml/admin/addStudents/addStudentsPage.fxml"), "Cannot find addStudentsPage.fxml"));
        adminPage.setId("admin_window");
        mainPage.setId("main_window");
        addStudentsPage.setId("add_students_window");
        addParents.accept(new Parent[] {
                mainPage,
                adminPage,
                addStudentsPage
        });
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
