package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import AtttributeSanPham.Ram;
import AtttributeSanPham.Thuonghieu;
import ConnectMysql.Connectmysql;
import Model.Nhacungcap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Nhacungcap_DAO implements DAOInterface<Nhacungcap>{

	@Override
	public int add(Nhacungcap t) {
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
	public int update(Nhacungcap t) {
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
	public int delete(Nhacungcap t) {
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
	public ObservableList<Nhacungcap> selectAll() {
		 ObservableList<Nhacungcap> check = FXCollections.observableArrayList();
		    try {
		        Session session = Hibernate_util.getSessionFactory().openSession();
		        List<Nhacungcap> result = session.createQuery("FROM Nhacungcap", Nhacungcap.class).list(); 
		        check.addAll(result);
		        session.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}

	@Override
	public Nhacungcap selectByName(Nhacungcap t) {
		// TODO Auto-generated method stub
		return null;
	}

}
