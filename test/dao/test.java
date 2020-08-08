package dao;

import dao.custom.impl.booksDAOImpl;
import entity.books;

import java.util.List;

public class test {
    public static void main(String[] args) {

        booksDAO booksDAO = new booksDAOImpl();
        List<books> customers = booksDAO.findAllBooks();
        for (books customer : customers) {
            System.out.println(customer);
        }
    }
}
