package io.github.pitzzahh.libraryManagementSystem.validator;

import static io.github.pitzzahh.util.utilities.validation.Validator.isWholeNumber;
import static io.github.pitzzahh.libraryManagementSystem.util.Util.MAX_LENGTH;
import static java.lang.String.format;

/**
 * Validates the account number entered by the user.
 */
public final class Validator {

    /**
     * Checks if the account number entered is valid.
     * @param accountNumber the account number.
     * @return true if the account number is valid, false otherwise.
     */
    public static boolean doesAccountExist(String accountNumber) {
        if (accountNumber.isEmpty()) throw new RuntimeException("Please enter your Student number");
        else if (accountNumber.length() != MAX_LENGTH && isWholeNumber().test(accountNumber)) throw new IllegalArgumentException(format("Student number must be %d digits long", MAX_LENGTH));
        else if (isWholeNumber().negate().test(accountNumber)) throw new IllegalArgumentException("Student number must be a number");
        // TODO: add checking for student account number
        return true;
    }

}
