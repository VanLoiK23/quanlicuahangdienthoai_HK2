package ControlleruserPhone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//
import javax.naming.spi.DirStateFactory.Result;

import DAO_UserPhone.Account_DAO;
import ModelUserPhone.SharedData;
import ModelUserPhone.Taikhoan;
import Security.Test_scurity;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private ImageView img;
	@FXML
	public void exit(MouseEvent event) {
		 Stage stage = (Stage) img.getScene().getWindow();
	        stage.close();
	    }
	 @FXML
	 private PasswordField password;

	 @FXML
	 private TextField username;
	 
	   private Stage stage;
	   private Scene scene;
	   private Parent root;
	 

     void clean() {
    	 username.setText(null);
    	 password.setText(null);
     }
 
HomeController home;
private Taikhoan t;
String namea;


public LoginController(Taikhoan t) {
    this.t = t;
}

public LoginController() {
}
@FXML
void submit(MouseEvent event) throws IOException {
	if(username.getText().isEmpty() || password.getText().isEmpty()) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Login");
		a.setContentText("Bạn chưa nhập tài khoản hoặc mật khẩu");
		a.showAndWait();
		
	}else {	
		
		String name = username.getText();
		Test_scurity encyt=new Test_scurity();
		String pass = encyt.Password(password.getText());

try {
			String url = "jdbc:mysql://localhost:3306/quan_ly_ban_hang";
		    String username = "root";
		    String password = "binvaloi123";
			Connection con = DriverManager.getConnection(url,username,password);
          //Kết nối sql
			PreparedStatement state = con.prepareStatement("SELECT * FROM taikhoan WHERE username = ? AND matkhau = ?");
          //Thực thi kết nối
			state.setString(1, name);
          state.setString(2, pass);
          
          
          //thiết lập  giá trị tham số thứ nhất và thứ 2 bằng name và pass 
            
            ResultSet rs = state.executeQuery();
            // thực thu truy vấn câu lệnh
       	

            if(rs.next()) {//trả về true rs.next trỏ đến dòng tiếp theo
     String namea = rs.getString("username");     
     int id = rs.getInt("id");

     Taikhoan currentUser = new Taikhoan();
     currentUser.setUsername(rs.getString("username"));
     currentUser.setId(id);
     
     // Lưu thông tin người dùng vào SharedData
     SharedData.getInstance().setCurrentUser(currentUser);
            	Alert a = new Alert(AlertType.INFORMATION);
        		a.setTitle(" Thông Báo đăng nhập");
        	a.setContentText("Đăng nhập thành công");
        	a.showAndWait();
        	   
        	//Phần để thêm liên kết với Trang chủ
        	
        	
        	try {
	    	       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewUserPhone./Home.fxml"));
		            root = loader.load();
		            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		            scene = new Scene(root);
		            scene.setFill(Color.WHITE);
		            stage.setScene(scene);
		            //stage.initStyle(StageStyle.DECORATED);
		            stage.show();
		            stage.centerOnScreen();
	            
	    	   } catch (IOException ex) {
	    	       ex.printStackTrace();
	    	   
	    	}
        	
        	 
            }else {
            	Alert a = new Alert(AlertType.INFORMATION);
        		a.setTitle(" Thông Báo đăng nhập");
        	a.setContentText("Bạn nhập sai tài khoản hoặc mật khẩu");
        	a.showAndWait();
        	clean();
        	
        	
            }
            rs.close();
            state.close();
            con.close();
		}catch (Exception ex) {
			// TODO: handle exception
		}
		

		

}
	

	}




}

