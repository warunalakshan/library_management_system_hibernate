package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddBookController {
    public AnchorPane root;
    public JFXTextField txt_Name;
    public JFXTextField txt_Author;
    public JFXTextField txt_Quantity;
    public JFXTextField txt_ISBN;
    public JFXButton btn_Add_new;
    public Label lbl_BookId;
    public JFXButton btn_Save;

    public void btn_Add_new_OnAction(ActionEvent actionEvent) {
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select book_id from books order by book_id desc limit 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                lbl_BookId.setText("B001");
            }else{
                String lastCode = resultSet.getString(1);
                String substring = lastCode.substring(1,4);
                int newCode = Integer.parseInt(substring) + 1;
                if (newCode < 10){
                    lbl_BookId.setText("B00" + newCode);
                }else if (newCode < 100){
                    lbl_BookId.setText("B0" + newCode);
                }else {
                    lbl_BookId.setText("B" + newCode);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void txt_ISBN_OnAction(ActionEvent actionEvent) {
        saveBook();
    }

    public void btn_Save_OnAction(ActionEvent actionEvent) {
        saveBook();
    }

    private void saveBook(){

        if (lbl_BookId.getText().equals("Book ID")){
            new Alert(Alert.AlertType.ERROR, "press Add New Book Button", ButtonType.OK).show();
            btn_Add_new.requestFocus();
            return;
        }
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

        String id = lbl_BookId.getText();
        String name = txt_Name.getText();
        String author = txt_Author.getText();
        int qty = Integer.parseInt(txt_Quantity.getText());
        String isbn = txt_ISBN.getText();

        try{
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO books VALUES (?,?,?,?,?)");
            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3, author);
            preparedStatement.setObject(4,qty);
            preparedStatement.setObject(5,isbn);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        new Alert(Alert.AlertType.INFORMATION, "Successfully added..", ButtonType.OK).show();
        txt_Name.clear();
        txt_Author.clear();
        txt_ISBN.clear();
        txt_Quantity.clear();
        btn_Add_new.requestFocus();
    }

}
