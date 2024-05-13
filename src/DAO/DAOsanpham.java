package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ModelUserPhone.modelsanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOsanpham {
	modelsanpham model;
	 public ObservableList<modelsanpham> selectAll() {
	        ObservableList<modelsanpham> check = FXCollections.observableArrayList();
	        try {
	            String url = "jdbc:mysql://localhost:3306/quan_ly_ban_hang";
	            String username = "root";
	            String password = "1122";
	            Connection connection = null;
	            connection = DriverManager.getConnection(url, username, password);
	            String sql = "SELECT sp.masp, " +
	                         "sp.tensp, " +
	                         "sp.hinhanh, " +
	                         "sp.soluongton, " +
	                         "gx.giaxuat " +
	                         "FROM quan_ly_ban_hang.sanpham AS sp " +
	                         "LEFT JOIN phienbansanpham AS gx ON sp.masp = gx.masp";
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(sql);
	            while (rs.next()) {
	                check.add(new modelsanpham(rs.getInt("masp"),
	                                           rs.getString("tensp"),
	                                           rs.getString("hinhanh"),
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
