package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ConnectMysql.Connectmysql;
import Model.PhieuNhap;
import Model.Sanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class PhieuNhap_DAO implements DAOInterface<PhieuNhap>{

	
	  public static PhieuNhap_DAO getInstance() {
	        return new PhieuNhap_DAO();
	    }
	@Override
	public int add(PhieuNhap t) {
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
	public int update(PhieuNhap t) {
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
	public int delete(PhieuNhap phieuNhap) {
	    Session session = Hibernate_util.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();

	      
	        
	        Query deleteDetails1 = session.createQuery("DELETE FROM ChitietSanpham WHERE maphieunhap = :maphieunhap");
	        deleteDetails1.setParameter("maphieunhap", phieuNhap.getMaphieu());
	        deleteDetails1.executeUpdate();
	        
	        Query deleteDetails2 = session.createQuery("DELETE FROM ChitietPhieuNhap WHERE maphieunhap = :maphieunhap");
	        deleteDetails2.setParameter("maphieunhap", phieuNhap.getMaphieu());
	        deleteDetails2.executeUpdate();

	        session.delete(phieuNhap);
	        

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return 0;
	}


	@Override
	public ObservableList<PhieuNhap> selectAll() {
		ObservableList<PhieuNhap> check = FXCollections.observableArrayList();
		try {
		    Connection connect = Connectmysql.getConnection();
		    java.sql.Statement st = connect.createStatement();
		    String sql = "SELECT pn.maphieunhap, "
		               + "pn.thoigian, "
		               + "pn.tongtien, "
		               + "nv.hoten, "
		               + "ncc.tennhacungcap "
		               + "FROM quan_ly_ban_hang.phieunhap AS pn "
		               + "LEFT JOIN quan_ly_ban_hang.nhanvien AS nv ON pn.nguoitaophieunhap = nv.manv "
		               + "LEFT JOIN quan_ly_ban_hang.nhacungcap AS ncc ON pn.manhacungcap = ncc.manhacungcap;";
		    ResultSet rs = st.executeQuery(sql);
		    while (rs.next()) {
		        check.add(new PhieuNhap(rs.getInt("maphieunhap"),
		                                rs.getString("tennhacungcap"),
		                                rs.getString("hoten"),
		                                rs.getTimestamp("thoigian"),
		                                rs.getLong("tongtien")));
		    }
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
	        String hql = "SELECT maphieu FROM PhieuNhap ORDER BY maphieu DESC";
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

	    return check + 1;
	}

	@Override
	public PhieuNhap selectByName(PhieuNhap t) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public PhieuNhap selectById(String t) {
    	PhieuNhap result = null;
        try {
            Connection con = (Connection) Connectmysql.getConnection();
            String sql = "SELECT * FROM phieunhap WHERE maphieunhap=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieunhap");
                Timestamp thoigiantao = rs.getTimestamp("thoigian");
                int mancc = rs.getInt("manhacungcap");
                int nguoitao = rs.getInt("nguoitaophieunhap");
                long tongtien = rs.getLong("tongtien");
                result = new PhieuNhap(mancc, maphieu, nguoitao, thoigiantao, tongtien);
            }
            con.close();
        } catch (Exception e) {
        }
        return result;
    }

}
