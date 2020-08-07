package business;

import dao.booksDAO;
import entity.books;

public class BusinessLogic {

    public static String getNewCustomerId() {

        String lastBookID = booksDAO.getBookID();
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
    public static boolean saveCustomer(String id, String name, String author, int qty, String isbn) {
        return booksDAO.SaveBooks(new books(id, name, author, qty, isbn));
    }
}
