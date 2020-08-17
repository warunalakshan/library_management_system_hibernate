package bo.custom;

import bo.SuperBO;
import util.ShowBooksTM;
import util.ShowMembersTM;

import java.time.LocalDate;
import java.util.List;

public interface IssueBO extends SuperBO {
    public String getNewIssueId() throws Exception;
    public boolean issueBook(String id, Object member_id, Object book_id, LocalDate issueDate) throws Exception;
    public List<ShowBooksTM> getAllBooks() throws Exception;
    public List<ShowMembersTM> getAllMembers() throws Exception;
}
