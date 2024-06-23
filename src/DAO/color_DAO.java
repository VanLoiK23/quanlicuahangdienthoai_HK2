package DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Color;
import AtttributeSanPham.Hedieuhanh;
import ConnectMysql.Connectmysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class color_DAO implements DAOInterface<Color>{

	@Override
	public int add(Color t) {
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
	public int update(Color t) {
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
	public int delete(Color t) {
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
	public ObservableList<Color> selectAll() {
		ObservableList<Color> check = FXCollections.observableArrayList();
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        List<Color> result = session.createQuery("FROM Color ORDER BY mamau ASC", Color.class).list(); 
	        check.addAll(result);
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}
	
	public ArrayList<Color> select() {
		ArrayList<Color> check = new ArrayList();
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        List<Color> result = session.createQuery("FROM Color ORDER BY mamau ASC", Color.class).list(); 
	        check.addAll(result);
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	@Override
	public Color selectByName(Color t) {
		return null;
	}
	public int selectByNam(String name) {
	    int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT mamau from AtttributeSanPham.Color where tenmau = :name";
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

