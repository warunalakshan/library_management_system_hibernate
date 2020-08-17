package bo.custom.impl;

import bo.custom.IssueBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.BooksDAO;
import dao.custom.IssueDAO;
import dao.custom.MembersDAO;
import entity.Books;
import entity.Issue;
import entity.Members;
import util.ShowBooksTM;
import util.ShowMembersTM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueBOImpl implements IssueBO {
    @Override
    public String getNewIssueId() throws Exception {
        IssueDAO issueDao = DAOFactory.getInstance().getDAO(DAOType.ISSUE);
        String lastIssueId = issueDao.getLastIssueID();
        if (lastIssueId == null){
            return "ISSUE/001";
        }else{
            int maxId=  Integer.parseInt(lastIssueId.replace("ISSUE/",""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "ISSUE/00" + maxId;
            } else if (maxId < 100) {
                id = "ISSUE/0" + maxId;
            } else {
                id = "ISSUE/" + maxId;
            }
            return id;
        }
    }

    @Override
    public boolean issueBook(String id, Object member_id, Object book_id, LocalDate issueDate) throws Exception {

        IssueDAO issueDao = DAOFactory.getInstance().getDAO(DAOType.ISSUE);
        return issueDao.add(new Issue(id, member_id, book_id, issueDate));
    }

    @Override
    public List<ShowBooksTM> getAllBooks() throws Exception {
        BooksDAO bookDao = DAOFactory.getInstance().getDAO(DAOType.ISSUE);
        List<Books> allBooks = bookDao.findAll();
        List<ShowBooksTM> booksTMS = new ArrayList<>();

        for (Books book : allBooks){
            booksTMS.add(new ShowBooksTM(book.getId(),book.getName(), book.getAuthor(), book.getQty(),book.getIsbn()));
        }

        return booksTMS;
    }

    @Override
    public List<ShowMembersTM> getAllMembers() throws Exception {
        MembersDAO memberDao = DAOFactory.getInstance().getDAO(DAOType.ISSUE);
        List<Members> allMembers = memberDao.findAll();
        List<ShowMembersTM> membersTMS = new ArrayList<>();

        for (Members member : allMembers){
            membersTMS.add(new ShowMembersTM(member.getId(), member.getName(),
                    member.getAddress(), member.getNic(), member.getContact()));
        }
        return membersTMS;
    }
}
