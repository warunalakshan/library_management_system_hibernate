package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.IssueDAO;
import entity.Issue;
import entity.Members;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IssueDAOImpl implements IssueDAO {

    @Override
    public String getLastIssueID() throws Exception {

        ResultSet resultSet = CrudUtil.execute("select * from Issue order by issue_id limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public List<Issue> findAll() throws Exception {

        ResultSet resultSet = CrudUtil.execute("select * from Issue");
        List<Issue> membersList = new ArrayList<>();
        while (resultSet.next()){
            membersList.add(new Issue(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)));
        }

        return null;
    }

    @Override
    public Issue find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean add(Issue entity) throws Exception {
        return CrudUtil.execute("insert into Issue  values(?,?,?,?)", entity.getIssueId(),
                entity.getMemberId(), entity.getBookId(), entity.getIssueDate());
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
