package io.github.pitzzahh.libraryManagementSystem.util;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getStage;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.entity.Page;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import io.github.pitzzahh.util.utilities.classes.DynamicArray;
import io.github.pitzzahh.util.utilities.SecurityUtil;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.effect.GaussianBlur;
import static java.lang.String.format;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import java.util.function.Consumer;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.scene.Parent;
import java.util.*;

/**
 * Utility interface for the ATM application.
 */
public interface Util {

    /**
     * admin credentials.
     */
    String $admin = SecurityUtil.decrypt("QGRtMW4xJHRyNHQwcg==");
    int MAX_LENGTH = 10;
    /**
     * Add a list parent to the parents array.
     * takes an array of parents
     */
    Consumer<Parent[]> addParents = Fields.parents::insert;

    /**
     * Gets the parent with the specified id.
     * @param id the id of the parent.
     * @return the parent with the specified id.
     */
    static Parent getParent(String id) {
        return Fields.parents.stream()
                .filter(parent -> parent.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(format("Cannot find parent with [%s] id", id)));
    }

    /**
     * Sets the tooltip for the specified control.
     * @param tip the tooltip.
     * @param event the mouse event.
     * @return the tooltip.
     * @see Tooltip
     */
    static Tooltip initToolTip(String tip, MouseEvent event, String styles) {
        var toolTip = new Tooltip(tip);
        toolTip.setX(event.getScreenX());
        toolTip.setY(event.getScreenY());
        toolTip.setStyle(styles);
        return toolTip;
    }

    /**
     * Gets the button styles for admin window
     * @return styles for admin window buttons.
     */
    static String adminButtonFunctionsToolTipStyle() {
        return "-fx-background-color: #003049; " +
               "-fx-text-fill: white; " +
               "-fx-font-weight: bold; " +
               "-fx-font-family: Jetbrains Mono;" +
               "-fx-font-size: 15px;";
    }

    static String errorToolTipStyle() {
        return "-fx-background-color: #CFD7DF; " +
                "-fx-text-fill: #D50000; " +
                "-fx-font-weight: bold; " +
                "-fx-font-family: Jetbrains Mono;" +
                "-fx-font-size: 15px;";
    }

    /**
     * Adds a limit to the specified text field.
     * @param textField the text field to add the limit.
     * @param maxLength the maximum length of the text field.
     */
    static void addTextLimiter(final TextField textField, final int maxLength) {
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if ((textField.getText().length() > maxLength)) {
                var limitedInput = textField.getText().substring(0, maxLength);
                if (!limitedInput.equals($admin.substring(0, maxLength))) textField.setText(limitedInput);
            }
        });
    }

    /**
     * Sets the center border pane to the specific window.
     * @param actionEvent the action event.
     * @param id the id of the window.
     */
    static void setCenterScreenOfBorderPane(ActionEvent actionEvent, String id) {
        final var BORDER_PANE = ((BorderPane)(((Button) actionEvent.getSource()).getParent().getParent()));
        BORDER_PANE.setCenter(Util.getParent(id));
    }

    /**
     * Used to modify the progress bar from the main window.
     * @param parent the main window parent.
     * @return an {@code Optional<ProgressBar>}.
     */
    static Optional<ProgressBar> getMainProgressBar(Parent parent) {
        return Optional.ofNullable((ProgressBar) parent.lookup("#progressBar"));
    }

    /**
     * Used to modify the message label from the main window.
     * @param parent the main window parent.
     * @return an {@code Optional<Label>}.
     */
    //TODO: fix bug
    static Optional<Label> getMessageLabel(Parent parent) {
        return Optional.ofNullable((Label) parent.lookup("#message"));
    }

    static void addActiveButtons(Button button) {
        boolean anyMatch = Fields.activeButtons.stream()
                .anyMatch(b -> b.getId().equals(button.getId()));
        if (!anyMatch) Fields.activeButtons.offer(button);
    }

    static Optional<Button> getActiveButton(String id) {
        return Fields.activeButtons.stream()
                .filter(button -> button.getId().equals(id))
                .findAny();
    }


    static boolean checkInputs(
            Button button,
            MouseEvent event,
            String id,
            String firstInput,
            String secondInput
    ) {
        Optional<Tooltip> tooltip1 = Optional.ofNullable(button.getTooltip());
        tooltip1.ifPresent(t -> button.setTooltip(null));
        if (id.isEmpty() && firstInput.isEmpty() && secondInput.isEmpty()) {
            Tooltip tooltip = initToolTip(
                    switch (getPage()) {
                        case ADD_STUDENTS -> "Cannot Add Student, All Required Input are empty";
                        case ADD_BOOKS -> "Cannot Add Books, All Required Input are empty";
                        default -> "Please fill in all the fields";
                    },
                    event,
                    errorToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            button.setTooltip(tooltip);
            return false;
        }
        else if (id.isEmpty() && (firstInput.isEmpty() || secondInput.isEmpty())) {
            Tooltip tooltip = initToolTip(
                    switch (getPage()) {
                        case ADD_STUDENTS -> "Cannot Add Student, All Required Input are empty";
                        case ADD_BOOKS -> "Cannot Add Book, Book Id is empty";
                        default -> "Please fill in book Id";
                    },
                    event,
                    errorToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            button.setTooltip(tooltip);
            return false;
        }
        else if (firstInput.isEmpty() && secondInput.isEmpty()) {
            Tooltip tooltip = initToolTip(
                    switch (getPage()) {
                        case ADD_STUDENTS -> "Cannot Add Student, First Name is empty";
                        case ADD_BOOKS -> "Cannot Add Book, Book Title is empty";
                        default -> "Please fill in the blank";
                    },
                    event,
                    errorToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            button.setTooltip(tooltip);
            return false;
        }
        else if (secondInput.isEmpty()) {
            Tooltip tooltip = initToolTip(
                    switch (getPage()) {
                        case ADD_STUDENTS -> "Cannot Add Student, Last Name is empty";
                        case ADD_BOOKS -> "Cannot Add Book, Book Author is empty";
                        default -> "Please fill in the blank";
                    },
                    event,
                    errorToolTipStyle()
            );
            tooltip.setShowDuration(Duration.seconds(3));
            button.setTooltip(tooltip);
            return false;
        }
        return true;
    }

    static String generateRandomAccountNumber() {
        return String.valueOf(new Random().nextInt(999999999) + 1);
    }

    /**
     * Get the event handler for toggling full screen
     * @return a {@code EventHandler<KeyEvent>}
     */
    static EventHandler<KeyEvent> getToggleFullScreenEvent() {
        return Fields.eventHandler;
    }

    static void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisible(false);
        progressBar.setStyle("-fx-accent: cyan;");
    }

    // TODO: refactor, use parent lookup
    @SuppressWarnings("unchecked")
    static Optional<ChoiceBox<Object>> getChoiceBox(Parent parent, String name) {
        return Optional.ofNullable((ChoiceBox<Object>) parent.lookup(format("#%s", name)));
    }

    static boolean isStudentAlreadyAdded(String studentNumber) {
        return getStudentsDataSource()
                .stream()
                .anyMatch(e -> e.getStudentNumber().equals(studentNumber));
    }

    static ObservableList<Student> getStudentsDataSource() {
        return Fields.studentsDataSource;
    }

    static ObservableList<Book> getBooksDataSource() {
        return Fields.booksDataSource;
    }

    /**
     * Used to create a gaussian blur effect.
     * @param radius the radius of the blur.
     * @return a {@code GaussianBlur}.
     */
    static GaussianBlur gaussianBlur(double radius) {
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(radius);
        return gaussianBlur;
    }

    static void resetInputs(
            TextField id,
            TextField firstInput,
            TextField secondInput,
            ChoiceBox<?> course
    ) {
        id.clear();
        firstInput.clear();
        secondInput.clear();
        course.getSelectionModel().selectFirst();
    }

    static void saveAllStudents() {
        Fields.studentsList = new ArrayList<>(getStudentsDataSource());
    }

    static List<Student> getAllStudents() {
        return Fields.studentsList;
    }

    static void saveAllBooks() {
        Fields.booksList = new ArrayList<>(getBooksDataSource());
    }

    static List<Book> getAllBooks() {
        return Fields.booksDataSource;
    }

    static void setPage(Page page) {
        Fields.page = page;
    }

    static Page getPage() {
        return Fields.page;
    }

}

/**
 * Fields for the Util interface.
 */
class Fields {
    /**
     * The parents array.
     */
    static DynamicArray<Parent> parents = new DynamicArray<>();
    static Queue<Button> activeButtons = new LinkedList<>();
    static ObservableList<Student> studentsDataSource = FXCollections.observableArrayList();
    static ObservableList<Book> booksDataSource = FXCollections.observableArrayList();

    // TODO: explicitly move to Util interface as a method
    static EventHandler<KeyEvent> eventHandler = event -> {
        if (KeyCode.F11.equals(event.getCode())) getStage().setFullScreen(!getStage().isFullScreen());
    };

    static List<Student> studentsList = Collections.emptyList();
    static List<Book> booksList = Collections.emptyList();
    static Page page;

}
