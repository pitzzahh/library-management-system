package io.github.pitzzahh.libraryManagementSystem.util;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getStage;
import io.github.pitzzahh.libraryManagementSystem.entity.Page;
import javafx.scene.layout.BorderPane;
import static java.lang.String.format;
import java.util.function.Consumer;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.List;

public interface WindowUtil {

    /**
     * Add a list parent to the parents array.
     * takes an array of parents
     */
    Consumer<List<Parent>> addParents = WindowUtilFields.parents::addAll;

    EventHandler<KeyEvent> eventHandler = event -> {
        if (KeyCode.F11.equals(event.getCode())) getStage().setFullScreen(!getStage().isFullScreen());
    };

    /**
     * Gets the parent with the specified id.
     * @param id the id of the parent.
     * @return the parent with the specified id.
     */
    static Parent getParent(String id) {
        return WindowUtilFields.parents.stream()
                .filter(parent -> parent.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(format("Cannot find parent with [%s] id", id)));
    }

    /**
     * Sets the center border pane to the specific window.
     * @param parentId the parent id.
     * @param windowToCenter the page id to be put in the center pane.
     */
    static void loadPage(String parentId, String windowToCenter) {
        ((BorderPane)(getParent(parentId)))
                .setCenter(getParent(windowToCenter));
    }

    static void loadParent(Parent parent, String stageTitle) {
        if (parent.getScene() != null)
            getStage().setScene(parent.getScene()); // if scene is present, get it
        else getStage().setScene(new Scene(parent)); // create new scene if new login
        getStage().setTitle(stageTitle);
        getStage().centerOnScreen();
        getStage().addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
        loadPage(parent.getId(), "promptPage_page");
        getStage().show();
    }

    static void setPage(Page page) {
        WindowUtilFields.page = page;
    }

    static Page getPage() {
        return WindowUtilFields.page;
    }

    static void logoutSession() {
        getStage().close();
        Parent mainWindow = getParent("main_window");
        ComponentUtil.getLabel(mainWindow, "message").ifPresent(label -> label.setText(""));
        ComponentUtil.getMainProgressBar(mainWindow).ifPresent(pb -> pb.setVisible(false));
        getStage().setTitle("Library Management System");
        getStage().centerOnScreen();
        getStage().setScene(mainWindow.getScene());
        getStage().show();
    }
}

class WindowUtilFields {
    static Page page;

    /**
     * The parents array.
     */
    static List<Parent> parents = new ArrayList<>();
}
