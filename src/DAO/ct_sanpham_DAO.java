package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ConnectMysql.Connectmysql;
import Model.ChitietSanpham;
import Model.Khuvuckho;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class ct_sanpham_DAO implements DAOInterface<ChitietSanpham>{

	@Override
	public int add(ChitietSanpham t) {
		int check = 0;
        SessionFactory factory = Hibernate_util.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            check = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
	}
	@Override
	public int update(ChitietSanpham t) {
	    int check = 0;
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        session.update(t); 
	        tx.commit();
	        session.close();
	        check = 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	@Override
	public int delete(ChitietSanpham t) {
	    int check = 0;
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        session.delete(t); 
	        tx.commit();
	        session.close();
	        check = 1; 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	@Override
	public ObservableList<ChitietSanpham> selectAll() {
	    

	    return null;
	}
	
	public ObservableList<ChitietSanpham> select(int a,int b) {
	    ObservableList<ChitietSanpham> resultList = FXCollections.observableArrayList();

	    try (Connection connection = Connectmysql.getConnection();
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery("SELECT cts.maimei " +
	                                                        "FROM ctsanpham cts " +
	                                                        "WHERE cts.maphienbansp =" +a+
	                                                        " AND cts.maphieunhap ="+b)) {

	        while (resultSet.next()) {
	            long maimei = resultSet.getLong("maimei");
	            resultList.add(new ChitietSanpham(maimei));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return resultList;
	}
	public ObservableList<ChitietSanpham> selectpx(int a,int b) {
	    ObservableList<ChitietSanpham> resultList = FXCollections.observableArrayList();

	    try (Connection connection = Connectmysql.getConnection();
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery("SELECT cts.maimei " +
	                                                        "FROM ctsanpham cts " +
	                                                        "WHERE cts.maphienbansp =" +a+
	                                                        " AND cts.maphieuxuat ="+b)) {

	        while (resultSet.next()) {
	            long maimei = resultSet.getLong("maimei");
	            resultList.add(new ChitietSanpham(maimei));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return resultList;
	}
	
	public ObservableList<ChitietSanpham> selectBymasp(int a) {
	    ObservableList<ChitietSanpham> resultList = FXCollections.observableArrayList();

	    try (Connection connection = Connectmysql.getConnection();
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery("SELECT * " +
	                                                        "FROM ctsanpham cts " +
	                                                        "WHERE cts.maphienbansp =" +a
	                                                       )) {

	        while (resultSet.next()) {
	            long maimei = resultSet.getLong("maimei");
	            int ban = resultSet.getInt("maphienbansp");
	            int n = resultSet.getInt("maphieunhap");
	            int x = resultSet.getInt("maphieuxuat");
	            int tt=resultSet.getInt("tinhtrang");
	            resultList.add(new ChitietSanpham(maimei,ban,n,x,tt));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return resultList;
	}


	@Override
	public ChitietSanpham selectByName(ChitietSanpham t) {
		// TODO Auto-generated method stub
		return null;
	}

}
