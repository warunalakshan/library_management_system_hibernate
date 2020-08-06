package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.ReturnDetailsTM;
import util.ShowBooksTM;
import util.ShowMembersTM;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

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
    public Label lbl_returnDate;
    public TableView <ReturnDetailsTM>tbl_Return;
    public JFXComboBox cmb_IssueID_Return;
    public JFXButton btn_return;
    public JFXTextField txt_Fee;
    public Label lbl_fee;
    public Label lbl_feeDetail;

    public void initialize(){
        img_ListDown.setVisible(true);

        tbl_Return.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("issueID"));
        tbl_Return.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("memberID"));
        tbl_Return.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookID"));
        tbl_Return.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bookName"));
        tbl_Return.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        tbl_Return.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        tbl_Return.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("lateDays"));

        loadBooks();
        loadMembers();
        loadTable();



        cmb_IssueID_Return.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ReturnDetailsTM>() {
            @Override
            public void changed(ObservableValue<? extends ReturnDetailsTM> observable, ReturnDetailsTM oldValue, ReturnDetailsTM newValue) {
                if (newValue == null){

                }else {
                    if (newValue.getLateDays() > 14){
                        lbl_feeDetail.setVisible(true);
                        lbl_feeDetail.setText("Member have to pay a fine Rs :");
                        lbl_fee.setText(" 50.00");
                        lbl_fee.setStyle("-fx-text-fill: #990000");
                        lbl_fee.setVisible(true);
                    }else{
                        lbl_feeDetail.setText("You do not have to pay a fine");
                        lbl_fee.setText("00.00");
                        lbl_fee.setVisible(false);

                    }
                }
            }
        });
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

    public void btn_Return_OnAction(ActionEvent actionEvent) {
        returnBook_deleteTable();
//        returnBook_insertTable();
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

    private void loadTable(){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select i.issue_id, i.member_id, i.book_id, b.book_name, i.issue_date from issue i\n" +
                    "INNER join books b on i.book_id = b.book_id ");
            tbl_Return.getItems().clear();
            cmb_IssueID_Return.getItems().clear();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String Mid = resultSet.getString(2);
                String Bid = resultSet.getString(3);
                String Bname = resultSet.getString(4);
                Date date = resultSet.getDate(5);

                //Difference between Days
                LocalDate Today = LocalDate.now();
                LocalDate next2Week = Today.plus(2, ChronoUnit.WEEKS);
//                lbl_returnDate.setText(String.valueOf(next2Week));

                LocalDate today = LocalDate.now();
                long diffInDays = ChronoUnit.DAYS.between(date.toLocalDate(), today);

                ReturnDetailsTM returnDetailsTM = new ReturnDetailsTM(id, Mid, Bid, Bname, date, next2Week, diffInDays);
                tbl_Return.getItems().add(returnDetailsTM);
                cmb_IssueID_Return.getItems().add(returnDetailsTM);
                tbl_Return.refresh();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void returnBook_deleteTable(){
        String id = String.valueOf(cmb_IssueID_Return.getSelectionModel().getSelectedItem());

        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from issue where issue_id =?");
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
            loadTable();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        cmb_IssueID_Return.getSelectionModel().clearSelection();
    }

    private void returnBook_insertTable(){

        String id = String.valueOf(cmb_IssueID_Return.getSelectionModel().getSelectedItem());
        LocalDate Today = LocalDate.now();
        Double fee = Double.valueOf(lbl_fee.getText());

        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into returns values(?,?,?)");
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,Today);
            preparedStatement.setObject(3, fee);
            preparedStatement.executeUpdate();
            new Alert(Alert.AlertType.INFORMATION, "Lk").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*private void calculateQty(ShowBooksTM showBooksTM) {
        for (ReturnDetailsTM orderDetails : tbl_Return.getItems()) {
            if (orderDetails.getBookID().equals(ShowBooksTM.getId())) {
                int qty = Integer.parseInt(ShowBooksTM.getQty()) - orderDetails.getQuantity();
                txtQOH.setText(qty + "");
                break;
            }
        }
    }*/

}

