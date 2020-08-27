package bo.custom.impl;

import bo.custom.BooksBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.BooksDAO;
import entity.Books;
import util.ShowBooksTM;

import java.util.ArrayList;
import java.util.List;

public class BooksBOImpl implements BooksBO {
    BooksDAO booksDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
    @Override
    public String getNewBookId() throws Exception {

        String lastBookID = booksDao.getLastBookID();
        if (lastBookID == null) {
            return "B001";
        } else {
            int maxId = Integer.parseInt(lastBookID.replace("B", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "B00" + maxId;
            } else if (maxId < 100) {
                id = "B0" + maxId;
            } else {
                id = "B" + maxId;
            }
            return id;
        }
    }

    @Override
    public List<ShowBooksTM> getAllBooks() throws Exception {
        BooksDAO booksDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
        List<Books> allBooks = booksDao.findAll();
        List<ShowBooksTM> booksTMS = new ArrayList<>();

        for (Books allBook : allBooks) {
            booksTMS.add(new ShowBooksTM(allBook.getId(), allBook.getName(), allBook.getAuthor(),
                    allBook.getQty(), allBook.getIsbn()));
        }
        return booksTMS;
    }

    @Override
    public boolean saveBook(String id, String name, String author, int qty, String isbn) throws Exception{
        BooksDAO bookDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
        return bookDao.add(new Books(id, name, author, qty, isbn));
    }

    @Override
    public boolean deleteBook(String bookId) throws Exception {
        BooksDAO bookDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
        return bookDao.delete(bookId);
    }

    @Override
    public boolean updateBook(String name, String author, int qty, String isbn, String id) throws Exception {
            BooksDAO booksDAO = DAOFactory.getInstance().getDAO(DAOType.BOOK);
            return booksDAO.update(new Books(id, name, author, qty, isbn));
    }
}
