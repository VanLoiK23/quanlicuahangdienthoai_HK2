package DAO_UserPhone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ModelUserPhone.ModelSanPhamDatHang;
import ModelUserPhone.modelsanpham;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class DAO_sanphamdathang {

	public int add(ModelSanPhamDatHang model) {
		int n = 0;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();
            n = 1;
        } catch (Exception e) {
        	   System.out.println("err");
        }
        return n;
	}
	
	  public int update(int soluongton,modelsanpham t) {
			int n = 0;
		    try {
		        Session session = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session.beginTransaction();
		        session.update(t); 
		        tx.commit();
		        session.close();
		        n = 1;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return n;
		}
	
}
