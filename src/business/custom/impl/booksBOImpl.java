package business.custom.impl;

import business.custom.booksBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.booksDAO;
import entity.books;
import util.ShowBooksTM;

import java.util.ArrayList;
import java.util.List;

public class booksBOImpl implements booksBO {
    @Override
    public String getNewBookId() throws Exception {
        booksDAO booksDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
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
        booksDAO booksDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
        List<books> allBooks = booksDao.findAll();
        List<ShowBooksTM> booksTMS = new ArrayList<>();

        for (books allBook : allBooks) {
            booksTMS.add(new ShowBooksTM(allBook.getId(), allBook.getName(), allBook.getAuthor(),
                    allBook.getQty(), allBook.getIsbn()));
        }
        return booksTMS;
    }

    @Override
    public boolean saveBook(String id, String name, String author, int qty, String isbn) throws Exception{
        booksDAO bookDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
        return bookDao.add(new books(id, name, author, qty, isbn));
    }

    @Override
    public boolean deleteBook(String bookId) throws Exception {
        booksDAO bookDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
        return bookDao.delete(bookId);
    }

    @Override
    public boolean updateBook(String name, String author, int qty, String isbn, String id) {
        try{
           booksDAO bookDao = DAOFactory.getInstance().getDAO(DAOType.BOOK);
           return bookDao.update(new books(id, name, author, qty, isbn));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
