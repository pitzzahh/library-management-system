package io.github.pitzzahh.libraryManagementSystem.util;

import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import io.github.pitzzahh.libraryManagementSystem.entity.User;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import java.util.stream.IntStream;
import java.util.*;

public interface DataUtil {

    /**
     * admin credentials.
     */
    String $admin = decrypt("QGRtMW4xJHRyNHQwcg==");
    int MAX_LENGTH = 10;

    static String generateRandomAccountNumber() {
        return String.valueOf(new Random().nextInt(999999999) + 1);
    }

    static ObservableList<Student> getStudentsDataSource() {
        return DataUtilFields.studentsDataSource;
    }

    static ObservableList<Book> getBooksDataSource() {
        return DataUtilFields.booksDataSource;
    }

    static void saveAllStudents() {
        DataUtilFields.studentsList = new ArrayList<>(getStudentsDataSource());
    }

    static List<? super Student> getAllStudents() {
        return DataUtilFields.studentsList;
    }

    static void saveAllBooks() {
        DataUtilFields.booksList = new ArrayList<>(getBooksDataSource());
    }

    static List<? super Book> getAllBooks() {
        return DataUtilFields.booksList;
    }

    static String decrypt(String data) throws IllegalArgumentException {
        if (data.trim().isEmpty()) throw new IllegalArgumentException("Text to be decrypted cannot be empty");
        byte[] b = Base64.getDecoder().decode(data);
        return IntStream.range(0, b.length).map(i -> b[i]).mapToObj(Character::toString).reduce("", String::concat);
    }

    static ObservableList<? super Book> getAvailableBooksDataSource() {
        return DataUtilFields.availableBooksDataSource;
    }

    static ObservableList<Book> getBorrowedBooksDataSource() {
        return DataUtilFields.borrowBooksDataSource;
    }

    static void saveAllBorrowedBooks() {
        DataUtilFields.borowedBooksList = new ArrayList<>(getBorrowedBooksDataSource());
    }

    static List<Book> getAllBorrowedBooks() {
        return DataUtilFields.borowedBooksList;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    static void setAvailableBooksData(TableView tableView, ChoiceBox<Category> objectChoiceBox) {
        getAvailableBooksDataSource().clear();
        Category selectedItem = objectChoiceBox.getSelectionModel().getSelectedItem();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        getAvailableBooksDataSource().addAll(ComponentUtil.getBooksByCategory(selectedItem));
        ComponentUtil.initTableColumns(tableView, new String[]{"bookId", "title", "author", "category"});
        tableView.setItems(getAvailableBooksDataSource());
    }

    static  void setCurrentUser(User<?> user) {
        DataUtilFields.currentUser = user;
    }

    static User<?> getCurrentUser() {
        return DataUtilFields.currentUser;
    }

    static void addUser(User<?> user) {
        DataUtilFields.usersList.add(user);
    }

    static Optional<User<?>> getUser(String username) {
        return DataUtilFields.usersList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }

}

class DataUtilFields {
    static User<?> currentUser;
    static List<Student> studentsList = new ArrayList<>();
    static List<User<?>> usersList = new ArrayList<>();
    static List<Book> booksList = new ArrayList<>();
    static List<Book> borowedBooksList = new ArrayList<>();
    static Queue<Button> activeButtons = new LinkedList<>();
    static ObservableList<Student> studentsDataSource = FXCollections.observableArrayList();
    static ObservableList<Book> booksDataSource = FXCollections.observableArrayList();
    static ObservableList<Book> availableBooksDataSource = FXCollections.observableArrayList();
    static ObservableList<Book> borrowBooksDataSource = FXCollections.observableArrayList();
}
