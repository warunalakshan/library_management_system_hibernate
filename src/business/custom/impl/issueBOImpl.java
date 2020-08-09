package business.custom.impl;

import business.custom.issueBO;
import util.ShowBooksTM;
import util.ShowMembersTM;

import java.util.List;

public class issueBOImpl implements issueBO {
    @Override
    public String getNewIssueId() throws Exception {
        return null;
    }

    @Override
    public boolean issueBook(String id, String member_id, String book_id) throws Exception {
        return false;
    }

    @Override
    public List<ShowBooksTM> getAllBooks() throws Exception {
        return null;
    }

    @Override
    public List<ShowMembersTM> getAllMembers() throws Exception {
        return null;
    }
}
