package bo.custom;

import bo.SuperBO;
import util.ShowMembersTM;

import java.util.List;

public interface MembersBO extends SuperBO {
    public  String getNewMemberId() throws Exception;

    public List<ShowMembersTM> getAllMembers()throws Exception;

    public  boolean SaveMembers(String id, String name, String address, String nic, String contact) throws Exception;

    public boolean DeleteMember(String memberId) throws Exception;

    public boolean updateMember(String name, String address, String nic, String contact, String MemberId) throws Exception;

}
