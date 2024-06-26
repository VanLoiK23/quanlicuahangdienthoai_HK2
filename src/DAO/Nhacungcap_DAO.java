package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Ram;
import AtttributeSanPham.Thuonghieu;
import ConnectMysql.Connectmysql;
import Model.Nhacungcap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Nhacungcap_DAO implements DAOInterface<Nhacungcap>{

	
	 public static Nhacungcap_DAO getInstance(){
	        return new Nhacungcap_DAO();
	    }
	@Override
	public int add(Nhacungcap t) {
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
	public int update(Nhacungcap t) {
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
	public int delete(Nhacungcap t) {
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
	public ObservableList<Nhacungcap> selectAll() {
		 ObservableList<Nhacungcap> check = FXCollections.observableArrayList();
		    try {
		        Session session = Hibernate_util.getSessionFactory().openSession();
		        List<Nhacungcap> result = session.createQuery("FROM Nhacungcap", Nhacungcap.class).list(); 
		        check.addAll(result);
		        session.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}

	@Override
	public Nhacungcap selectByName(Nhacungcap t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int selectByNam(String name) {
	    int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT mancc from Model.Nhacungcap where tennhacungcap = :name";
	        Query<Integer> query = session.createQuery(hql, Integer.class);
	        query.setParameter("name", name);
	        List<Integer> results = query.list();
	        if (!results.isEmpty()) {
	            check = results.get(0);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}
	
	public Nhacungcap selectById(String t) {
		Nhacungcap result = null;
        try {
            Connection con = (Connection) Connectmysql.getConnection();
            String sql = "SELECT * FROM nhacungcap WHERE manhacungcap=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("manhacungcap");
                String tenncc = rs.getString("tennhacungcap");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");
                
                result = new Nhacungcap(mancc,tenncc,diachi,email,sdt);
            }
            con.close();
        } catch (Exception e) {
        }
        return result;
    }

}
