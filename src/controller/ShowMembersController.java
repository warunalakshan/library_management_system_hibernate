package controller;

import bo.BOFactory;
import bo.BOType;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import util.ShowBooksTM;
import util.ShowMembersTM;
import bo.custom.MembersBO;

import java.sql.*;
import java.util.List;

public class ShowMembersController {
    public TableView <ShowMembersTM>tbl_AllMembers;
    public Label lbl_memberID;
    public JFXTextField txt_Name;
    public JFXTextField txt_Address;
    public JFXTextField txt_Contact;
    public JFXTextField txt_NIC;
    public Pane pane_Update;
    public Button btn_Update;
    public Button btn_Delete;
    public Pane pane_Show;
    public TableView <ShowMembersTM>tbl_AllMembersShow;

    MembersBO memberBO = BOFactory.getInstance().getBO(BOType.MEMBER);

    public void initialize(){

        tbl_AllMembersShow.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_AllMembersShow.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_AllMembersShow.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tbl_AllMembersShow.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tbl_AllMembersShow.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));

//        ------------------------- pane 2--------------------------
        tbl_AllMembers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_AllMembers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_AllMembers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tbl_AllMembers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tbl_AllMembers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));

        tbl_AllMembers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShowMembersTM>() {
            @Override
            public void changed(ObservableValue<? extends ShowMembersTM> observable, ShowMembersTM oldValue, ShowMembersTM newValue) {
                ShowMembersTM selectMember = tbl_AllMembers.getSelectionModel().getSelectedItem();
                if (newValue == null){
//                    lbl_memberID.setVisible(false);
                }else {
                    lbl_memberID.setText(selectMember.getId());
                    txt_Name.setText(selectMember.getName());
                    txt_Address.setText(selectMember.getAddress());
                    txt_NIC.setText(selectMember.getNic());
                    txt_Contact.setText(selectMember.getContact());

//                    lbl_memberID.setVisible(true);
//                    txt_Name.setVisible(true);
//                    txt_Address.setVisible(true);
//                    txt_Contact.setVisible(true);
//                    txt_NIC.setVisible(true);
                }
            }
        });

        loadMembers();
    }

    public void btn_Update_OnAction(ActionEvent actionEvent) {

        if (txt_Name.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Enter name please", ButtonType.OK).show();
            txt_Name.requestFocus();
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
        if (txt_Contact.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Contact No enter Please", ButtonType.OK).show();
            txt_Contact.requestFocus();
            return;
        }
//
//        String id = lbl_memberID.getText();
//        String name = txt_Name.getText();
//        String NIC = txt_NIC.getText();
//        String address = txt_Address.getText();
//        String contact = txt_Contact.getText();


        ShowMembersTM selectMember = tbl_AllMembers.getSelectionModel().getSelectedItem();
        boolean result = false;
        try {
            result = memberBO.updateMember(txt_Name.getText(), txt_Address.getText(), txt_NIC.getText(),
                    txt_Contact.getText(), selectMember.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!result){
                new Alert(Alert.AlertType.ERROR, "Failed to update the member", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Update Finish", ButtonType.OK).show();
                loadMembers();
                txt_Name.clear();
                txt_Contact.clear();
                txt_Address.clear();
                txt_NIC.clear();
            }
    }

    public void btn_Delete_OnAction(ActionEvent actionEvent) {

        String id = lbl_memberID.getText();

        ShowMembersTM selectMember = tbl_AllMembers.getSelectionModel().getSelectedItem();
        boolean result = false;
        try{
           result = memberBO.DeleteMember(selectMember.getId());
            loadMembers();

        } catch (Exception e) {
            e.printStackTrace();

    }if (!result){
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer", ButtonType.OK).show();
        }
        txt_Name.clear();
        txt_Contact.clear();
        txt_Address.clear();
        txt_NIC.clear();

    }

    public void btn_UpdateOrDelete(ActionEvent actionEvent) {
        pane_Show.setVisible(false);
        pane_Update.setVisible(true);
    }


    private void loadMembers(){
        tbl_AllMembers.getItems().clear();
        List<ShowMembersTM> allMembers = null;
        try{
            allMembers = memberBO.getAllMembers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<ShowMembersTM> member = FXCollections.observableArrayList(allMembers);
        tbl_AllMembers.setItems(member);
        tbl_AllMembersShow.setItems(member);

    }

}
