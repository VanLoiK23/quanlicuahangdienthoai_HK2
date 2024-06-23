package DAO_UserPhone;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ModelUserPhone.ModelGiohang;
import ModelUserPhone.modelsanpham;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DAO_giohang {

	
	public int add(ModelGiohang t) {
		int n = 0;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            n = 1;

        } catch (Exception e) {

   System.out.println("err");
		
        }
     		 
        return n;
		}
		 public int delete(ModelGiohang t) {
				int n = 0;
			    try {
			        Session session = HibernateUtil.getSessionFactory().openSession();
			        Transaction tx = session.beginTransaction();
			        session.delete(t); 
			        tx.commit();
			        

			        session.close();
			        n = 1; 
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			    return n;
			}

	  public ObservableList<ModelGiohang> getList() {
	        ObservableList<ModelGiohang> itemData = FXCollections.observableArrayList();
	        ModelGiohang model=null;
	        try  {
	        	 Session session =HibernateUtil.getSessionFactory().openSession();
		        	 
	             List<ModelGiohang> resultList = session.createQuery("FROM ModelGiohang").getResultList();
	            itemData.addAll(resultList);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return itemData;
	    }
	 
//	  public boolean updatesoluong(Integer soluongton,Integer masp) {
//			Connection con = null;
//			String url = "jdbc:mysql://localhost:3306/quan_ly_ban_hang";
//		    String username = "root";
//		    String password = "binvaloi123";
//			try {
//				con = DriverManager.getConnection(url, username, password);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			   String sql = "UPDATE phienbansanpham SET soluongton=? WHERE maphienbansp =?";
//			  try { 
//				 
//			PreparedStatement ps = con.prepareStatement(sql);
//		      ps.setInt(1, soluongton);
//		      ps.setInt(2,masp);
//		  
//		    
//		     
//	         
//		      return ps.executeUpdate()>0;
//
//		   } catch (SQLException e) {
//			   Alert a = new Alert(AlertType.INFORMATION);
//	   		a.setTitle("Lỗi !");
//	  
//	   	a.showAndWait();
//		   
//
//		   }
//			return false;}
//	  
}


