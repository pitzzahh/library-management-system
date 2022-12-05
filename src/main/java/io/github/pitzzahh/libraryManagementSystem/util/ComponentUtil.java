package io.github.pitzzahh.libraryManagementSystem.util;

import io.github.pitzzahh.libraryManagementSystem.entity.Category;
import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import static java.lang.String.format;
import java.util.stream.Collectors;
import javafx.scene.control.*;
import javafx.scene.Parent;
import java.util.Optional;
import java.util.List;

public interface ComponentUtil {
    /**
     * Used to get a table from a parent node.
     * @param parentId the parent parentId.
     * @param tableId the id of the table.
     * @return an {@code Optional<TableView<?>>}.
     */
    static Optional<TableView<?>> getTable(String parentId, String tableId) {
        return Optional.ofNullable((TableView<?>) WindowUtil.getParent(parentId).lookup(format("#%s", tableId)));
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
    static Optional<Label> getLabel(Parent parent, String id) {
        return Optional.ofNullable((Label) parent.lookup(format("#%s", id)));
    }

    static Optional<ChoiceBox<?>> getChoiceBox(Parent parent, String name) {
        return Optional.ofNullable((ChoiceBox<?>) parent.lookup(format("#%s", name)));
    }

    static List<Book> getBooksByCategory(Category category) {
        return DataUtil.getAllBooks()
                .stream()
                .map(e -> (Book) e)
                .filter(book -> book.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    static void initTableColumns(TableView<?> table, String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            TableColumn<?, ?> column = table.getColumns().get(i);
            if (i == 0 || i == 3) column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(columns[i]));
        }
    }

    static void addActiveButtons(Button button) {
        boolean anyMatch = DateUtilFields.activeButtons.stream()
                .anyMatch(b -> b.getId().equals(button.getId()));
        if (!anyMatch) DateUtilFields.activeButtons.offer(button);
    }

    static Optional<Button> getActiveButton(String id) {
        return DateUtilFields.activeButtons.stream()
                .filter(button -> button.getId().equals(id))
                .findAny();
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
}
