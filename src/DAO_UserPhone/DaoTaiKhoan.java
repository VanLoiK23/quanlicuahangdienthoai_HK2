package DAO_UserPhone;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import ModelUserPhone.Taikhoan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoTaiKhoan {

	 public ObservableList<Taikhoan> getList() {
		        ObservableList<Taikhoan> itemData = FXCollections.observableArrayList();
		        Taikhoan model=null;
		        try  {
		        	 Session session =HibernateUtil.getSessionFactory().openSession();
			        	 
		             List<Taikhoan> resultList = session.createQuery("FROM Taikhoan").getResultList();
		            itemData.addAll(resultList);
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return itemData;
		    }
		 
	 public int getId(String name) {
	        int customerId = -1;
	        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	        try (Session session = sessionFactory.openSession()) {
	            String sql = "SELECT makh FROM khachhang WHERE tenkhachhang = :customerName";
	            NativeQuery<Integer> query = session.createNativeQuery(sql);
	            query.setParameter("customerName", name);

	            List<Integer> results = query.list();
	            if (!results.isEmpty()) {
	                customerId = results.get(0);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return customerId;
	 }

}
