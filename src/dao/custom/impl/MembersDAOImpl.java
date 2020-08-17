package dao.custom.impl;



import dao.CrudUtil;
import dao.custom.MembersDAO;
import entity.Members;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MembersDAOImpl implements MembersDAO {

    @Override
    public String getLastCustomerID() throws Exception {
        ResultSet resultSet = CrudUtil.execute("Select * from Members order by member_id desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public List<Members> findAll() throws Exception {

        ResultSet resultSet = CrudUtil.execute("select * from Members");
        List<Members> membersList = new ArrayList<>();
        while (resultSet.next()){
            membersList.add(new Members(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));
        }
        return membersList;
    }

    @Override
    public Members find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean add(Members entity) throws Exception {
        return CrudUtil.execute("insert into Members values (?,?,?,?,?)",
                entity.getId(), entity.getName(), entity.getAddress(), entity.getNic(), entity.getContact());
    }

    @Override
    public boolean update(Members entity) throws Exception {
        return CrudUtil.execute("update Members set name = ?, address =?," +
                "nic=?, contact=? where member_id =?", entity.getName(),
                entity.getAddress(), entity.getNic(), entity.getContact(), entity.getId());
    }

    @Override
    public boolean delete(String pk) throws Exception {
        return CrudUtil.execute("delete from Members where member_id =?", pk);
    }
}
