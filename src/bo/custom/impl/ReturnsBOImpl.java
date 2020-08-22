package bo.custom.impl;

import bo.custom.ReturnsBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ReturnsDAO;
import entity.Issue;
import util.IssueDetailsTM;
import util.ReturnDetailsTM;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReturnsBOImpl implements ReturnsBO {

    @Override
    public List<ReturnDetailsTM> getAllReturnDetails() throws Exception {
        return null;
    }

    @Override
    public boolean deleteIssue(String issueId) throws Exception {
        return false;
    }

    @Override
    public ResultSet getIssuesID() throws Exception {
        ReturnsDAO returnsDAO = DAOFactory.getInstance().getDAO(DAOType.RETURN);
        List<Issue> allIssues = returnsDAO.findAll();
        List<IssueDetailsTM> issueDetailsTMS = new ArrayList<>();

        for (Issue allIssue : allIssues) {
            issueDetailsTMS.add(new IssueDetailsTM(allIssue.getIssueId(),allIssue.getMemberId(),
                    allIssue.getBookId(),allIssue.getIssueDate()));
        }
        return (ResultSet) issueDetailsTMS;
    }
}





