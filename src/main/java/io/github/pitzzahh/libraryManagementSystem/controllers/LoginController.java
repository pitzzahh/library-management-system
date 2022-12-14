package io.github.pitzzahh.libraryManagementSystem.controllers;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.*;
import io.github.pitzzahh.libraryManagementSystem.validator.Validator;
import io.github.pitzzahh.libraryManagementSystem.util.*;
import java.util.concurrent.atomic.AtomicReference;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.concurrent.Service;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 * @author pitzzahh
 */
public class LoginController {

    @FXML
    public ProgressBar progressBar;


    @FXML
    private PasswordField credentialField;

    @FXML
    private Label message;

    private Stage stage; // TODO: move to Util interface

    /**
     * Checks if the Enter key is pressed and invokes the check() method.
     * @param keyEvent the key event.
     */
    @FXML
    public void onEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            credentialField.setVisible(false);
            checkCredential();
        }
    }

    /**
     * Checks if the account number entered exists in the database as an account.
     */
    @FXML
    private void checkCredential() {
        final String fieldText = credentialField.getText();
        AtomicReference<String> debugMessage = new AtomicReference<>("");
        System.out.printf("Credential: %s%n", DataUtil.$admin);

        final Service<Void> progressBarService = PBar.initProgressBar(progressBar);

        progressBarService.setOnScheduled(e -> {
            message.setText("Please Wait");
            message.setStyle("" +
                    "-fx-font-family: JetBrains Mono;" +
                    "fx-font-size: 20px;" +
                    "-fx-text-fill: #F1EED5;" +
                    "-fx-font-weight: bold;"
            );
        });
        progressBarService.setOnSucceeded(stateEvent -> {
            message.setStyle("" +
                    "-fx-font-family: JetBrains Mono;" +
                    "-fx-font-size: 20px;" +
                    "-fx-text-fill: #d62828;" +
                    "-fx-font-weight: bold;"
            );
            checker(fieldText, debugMessage);
        });
        progressBarService.start();
    }

    /**
     * To shorten the lambda above.
     * @param fieldText the input from the user.
     * @param debugMessage the message for debugging. also the error message.
     */
    private void checker(String fieldText, AtomicReference<String> debugMessage) {
        if (fieldText.equals(DataUtil.$admin)) {
            WindowUtil.loadParent(WindowUtil.getParent("admin_window"), "Administrator", false);
            progressBar.setVisible(false);
            credentialField.clear();
            credentialField.setVisible(true);
            debugMessage.set("Welcome admin!");
            progressBar.progressProperty().unbind();
        }
        else {
            try {
                final boolean doesAccountExist = Validator.doesAccountExist(fieldText);
                if (doesAccountExist) {
                    debugMessage.set("Account exists");
                    WindowUtil.loadParent(WindowUtil.getParent("student_window"), "Student", false);
                    credentialField.clear();
                } else {
                    debugMessage.set("Account does not exist");
                    message.setText(debugMessage.get());
                }
            } catch (RuntimeException runtimeException) {
                message.setText(runtimeException.getMessage());
                System.out.println(runtimeException.getMessage());
                credentialField.setVisible(true);
            }
        }
        credentialField.setVisible(true);
        progressBar.setVisible(false);
    }

    /**
     * Checks if the credential field is empty, then clears the message label.
     * @param keyEvent the key event
     */
    @FXML
    public void onKeyTyped(KeyEvent keyEvent) {
        TextFieldUtil.addTextLimiter(credentialField, DataUtil.MAX_LENGTH);
        if (credentialField.getText().isEmpty() && keyEvent.getCode() != KeyCode.ENTER) {
            message.setText("");
        }
    }

    /**
     * Shows the tooltip when the mouse is hovered over the credential field. and if th field is empty.
     * @param mouseEvent the mouse event.
     */
    @FXML
    public void onMouseEntered(MouseEvent mouseEvent) {
        if (stage != null) stage = (Stage) credentialField.getScene().getWindow();
        final String $an = credentialField.getText().trim();
        final Optional<Tooltip> optionalTooltip = Optional.ofNullable(credentialField.getTooltip());
        if ($an.isEmpty()) {
            if (optionalTooltip.isEmpty()) {
                Tooltip toolTip = getFieldToolTip(mouseEvent);
                credentialField.setTooltip(toolTip);
            }
        } else if (optionalTooltip.isPresent()) {
            credentialField.setTooltip(null);
        }
    }

    /**
     * Creates a simple tooltip when mouse is hovered over the credentials field.
     * @param mouseEvent the mouse event.
     * @return a tooltip.
     */
    @FXML
    private static Tooltip getFieldToolTip(MouseEvent mouseEvent) {
        return ToolTipUtil.initToolTip(
                "Enter your credentials",
                mouseEvent,
                Style.errorToolTipStyle(),
                null
        );
    }
}

