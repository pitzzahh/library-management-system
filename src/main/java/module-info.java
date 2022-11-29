module io.github.pitzzahh.libraryMangementSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires util.classes;
    requires ch.qos.logback.core;
    requires org.slf4j;
    requires org.controlsfx.controls;
    requires lombok;

    opens io.github.pitzzahh.libraryManagementSystem.controllers to javafx.fxml;
    opens io.github.pitzzahh.libraryManagementSystem.entity to javafx.base;
    exports io.github.pitzzahh.libraryManagementSystem;
}