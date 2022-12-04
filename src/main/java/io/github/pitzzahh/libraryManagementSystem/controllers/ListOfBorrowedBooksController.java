package io.github.pitzzahh.libraryManagementSystem.controllers;

import io.github.pitzzahh.libraryManagementSystem.entity.Book;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.net.URL;

public class ListOfBorrowedBooksController implements Initializable {

    @FXML
    public Button separateWindowButton;


    @FXML
    public TableView<Book> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
