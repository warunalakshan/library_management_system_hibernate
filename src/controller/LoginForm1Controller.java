package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginForm1Controller {
    public Pane pane_SignIn;
    public TextField txt_userName;
    public TextField txt_Password;
    public Button btn_Submit;
    public Label lbl_SignUp;
    public Pane pane_SignUp;
    public TextField txt_NIC_SignUp;
    public Button btn_SignUp;
    public TextField txt_Password_SignUp;
    public TextField txt_UserName_SignUp;
    public AnchorPane root;


    public void btn_Submit_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        Stage stage = (Stage)(this.root.getScene().getWindow());
        stage.setScene(mainScene);
        stage.centerOnScreen();
    }


    public void txt_Password_OnAction(ActionEvent actionEvent) {

    }


    public void lbl_SignUp_OnAction(MouseEvent mouseEvent) {
        pane_SignIn.setVisible(false);
        pane_SignUp.setVisible(true);
    }


    public void btn_SignUp_OnAction(ActionEvent actionEvent) {

    }


    public void txt_NIC_SignUp_OnAction(ActionEvent actionEvent) {

    }


    public void img_Back_OnAction(MouseEvent mouseEvent) {
        pane_SignUp.setVisible(false);
        pane_SignIn.setVisible(true);
    }
}
