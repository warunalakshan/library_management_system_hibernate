package business.custom.impl;

import business.custom.membersBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.membersDAO;
import entity.members;
import util.ShowMembersTM;

import java.util.ArrayList;
import java.util.List;

public class membersBOImpl implements membersBO {
    @Override
    public String getNewMemberId() throws Exception {

        membersDAO membersDao = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        String lastmemberID = membersDao.getLastCustomerID();
        if (lastmemberID == null) {
            return "M001";
        } else {
            int maxId = Integer.parseInt(lastmemberID.replace("M", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "M00" + maxId;
            } else if (maxId < 100) {
                id = "M0" + maxId;
            } else {
                id = "M" + maxId;
            }
            return id;
        }
    }

    @Override
    public List<ShowMembersTM> getAllMembers() throws Exception {

        membersDAO memberDao = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        List<members> allMembers = memberDao.findAll();
        List<ShowMembersTM> membersTMS = new ArrayList<>();

        for (members member : allMembers){
            membersTMS.add(new ShowMembersTM(member.getId(), member.getName(), member.getAddress(), member.getNic(), member.getContact()));
        }
        return membersTMS;
    }

    @Override
    public boolean SaveMembers(String id, String name, String address, String nic, String contact) throws Exception {
        membersDAO membersDao = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        return membersDao.add(new members(id,name,address, nic, contact));
    }

    @Override
    public boolean DeleteMember(String memberId) throws Exception {
        membersDAO membersDao = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        return membersDao.delete(memberId);
    }

    @Override
    public boolean updateCustomer(String name, String address, String nic, String contact, String MemberId) throws Exception {
        membersDAO membersDao = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        return membersDao.update(new members(name, address, nic, contact, MemberId));
    }
}
