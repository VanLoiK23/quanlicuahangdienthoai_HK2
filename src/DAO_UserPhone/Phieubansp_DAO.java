package DAO_UserPhone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ConnectMysql.Connectmysql;
import ModelUserPhone.Phieubansanpham;
import ModelUserPhone.modelsanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Phieubansp_DAO {

	

	public int update(Phieubansanpham t) {
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
	
	public int updateQuantity(int a,int b) {
	    int check = 0;
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        
	        Query query = session.createQuery(
	            "UPDATE Phieubansanpham sp " +
	            "SET sp.soluongton = sp.soluongton - :additionalQuantity " +
	            "WHERE sp.maphienbansp = :productId");
	        query.setParameter("additionalQuantity", b);
	        query.setParameter("productId", a);
	        
	        int rowCount = query.executeUpdate();
	        
	        tx.commit();
	        session.close();
	        
	        if (rowCount > 0) {
	            check = 1;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}

	public int updatesl(int masp) {
	    int check = 0;
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = Hibernate_util.getConnection();
	        
	        String sql = "UPDATE sanpham sp " +
	                     "SET sp.Soluongton = (SELECT SUM(pbsp.soluongton) " +
	                     "                      FROM phienbansanpham pbsp " +
	                     "                      WHERE pbsp.masp = ?) " +
	                     "WHERE sp.masp = ?";
	                     
	        preparedStatement = connection.prepareStatement(sql);
	        
	        preparedStatement.setInt(1, masp);
	        preparedStatement.setInt(2, masp);
	        
	        int rowCount = preparedStatement.executeUpdate();
	        
	        if (rowCount > 0) {
	            check = 1;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return check;
	}
	public int getQuantityByPhieuBanSP(int maphieuban) {
        int quantity = 0;
        try (Session session = Hibernate_util.getSessionFactory().openSession()) {
            String hql = "SELECT pbsp.soluongton FROM Phieubansanpham pbsp WHERE pbsp.maphienbansp = :maphieuban";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            query.setParameter("maphieuban", maphieuban);
            List<Integer> results = query.list();
            if (!results.isEmpty()) {
            	Integer result = results.get(0);
                if (result != null) {
                    quantity = result.intValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }
  
	public ObservableList<Phieubansanpham> ctphieunhap(int phieunhap) {
	    ObservableList<Phieubansanpham> check = FXCollections.observableArrayList();
	    try {
	        Connection connect = Connectmysql.getConnection();
	        java.sql.Statement st = connect.createStatement();
	        String sql = "SELECT sp.masp, "+
	                      " sp.maphienbansp,"
	                    +"ra.kichthuocram, " +
	                     "ro.kichthuocrom, " +
	                     "col.tenmau, " +
	                     "sp.gianhap, " +
	                     "s.tensp, " +
	                     "ct.soluong " +
	                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " +
	                     "LEFT JOIN quan_ly_ban_hang.dungluongram AS ra ON sp.ram = ra.madlram " +
	                     "LEFT JOIN quan_ly_ban_hang.dungluongrom AS ro ON sp.rom = ro.madlrom " +
	                     "LEFT JOIN quan_ly_ban_hang.mausac AS col ON sp.mausac = col.mamau "+
	                     "LEFT JOIN quan_ly_ban_hang.sanpham AS s ON sp.masp = s.masp "+
	                     "JOIN chitietphieunhap  ct ON sp.maphienbansp  = ct.maphienbansp"
	                      + " WHERE ct.maphieunhap = "+phieunhap;
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            check.add(new Phieubansanpham( rs.getInt("maphienbansp"),
	            		                       rs.getInt("masp"),
	            		                       rs.getString("tensp"),
	                                           rs.getInt("kichthuocram"),
	                                           rs.getInt("kichthuocrom"),
	                                           rs.getString("tenmau"),
	                                           rs.getInt("gianhap"),
	                                           rs.getInt("soluong")));
	        }
	        Connectmysql.close(connect);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		    return check;
	}
	
	public int select512(modelsanpham t,int a) {
		int check = 0;
		    try {
		        Connection connect = Connectmysql.getConnection();
		        java.sql.Statement st = connect.createStatement();
		        if(a==2) {
		        	String sql = "SELECT sp.maphienbansp, "+
		                     "sp.giaxuat " +	                    
		                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " 
		                      + "WHERE sp.masp = "+t.getMasp()+" and sp.rom=2;";
		        	 ResultSet rs = st.executeQuery(sql);
				        while (rs.next()) {
				          
				                                        check=   rs.getInt("giaxuat");
				                                        writeNumberToFile(rs.getInt("maphienbansp"),"ma.txt"); 
				        }
				        Connectmysql.close(connect);
				        return check;
		        }
		        else {
		        	String sql = "SELECT sp.maphienbansp, "+
		                     "sp.giaxuat " +	                    
		                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " 
		                      + "WHERE sp.masp = "+t.getMasp()+" and sp.rom=3;";
		        	 ResultSet rs = st.executeQuery(sql);
				        while (rs.next()) {
				                                        check=   rs.getInt("giaxuat");
				                                        writeNumberToFile(rs.getInt("maphienbansp"),"ma.txt"); 
				        }
				        Connectmysql.close(connect);
				        return check;

		        }
		       
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}
	public int selectMapb(String name,int rom) {
		int check = 0;
		DAOsanpham d=new DAOsanpham();
		    try {
		        Connection connect = Connectmysql.getConnection();
		        java.sql.Statement st = connect.createStatement();
		        	String sql = "SELECT sp.maphienbansp "+	                    
		                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " 
		                      + "WHERE sp.masp = "+d.getMaspByTensp(name)+" and sp.rom="+d.selectByNam(rom);
		        	 ResultSet rs = st.executeQuery(sql);
				        while (rs.next()) {
				          
				                                        check=   rs.getInt("maphienbansp");
				        }
				        Connectmysql.close(connect);
				        return check;
		       
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}
	public static void writeNumberToFile(int number, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(String.valueOf(number));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
