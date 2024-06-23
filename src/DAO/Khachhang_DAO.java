package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ConnectMysql.Connectmysql;
import Model.Khachhang;
import Model.Nhacungcap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Khachhang_DAO {

	
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

	public static Khachhang_DAO getInstance() {
		
		return new Khachhang_DAO();
	}

	
	public Khachhang selectById(String t) {
		Khachhang result = null;
        try {
            Connection con = (Connection) Connectmysql.getConnection();
            String sql = "SELECT * FROM khachhang WHERE makh=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("makh");
                String tenncc = rs.getString("tenkhachhang");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                
                result = new Khachhang(mancc,tenncc,diachi,sdt);
            }
            con.close();
        } catch (Exception e) {
        }
        return result;
    }

	
}
