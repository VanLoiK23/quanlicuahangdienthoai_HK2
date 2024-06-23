package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ConnectMysql.Connectmysql;
import Model.ChitietPhieuNhap;
import Model.ChitietSanpham;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class ct_phieunhap_DAO implements DAOInterface<ChitietPhieuNhap>{

	
	public static ct_phieunhap_DAO getInstance() {
        return new ct_phieunhap_DAO();
    }
	@Override
	public int add(ChitietPhieuNhap t) {
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
	public int update(ChitietPhieuNhap t) {
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
	public int delete(ChitietPhieuNhap t) {
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
	public ObservableList<ChitietPhieuNhap> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	  public ArrayList<ChitietPhieuNhap> select(String t) {
	        ArrayList<ChitietPhieuNhap> result = new ArrayList<>();
	        try {
	            Connection con = (Connection) Connectmysql.getConnection();
	            String sql = "SELECT * FROM chitietphieunhap WHERE maphieunhap = ?";
	            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	            pst.setString(1, t);
	            ResultSet rs = (ResultSet) pst.executeQuery();
	            while (rs.next()) {
	                int maphieu = rs.getInt("maphieunhap");
	                int maphienbansp = rs.getInt("maphienbansp");
	                int dongia = rs.getInt("dongia");
	                int soluong = rs.getInt("soluong");
	                ChitietPhieuNhap ctphieu = new ChitietPhieuNhap( maphieu, maphienbansp, soluong, dongia);
	                result.add(ctphieu);
	            }
	            con.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	        return result;
	    }

	@Override
	public ChitietPhieuNhap selectByName(ChitietPhieuNhap t) {
		// TODO Auto-generated method stub
		return null;
	}

}
