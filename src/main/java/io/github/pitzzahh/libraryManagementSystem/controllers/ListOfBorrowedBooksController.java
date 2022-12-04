package io.github.pitzzahh.libraryManagementSystem.controllers;

import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

public class ListOfBorrowedBooksController {

    @FXML
    public Button separateWindowButton;

    @FXML
    public TableView<Book> table;

}
