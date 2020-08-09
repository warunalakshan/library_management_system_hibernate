package business.custom;

import business.SuperBO;
import util.ShowBooksTM;
import util.ShowMembersTM;

import java.util.List;

public interface issueBO extends SuperBO {
    public String getNewIssueId() throws Exception;
    public boolean issueBook(String id, String member_id, String book_id) throws Exception;
    public List<ShowBooksTM> getAllBooks() throws Exception;
    public List<ShowMembersTM> getAllMembers() throws Exception;
}
