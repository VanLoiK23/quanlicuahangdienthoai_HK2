package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
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
import Controller.Sanpham_controller;
import Model.Phieubansanpham;
import Model.Sanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Hibernate_util;

public class Phieubansp_DAO implements DAOInterface<Phieubansanpham>{

	@Override
	public int add(Phieubansanpham t) {
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

	public int getRemainingQuantityByPhienBanSP(int maphienbansp) {
	    int remainingQuantity = 0;

	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();

	        Query query = session.createQuery("SELECT pb.soluongton FROM Phieubansanpham pb WHERE pb.maphienbansp = :maphienbansp");
	        query.setParameter("maphienbansp", maphienbansp);
	        Integer totalSoldQuantity = (Integer) query.uniqueResult();

	        if (totalSoldQuantity != null) {
	            remainingQuantity = totalSoldQuantity;
	        }

	        tx.commit();
	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return remainingQuantity;
	}

	@Override
	public int update(Phieubansanpham t) {
		int check = 0;
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        t.setSoluongton(getRemainingQuantityByPhienBanSP(t.getMaphienbansp()));
	        session.update(t); 
	        tx.commit();
	        session.close();
	        check = 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return check;
	}
	
	public int updateQuantity(Phieubansanpham t) {
	    int check = 0;
	    try {
	        Session session = Hibernate_util.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        
	        Query query = session.createQuery(
	            "UPDATE Phieubansanpham sp " +
	            "SET sp.soluongton = sp.soluongton + :additionalQuantity " +
	            "WHERE sp.maphienbansp = :productId");
	        query.setParameter("additionalQuantity", t.getSoluongton());
	        query.setParameter("productId", t.getMaphienbansp());
	        
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


	@Override
	public int delete(Phieubansanpham t) {
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
    private boolean u;
    
	public boolean isU() {
		return u;
	}

	public void setU(boolean u) {
		this.u = u;
	}
	@Override
	public ObservableList<Phieubansanpham> selectAll() {
	    ObservableList<Phieubansanpham> check = FXCollections.observableArrayList();
		    try {
		        Connection connect = Connectmysql.getConnection();
		        java.sql.Statement st = connect.createStatement();
		        String sql = "SELECT sp.maphienbansp, " 
		                    +"ra.kichthuocram, " +
		                     "ro.kichthuocrom, " +
		                     "col.tenmau, " +
		                     "sp.gianhap, " +
		                     "sp.giaxuat " +
		                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " +
		                     "LEFT JOIN quan_ly_ban_hang.dungluongram AS ra ON sp.ram = ra.madlram " +
		                     "LEFT JOIN quan_ly_ban_hang.dungluongrom AS ro ON sp.rom = ro.madlrom " +
		                     "LEFT JOIN quan_ly_ban_hang.mausac AS col ON sp.mausac = col.mamau "
		                     + "WHERE sp.masp="+docso("masp.txt")+";";
		        ResultSet rs = st.executeQuery(sql);
		        while (rs.next()) {
		            check.add(new Phieubansanpham(rs.getInt("maphienbansp"),
		                                           rs.getInt("kichthuocram"),
		                                           rs.getInt("kichthuocrom"),
		                                           rs.getString("tenmau"),
		                                           rs.getInt("gianhap"),
		                                           rs.getInt("giaxuat")));
		        }
		        Connectmysql.close(connect);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}
	public ObservableList<Phieubansanpham> select() {
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
	                     "sp.soluongton, " +
	                     "s.tensp " +
	                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " +
	                     "LEFT JOIN quan_ly_ban_hang.dungluongram AS ra ON sp.ram = ra.madlram " +
	                     "LEFT JOIN quan_ly_ban_hang.dungluongrom AS ro ON sp.rom = ro.madlrom " +
	                     "LEFT JOIN quan_ly_ban_hang.mausac AS col ON sp.mausac = col.mamau "+
	                     "LEFT JOIN quan_ly_ban_hang.sanpham AS s ON sp.masp = s.masp "
	                      + "WHERE sp.masp="+docso("masp.txt")+";";
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            check.add(new Phieubansanpham( rs.getInt("maphienbansp"),
	            		                       rs.getInt("masp"),
	            		                       rs.getString("tensp"),
	                                           rs.getInt("kichthuocram"),
	                                           rs.getInt("kichthuocrom"),
	                                           rs.getString("tenmau"),
	                                           rs.getInt("gianhap"),
	                                           rs.getInt("soluongton")));
	        }
	        Connectmysql.close(connect);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		    return check;
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
	public ObservableList<Phieubansanpham> ctphieuxuat(int phieuxuat) {
	    ObservableList<Phieubansanpham> check = FXCollections.observableArrayList();
	    try {
	        Connection connect = Connectmysql.getConnection();
	        java.sql.Statement st = connect.createStatement();
	        String sql = "SELECT sp.masp, "+
	                      " sp.maphienbansp,"
	                    +"ra.kichthuocram, " +
	                     "ro.kichthuocrom, " +
	                     "col.tenmau, " +
	                     "sp.giaxuat, " +
	                     "s.tensp, " +
	                     "ct.soluong " +
	                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " +
	                     "LEFT JOIN quan_ly_ban_hang.dungluongram AS ra ON sp.ram = ra.madlram " +
	                     "LEFT JOIN quan_ly_ban_hang.dungluongrom AS ro ON sp.rom = ro.madlrom " +
	                     "LEFT JOIN quan_ly_ban_hang.mausac AS col ON sp.mausac = col.mamau "+
	                     "LEFT JOIN quan_ly_ban_hang.sanpham AS s ON sp.masp = s.masp "+
	                     "JOIN chitietphieuxuat  ct ON sp.maphienbansp  = ct.maphienbansp"
	                      + " WHERE ct.maphieuxuat = "+phieuxuat;
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            check.add(new Phieubansanpham( rs.getInt("maphienbansp"),
	            		                       rs.getInt("masp"),
	            		                       rs.getString("tensp"),
	                                           rs.getInt("kichthuocram"),
	                                           rs.getInt("kichthuocrom"),
	                                           rs.getString("tenmau"),
	                                           rs.getFloat("giaxuat"),
	                                           rs.getInt("soluong")));
	        }
	        Connectmysql.close(connect);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		    return check;
	}
	public List<Phieubansanpham> cauhinh() {
		List<Phieubansanpham> check = new ArrayList();
		    try {
		        Connection connect = Connectmysql.getConnection();
		        java.sql.Statement st = connect.createStatement();
		        String sql = "SELECT sp.masp, "+
		                      " sp.maphienbansp,"
		                    +"ra.kichthuocram, " +
		                     "ro.kichthuocrom, " +
		                     "col.tenmau, " +
		                     "sp.gianhap, " +
		                     "sp.soluongton, " +
		                     "s.tensp " +
		                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " +
		                     "LEFT JOIN quan_ly_ban_hang.dungluongram AS ra ON sp.ram = ra.madlram " +
		                     "LEFT JOIN quan_ly_ban_hang.dungluongrom AS ro ON sp.rom = ro.madlrom " +
		                     "LEFT JOIN quan_ly_ban_hang.mausac AS col ON sp.mausac = col.mamau "+
		                     "LEFT JOIN quan_ly_ban_hang.sanpham AS s ON sp.masp = s.masp "
		                      + "WHERE sp.masp="+docso("masp.txt")+";";
		        ResultSet rs = st.executeQuery(sql);
		        while (rs.next()) {
		            check.add(new Phieubansanpham( rs.getInt("maphienbansp"),
		            		                       rs.getInt("masp"),
		            		                       rs.getString("tensp"),
		                                           rs.getInt("kichthuocram"),
		                                           rs.getInt("kichthuocrom"),
		                                           rs.getString("tenmau"),
		                                           rs.getInt("gianhap"),
		                                           rs.getInt("soluongton")));
		        }
		        Connectmysql.close(connect);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}
	
	public int selectSL(int ma) {
		int check = 0;
		    try {
		        Connection connect = Connectmysql.getConnection();
		        java.sql.Statement st = connect.createStatement();
		        String sql = "SELECT "+
		                     "sp.soluongton " +	                    
		                     "FROM quan_ly_ban_hang.phienbansanpham AS sp " 
		                      + "WHERE sp.maphienbansp = "+ma+";";
		        ResultSet rs = st.executeQuery(sql);
		        while (rs.next()) {
		          
		                                        check=   rs.getInt("soluongton");
		        }
		        Connectmysql.close(connect);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return check;
	}


	@Override
	public Phieubansanpham selectByName(Phieubansanpham t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public Phieubansanpham selectById(int mapb) {
		 Phieubansanpham ch = null;
	        try {
	            Connection con = (Connection) Connectmysql.getConnection();
	            String sql = "SELECT * FROM phienbansanpham WHERE maphienbansp = ?";
	            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	            pst.setInt(1, mapb);
	            ResultSet rs = (ResultSet) pst.executeQuery();
	            while (rs.next()) {
	                int maphienbansp = rs.getInt("maphienbansp");
	                int masp = rs.getInt("masp");
	                int ram = rs.getInt("ram");
	                int mausac = rs.getInt("mausac");
	                int rom = rs.getInt("rom");
	                int gianhap = rs.getInt("gianhap");
	                int giaxuat = rs.getInt("giaxuat");
	                int soluongton = rs.getInt("soluongton");
	                ch = new Phieubansanpham(maphienbansp, masp, ram, rom, mausac, gianhap, giaxuat, soluongton);
	            }
	            con.close();
	        } catch (SQLException e) {
	        }
	        return ch;
	    }
	
	public int docso(String fileName) {
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(fileName));
            String line;
            
            while ((line = inFile.readLine()) != null) {
                int number = Integer.parseInt(line);
                return number;
            }

            inFile.close();
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc từ tập tin: " + e.getMessage());
        }
        return 0;
	}

}
