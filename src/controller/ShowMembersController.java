package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import util.ShowMembersTM;

import java.sql.*;

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
                    lbl_memberID.setVisible(false);
                }else {
                    lbl_memberID.setText(selectMember.getId());
                    lbl_memberID.setVisible(true);
                    txt_Name.setText(selectMember.getName());
                    txt_Address.setText(selectMember.getAddress());
                    txt_NIC.setText(selectMember.getNic());
                    txt_Contact.setText(selectMember.getContact());
                    txt_Name.setVisible(true);
                    txt_Address.setVisible(true);
                    txt_Contact.setVisible(true);
                    txt_NIC.setVisible(true);
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

        String id = lbl_memberID.getText();
        String name = txt_Name.getText();
        String NIC = txt_NIC.getText();
        String address = txt_Address.getText();
        String contact = txt_Contact.getText();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE members set name =?, address =?, nic =?, contact =? where member_id =?");
//            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement();
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,address);
            preparedStatement.setObject(3,NIC);
            preparedStatement.setObject(4,contact);
            preparedStatement.setObject(5,id);
            preparedStatement.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION, "Update Finish", ButtonType.OK).show();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        loadMembers();
        txt_Name.clear();
        txt_Contact.clear();
        txt_Address.clear();
        txt_NIC.clear();
    }

    public void btn_Delete_OnAction(ActionEvent actionEvent) {

        String id = lbl_memberID.getText();

        ShowMembersTM selectMember = tbl_AllMembers.getSelectionModel().getSelectedItem();
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Delete from members where member_id =?");
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
            loadMembers();

        } catch (Exception e) {
            e.printStackTrace();
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
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from members");
            tbl_AllMembers.getItems().clear();
            tbl_AllMembersShow.getItems().clear();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String nic = resultSet.getString(3);
                String address = resultSet.getString(4);
                String contact = resultSet.getString(5);

                ShowMembersTM membersTM = new ShowMembersTM(id, name, nic, address, contact);
                tbl_AllMembers.getItems().add(membersTM);
                tbl_AllMembersShow.getItems().add(membersTM);
                tbl_AllMembersShow.refresh();
                tbl_AllMembers.refresh();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
