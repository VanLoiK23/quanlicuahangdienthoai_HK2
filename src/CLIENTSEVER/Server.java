package CLIENTSEVER;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DAO_UserPhone.DAO_giohang;
import DAO_UserPhone.DAO_sanphamdathang;
import DAO_UserPhone.DAOsanpham;
import DAO_UserPhone.HibernateUtil;
import ModelUserPhone.ModelGiohang;
import ModelUserPhone.ModelSanPhamDatHang;
import ModelUserPhone.SharedData;
import ModelUserPhone.Taikhoan;
import ModelUserPhone.modelsanpham;
import antlr.collections.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Server {

    public static void main(String[] args) {
        final int PORT = 9999;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

	// Xử lý yêu cầu từ client
    Thread thread = new Thread(new ClientHandler(socket));
    thread.start();
                
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
		
		}
    }
}

class ClientHandler implements Runnable {
	
	 	 
    private Socket socket;
    DAOsanpham dsp;
ModelGiohang giohang;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

	 
    @Override
    public void run() {
    
        try (
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
       
            Object obj = in.readObject();
            // Nhận đối tượng ModelGiohang từ client
    		

        	if(obj instanceof ModelGiohang) {
        		ModelGiohang giohang = (ModelGiohang) obj;
            	DAO_giohang dao = new DAO_giohang();
    dao.add(giohang);
    out.writeObject("Inserted Thanh cong Vao Gio Hàng!");
     		  
        	}else if(obj instanceof ModelSanPhamDatHang) {
        Thread	thread =new Thread(()->{
        	ModelSanPhamDatHang spdh = (ModelSanPhamDatHang) obj;
        	DAO_sanphamdathang daodathang = new DAO_sanphamdathang();
        	daodathang.add(spdh);
        		}); thread.start();
        		

    out.writeObject("Đặt hàng thành công");
        	}
        	
        	
    	
//else if(obj instanceof String) {
                String response = (String)obj;
               
           if(response.equals("select")) {
   try {
       String url = "jdbc:mysql://localhost:3306/quan_ly_ban_hang";
      String username = "root";
      String password = "binvaloi123";
      Connection connection = null;
      try {
		connection = DriverManager.getConnection(url, username, password);
      String sql = "SELECT sp.masp, " +
              "sp.tensp, " +
              "sp.hinhanh, " +
              "sp.soluongton, " +
              "gx.giaxuat, "
              + "gx.maphienbansp " +
              "FROM quan_ly_ban_hang.sanpham AS sp " +
              "LEFT JOIN phienbansanpham AS gx ON sp.masp = gx.masp " +
              "WHERE gx.rom = 1 AND sp.soluongton >= 0";

       Statement statement = connection.createStatement();
       ResultSet rs = statement.executeQuery(sql);
       while (rs.next()) {
              	   modelsanpham m = new modelsanpham(
                   rs.getInt("masp"),
                   rs.getString("tensp"),
                   rs.getString("hinhanh"),
                   rs.getInt("maphienbansp"),
                   rs.getInt("soluongton"),
                  rs.getInt("giaxuat")
               );
	out.writeObject(m);
       }
       
       connection.close();
        	socket.close();
      } catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
      }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    //}

}else {
	
	       String url = "jdbc:mysql://localhost:3306/quan_ly_ban_hang";
	      String username = "root";
	      String password = "binvaloi123";
	      Connection connection = null;
	      try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	

    //sql
	String sql = "SELECT * FROM giohangsanpham WHERE taikhoanuser = ?";
	PreparedStatement pstmt = connection.prepareStatement(sql);
	pstmt.setString(1, response);
	ResultSet rs = pstmt.executeQuery();
	
    while (rs.next()) {        
 	   ModelGiohang m = new ModelGiohang(
 			   rs.getInt("id"),
                rs.getInt("masp")
                ,rs.getString("taikhoanuser"),
                rs.getString("tensp"),        
                rs.getString("hinhanh"),
                rs.getInt("maphienbansp"),
                rs.getInt("soluongton"),
                rs.getInt("giatien"),
                rs.getString("rom"));
 	           ;
 		out.writeObject(m);

    }
    

}
        	
        
        
        } catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
} catch (ClassNotFoundException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}}}
