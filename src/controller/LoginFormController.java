package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public Pane Pane1_Login;
    public JFXTextField txt_UserName;
    public JFXTextField txt_Password;
    public Label lbl_register;
    public ImageView img_Password;
    public JFXButton btn_Login;
//    ===================================
    public JFXTextField txt_Username_Register;
    public JFXTextField txt_Password_register;
    public JFXButton btn_Register;
    public Pane panel2_Register;
    public JFXTextField txt_UserID_Register1;
    public AnchorPane root;

    public void lbl_Register_OnAction(MouseEvent mouseEvent) {
        panel2_Register.setVisible(true);
        Pane1_Login.setVisible(false);
    }

    public void Img_Password_show_OnAction(MouseEvent mouseEvent) {
    }

    public void btn_Login_OnAction(ActionEvent actionEvent) {
        loginPassword();
    }

    public void txt_Password_OnAction(ActionEvent actionEvent) {
        loginPassword();
    }


    public void btn_Register_OnAction(ActionEvent actionEvent) throws SQLException {

        String id = txt_UserID_Register1.getText();
        String userName = txt_Username_Register.getText();
        String password = txt_Password_register.getText();

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Users values (?,?,?) ");
        preparedStatement.setObject(1,id);
        preparedStatement.setObject(2, userName);
        preparedStatement.setObject(3, password);
        preparedStatement.executeUpdate();
        new Alert(Alert.AlertType.INFORMATION,"Registration is successfully...!", ButtonType.OK).showAndWait();
        Pane1_Login.setVisible(true);
        panel2_Register.setVisible(false);

    }
    private void loginPassword(){

        if (txt_UserName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter username...").showAndWait();
            txt_UserName.requestFocus();
            return;
        }

        if (txt_Password.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter password...").showAndWait();
            txt_Password.requestFocus();
            return;
        }
            String userName = txt_UserName.getText();
            String password = txt_Password.getText();

            try {
                PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * from Users where name=? AND password=? ");
                preparedStatement.setObject(1, userName);
                preparedStatement.setObject(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter the correct username and password", ButtonType.OK).showAndWait();
                txt_Password.selectAll();
                txt_Password.requestFocus();

                } else {
//                resultSet.beforeFirst();
//                while (resultSet.next()) {
                    try {
                        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
                        Scene mainScene = new Scene(root);
                        Stage stage = (Stage) (this.root.getScene().getWindow());
                        stage.setScene(mainScene);
                        stage.centerOnScreen();
                        stage.sizeToScene();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//            }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

}
