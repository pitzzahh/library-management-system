package io.github.pitzzahh.libraryManagementSystem.util;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public interface ToolTipUtil {

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

    static void checkInputsToolTip(String tip, MouseEvent event, String styles, Button button) {
        Tooltip tooltip = initToolTip(
                tip,
                event,
                styles,
                null
        );
        tooltip.setShowDuration(Duration.seconds(3));
        button.setTooltip(tooltip);
    }

    static void showToolTipOnHover(String tip, MouseEvent mouseEvent, Button logout) {
        Tooltip tooltip = initToolTip(
                tip,
                mouseEvent,
                Style.leftButtonSelectionFunctionStyle(),
                null
        );
        tooltip.setShowDuration(Duration.seconds(3));
        logout.setTooltip(tooltip);
    }

    static void onHoverButtons(String tip, MouseEvent event, Button removeAll) {
        checkInputsToolTip(tip, event, Style.leftButtonSelectionFunctionStyle(), removeAll);
    }
}
