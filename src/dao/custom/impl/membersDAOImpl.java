package dao.custom.impl;



import dao.CrudUtil;
import dao.custom.membersDAO;
import entity.members;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class membersDAOImpl implements membersDAO {

    @Override
    public String getLastCustomerID() throws Exception {
        ResultSet resultSet = CrudUtil.execute("Select * from members order by member_id desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public List<members> findAll() throws Exception {

        ResultSet resultSet = CrudUtil.execute("select * from members");
        List<members> membersList = new ArrayList<>();
        while (resultSet.next()){
            membersList.add(new members(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));
        }
        return membersList;
    }

    @Override
    public members find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean add(members entity) throws Exception {
        return CrudUtil.execute("insert into members values (?,?,?,?,?)",
                entity.getId(), entity.getName(), entity.getAddress(), entity.getNic(), entity.getContact());
    }

    @Override
    public boolean update(members entity) throws Exception {
        return CrudUtil.execute("update members set name = ?, address =?," +
                "nic=?, contact=? where member_id =?", entity.getName(),
                entity.getAddress(), entity.getNic(), entity.getContact(), entity.getId());
    }

    @Override
    public boolean delete(String pk) throws Exception {
        return CrudUtil.execute("delete from members where member_id =?", pk);
    }
}
