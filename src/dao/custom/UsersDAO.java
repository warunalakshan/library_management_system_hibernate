package dao.custom;


import dao.CrudDAO;
import entity.Users;

public interface UsersDAO extends CrudDAO<Users, String> {
public String getLastUserId() throws Exception;
}
