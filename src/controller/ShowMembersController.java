package controller;

import db.DBConnection;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowMembersController {
    public TableView tbl_AllMembers;


    private void loadMembers(){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from members");
            while (resultSet.next()){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
