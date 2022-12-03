module io.github.pitzzahh.libraryMangementSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens io.github.pitzzahh.libraryManagementSystem.controllers to javafx.fxml;
    opens io.github.pitzzahh.libraryManagementSystem.entity to javafx.base;
    exports io.github.pitzzahh.libraryManagementSystem;
}