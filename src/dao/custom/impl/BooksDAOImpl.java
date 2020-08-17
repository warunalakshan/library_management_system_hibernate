package dao.custom.impl;


import dao.CrudUtil;
import dao.custom.BooksDAO;
import entity.Books;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BooksDAOImpl implements BooksDAO {

 @Override
 public String getLastBookID() throws Exception {
  ResultSet resultSet = CrudUtil.execute("select * from Books order by book_id desc limit 1");
  if (resultSet.next()) {
   return resultSet.getString(1);
  } else {
   return null;
  }
 }

 @Override
 public List<Books> findAll() throws Exception {
  ResultSet resultSet = CrudUtil.execute("select * from Books");
  List<Books> booksList = new ArrayList<>();
  while (resultSet.next()){
   booksList.add(new Books(resultSet.getString(1),
           resultSet.getString(2),
           resultSet.getString(3),
           resultSet.getInt(4),
           resultSet.getString(5)));
  }
  return booksList;
 }

 @Override
 public Books find(String pk) throws Exception {
  ResultSet resultSet = CrudUtil.execute("select * from Books where book_id = ?", pk);

  if (resultSet.next()) {
   return new Books(resultSet.getString(1),
           resultSet.getString(2),
           resultSet.getString(3),
           resultSet.getInt(4),
           resultSet.getString(5));
  }
  return null;
 }

 @Override
 public boolean add(Books entity) throws Exception {
  return CrudUtil.execute("Insert into Books values(?,?,?,?,?)", entity.getId(), entity.getName(),
          entity.getAuthor(), entity.getQty(), entity.getIsbn());
 }

 @Override
 public boolean update(Books entity) throws Exception {
  return CrudUtil.execute("update Books set name=?, author=?, quantity=?, isbn=? where book_id =?", entity.getName(), entity.getAuthor(), entity.getQty(), entity.getIsbn(), entity.getId());
 }

 @Override
 public boolean delete(String pk) throws Exception {
  return CrudUtil.execute("delete from Books where book_id=?", pk);
 }
}
