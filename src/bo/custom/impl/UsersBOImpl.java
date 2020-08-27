package bo.custom.impl;

import bo.custom.UsersBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.UsersDAO;
import entity.Users;

public class UsersBOImpl implements UsersBO {
    @Override
    public String getNewUserId() throws Exception {
        UsersDAO usersDAO = DAOFactory.getInstance().getDAO(DAOType.USERS);
        String lastUserId = usersDAO.getLastUserId();
        if (lastUserId == null){
            return "U001";
        }else {
            int maxID = Integer.parseInt(lastUserId.replace("U", ""));
            maxID = maxID + 1;
            String id = "";
            if (maxID < 10){
                id = "U00" + maxID;
            }else if (maxID < 100){
                id = "U0" + maxID;
            }else {
                id = "U" + maxID;
            }
            return id;
        }
    }

    @Override
    public boolean saveUsers(String id, String name, String password) throws Exception {
        UsersDAO usersDAO = DAOFactory.getInstance().getDAO(DAOType.USERS);
        return usersDAO.add(new Users(id,name,password));
    }
}
