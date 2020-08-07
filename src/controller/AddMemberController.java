package controller;

import business.BusinessLogic;
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
        lbl_MemberId.setText(BusinessLogic.getNewMemberId());
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

        BusinessLogic.SaveMembers(lbl_MemberId.getText(),
                txt_name.getText(),
                txt_Address.getText(),
                txt_NIC.getText(),
                txt_contact.getText());


        new Alert(Alert.AlertType.INFORMATION, "Successfully added...", ButtonType.OK).show();
        txt_name.clear();
        txt_NIC.clear();
        txt_contact.clear();
        txt_Address.clear();
        btn_AddNewMember.requestFocus();
//        System.exit(0);

    }
}
