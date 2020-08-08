package dao.custom.impl;

import dao.booksDAO;
import db.DBConnection;
import entity.books;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

 public class booksDAOImpl implements booksDAO {

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

    public static boolean UpdateBooks(books book) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Update books set book_name =?, author =?, quantity=?, isbn=? where book_id=?");

            preparedStatement.setObject(1, book.getName());
            preparedStatement.setObject(2, book.getAuthor());
            preparedStatement.setObject(3, book.getQty());
            preparedStatement.setObject(4, book.getIsbn());
            preparedStatement.setObject(5, book.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean DeleteBooks(String BookId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Delete from books where book_id=?");
            preparedStatement.setObject(1, BookId);
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
            if (!rst.next()) {
                return null;
            } else {
                return rst.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static List<books> findAllBooks(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM books");
            List<books> books = new ArrayList<>();
            while (rst.next()){
                books.add(new books(rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5)));
            }
            return books;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


//    public static List<books> findAllCustomers() {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from books");
//            List<books> books = new ArrayList<>();;
//            while (resultSet.next()) {
//                String id = resultSet.getString(1);
//                String name = resultSet.getString(2);
//                String author = resultSet.getString(3);
//                int qty = resultSet.getInt(4);
//                String isbn = resultSet.getString(5);
//
//                ShowBooksTM showBooksTM = new ShowBooksTM(id, name, author, qty, isbn);
//                tbl_AllBooks.getItems().add(showBooksTM);
//                tbl_AllBooks.refresh();
//            }
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
