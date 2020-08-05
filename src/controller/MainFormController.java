package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.ShowBooksTM;
import util.ShowMembersTM;

import java.io.IOException;
import java.sql.*;

public class MainFormController {
    public AnchorPane root;
    public ImageView img_ListDown;
    public ImageView img_ListUp;
    public Pane Pane_1_DashBoard;
    public Pane pane_return;
    public Pane pane_Issue;
    public ImageView img_AddMember;
    public ImageView img_ShowMembers;
    public ImageView img_AddBooks;
    public ImageView img_BooksList;
    public JFXButton btn_Cancel_BookIssue;
    public Pane pane_img_DashBoard;
    public JFXComboBox cmb_MemberID;
    public JFXComboBox cmb_BookID;
    public Label lbl_IssueID;
    public JFXDatePicker dtp_Date;
    public JFXButton btn_Issue;
    public JFXButton btn_Cancel_returnBook;

    public void initialize(){
        img_ListDown.setVisible(true);
        loadBooks();
        loadMembers();
    }

    public void img_List(MouseEvent mouseEvent) {
        Pane_1_DashBoard.setVisible(true);
    }

    public void img_ListUp(MouseEvent mouseEvent) {
        img_ListDown.setVisible(true);
        img_ListUp.setVisible(false);
        Pane_1_DashBoard.setVisible(false);
    }

    public void img_ListDown(MouseEvent mouseEvent) {
        img_ListUp.setVisible(true);
        img_ListDown.setVisible(false);
        Pane_1_DashBoard.setVisible(true);
    }

    public void btn_BookIssue_DashBoard(ActionEvent actionEvent) {
        pane_Issue.setVisible(true);
        pane_return.setVisible(false);
        pane_img_DashBoard.setVisible(false);
        generateIssueID();
    }

    public void btn_ReturnBooks_DashBoard(ActionEvent actionEvent) {
        pane_return.setVisible(true);
        pane_Issue.setVisible(false);
        pane_img_DashBoard.setVisible(false);
    }

    public void img_AddMember_OnAction(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AddMember.fxml"));
        Scene mainScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(mainScene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void img_AddBooks_OnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AddBook.fxml"));
        Scene mainScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(mainScene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void img_ShowMembers_OnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/ShowMembers.fxml"));
        Scene mainScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(mainScene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void img_BooksList_OnAction(MouseEvent mouseEvent) throws IOException {
        Parent root =FXMLLoader.load(this.getClass().getResource("/view/ShowBooks.fxml"));
        Scene mainScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(mainScene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void Img_Signout(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LoginForm1.fxml"));
        Scene mainScene = new Scene(root);
        Stage stage = (Stage)(this.root.getScene().getWindow());
        stage.setScene(mainScene);
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    public void btn_Cancel_Book_Issues_OnAction(ActionEvent actionEvent) {
        pane_Issue.setVisible(false);
        pane_img_DashBoard.setVisible(true);
    }

    public void btn_Issue_OnAction(ActionEvent actionEvent) {
//
//        String issue_id = lbl_IssueID.getText();
        String member_id = String.valueOf(cmb_MemberID.getSelectionModel().getSelectedItem());
        String book_id = String.valueOf(cmb_BookID.getSelectionModel().getSelectedItem());
//        String date = dtp_Date.g

        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into issue values (?,?,?,?)");
            preparedStatement.setObject(1, lbl_IssueID.getText());
            preparedStatement.setObject(2, member_id);
            preparedStatement.setObject(3, book_id);
            preparedStatement.setObject(4, dtp_Date.getValue());
            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        new Alert(Alert.AlertType.INFORMATION, "Issue successfully", ButtonType.OK).show();
        pane_Issue.setVisible(false);
        pane_img_DashBoard.setVisible(true);


    }

    public void btn_Cancel_ReturnBooks_OnAction(ActionEvent actionEvent) {
        pane_return.setVisible(false);
        pane_img_DashBoard.setVisible(true);
    }

    private void generateIssueID(){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select issue_id from issue order by issue_id desc limit 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                lbl_IssueID.setText("ISSUE/001");
            }else{
                String lastCode = resultSet.getString(1);
                String substring = lastCode.substring(6,9);
                int newCode = Integer.parseInt(substring) + 1;
                if (newCode < 10){
                    lbl_IssueID.setText("ISSUE/00" + newCode);
                }else if (newCode < 100){
                    lbl_IssueID.setText("ISSUE/0" + newCode);
                }else {
                    lbl_IssueID.setText("ISSUE/" + newCode);
                }
            }

       /* }else{
            String lastCode = resultSet.getString(1);
            String substring = lastCode.substring(1,4);
            int newCode = Integer.parseInt(substring) + 1;
            if (newCode < 10){
                lbl_BookId.setText("B00" + newCode);
            }else if (newCode < 100){
                lbl_BookId.setText("B0" + newCode);
            }else {
                lbl_BookId.setText("B" + newCode);
            }*/


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void loadBooks() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from books");
            cmb_BookID.getItems().clear();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String author = resultSet.getString(3);
                int qty = resultSet.getInt(4);
                String isbn = resultSet.getString(5);

                ShowBooksTM showBooksTM = new ShowBooksTM(id, name, author, qty, isbn);
                cmb_BookID.getItems().add(showBooksTM);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void loadMembers(){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from members");
            cmb_MemberID.getItems().clear();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String nic = resultSet.getString(3);
                String address = resultSet.getString(4);
                String contact = resultSet.getString(5);

                ShowMembersTM membersTM = new ShowMembersTM(id, name, nic, address, contact);
                cmb_MemberID.getItems().add(membersTM);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

