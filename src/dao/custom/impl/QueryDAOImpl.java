package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getReturnDetails() throws Exception {
        ResultSet resultSet = CrudUtil.execute("select i.issue_id, i.member_id, i.book_id, b.book_name, i.issue_date from Issue i\\n\" +\n" +
                "                    \"INNER join Books b on i.book_id = b.book_id ");
        ArrayList<CustomEntity> customEntities = new ArrayList<>();

       while (resultSet.next()){
           customEntities.add(new CustomEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                   resultSet.getString(4),resultSet.getDate(5),resultSet.getDate(6),resultSet.getInt(7)));
       }
       return customEntities;
    }
}
