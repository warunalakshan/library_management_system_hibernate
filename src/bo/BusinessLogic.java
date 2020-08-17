/*
package business;

import dao.booksDAO;
import dao.custom.impl.booksDAOImpl;
import dao.custom.impl.membersDAOImpl;
import entity.books;
import entity.members;
import util.ShowBooksTM;

import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {

    public static String getNewBookId() {

        String lastBookID = booksDAOImpl.getBookID();
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

    public static String getNewMemberId() {

        String lastmemberID = membersDAOImpl.getMemberID();
        if (lastmemberID == null) {
            return "M001";
        } else {
            int maxId = Integer.parseInt(lastmemberID.replace("M", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "M00" + maxId;
            } else if (maxId < 100) {
                id = "M0" + maxId;
            } else {
                id = "M" + maxId;
            }
            return id;
        }
    }

//    public static List<ShowBooksTM> getAllBooks() {
      */
/*  List<books> allBooks = booksDAO.findAllCustomers();
        List<ShowBooksTM> Books = new ArrayList<>();
        for (books book : allBooks) {
            Books.add(new ShowBooksTM(book.getId(), book.getName(), book.getAuthor(), book.getQty(), book.getIsbn()));
        }
        return Books*//*
;

    public static List<ShowBooksTM> getAllCustomers(){

        booksDAO customerDAO = new booksDAOImpl();
        List<books> allCustomers = booksDAOImpl.findAllBooks();
        List<ShowBooksTM> customers = new ArrayList<>();
        for (Object c : allCustomers) {
            books customer = (books) c;
            customers.add(new ShowBooksTM(customer.getId(),customer.getName(), customer.getAuthor(), customer.getQty(),customer.getIsbn()));
        }
        return customers;
    }
//run karapn

    public static boolean SaveBook(String id, String name, String author, int qty, String isbn) {
        return booksDAOImpl.SaveBooks(new books(id, name, author, qty, isbn));
    }

    public static boolean UpdateBook(String name, String author, int qty, String isbn, String id) {
        return booksDAOImpl.UpdateBooks(new books(id, name, author, qty, isbn));
    }

    public static boolean DeleteBooks(String BooksId) {
        return booksDAOImpl.DeleteBooks(BooksId);
    }

    public static boolean SaveMembers(String id, String name, String address, String nic, String contact) {
        return membersDAOImpl.SaveMembers(new members(id, name, address, nic, contact));
    }

}
*/
