package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

	@Override
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


	@Override
	public Phieubansanpham selectByName(Phieubansanpham t) {
		// TODO Auto-generated method stub
		return null;
	}
	private int docso(String fileName) {
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
