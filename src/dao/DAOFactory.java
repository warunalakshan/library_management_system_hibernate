package dao;


import dao.custom.membersDAO;
import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public membersDAO getMembersDAO(){
        return new membersDAOImpl();
        }
        public <T extends superDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case MEMBER:
                return (T) new membersDAOImpl();
            case BOOK:
                return (T) new booksDAOImpl();
            case ISSUE:
                return (T) new issueDAOImpl();
            case RETURN:
                return (T) new returnsDAOImpl();
            case USERS:
                return (T) new usersDAOImpl();
            default:
                return null;
        }
    }
}

