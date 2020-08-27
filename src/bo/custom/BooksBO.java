package bo.custom;

import bo.SuperBO;
import util.ShowBooksTM;

import java.util.List;

public interface BooksBO extends SuperBO {
    public String getNewBookId()throws Exception;
    public List<ShowBooksTM> getAllBooks()throws Exception;
    public boolean saveBook(String id, String name, String author, int qty, String isbn) throws Exception;
    public boolean deleteBook(String bookId)throws Exception;
    public boolean updateBook(String name, String author, int qty, String isbn,String id) throws Exception;
}
