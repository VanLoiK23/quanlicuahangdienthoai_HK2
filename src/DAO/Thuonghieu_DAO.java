package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Hedieuhanh;
import AtttributeSanPham.Ram;
import AtttributeSanPham.Thuonghieu;
import AtttributeSanPham.Xuatxu;
import ConnectMysql.Connectmysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Thuonghieu_DAO implements DAOInterface<Thuonghieu>{

	@Override
	public int add(Thuonghieu t) {
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
	public int update(Thuonghieu t) {
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
	public int delete(Thuonghieu t) {
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
	public ObservableList<Thuonghieu> selectAll() {
		ObservableList<Thuonghieu> check = FXCollections.observableArrayList();
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        List<Thuonghieu> result = session.createQuery("FROM Thuonghieu", Thuonghieu.class).list(); 
	        check.addAll(result);
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	@Override
	public Thuonghieu selectByName(Thuonghieu t) {
		return null;
	}
	public int selectByNam(String name) {
	    int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT math from AtttributeSanPham.Thuonghieu where tenthuonghieu = :name";
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
