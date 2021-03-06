package dao.custom;

import dao.CrudDAO;
import entity.Issue;
import entity.Returns;

public interface ReturnsDAO extends CrudDAO<Returns, String > {
    public String getAllIssuesID() throws Exception;
}
