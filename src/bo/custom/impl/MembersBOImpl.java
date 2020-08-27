package bo.custom.impl;

import bo.custom.MembersBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.BooksDAO;
import dao.custom.MembersDAO;
import entity.Members;
import util.ShowMembersTM;

import java.util.ArrayList;
import java.util.List;

public class MembersBOImpl implements MembersBO {

    @Override
    public String getNewMemberId() throws Exception {


        MembersDAO membersDAO = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        String lastMemberId = membersDAO.getLastMemberID();
        if (lastMemberId == null) {
            return "M001";
        } else {
            int maxId = Integer.parseInt(lastMemberId.replace("M", ""));
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

        MembersDAO memberDao = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        List<Members> allMembers = memberDao.findAll();
        List<ShowMembersTM> membersTMS = new ArrayList<>();

        for (Members member : allMembers){
            membersTMS.add(new ShowMembersTM(member.getId(),
                    member.getName(),
                    member.getAddress(),
                    member.getNic(),
                    member.getContact()));
        }
        return membersTMS;
    }

    @Override
    public boolean SaveMembers(String id, String name, String address, String nic, String contact) throws Exception {
        MembersDAO membersDao = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        return membersDao.add(new Members(id,name,address, nic, contact));
    }

    @Override
    public boolean DeleteMember(String memberId) throws Exception {
        MembersDAO membersDao = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        return membersDao.delete(memberId);
    }

    @Override
    public boolean updateMember(String name, String address, String nic, String contact, String MemberId) throws Exception {
        MembersDAO membersDAO = DAOFactory.getInstance().getDAO(DAOType.MEMBER);
        return membersDAO.update(new Members(name, address, nic, contact, MemberId));
    }
}
