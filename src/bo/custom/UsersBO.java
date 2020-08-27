package bo.custom;

import bo.SuperBO;

public interface UsersBO extends SuperBO {
    public String getNewUserId() throws Exception;
    public boolean saveUsers(String id, String name, String password) throws Exception;
}
