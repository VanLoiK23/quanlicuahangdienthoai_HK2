package DAO_UserPhone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import ConnectMysql.Connectmysql;
import ModelUserPhone.PhieuXuat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class PhieuXuat_DAO {

	public int add(PhieuXuat t) {
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

	public int delete(PhieuXuat t) {
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
	public int maphieu() {
	    int check = 0;
	    Transaction transaction = null;

	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        String hql = "SELECT maphieu FROM PhieuXuat ORDER BY maphieu DESC";
	        Query<Integer> query = session.createQuery(hql, Integer.class);
	        query.setMaxResults(1); 
	        Integer result = query.uniqueResult();

	        if (result != null) {
	            check = result;
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }

	    return check ;
	}
	public int manv(int a) {
	    int customerId = 0;
	    Transaction transaction = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        String hql = "SELECT nguoitaophieuxuat FROM quan_ly_ban_hang.phieuxuat where maphieuxuat = "+a;
	        NativeQuery<Integer> query = session.createNativeQuery(hql);

            List<Integer> results = query.list();
            if (!results.isEmpty()) {
                customerId = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerId;
     }


}
