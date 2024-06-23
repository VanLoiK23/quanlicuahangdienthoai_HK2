package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Rom;
import Model.Thongke_tongquan;
import util.Hibernate_util;

public class Thongke_tongquan_DAO {
	public void updateThongKeTongQuan() {
	    Session session = null;
	    Transaction transaction = null;

	    try {
	        session = Hibernate_util.getSessionFactory().openSession();
	        transaction = session.beginTransaction();

	        String sql = (
	            "UPDATE thongke_tongquan tk " +
	            "SET tk.sanpham = (SELECT COUNT(*) FROM sanpham sp), " +
	            "tk.khachhang = (SELECT COUNT(*) FROM khachhang kh), " +
	            "tk.nhanvien = (SELECT COUNT(*) FROM nhanvien nv), " +
	            "tk.von = (SELECT SUM(pn.tongtien) FROM phieunhap pn), " +
	            "tk.doanthu = (SELECT SUM(px.tongtien) FROM phieuxuat px), " +
	            "tk.loinhuan = ((SELECT SUM(px.tongtien) FROM phieuxuat px) - " +
	            "(SELECT SUM(pn.tongtien) FROM phieunhap pn)) " +
	            "WHERE tk.nhanvien = :nhanvienId"
	        );

	        Query updateQuery = session.createNativeQuery(sql);
	        updateQuery.setParameter("nhanvienId", 3);

	        int rowsAffected = updateQuery.executeUpdate();
	        transaction.commit();

	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}


	public List<Thongke_tongquan> getAllThongkeTongquan() {
	    List<Thongke_tongquan> thongkeList = new ArrayList<>();
	    
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        
	        Query<Thongke_tongquan> query = session.createQuery("FROM Thongke_tongquan", Thongke_tongquan.class);
	        thongkeList = query.getResultList();
	        
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return thongkeList;
	}

}
