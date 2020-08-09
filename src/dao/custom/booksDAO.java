package dao.custom;

import dao.CrudDAO;
import entity.books;

public interface booksDAO extends CrudDAO<books, String> {
    public String getLastBookID() throws Exception;
}
