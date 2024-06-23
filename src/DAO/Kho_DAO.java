package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Xuatxu;
import ConnectMysql.Connectmysql;
import Model.Khuvuckho;
import Model.Nhacungcap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Kho_DAO implements DAOInterface<Khuvuckho> {

	@Override
	public int add(Khuvuckho t) {
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
	public int update(Khuvuckho t) {
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
	public int delete(Khuvuckho t) {
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
	public ObservableList<Khuvuckho> selectAll() {
		 ObservableList<Khuvuckho> check = FXCollections.observableArrayList();
		    try {
		        Session session = Hibernate_util.getSessionFactory().openSession();
		        List<Khuvuckho> result = session.createQuery("FROM Khuvuckho", Khuvuckho.class).list(); 
		        check.addAll(result);
		        session.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}


	@Override
	public Khuvuckho selectByName(Khuvuckho t) {
		// TODO Auto-generated method stub
		return null;
	}
	public int selectByNam(String name) {
		int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT makhuvuc from Model.Khuvuckho where tenkhuvuc = :name";
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
	public String ten(int id) {
		String check = "";
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT tenkhuvuc from Model.Khuvuckho where makhuvuc = :id";
	        Query<String> query = session.createQuery(hql, String.class);
	        query.setParameter("id", id);
	        List<String> results = query.list();
	        if (!results.isEmpty()) {
	            check = results.get(0);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}
}
