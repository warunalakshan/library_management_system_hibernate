package dao.custom.impl;
import dao.CrudUtil;
import dao.custom.ReturnsDAO;
import entity.Issue;
import entity.Returns;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ReturnsDAOImpl implements ReturnsDAO {

    @Override
    public String getAllIssuesID() throws Exception {
        return CrudUtil.execute("SELECT id FROM Issue");
    }

    @Override
    public List<Returns> findAll() throws Exception {

//        ResultSet resultSet = CrudUtil.execute("Select * from Issue");
//        List<Returns> issuesList = new ArrayList<>();
//
//        while (resultSet.next()){
//            issuesList.add(new Returns(resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDate(4)));
//        }
//        return issuesList;
        return null;
    }

    @Override
    public Returns find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean add(Returns entity) throws Exception {
        return CrudUtil.execute("insert into Returns values (?,?,?)",
        entity.getIssueId(),entity.getReturnDate(),entity.getFee());
    }

    @Override
    public boolean update(Returns entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String pk) throws Exception {
        return CrudUtil.execute("delete from Issue where issue_id = ?", pk);
    }
}
