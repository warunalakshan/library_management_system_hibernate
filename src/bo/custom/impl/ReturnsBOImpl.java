package bo.custom.impl;

import bo.custom.ReturnsBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.IssueDAO;
import dao.custom.QueryDAO;
import dao.custom.ReturnsDAO;
import entity.CustomEntity;
import entity.Issue;
import entity.Returns;
import util.IssueDetailsTM;
import util.ReturnDetailsTM;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnsBOImpl implements ReturnsBO {
    ReturnsDAO returnsDAO = DAOFactory.getInstance().getDAO(DAOType.RETURN);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
    IssueDAO issueDAO = DAOFactory.getInstance().getDAO(DAOType.ISSUE);
    @Override
    public List<ReturnDetailsTM> getAllReturnDetails() throws Exception {
        List<CustomEntity> returnDetails = queryDAO.getReturnDetails();
        List<ReturnDetailsTM> returnDetailsTMS = new ArrayList<>();

        for (CustomEntity customEntity : returnDetails) {
            returnDetailsTMS.add(new ReturnDetailsTM(customEntity.getIssueID(),
                    customEntity.getMemberID(),
                    customEntity.getBookID(),
                    customEntity.getBookName(),
                    customEntity.getReturnDate(),
                    customEntity.getLateDays(),
                    customEntity.getFee()));

        }
        return returnDetailsTMS;
    }

    @Override
    public boolean deleteIssue(String issueId) throws Exception {
        ReturnsDAO returnsDAO = DAOFactory.getInstance().getDAO(DAOType.RETURN);
        return returnsDAO.delete(issueId);
    }

    @Override
    public ResultSet getIssuesID() throws Exception {

        List<Issue> allIssues = issueDAO.findAll();
        List<IssueDetailsTM> issueDetailsTMS = new ArrayList<>();

        for (Issue allIssue : allIssues) {
            issueDetailsTMS.add(new IssueDetailsTM(allIssue.getIssueId(),
                    allIssue.getMemberId(),
                    allIssue.getBookId(),
                    allIssue.getIssueDate()));
        }
        return (ResultSet) issueDetailsTMS;
    }

    @Override
    public boolean saveReturnDetails(String issueId, Date returnDate, BigDecimal fee) throws Exception {
        return returnsDAO.add(new Returns(issueId,returnDate,fee));
    }

}





