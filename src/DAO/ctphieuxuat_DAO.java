package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ConnectMysql.Connectmysql;
import Model.ChitietPhieuNhap;
import Model.ChitietPhieuXuat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class ctphieuxuat_DAO {
	public static ctphieuxuat_DAO getInstance() {
        return new ctphieuxuat_DAO();
    }
	public int add(ChitietPhieuXuat t) {
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
	public int delete(ChitietPhieuXuat t) {
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
	public int update(ChitietPhieuXuat t) {
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
	public long sl(int a) {
	    long ma=0;

	    try (Connection connection = Connectmysql.getConnection();
	         Statement statement = connection.createStatement();
	    		ResultSet resultSet = statement.executeQuery("SELECT MAX(maimei) AS max_maimei " +
                        "FROM ctsanpham " +
                        "WHERE maphienbansp = " + a+" AND maphieuxuat IS NULL")) {
	        while (resultSet.next()) {
	            ma = resultSet.getLong("max_maimei");
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ma;
	}
	public int pn(long a) {
	    int ma=0;

	    try (Connection connection = Connectmysql.getConnection();
	         Statement statement = connection.createStatement();
	    		ResultSet resultSet = statement.executeQuery("SELECT maphieunhap  " +
                        "FROM ctsanpham " +
                        "WHERE maimei = '" + a+" '")) {
	        while (resultSet.next()) {
	            ma = resultSet.getInt("maphieunhap");
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ma;
	}
	public ArrayList<ChitietPhieuXuat> select(String t) {
        ArrayList<ChitietPhieuXuat> result = new ArrayList<>();
        try {
            Connection con = (Connection) Connectmysql.getConnection();
            String sql = "SELECT * FROM chitietphieuxuat WHERE maphieuxuat = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("maphieuxuat");
                int maphienbansp = rs.getInt("maphienbansp");
                int dongia = rs.getInt("dongia");
                int soluong = rs.getInt("soluong");
                ChitietPhieuXuat ctphieu = new ChitietPhieuXuat( maphieu, maphienbansp, soluong, dongia);
                result.add(ctphieu);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

	


}
