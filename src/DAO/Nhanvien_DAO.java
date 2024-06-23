package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ConnectMysql.Connectmysql;
import Model.Nhacungcap;
import Model.Nhanvien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Nhanvien_DAO implements DAOInterface<Nhanvien>{

	
	 public static Nhanvien_DAO getInstance(){
	        return new Nhanvien_DAO();
	    }
	@Override
	public int add(Nhanvien t) {
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
	public int update(Nhanvien t) {
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
	public int delete(Nhanvien t) {
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
	public ObservableList<Nhanvien> selectAll() {
		 ObservableList<Nhanvien> check = FXCollections.observableArrayList();
		    try {
		        Session session = Hibernate_util.getSessionFactory().openSession();
		        List<Nhanvien> result = session.createQuery("FROM Nhanvien", Nhanvien.class).list(); 
		        check.addAll(result);
		        session.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}

	@Override
	public Nhanvien selectByName(Nhanvien t) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int selectByNam(String name) {
	    int check = 0;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        String hql = "SELECT manv from Model.Nhanvien where hoten = :name";
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
	
	 public String selectById(String t) {
	        String result = "";
	        try {
	            Connection con = (Connection) Connectmysql.getConnection();
	            String sql = "SELECT * FROM nhanvien WHERE manv=?";
	            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	            pst.setString(1, t);
	            ResultSet rs = (ResultSet) pst.executeQuery();
	            while(rs.next()){
	                int manv = rs.getInt("manv");
	                String hoten = rs.getString("hoten");
	                String gioitinh = rs.getString("gioitinh");
	                Date ngaysinh = rs.getDate("ngaysinh");
	                String sdt = rs.getString("sdt");
	                int trangthai = rs.getInt("trangthai");
	                String email = rs.getString("email");
	                result=hoten;
	            }
	            con.close();
	        } catch (Exception e) {
	        }
	        return result;
	    }
}
