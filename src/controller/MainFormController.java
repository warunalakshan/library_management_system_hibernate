package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void initialize(){
        img_ListDown.setVisible(true);
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
    }

    public void btn_Cancel_BookIssue_OnAction(MouseEvent mouseEvent) {
        pane_Issue.setVisible(false);
        pane_img_DashBoard.setVisible(true);
    }

    public void btn_Cancel_ReturnBooks_OnAction(MouseEvent mouseEvent) {
        pane_return.setVisible(false);
        pane_img_DashBoard.setVisible(true);
    }
}
