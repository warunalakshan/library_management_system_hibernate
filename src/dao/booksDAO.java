package dao;

import db.DBConnection;
import entity.books;

import java.sql.*;

public class booksDAO {

    public static boolean SaveBooks(books book) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO books VALUES (?,?,?,?,?)");
            preparedStatement.setObject(1, book.getId());
            preparedStatement.setObject(2, book.getName());
            preparedStatement.setObject(3, book.getAuthor());
            preparedStatement.setObject(4, book.getQty());
            preparedStatement.setObject(5, book.getIsbn());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static String getBookID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM books ORDER BY book_id DESC LIMIT 1");
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
