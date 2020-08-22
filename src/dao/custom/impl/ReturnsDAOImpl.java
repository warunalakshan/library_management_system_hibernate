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
    public List<Issue> findAll() throws Exception {

//        ResultSet resultSet = CrudUtil.execute("select * from Issue");
//        List<Issue> membersList = new ArrayList<>();
//        while (resultSet.next()){
//            membersList.add(new Issue(resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDate(4)));
//        }
//
//        return null;

        ResultSet resultSet = CrudUtil.execute("Select * from Issue");
        List<Issue> issuesList = new ArrayList<>();

        while (resultSet.next()){
            issuesList.add(new Issue(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)));
        }
        return issuesList;
    }

    @Override
    public Issue find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean add(Issue entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Issue entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String pk) throws Exception {
        return false;
    }
}
