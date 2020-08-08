package dao.custom;

import dao.CrudDAO;
import dao.superDAO;
import entity.members;

public interface membersDAO extends CrudDAO<members, String > {
    public String getLastCustomerID() throws Exception;

}
