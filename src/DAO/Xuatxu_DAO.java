package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Thuonghieu;
import AtttributeSanPham.Xuatxu;
import ConnectMysql.Connectmysql;
import Model.Khachhang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Xuatxu_DAO implements DAOInterface<Xuatxu>{
	@Override
    public int add(Xuatxu t) {
        int check = 0;
        try (Session session = Hibernate_util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(t);
            tx.commit();
            check = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
	@Override
	public int update(Xuatxu t) {
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
	public int delete(Xuatxu t) {
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
	public ObservableList<Xuatxu> selectAll() {
		ObservableList<Xuatxu> check = FXCollections.observableArrayList();
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        List<Xuatxu> result = session.createQuery("FROM Xuatxu", Xuatxu.class).list(); 
	        check.addAll(result);
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}
	@Override
	public Xuatxu selectByName(Xuatxu t) {
		// TODO Auto-generated method stub
		return null;
	}
	public int selectByNam(String name) {
		int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
			String hql="SELECT maxuatxu from AtttributeSanPham.Xuatxu where tenxuatxu = :name";
	        Query<Integer> query = session.createQuery(hql, Integer.class);
	        query.setParameter("name", name);
	        List<Integer> results = query.list();
	        if (!results.isEmpty()) {
	            check = results.get(0);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}
	
}
