package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Thongkesp;
import util.Hibernate_util;

public class Thongke_sp_DAO  {
	public void updateThongKeTongQuan() {
	    Session session = null;
	    Transaction transaction = null;

	    try {
	        session = Hibernate_util.getSessionFactory().openSession();
	        transaction = session.beginTransaction();

	        session.createNativeQuery("TRUNCATE TABLE thongke_sp").executeUpdate();

	        String sql = "SELECT sp.tensp, SUM(ctx.soluong) AS tong_soluong " +
	                     "FROM chitietphieuxuat ctx " +
	                     "JOIN phienbansanpham pbp ON ctx.maphienbansp = pbp.maphienbansp " +
	                     "JOIN sanpham sp ON pbp.masp = sp.masp " +
	                     "GROUP BY sp.tensp";

	        List<Object[]> results = session.createNativeQuery(sql).getResultList();

	        for (Object[] result : results) {
	            String tensanpham = (String) result[0];
	            int soluong = ((Number) result[1]).intValue(); 

	            Thongkesp thongke = new Thongkesp();
	            thongke.setTensanpham(tensanpham);
	            thongke.setSoluong(soluong);

	            session.save(thongke);
	        }

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


	public List<Thongkesp> getAllThongkeSp() {
	    List<Thongkesp> thongkeList = null;
	    Transaction transaction = null;
	    try (Session session = Hibernate_util.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        thongkeList = session.createQuery("FROM Thongkesp", Thongkesp.class).list();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return thongkeList;
	}


}
