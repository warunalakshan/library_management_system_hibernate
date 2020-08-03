package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMemberController {

    public AnchorPane root;
    public JFXTextField txt_name;
    public JFXTextField txt_NIC;
    public JFXTextField txt_Address;
    public JFXTextField txt_contact;
    public JFXButton btn_AddNewMember;
    public Label lbl_MemberId;
    public JFXButton btn_Save;

    public void btn_AddNewMember_OnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select member_id from members order by member_id desc limit 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                lbl_MemberId.setText("M001");
            } else {
                String lastCode = resultSet.getString(1);
                String substring = lastCode.substring(1, 4);
                int newCode = Integer.parseInt(substring) + 1;
                if (newCode < 10) {
                    lbl_MemberId.setText("M00" + newCode);
                } else if (newCode < 100) {
                    lbl_MemberId.setText("M0" + newCode);
                } else {
                    lbl_MemberId.setText("M" + newCode);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btn_Save_OnAction(ActionEvent actionEvent) {
        saveMember();
    }

    public void txt_contact_OnAction(ActionEvent actionEvent) {
        saveMember();
    }

    private void saveMember(){
        if (lbl_MemberId.getText().equals("Member ID")){
            new Alert(Alert.AlertType.ERROR, "Please press Add New member Button and generate member ID..", ButtonType.OK).show();
            btn_AddNewMember.requestFocus();
            return;
        }
        if (txt_name.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Enter name please", ButtonType.OK).show();
            txt_name.requestFocus();
            return;
        }
        if (txt_NIC.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Enter NIC no please", ButtonType.OK).show();
            txt_NIC.requestFocus();
            return;
        }
        if (txt_Address.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Enter address please", ButtonType.OK).show();
            txt_Address.requestFocus();
            return;
        }
        if (txt_contact.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Contact No enter Please", ButtonType.OK).show();
            txt_contact.requestFocus();
            return;
        }

        String id = lbl_MemberId.getText();
        String name = txt_name.getText();
        String NIC = txt_NIC.getText();
        String address = txt_Address.getText();
        String contact = txt_contact.getText();

            try {
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO members values (?,?,?,?,?) ");
                preparedStatement.setObject(1, id);
                preparedStatement.setObject(2, name);
                preparedStatement.setObject(3, NIC);
                preparedStatement.setObject(4, address);
                preparedStatement.setObject(5, contact);
                preparedStatement.executeUpdate();



            } catch (Exception e) {
                e.printStackTrace();
            }

        new Alert(Alert.AlertType.INFORMATION, "Successfully added...", ButtonType.OK).show();
        txt_name.clear();
        txt_NIC.clear();
        txt_contact.clear();
        txt_Address.clear();
        btn_AddNewMember.requestFocus();
//        System.exit(0);

    }
}


        /*else {
            try {
                PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE members set name=?, NIC=?, address=?, contact=? where id=?");
                preparedStatement.setObject(1,name);
                preparedStatement.setObject(2,NIC);
                preparedStatement.setObject(3,address);
                preparedStatement.setObject(4,contact);
                preparedStatement.setObject(5,id);
                preparedStatement.executeUpdate();

                new Alert(Alert.AlertType.INFORMATION, "Update Finish", ButtonType.OK).show();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/

