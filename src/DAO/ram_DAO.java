package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Ram;
import AtttributeSanPham.Rom;
import ConnectMysql.Connectmysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class ram_DAO implements DAOInterface<Ram>{

	@Override
	public int add(Ram t) {
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
	public int update(Ram t) {
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
	public int delete(Ram t) {
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
	public ObservableList<Ram> selectAll() {
	    ObservableList<Ram> check = FXCollections.observableArrayList();
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        List<Ram> result = session.createQuery("FROM Ram", Ram.class).list(); 
	        check.addAll(result);
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	public ArrayList<Ram> select() {
		ArrayList<Ram> check = new ArrayList();
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        List<Ram> result = session.createQuery("FROM Ram", Ram.class).list(); 
	        check.addAll(result);
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	@Override
	public Ram selectByName(Ram t) {
		return null;
	}
	public int selectByNam(Integer name) {
	    int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT madl FROM AtttributeSanPham.Ram WHERE kichthuocram = :name";
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
