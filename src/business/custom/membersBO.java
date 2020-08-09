package business.custom;

import business.SuperBO;
import util.ShowMembersTM;

import java.util.List;

public interface membersBO extends SuperBO {
    public  String getNewMemberId() throws Exception;

    public List<ShowMembersTM> getAllMembers()throws Exception;

    public  boolean SaveMembers(String id, String name, String address, String nic, String contact) throws Exception;

    public boolean DeleteMember(String memberId) throws Exception;

    public boolean updateCustomer(String name, String address, String nic, String contact, String MemberId) throws Exception;

}
