package controller;

import business.BusinessLogic;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.booksDAOImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import util.ShowBooksTM;

import java.util.List;
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

        String id = lbl_BookID.getText();
        String name = txt_Name.getText();
        String author = txt_Author.getText();
        int qty = Integer.parseInt(txt_Quantity.getText());
        String isbn = txt_ISBN.getText();

        ShowBooksTM selectBook = tbl_AllBooks.getSelectionModel().getSelectedItem();
        boolean result = BusinessLogic.UpdateBook(txt_Name.getText(), txt_Author.getText(), Integer.parseInt(txt_Quantity.getText()), txt_ISBN.getText(), selectBook.getId());
        if (!result) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the Book", ButtonType.OK).show();
        }

        new Alert(Alert.AlertType.INFORMATION, "Update Successfully", ButtonType.OK).show();
        loadBooks();
        txt_Name.clear();
        txt_Author.clear();
        txt_Quantity.clear();
        txt_ISBN.clear();
    }


    public void btn_Delete_OnAction(ActionEvent actionEvent) {

        String id = lbl_BookID.getText();

        tbl_AllBooks.getSelectionModel().getSelectedItem();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure delete this book ?", ButtonType.YES, ButtonType.NO);
        Optional confirmtype = confirm.showAndWait();

        if (confirmtype.get() == ButtonType.YES) {
            ShowBooksTM selectBooks = tbl_AllBooks.getSelectionModel().getSelectedItem();
            boolean result = BusinessLogic.DeleteBooks(selectBooks.getId());
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

    /*    tbl_AllBooks.getItems().clear();
        List<ShowBooksTM> allBooks = BusinessLogic.getAllBooks();
        ObservableList<ShowBooksTM> customers = FXCollections.observableArrayList(allBooks);
        tbl_AllBooks.setItems(customers);
    }*/

        tbl_AllBooks.getItems().clear();
        List<ShowBooksTM> allCustomers = BusinessLogic.getAllCustomers();
        ObservableList<ShowBooksTM> customers = FXCollections.observableArrayList(allCustomers);
        tbl_AllBooks.setItems(customers);

       /* booksDAOImpl booksDAO = new booksDAOImpl();
        List<books> customers = booksDAOImpl.findAllBooks();
        for (books customer : customers) {
            System.out.println(customer);
        }*/
    }
}

