package controller;

import bo.BOFactory;
import bo.BOType;
import bo.SuperBO;
import bo.custom.BooksBO;
import bo.custom.IssueBO;
import bo.custom.MembersBO;
import bo.custom.ReturnsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entity.Issue;
import entity.Returns;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import util.IssueDetailsTM;
import util.ReturnDetailsTM;
import util.ShowBooksTM;
import util.ShowMembersTM;

import javax.swing.event.ChangeEvent;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public JFXComboBox<ShowMembersTM> cmb_MemberID;
    public JFXComboBox<ShowBooksTM> cmb_BookID;
    public Label lbl_IssueID;
    public JFXDatePicker dtp_Date;
    public JFXButton btn_Issue;
    public JFXButton btn_Cancel_returnBook;
    public TableView <ReturnDetailsTM>tbl_Return;
    public JFXComboBox<Issue> cmb_IssueID_Return;
    public JFXButton btn_return;
    public Label lbl_fee;
    public Label lbl_ReturnDate;
    public Label lbl_IssueId_Return;

    MembersBO membersBO= BOFactory.getInstance().getBO(BOType.MEMBER);
    IssueBO issueBO=BOFactory.getInstance().getBO(BOType.ISSUE);
    BooksBO booksBO = BOFactory.getInstance().getBO(BOType.BOOK);
    ReturnsBO returnsBO = BOFactory.getInstance().getBO(BOType.RETURN);

    public void initialize() throws Exception {

        img_ListDown.setVisible(true);

        tbl_Return.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("issueID"));
        tbl_Return.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("memberID"));
        tbl_Return.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookID"));
        tbl_Return.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bookName"));
        tbl_Return.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        tbl_Return.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("lateDays"));
        tbl_Return.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadBooks();
        loadMembers();
        loadReturnTable();

        tbl_Return.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ReturnDetailsTM>() {
            @Override
            public void changed(ObservableValue<? extends ReturnDetailsTM> observable, ReturnDetailsTM oldValue, ReturnDetailsTM newValue) {
                ReturnDetailsTM select = tbl_Return.getSelectionModel().getSelectedItem();
                if (newValue == null){
                }
                else {
                    lbl_ReturnDate.setText(String.valueOf("Return Date: " + select.getReturnDate()));
                    lbl_fee.setText("Fee : Rs " + select.getFee());
                    lbl_IssueId_Return.setText(select.getIssueID());
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

    public void btn_BookIssue_DashBoard(ActionEvent actionEvent) throws Exception {
        pane_Issue.setVisible(true);
        pane_return.setVisible(false);
        pane_img_DashBoard.setVisible(false);
        generateIssueID();
        loadMembers();
        loadBooks();
    }

    public void btn_ReturnBooks_DashBoard(ActionEvent actionEvent) {
        pane_return.setVisible(true);
        pane_Issue.setVisible(false);
        pane_img_DashBoard.setVisible(false);
        loadReturnTable();
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

//    ----------------------------issue start--------------------------------

    public void btn_Cancel_Book_Issues_OnAction(ActionEvent actionEvent) {
        pane_Issue.setVisible(false);
        pane_img_DashBoard.setVisible(true);
    }

    public void btn_Issue_OnAction(ActionEvent actionEvent) {
        LocalDate value = dtp_Date.getValue();

        System.out.println( cmb_MemberID.getSelectionModel().getSelectedItem().getId());
        boolean result = false;
        try{
            result =  issueBO.issueBook(lbl_IssueID.getText(),
                    cmb_MemberID.getSelectionModel().getSelectedItem().getId(),
                    cmb_BookID.getSelectionModel().getSelectedItem().getId(),
                    Date.valueOf(value.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!result){
            new Alert(Alert.AlertType.ERROR, "Failed to Issue the book", ButtonType.OK).show();
        }

        pane_Issue.setVisible(false);
        pane_img_DashBoard.setVisible(true);
    }

    private void loadBooks() throws Exception {
        List<ShowBooksTM> allBooks = booksBO.getAllBooks();
        ObservableList<ShowBooksTM> showBooksTMS = FXCollections.observableArrayList(allBooks);
        cmb_BookID.setItems(showBooksTMS);
    }

    private void loadMembers() throws Exception {

        List<ShowMembersTM> allMembers = membersBO.getAllMembers();
        ObservableList<ShowMembersTM> showMembersTMS = FXCollections.observableArrayList(allMembers);
        cmb_MemberID.setItems(showMembersTMS);
    }

    private void generateIssueID(){
        try{
            lbl_IssueID.setText(issueBO.getNewIssueId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    -----------------------------return start--------------------------

    public void btn_Return_OnAction(ActionEvent actionEvent) {

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"are you sure this issue is is correct..?",ButtonType.NO,ButtonType.YES);
        Optional confirmType = confirm.showAndWait();
        if (confirmType.get() == ButtonType.YES) {
            ReturnDetailsTM selectedItem = tbl_Return.getSelectionModel().getSelectedItem();
            boolean result = false;

            try {
                result = returnsBO.deleteIssue(selectedItem.getIssueID());
                
//                result = returnsBO.saveReturnDetails(lbl_IssueId_Return.getText(),
//                        Date.valueOf(lbl_ReturnDate.getText()),
//                        BigDecimal.valueOf(Long.parseLong(lbl_fee.getText())));

                new Alert(Alert.AlertType.INFORMATION, "Return successfully..", ButtonType.OK).show();
                loadReturnTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result) {
                new Alert(Alert.AlertType.ERROR, "Failed to Return", ButtonType.OK).show();
            }
            tbl_Return.getSelectionModel().clearSelection();
            loadReturnTable();
        }
    }

    public void btn_Cancel_ReturnBooks_OnAction(ActionEvent actionEvent) {
        pane_return.setVisible(false);
        pane_img_DashBoard.setVisible(true);
    }

    void loadCombo(){
        try {
            ObservableList<Issue> items = cmb_IssueID_Return.getItems();
            items.clear();

            List<Issue> allIssues = issueBO.getAllIssues();
            System.out.println(allIssues);
            for (Issue allIssue : allIssues) {
                items.add(allIssue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadReturnTable(){
        tbl_Return.getItems().clear();
        List<ReturnDetailsTM> allDetails = null;
        try{
            allDetails = returnsBO.getAllReturnDetails();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<ReturnDetailsTM> returnDetailsTMS = FXCollections.observableArrayList(allDetails);
        tbl_Return.setItems(returnDetailsTMS);
        tbl_Return.refresh();
    }
}

