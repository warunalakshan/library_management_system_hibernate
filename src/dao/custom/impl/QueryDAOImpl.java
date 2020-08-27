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
        ResultSet resultSet = CrudUtil.execute("select i.issue_id, i.member_id, i.book_id, b.book_name, \n" +
                "       DATE_ADD(i.issue_date, INTERVAL 14 DAY) AS return_Date, (CURDATE()-i.issue_date)-14\n" +
                "                AS lateDays, (2*((CURDATE()-i.issue_date)-14)) AS fine_fee from Issue i\n" +
                "                INNER join Books b on i.book_id = b.book_id ");
        List<CustomEntity> customEntities = new ArrayList<>();

       while (resultSet.next()){
           customEntities.add(new CustomEntity(resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getString(3),
                   resultSet.getString(4),
                   resultSet.getDate(5),
                   resultSet.getInt(6),
                   resultSet.getDouble(7)));
       }
       return customEntities;
    }
}
