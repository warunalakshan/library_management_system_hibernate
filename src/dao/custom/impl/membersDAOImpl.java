package dao.custom.impl;

import db.DBConnection;
import entity.members;

import java.sql.*;

public class membersDAOImpl {

    public static boolean SaveMembers(members member) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO members values (?,?,?,?,?) ");
            preparedStatement.setObject(1, member.getId());
            preparedStatement.setObject(2, member.getName());
            preparedStatement.setObject(3, member.getAddress());
            preparedStatement.setObject(4, member.getNic());
            preparedStatement.setObject(5, member.getContact());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getMemberID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM members ORDER BY member_id DESC LIMIT 1");
            if (!rst.next()){
                return null;
            }else{
                return rst.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
