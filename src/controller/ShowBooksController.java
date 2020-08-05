package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import util.ShowBooksTM;

import java.sql.*;
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

    public void initialize(){
        tbl_AllBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_AllBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_AllBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tbl_AllBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tbl_AllBooks.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("isbn"));

        tbl_AllBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShowBooksTM>() {
            @Override
            public void changed(ObservableValue<? extends ShowBooksTM> observable, ShowBooksTM oldValue, ShowBooksTM newValue) {
                ShowBooksTM selectBook = tbl_AllBooks.getSelectionModel().getSelectedItem();
                if (newValue == null){
                }else {
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

        if (txt_Name.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, " No name included", ButtonType.OK).show();
            txt_Name.requestFocus();
            return;
        }
        if (txt_Author.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "please enter author name", ButtonType.OK).show();
            txt_Author.requestFocus();
            return;
        }
        if (txt_Quantity.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "enter quantity", ButtonType.OK).show();
            txt_Quantity.requestFocus();
            return;
        }
        if (txt_ISBN.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "enter ISBN", ButtonType.OK).show();
            txt_ISBN.requestFocus();
            return;
        }

        String id = lbl_BookID.getText();
        String name = txt_Name.getText();
        String author = txt_Author.getText();
        int qty = Integer.parseInt(txt_Quantity.getText());
        String isbn = txt_ISBN.getText();

        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Update books set book_name =?, author =?, quantity=?, isbn=? where book_id=?");
            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2,author);
            preparedStatement.setObject(3,qty);
            preparedStatement.setObject(4,isbn);
            preparedStatement.setObject(5,id);
            preparedStatement.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION, "Update Successfully", ButtonType.OK).show();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

            try {
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("Delete from books where book_id=?");
                preparedStatement.setObject(1, id);
                preparedStatement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
            loadBooks();
            txt_Name.clear();
            txt_Author.clear();
            txt_Quantity.clear();
            txt_ISBN.clear();
        }
    }

    private void loadBooks() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from books");
            tbl_AllBooks.getItems().clear();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String author = resultSet.getString(3);
                int qty = resultSet.getInt(4);
                String isbn = resultSet.getString(5);

                ShowBooksTM showBooksTM = new ShowBooksTM(id, name, author, qty, isbn);
                tbl_AllBooks.getItems().add(showBooksTM);
                tbl_AllBooks.refresh();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
