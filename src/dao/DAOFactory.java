package dao;


import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

        public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case MEMBER:
                return (T) new MembersDAOImpl();
            case BOOK:
                return (T) new BooksDAOImpl();
            case ISSUE:
                return (T) new IssueDAOImpl();
            case RETURN:
                return (T) new ReturnsDAOImpl();
            case USERS:
                return (T) new UersDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}

