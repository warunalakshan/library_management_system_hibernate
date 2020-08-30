package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.UsersDAO;
import entity.Users;

import java.sql.ResultSet;
import java.util.List;

public class UsersDAOImpl implements UsersDAO {
    @Override
    public String getLastUserId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select * from Users order by user_id desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public List<Users> findAll() throws Exception {
        return null;
    }

    @Override
    public Users find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean add(Users entity) throws Exception {
        return CrudUtil.execute("insert into Users values(?,?,?)",
                entity.getId(), entity.getUsername(), entity.getPassword());
    }

    @Override
    public boolean update(Users entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String pk) throws Exception {
        return false;
    }
}
