package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType){
        switch (boType){
            case BOOK:
                return (T) new BooksBOImpl();
            case MEMBER:
                return (T) new MembersBOImpl();
            case ISSUE:
                return (T) new IssueBOImpl();
            case RETURN:
                return (T) new ReturnsBOImpl();
            case USERS:
                return (T) new UsersBOImpl();
            default:
                return null;
        }
    }
}
