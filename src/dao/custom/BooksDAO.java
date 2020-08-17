package dao.custom;

import dao.CrudDAO;
import entity.Books;

public interface BooksDAO extends CrudDAO<Books, String> {
    public String getLastBookID() throws Exception;
}
