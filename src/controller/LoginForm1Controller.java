package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm1Controller {
    public Pane pane_SignIn;
    public Button btn_Submit;
    public Label lbl_SignUp;
    public Pane pane_SignUp;
    public TextField txt_ID_SignUp;
    public Button btn_SignUp;
    public TextField txt_Password_SignUp;
    public TextField txt_UserName_SignUp;
    public AnchorPane root;
    public TextField txt_userName_Login;
    public TextField txt_Password_Login;


    public void initialize() {

    }

    public void btn_Submit_OnAction(ActionEvent actionEvent) throws IOException {
        Login();
    }

    public void txt_Password_Login_OnAction(ActionEvent actionEvent) {
        Login();
    }

    public void lbl_SignUp_OnAction(MouseEvent mouseEvent) {
        pane_SignIn.setVisible(false);
        pane_SignUp.setVisible(true);
    }


    public void btn_SignUp_OnAction(ActionEvent actionEvent) throws SQLException {
        signUp();
    }


    public void txt_password_SignUp_OnAction(ActionEvent actionEvent) {
        signUp();
    }


    public void img_Back_OnAction(MouseEvent mouseEvent) {
        pane_SignUp.setVisible(false);
        pane_SignIn.setVisible(true);
    }

    private void Login(){

        if (txt_userName_Login.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "please enter username").show();
            txt_userName_Login.requestFocus();
            return;
        }
        if (txt_Password_Login.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "please enter your password").show();
            txt_Password_Login.requestFocus();
            return;
        }

        String userName = txt_userName_Login.getText();
        String password = txt_Password_Login.getText();

        try{
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("select * from users where username=? and password=?");
            preparedStatement.setObject(1,userName);
            preparedStatement.setObject(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                new Alert(Alert.AlertType.ERROR, "Invalid!...  please check the username and password").show();
                txt_Password_Login.requestFocus();
            }
            else {
                try {

                    Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
                    Scene mainScene = new Scene(root);
                    Stage stage = (Stage) (this.root.getScene().getWindow());
                    stage.setScene(mainScene);
                    stage.centerOnScreen();
                    stage.setResizable(false);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        String userName = txt_UserName_SignUp.getText();
        String password = txt_Password_SignUp.getText();
//        String id = txt_ID_SignUp.getText();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values (?,?) ");
//            preparedStatement.setObject(1, id);
            preparedStatement.setObject(1, userName);
            preparedStatement.setObject(2, password);
            preparedStatement.executeUpdate();
            new Alert(Alert.AlertType.INFORMATION, "Registration is successfully...!", ButtonType.OK).showAndWait();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
