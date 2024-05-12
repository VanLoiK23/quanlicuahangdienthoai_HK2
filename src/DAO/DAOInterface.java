package DAO;

import java.util.ArrayList;

import Model.Sanpham;
import javafx.collections.ObservableList;

public interface DAOInterface<T> {
   public int add(T t);
   
   public int update(T t);
   
   public int delete(T t);
   
   public ObservableList<T> selectAll();
   
   public T selectByName(T t);
   
}
