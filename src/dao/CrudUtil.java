package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sqlStatement, Object ... paramList){
        Connection connection = DBConnection.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            int i = 1;
            for (Object o : paramList) {
                preparedStatement.setObject(i,o);
                i++;
            }
            if (sqlStatement.startsWith("select")){
                return (T) preparedStatement.executeQuery();
            }
            return (T) ((Boolean)(preparedStatement.executeUpdate() > 0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
