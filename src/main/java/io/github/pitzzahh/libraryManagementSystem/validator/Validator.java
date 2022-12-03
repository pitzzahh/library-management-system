package io.github.pitzzahh.libraryManagementSystem.validator;

import static io.github.pitzzahh.libraryManagementSystem.util.Util.*;
import io.github.pitzzahh.libraryManagementSystem.entity.Student;
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
        else if ((credential.length() != MAX_LENGTH && credential.length() != MAX_LENGTH - 1) && isWholeNumber().test(credential)) throw new IllegalArgumentException(format("Student number must be %d digits long", MAX_LENGTH));
        else if (isWholeNumber().negate().test(credential)) throw new IllegalArgumentException("Student number must be a number");
        return getAllStudents().stream().map(Student::getStudentNumber).anyMatch(credential::equals);
    }

    static Predicate<String> isWholeNumber() {
        return input -> Pattern.compile("^\\d+$").matcher(input).find();
    }

}
