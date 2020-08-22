package dao.custom;

import dao.CrudDAO;
import entity.Issue;

public interface IssueDAO extends CrudDAO<Issue, String> {
    public String getLastIssueID() throws Exception;
}
