package business;

import dao.booksDAO;
import dao.membersDAO;
import entity.books;
import entity.members;

public class BusinessLogic {

    public static String getNewBookId() {

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

    public static String getNewMemberId() {

        String lastmemberID = membersDAO.getMemberID();
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


    public static boolean SaveBook(String id, String name, String author, int qty, String isbn) {
        return booksDAO.SaveBooks(new books(id, name, author, qty, isbn));
    }

    public static boolean SaveMembers(String id, String name, String address, String nic, String contact) {
        return membersDAO.SaveMembers(new members(id, name, address, nic, contact));
    }

}
