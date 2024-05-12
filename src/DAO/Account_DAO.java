package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Ram;
import ConnectMysql.Connectmysql;
import Model.Khachhang;
import Model.Sanpham;
import Model.Taikhoan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Account_DAO {
	public int add(Taikhoan t) {
	    int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        Transaction tx = session.beginTransaction();
	        
	        Serializable id = session.save(t);
	        if (id != null) {
	            Khachhang khachhang = new Khachhang();
	            khachhang.setMaKH((int) id);
	            khachhang.setHoten(t.getUsername());
	            khachhang.setDiachi(t.getAddress());
	            khachhang.setSdt(t.getSdt());
	            khachhang.setTrangthai(t.getTrangthai());
	            khachhang.setNgaythamgia(LocalDateTime.now());
	            session.save(khachhang);
	        }
	        
	        tx.commit();
	        check = 1; 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	public int update(Taikhoan t) {
	    int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        Transaction tx = session.beginTransaction();
	        
	        String hql = "UPDATE Khachhang SET tenkhachhang = :name, diachi = :addr, sdt = :phone ,trangthai = :so WHERE makh = :value";
	        Query query = session.createQuery(hql);
	        query.setParameter("name", t.getUsername());
	        query.setParameter("addr", t.getAddress());
	        query.setParameter("phone", t.getSdt());
	        query.setParameter("so", t.getTrangthai());
	        query.setParameter("value", t.getId());
	        int rowCount = query.executeUpdate();
	        
	        session.update(t);
	        
	        tx.commit();
	        check = 1; 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}



	public int delete(Khachhang t) {
	    int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        Transaction tx = session.beginTransaction();
	        
	        session.delete(t);
	        
	        Taikhoan taikhoan = session.get(Taikhoan.class, t.getMaKH());
	        if (taikhoan != null) {
	            session.delete(taikhoan);
	        }
	        
	        tx.commit();
	        check = 1; 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	public boolean khachhang(Taikhoan t) {
	    boolean check = false;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT COUNT(*) FROM Taikhoan WHERE BINARY username = :username AND matkhau = :matkhau";
	        Query<Long> query = session.createQuery(hql, Long.class);
	        query.setParameter("username", t.getUsername());
	        query.setParameter("matkhau", t.getMatkhau());
	        
	        Long count = query.uniqueResult();
	        if (count != null && count > 0) {
	            check = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	public boolean admin(Taikhoan t) {
	    boolean check = false;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT COUNT(*) FROM Taikhoan WHERE BINARY username = :username AND matkhau = :matkhau AND vaitro = 'admin'";
	        Query<Long> query = session.createQuery(hql, Long.class);
	        query.setParameter("username", t.getUsername());
	        query.setParameter("matkhau", t.getMatkhau());
	        
	        Long count = query.uniqueResult();
	        if (count != null && count > 0) {
	            check = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	public ObservableList<Khachhang> selectAll() {
		ObservableList<Khachhang> check = FXCollections.observableArrayList();
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        List<Khachhang> result = session.createQuery("FROM Khachhang", Khachhang.class).list(); 
	        check.addAll(result);
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	public Taikhoan selectByName(Taikhoan t) {
		// TODO Auto-generated method stub
		return null;
	}

}
