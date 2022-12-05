package io.github.pitzzahh.libraryManagementSystem.util;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

import static io.github.pitzzahh.libraryManagementSystem.entity.Page.ADD_BOOKS;
import static io.github.pitzzahh.libraryManagementSystem.entity.Page.ADD_STUDENTS;

public interface TextFieldUtil {

    /**
     * Adds a limit to the specified text field.
     * @param textField the text field to add the limit.
     * @param maxLength the maximum length of the text field.
     */
    static void addTextLimiter(final TextField textField, final int maxLength) {
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if ((textField.getText().length() > maxLength)) {
                String limitedInput = textField.getText().substring(0, maxLength);
                if (!limitedInput.equals(DataUtil.$admin.substring(0, maxLength))) textField.setText(limitedInput);
            }
        });
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
            String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Cannot Add Student, All Required Input are empty" :
                    WindowUtil.getPage().equals(ADD_BOOKS) ? "Cannot Add Books, All Required Input are empty" : "Please fill in all the fields";
            ToolTipUtil.checkInputsToolTip(message, event, Style.errorToolTipStyle(), button);
            return false;
        }
        else if (id.isEmpty() && (firstInput.isEmpty() || secondInput.isEmpty())) {
            String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Cannot Add Student, All Required Input are empty" :
                    WindowUtil.getPage().equals(ADD_BOOKS) ? "Cannot Add Book, Book Id is empty" : "Please fill in all the fields";
            ToolTipUtil.checkInputsToolTip(message, event, Style.errorToolTipStyle(), button);
            return false;
        }
        else if (firstInput.isEmpty() && secondInput.isEmpty()) {
            String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Cannot Add Student, First Name is empty" :
                    WindowUtil.getPage().equals(ADD_BOOKS) ? "Cannot Add Book, Book Title is empty" : "Please fill in all the fields";
            ToolTipUtil.checkInputsToolTip(message, event, Style.errorToolTipStyle(), button);
            return false;
        }
        else if (secondInput.isEmpty()) {
            String message = WindowUtil.getPage().equals(ADD_STUDENTS) ? "Cannot Add Student, Last Name is empty" :
                    WindowUtil.getPage().equals(ADD_BOOKS) ? "Cannot Add Book, Book Author is empty" : "Please fill in all the fields";
            ToolTipUtil.checkInputsToolTip(message, event, Style.errorToolTipStyle(), button);
            return false;
        }
        return true;
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
}
