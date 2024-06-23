package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import Model.ChitietPhieuNhap;
import Model.ChitietPhieuXuat;
import Model.ChitietSanpham;
import Model.Khuvuckho;
import Model.Phieubansanpham;
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
	
	public int updatesl(Sanpham t) {
	    int check = 0;
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        
	        Query query = session.createQuery(
	            "UPDATE Sanpham sp " +
	            "SET sp.soluongton =" +
	            "    (SELECT SUM(pbsp.soluongton) " +
	            "     FROM Phieubansanpham pbsp " +
	            "     WHERE pbsp.masp = :masp) " +
	            "WHERE sp.masp = :masp");
	        query.setParameter("masp", t.getMasp());
	        
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


	public int delete(Sanpham t) {
	    int check = 0;
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
 
	        List<Phieubansanpham> phieubansanphams = session.createQuery("FROM Phieubansanpham WHERE masp = :masp", Phieubansanpham.class)
	                                                .setParameter("masp", t.getMasp())
	                                                .list();
	        for (Phieubansanpham phieu : phieubansanphams) {
	        	 List<ChitietSanpham> ctsanpham = session.createQuery("FROM ChitietSanpham WHERE maphienbansp = :maphienbansp", ChitietSanpham.class)
	                     .setParameter("maphienbansp", phieu.getMaphienbansp())
	                     .list();
	             for (ChitietSanpham ct : ctsanpham) {
	                  session.delete(ct);
	              }
	             List<ChitietPhieuNhap> ctphieunhap = session.createQuery("FROM ChitietPhieuNhap WHERE maphienbansp = :maphienbansp", ChitietPhieuNhap.class)
	                     .setParameter("maphienbansp", phieu.getMaphienbansp())
	                     .list();
	             for (ChitietPhieuNhap ctn : ctphieunhap) {
	                  session.delete(ctn);
	              }
	             List<ChitietPhieuXuat> ctphieuxuat = session.createQuery("FROM ChitietPhieuXuat WHERE maphienbansp = :maphienbansp", ChitietPhieuXuat.class)
	                     .setParameter("maphienbansp", phieu.getMaphienbansp())
	                     .list();
	             for (ChitietPhieuXuat ctx : ctphieuxuat) {
	                  session.delete(ctx);
	              }
	            session.delete(phieu);
	        }

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
    
    public Sanpham selectByPhienBan(String t) {
        Sanpham result = null;
        try {
            Connection con = (Connection) Connectmysql.getConnection();
            String sql = "SELECT * FROM sanpham sp join phienbansanpham pb on sp.masp=pb.masp WHERE maphienbansp=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("masp");
                String tendm = rs.getString("tensp");
                String hinhanh = rs.getString("hinhanh");
                int xuatxu = rs.getInt("xuatxu");
                int dungluongpin = rs.getInt("dungluongpin");
                double kichthuocman = rs.getDouble("kichthuocman");
                int hedieuhanh = rs.getInt("hedieuhanh");
                int phienbanhdh = rs.getInt("phienbanhdh");
                String camerasau = rs.getString("camerasau");
                String cameratruoc = rs.getString("cameratruoc");
                int thoigianbaohanh = rs.getInt("thoigianbaohanh");
                int thuonghieu = rs.getInt("thuonghieu");
                int khuvuckho = rs.getInt("khuvuckho");
                int soluongton = rs.getInt("soluongton");
                result = new Sanpham(madm, tendm, hinhanh, xuatxu, dungluongpin, kichthuocman, hedieuhanh, phienbanhdh, camerasau, cameratruoc, thoigianbaohanh, thuonghieu, khuvuckho, soluongton);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
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
