package io.github.pitzzahh.libraryManagementSystem.util;

public interface Style {
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
}
