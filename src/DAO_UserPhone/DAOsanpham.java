package DAO_UserPhone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ConnectMysql.Connectmysql;
import ModelUserPhone.modelsanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class DAOsanpham {
	modelsanpham model;
	
	public int getMaspByTensp(String tensp) {
        int masp = -1; 

        try (Session session = Hibernate_util.getSessionFactory().openSession()) {
            String hql = "SELECT sp.masp FROM ModelUserPhone.modelsanpham sp WHERE sp.Phonename = :tensp";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            query.setParameter("tensp", tensp);
            Integer result = query.uniqueResult();
            if (result != null) {
                masp = result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return masp;
    }
	
	public int selectByNam(Integer name) {
	    int check = 0;
	    String sql = "SELECT madlrom FROM dungluongrom WHERE kichthuocrom = ?";
	    
	    try (Connection connection = Connectmysql.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setInt(1, name);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            check = resultSet.getInt("madlrom");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return check;
	}

	
	
	
	
	
	
	
	
	 public ObservableList<modelsanpham> selectAll() {
	        ObservableList<modelsanpham> check = FXCollections.observableArrayList();
	        try {
	            String url = "jdbc:mysql://localhost:3306/quan_ly_ban_hang";
	            String username = "root";
	            String password = "binvaloi123";
	            Connection connection = null;
	            connection = DriverManager.getConnection(url, username, password);
	            String sql = "SELECT sp.masp, " +
	                         "sp.tensp, " +
	                         "sp.hinhanh, " +
	                         "gx.maphienbansp, "+ 
	                         "gx.soluongton, " +
	                         "gx.giaxuat " +
	                         "FROM quan_ly_ban_hang.sanpham AS sp " +
	                         "LEFT JOIN phienbansanpham AS gx ON sp.masp = gx.masp";
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(sql);
	            while (rs.next()) {
	                check.add(new modelsanpham(rs.getInt("masp"),
	                		                   
	                                           rs.getString("tensp"),
	                                           rs.getString("hinhanh"),
	                                           rs.getInt("maphienbansp"),
	                                           rs.getInt("soluongton"),
	                                           rs.getInt("giaxuat")));
	               
	            }
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return check;
	    }
}
