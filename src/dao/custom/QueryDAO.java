package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;
import entity.Members;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<CustomEntity> getReturnDetails()throws Exception;

}
