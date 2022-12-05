package io.github.pitzzahh.libraryManagementSystem.util;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getStage;
import static io.github.pitzzahh.libraryManagementSystem.entity.Page.ADD_STUDENTS;
import static io.github.pitzzahh.libraryManagementSystem.entity.Page.ADD_BOOKS;
import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.entity.Page;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.effect.GaussianBlur;
import static java.lang.String.format;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.*;

/**
 * Utility interface for the ATM application.
 */
public interface Util {

    /**
     * admin credentials.
     */
    String $admin = decrypt("QGRtMW4xJHRyNHQwcg==");
    int MAX_LENGTH = 10;
    /**
     * Add a list parent to the parents array.
     * takes an array of parents
     */
    Consumer<List<Parent>> addParents = Fields.parents::addAll;

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
    static Tooltip initToolTip(String tip, MouseEvent event, String styles, Control control) {
        Tooltip toolTip = new Tooltip(tip);
        if (event != null) {
            toolTip.setX(event.getScreenX());
            toolTip.setY(event.getScreenY());
        }
        if (control != null) {
            toolTip.setX(control.getBoundsInParent().getMinX());
            toolTip.setY(control.getBoundsInParent().getMinX());
        }
        toolTip.setStyle(styles);
        toolTip.setAutoHide(true);
        toolTip.setShowDuration(Duration.seconds(3));
        return toolTip;
    }

    /**
     * Gets the button styles for admin window
     * @return styles for admin window buttons.
     */
    static String leftButtonSelectionFunctionStyle() {
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
                String limitedInput = textField.getText().substring(0, maxLength);
                if (!limitedInput.equals($admin.substring(0, maxLength))) textField.setText(limitedInput);
            }
        });
    }

    /**
     * Sets the center border pane to the specific window.
     * @param actionEvent the action event.
     * @param id the id of the window.
     */
    static void loadPage(ActionEvent actionEvent, String id) {
        ((BorderPane)(((Button) actionEvent.getSource()).getParent().getParent()))
                .setCenter(getParent(id));
    }

    static void loadParent(Parent parent, String stageTitle) {
        if (parent.getScene() != null)
            getStage().setScene(parent.getScene()); // if scene is present, get it
        else getStage().setScene(new Scene(parent)); // create new scene if new login
        getStage().setTitle(stageTitle);
        getStage().centerOnScreen();
        getStage().addEventHandler(KeyEvent.KEY_PRESSED, getToggleFullScreenEvent());
        getStage().show();
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
    static Optional<Label> getLabel(Parent parent, String id) {
        return Optional.ofNullable((Label) parent.lookup(format("#%s", id)));
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
            String message = getPage().equals(ADD_STUDENTS) ? "Cannot Add Student, All Required Input are empty" :
                    getPage().equals(ADD_BOOKS) ? "Cannot Add Books, All Required Input are empty" : "Please fill in all the fields";
            checkInputsToolTip(message, event, errorToolTipStyle(), button);
            return false;
        }
        else if (id.isEmpty() && (firstInput.isEmpty() || secondInput.isEmpty())) {
            String message = getPage().equals(ADD_STUDENTS) ? "Cannot Add Student, All Required Input are empty" :
                    getPage().equals(ADD_BOOKS) ? "Cannot Add Book, Book Id is empty" : "Please fill in all the fields";
            checkInputsToolTip(message, event, errorToolTipStyle(), button);
            return false;
        }
        else if (firstInput.isEmpty() && secondInput.isEmpty()) {
            String message = getPage().equals(ADD_STUDENTS) ? "Cannot Add Student, First Name is empty" :
                    getPage().equals(ADD_BOOKS) ? "Cannot Add Book, Book Title is empty" : "Please fill in all the fields";
            checkInputsToolTip(message, event, errorToolTipStyle(), button);
            return false;
        }
        else if (secondInput.isEmpty()) {
            String message = getPage().equals(ADD_STUDENTS) ? "Cannot Add Student, Last Name is empty" :
                    getPage().equals(ADD_BOOKS) ? "Cannot Add Book, Book Author is empty" : "Please fill in all the fields";
            checkInputsToolTip(message, event, errorToolTipStyle(), button);
            return false;
        }
        return true;
    }

    private static void checkInputsToolTip(String tip, MouseEvent event, String styles, Button button) {
        Tooltip tooltip = initToolTip(
                tip,
                event,
                styles,
                null
        );
        tooltip.setShowDuration(Duration.seconds(3));
        button.setTooltip(tooltip);
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

    static Optional<ChoiceBox<?>> getChoiceBox(Parent parent, String name) {
        return Optional.ofNullable((ChoiceBox<?>) parent.lookup(format("#%s", name)));
    }

    static boolean isStudentAlreadyAdded(String studentNumber) {
        return getAllStudents()
                .stream()
                .noneMatch(e -> e.getStudentNumber().equals(studentNumber)) && getStudentsDataSource().stream()
                .noneMatch(e -> e.getStudentNumber().equals(studentNumber));
    }

    static boolean isBookAlreadyAdded(String bookId) {
        return getAllBooks()
                .stream()
                .noneMatch(e -> e.getBookId().equals(bookId)) && getBooksDataSource().stream()
                .noneMatch(e -> e.getBookId().equals(bookId));
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
        return Fields.booksList;
    }

    static void setPage(Page page) {
        Fields.page = page;
    }

    static Page getPage() {
        return Fields.page;
    }

    static void logoutSession(ActionEvent actionEvent) {
        getStage().close();
        loadPage(actionEvent, "promptPage_page");
        Parent mainWindow = getParent("main_window");
        getLabel(mainWindow, "message").ifPresent(label -> label.setText(""));
        getMainProgressBar(mainWindow).ifPresent(pb -> pb.setVisible(false));
        getStage().setTitle("Library Management System");
        getStage().centerOnScreen();
        getStage().setScene(mainWindow.getScene());
        getStage().show();
    }
    static void showToolTipOnHover(String tip, MouseEvent mouseEvent, Button logout) {
        Tooltip tooltip = initToolTip(
                tip,
                mouseEvent,
                leftButtonSelectionFunctionStyle(),
                null
        );
        tooltip.setShowDuration(Duration.seconds(3));
        logout.setTooltip(tooltip);
    }

    static void onHoverButtons(String tip, MouseEvent event, Button removeAll) {
        checkInputsToolTip(tip, event, leftButtonSelectionFunctionStyle(), removeAll);
    }

    private static String decrypt(String data) throws IllegalArgumentException {
        if (data.trim().isEmpty()) throw new IllegalArgumentException("Text to be decrypted cannot be empty");
        byte[] b = Base64.getDecoder().decode(data);
        return IntStream.range(0, b.length).map(i -> b[i]).mapToObj(Character::toString).reduce("", String::concat);
    }

    static List<Book> getBooksByCategory(Category category) {
        return getAllBooks()
                .stream()
                .filter(book -> book.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    static ObservableList<Book> getAvailableBooksDataSource() {
        return Fields.availableBooksDataSource;
    }

    static ObservableList<Book> getBorrowedBooksDataSource() {
        return Fields.borrowBooksDataSource;
    }

    static void saveAllBorrowedBooks() {
        Fields.borowedBooksList = new ArrayList<>(getBorrowedBooksDataSource());
    }

    static List<Book> getAllBorrowedBooks() {
        return Fields.borowedBooksList;
    }

    static void initTableColumns(TableView<?> table, String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            TableColumn<?, ?> column = table.getColumns().get(i);
            if (i == 0 || i == 3) column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(columns[i]));
        }
    }

    /**
     * Used to get a table from a parent node.
     * @param parent the parent node.
     * @param tableId the id of the table.
     * @return an {@code Optional<TableView<?>>}.
     */
    @SuppressWarnings("rawtypes")
    static Optional<TableView> getTable(String parentId, String tableId) {
        return Optional.ofNullable((TableView) getParent(parentId).lookup(format("#%s", tableId)));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    static void setAvailableBooksData(TableView tableView, ChoiceBox<Category> objectChoiceBox) {
        getAvailableBooksDataSource().clear();
        Category selectedItem = objectChoiceBox.getSelectionModel().getSelectedItem();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        getAvailableBooksDataSource().addAll(getBooksByCategory(selectedItem));
        initTableColumns(tableView, new String[]{"bookId", "title", "author", "category"});
        tableView.setItems(getAvailableBooksDataSource());
    }
}

/**
 * Fields for the Util interface.
 */
class Fields {
    /**
     * The parents array.
     */
    static List<Parent> parents = new ArrayList<>();
    static Queue<Button> activeButtons = new LinkedList<>();
    static ObservableList<Student> studentsDataSource = FXCollections.observableArrayList();
    static ObservableList<Book> booksDataSource = FXCollections.observableArrayList();

    static ObservableList<Book> availableBooksDataSource = FXCollections.observableArrayList();
    static ObservableList<Book> borrowBooksDataSource = FXCollections.observableArrayList();

    // TODO: explicitly move to Util interface as a method
    static EventHandler<KeyEvent> eventHandler = event -> {
        if (KeyCode.F11.equals(event.getCode())) getStage().setFullScreen(!getStage().isFullScreen());
    };

    static List<Student> studentsList = new ArrayList<>();
    static List<Book> booksList = new ArrayList<>();
    static List<Book> borowedBooksList = new ArrayList<>();
    static Page page;

}
