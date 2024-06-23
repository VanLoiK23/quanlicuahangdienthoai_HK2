package Controller;

import java.io.IOException;

import DAO.Account_DAO;
import Model.Taikhoan;
import Security.Test_scurity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login_controller {
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
	 @FXML
	 void submit(MouseEvent event) throws IOException {
         Account_DAO signin=new Account_DAO();
         if(!username.getText().isEmpty()&&!password.getText().isEmpty()) {
     		 Test_scurity encryt=new Test_scurity();
        	 boolean check=signin.khachhang(new Taikhoan(username.getText(),encryt.Password(password.getText())));
        	 boolean ad=signin.admin(new Taikhoan(username.getText(),encryt.Password(password.getText())));
        	 if(ad) {
     		    Alert alert = new Alert(AlertType.CONFIRMATION);
		            alert.setTitle("Success!!!!");
		            alert.setHeaderText(null);
		            alert.setContentText("Bạn đã đăng nhập thành công");
		            alert.showAndWait();
		            clean();
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Quan_li_view.fxml"));
		            root = loader.load();
		            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		            scene = new Scene(root);
		            scene.setFill(Color.WHITE);
		            stage.setScene(scene);
		            //stage.initStyle(StageStyle.DECORATED);
		            stage.show();
		            stage.centerOnScreen();
     	      }
        	 else if(check) {
        		    Alert alert = new Alert(AlertType.CONFIRMATION);
		            alert.setTitle("Success!!!!");
		            alert.setHeaderText(null);
		            alert.setContentText("Bạn đã đăng nhập thành công");
		            alert.showAndWait();
		            clean();
        	 }
        	 else {
        		Alert alert = new Alert(AlertType.WARNING);
 	            alert.setTitle("Cảnh báo!");
 	            alert.setHeaderText(null);
 	            alert.setContentText("Không tìm thấy thông tin tài khoản!");
 	            alert.showAndWait();
 	            clean();
        	 }
         }
         else {
        	    Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Cảnh báo!");
	            alert.setHeaderText(null);
	            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
	            alert.showAndWait();
	            clean();
         }
	 }
     void clean() {
    	 username.setText(null);
    	 password.setText(null);
     }
 
}
