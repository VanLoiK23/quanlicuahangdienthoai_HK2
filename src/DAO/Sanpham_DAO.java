package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import AtttributeSanPham.Color;
import AtttributeSanPham.Thuonghieu;
import ConnectMysql.Connectmysql;
import Model.Khuvuckho;
import Model.Sanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Sanpham_DAO implements DAOInterface<Sanpham>{

	@Override
	public int add(Sanpham t) {
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
	public int update(Sanpham t) {
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
	public int delete(Sanpham t) {
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
	public ObservableList<Sanpham> selectAll() {
		ObservableList<Sanpham> check=FXCollections.observableArrayList();
		try {
			Connection connect = Connectmysql.getConnection();
			java.sql.Statement st =connect.createStatement();
			String sql="SELECT sp.masp,"
					+ "sp.tensp,"
					+ "sp.soluongton,"
					+ "sp.camerasau,"
					+ "sp.cameratruoc,"
					+ "sp.phienbanhdh,"
					+ "sp.thoigianbaohanh,"
					+ "th.tenthuonghieu,"
					+ "hdh.tenhedieuhanh,"
					+ "sp.kichthuocman,"
					+ "sp.hinhanh,"
					+ "sp.dungluongpin,"
					+ "xuatxu.tenxuatxu,"
					+ "kvk.tenkhuvuc "
					+ "from quan_ly_ban_hang.sanpham AS sp"
					+ " LEFT JOIN"
					+ " quan_ly_ban_hang.xuatxu AS xuatxu ON sp.xuatxu=xuatxu.maxuatxu"
					+ " LEFT JOIN"
					+ " quan_ly_ban_hang.hedieuhanh AS hdh ON sp.hedieuhanh = hdh.mahedieuhanh"
					+ " LEFT JOIN"
					+ " quan_ly_ban_hang.thuonghieu AS th ON sp.thuonghieu = th.mathuonghieu"
					+ " LEFT JOIN"
					+ " quan_ly_ban_hang.khuvuckho AS kvk ON sp.khuvuckho = kvk.makhuvuc;";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				check.add(new Sanpham(rs.getInt("masp"),
						    rs.getString("tensp"),
						    rs.getInt("soluongton"),
						    rs.getString("camerasau"),
						    rs.getString("cameratruoc"),
						    rs.getInt("phienbanhdh"),
						    rs.getInt("thoigianbaohanh"),
						    rs.getString("tenthuonghieu"),
						    rs.getString("tenhedieuhanh"),
						    rs.getDouble("kichthuocman"),
						    rs.getString("hinhanh"),
						    rs.getInt("dungluongpin"),
						    rs.getString("tenxuatxu"),
						    rs.getString("tenkhuvuc")));
				
			}
			Connectmysql.close(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public Sanpham selectByName(Sanpham t) {
		
		return null;
	}
    public List<Sanpham> sptrongkho(Khuvuckho t){
    	 List<Sanpham> check=new ArrayList<>();
    	 try {
 			Connection connect = Connectmysql.getConnection();
 			java.sql.Statement st =connect.createStatement();
 			String sql="SELECT tensp,soluongton,hinhanh from sanpham WHERE khuvuckho="+t.getMakhuvuc();
 			ResultSet rs=st.executeQuery(sql);
 			while(rs.next()) {
 				check.add(new Sanpham(
 						rs.getString("tensp"),
 						rs.getInt("soluongton"),
 						rs.getString("hinhanh")));
 			}
 			Connectmysql.close(connect);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
    	return check;
    }
    public int masp(Sanpham t) {
        int check = 0;
        try {
            Session session = Hibernate_util.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "SELECT masp FROM Sanpham WHERE tensp = :tensp";
            Query query = session.createQuery(hql);
            query.setParameter("tensp", t.getTensp());
            
            Object result = query.uniqueResult();
            if (result != null) {
                check = (int) result;
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    public boolean isTenSPExists(String tenSP) {
        boolean exists = false;
        try {
            Session session = Hibernate_util.getSessionFactory().openSession();
            Query query = session.createQuery("select count(*) from Sanpham where tensp = :tenSP");
            query.setParameter("tenSP", tenSP);
            Long count = (Long) query.uniqueResult();
            if (count != null && count > 0) {
                exists = true;
            }
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return exists;
    }


}
