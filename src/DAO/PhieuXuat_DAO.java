package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ConnectMysql.Connectmysql;
import Model.PhieuNhap;
import Model.PhieuXuat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class PhieuXuat_DAO implements DAOInterface<PhieuXuat>{

	

	  public static PhieuXuat_DAO getInstance() {
	        return new PhieuXuat_DAO();
	    }
	@Override
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

	@Override
	public int update(PhieuXuat t) {
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

	@Override
	public ObservableList<PhieuXuat> selectAll() {
		ObservableList<PhieuXuat> check=FXCollections.observableArrayList();
		try {
			Connection connect = Connectmysql.getConnection();
			java.sql.Statement st =connect.createStatement();
			String sql="SELECT px.maphieuxuat,"
					+ "px.thoigian,"
					+ "px.tongtien,"
					+ "nv.hoten,"
					+ "kh.tenkhachhang "
					+ "from quan_ly_ban_hang.phieuxuat AS px"
					+ " LEFT JOIN"
					+ " quan_ly_ban_hang.nhanvien AS nv ON px.nguoitaophieuxuat = nv.manv"
					+ " LEFT JOIN"
					+ " quan_ly_ban_hang.khachhang AS kh ON px.makh = kh.makh;";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				check.add(new PhieuXuat(rs.getInt("maphieuxuat"),
						                rs.getString("tenkhachhang"),
						                rs.getString("hoten"),
						                rs.getTimestamp("thoigian"),
						                rs.getLong("tongtien")));
				
			}
			Connectmysql.close(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public PhieuXuat selectByName(PhieuXuat t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public PhieuXuat selectById(String t) {
		  PhieuXuat result = null;
	        try {
	            Connection con = (Connection) Connectmysql.getConnection();
	            String sql = "SELECT * FROM phieuxuat WHERE maphieuxuat=?";
	            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	            pst.setString(1, t);
	            ResultSet rs = (ResultSet) pst.executeQuery();
	            while (rs.next()) {
	                int maphieu = rs.getInt("maphieuxuat");
	                Timestamp thoigiantao = rs.getTimestamp("thoigian");
	                int mancc = rs.getInt("makh");
	                int nguoitao = rs.getInt("nguoitaophieuxuat");
	                long tongtien = rs.getLong("tongtien");
	                result = new PhieuXuat(mancc, maphieu, nguoitao, thoigiantao, tongtien);
	            }
	            con.close();
	        } catch (Exception e) {
	        }
	        return result;
	    }

}
