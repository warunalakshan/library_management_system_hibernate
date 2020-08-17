package dao.custom;

import dao.CrudDAO;
import entity.Members;

public interface MembersDAO extends CrudDAO<Members, String > {
    public String getLastCustomerID() throws Exception;

}
