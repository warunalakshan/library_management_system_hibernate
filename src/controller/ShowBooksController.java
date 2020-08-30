package controller;


import bo.BOFactory;
import bo.BOType;
import bo.custom.BooksBO;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import util.ShowBooksTM;

import java.util.Optional;

public class ShowBooksController {
    public TableView<ShowBooksTM> tbl_AllBooks;
    public JFXTextField txt_Name;
    public JFXTextField txt_Quantity;
    public JFXTextField txt_Author;
    public JFXTextField txt_ISBN;
    public Label lbl_BookID;
    public Button btn_Update;
    public Button btn_Delete;

    BooksBO bookBO = BOFactory.getInstance().getBO(BOType.BOOK);

    public void initialize() {
        tbl_AllBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_AllBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_AllBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tbl_AllBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tbl_AllBooks.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("isbn"));

        tbl_AllBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShowBooksTM>() {
            @Override
            public void changed(ObservableValue<? extends ShowBooksTM> observable, ShowBooksTM oldValue, ShowBooksTM newValue) {
                ShowBooksTM selectBook = tbl_AllBooks.getSelectionModel().getSelectedItem();

                if (newValue == null) {
                } else {
                    lbl_BookID.setText(selectBook.getId());
                    txt_Name.setText(selectBook.getName());
                    txt_Author.setText(selectBook.getAuthor());
                    txt_Quantity.setText(String.valueOf(selectBook.getQty()));
                    txt_ISBN.setText(selectBook.getIsbn());
                }
            }
        });

        loadBooks();
    }

    public void btn_Update_OnAction(ActionEvent actionEvent) {

        if (txt_Name.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, " No name included", ButtonType.OK).show();
            txt_Name.requestFocus();
            return;
        }
        if (txt_Author.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "please enter author name", ButtonType.OK).show();
            txt_Author.requestFocus();
            return;
        }
        if (txt_Quantity.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "enter quantity", ButtonType.OK).show();
            txt_Quantity.requestFocus();
            return;
        }
        if (txt_ISBN.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "enter ISBN", ButtonType.OK).show();
            txt_ISBN.requestFocus();
            return;
        }

        ShowBooksTM selectBook = tbl_AllBooks.getSelectionModel().getSelectedItem();
        boolean result = false;
try {
    result = bookBO.updateBook(txt_Name.getText(), txt_Author.getText(), Integer.parseInt(txt_Quantity.getText()), txt_ISBN.getText(),selectBook.getId());
    } catch (Exception e) {
    e.printStackTrace();
}
        if (!result) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the Book", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Update Successfully", ButtonType.OK).show();
            loadBooks();
            txt_Name.clear();
            txt_Author.clear();
            txt_Quantity.clear();
            txt_ISBN.clear();
        }
    }


    public void btn_Delete_OnAction(ActionEvent actionEvent) {

        String id = lbl_BookID.getText();

        tbl_AllBooks.getSelectionModel().getSelectedItem();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure delete this book ?", ButtonType.YES, ButtonType.NO);
        Optional confirmtype = confirm.showAndWait();

        if (confirmtype.get() == ButtonType.YES) {
            ShowBooksTM selectBooks = tbl_AllBooks.getSelectionModel().getSelectedItem();
            boolean result = false;
            try {
                result = bookBO.deleteBook(selectBooks.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Book", ButtonType.OK).show();
            } else {
                tbl_AllBooks.getSelectionModel().clearSelection();
            }
            loadBooks();
            txt_Name.clear();
            txt_Author.clear();
            txt_Quantity.clear();
            txt_ISBN.clear();
        }
    }

    private void loadBooks() {


        ObservableList<ShowBooksTM> book = tbl_AllBooks.getItems();
        book.clear();
        try{
            book = FXCollections.observableArrayList(bookBO.getAllBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tbl_AllBooks.setItems(book);

    }
}

