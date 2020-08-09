package dao.custom.impl;


import dao.CrudUtil;
import dao.custom.booksDAO;
import entity.books;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class booksDAOImpl implements booksDAO {

 @Override
 public String getLastBookID() throws Exception {
  ResultSet resultSet = CrudUtil.execute("select * from books order by book_id desc limit 1");
  if (resultSet.next()) {
   return resultSet.getString(1);
  } else {
   return null;
  }
 }

 @Override
 public List<books> findAll() throws Exception {
  ResultSet resultSet = CrudUtil.execute("select * from books");
  List<books> booksList = new ArrayList<>();
  while (resultSet.next()){
   booksList.add(new books(resultSet.getString(1),
           resultSet.getString(2),
           resultSet.getString(3),
           resultSet.getInt(4),
           resultSet.getString(5)));
  }
  return booksList;
 }

 @Override
 public books find(String pk) throws Exception {
  ResultSet resultSet = CrudUtil.execute("select * from books where book_id = ?", pk);

  if (resultSet.next()) {
   return new books(resultSet.getString(1),
           resultSet.getString(2),
           resultSet.getString(3),
           resultSet.getInt(4),
           resultSet.getString(5));
  }
  return null;
 }

 @Override
 public boolean add(books entity) throws Exception {
  return CrudUtil.execute("Insert into books values(?,?,?,?,?)", entity.getId(), entity.getName(),
          entity.getAuthor(), entity.getQty(), entity.getIsbn());
 }

 @Override
 public boolean update(books entity) throws Exception {
  return CrudUtil.execute("update books set name=?, author=?, quantity=?, isbn=? where book_id =?", entity.getName(), entity.getAuthor(), entity.getQty(), entity.getIsbn(), entity.getId());
 }

 @Override
 public boolean delete(String pk) throws Exception {
  return CrudUtil.execute("delete from books where book_id=?", pk);
 }
}
