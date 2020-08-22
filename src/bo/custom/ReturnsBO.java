package bo.custom;

import bo.SuperBO;
import util.ReturnDetailsTM;

import java.sql.ResultSet;
import java.util.List;

public interface ReturnsBO extends SuperBO{
    public List<ReturnDetailsTM> getAllReturnDetails() throws Exception;
    public boolean deleteIssue(String issueId)throws Exception;
    public ResultSet getIssuesID()throws Exception;
}
