package io.github.pitzzahh.libraryManagementSystem.validator;

import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
import io.github.pitzzahh.libraryManagementSystem.util.DataUtil;

import static java.lang.String.format;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Validates the account number entered by the user.
 */
public final class Validator {

    /**
     * Checks if the account number entered is valid.
     * @param credential the credential number, can be the student number.
     * @return true if the account number is valid, false otherwise.
     */
    public static boolean doesAccountExist(String credential) {
        if (credential.isEmpty()) throw new RuntimeException("Please enter your Student number");
        else if ((credential.length() != DataUtil.MAX_LENGTH && credential.length() != DataUtil.MAX_LENGTH - 1) && isWholeNumber().test(credential)) throw new IllegalArgumentException(format("Student number must be %d digits long", DataUtil.MAX_LENGTH));
        else if (isWholeNumber().negate().test(credential)) throw new IllegalArgumentException("Student number must be a number");
        return DataUtil.getAllStudents().stream().map(e -> (Student) e).map(Student::getStudentNumber).anyMatch(credential::equals);
    }

    static Predicate<String> isWholeNumber() {
        return input -> Pattern.compile("^\\d+$").matcher(input).find();
    }

    public static boolean isStudentAlreadyAdded(String studentNumber) {
        return DataUtil.getAllStudents()
                .stream()
                .map(e -> (Student) e)
                .noneMatch(e -> e.getStudentNumber().equals(studentNumber)) && DataUtil.getStudentsDataSource().stream()
                .noneMatch(e -> e.getStudentNumber().equals(studentNumber));
    }

    public static boolean isBookAlreadyAdded(String bookId) {
        return DataUtil.getAllBooks()
                .stream()
                .map(e -> (Book) e)
                .noneMatch(e -> e.getBookId().equals(bookId)) && DataUtil.getBooksDataSource().stream()
                .noneMatch(e -> e.getBookId().equals(bookId));
    }


}
