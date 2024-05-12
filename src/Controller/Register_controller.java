package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.Account_DAO;
import Model.Taikhoan;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Register_controller {
	    @FXML
	    private ImageView img;
	    @FXML
	    public void exit(MouseEvent event) {
		 Stage stage = (Stage) img.getScene().getWindow();
	        stage.close();
	    }
	    @FXML
	    private TextField address;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private TextField phone;

	    @FXML
	    private PasswordField retype;

	    @FXML
	    private TextField username;

	    @FXML
	    void create(MouseEvent event) {
	        if (!address.getText().isEmpty() && !password.getText().isEmpty() && !phone.getText().isEmpty() && !retype.getText().isEmpty() && !username.getText().isEmpty()) {
	        	Pattern pa=Pattern.compile("\\d*");
				Matcher ma=pa.matcher(username.getText());
				Matcher ma1=pa.matcher(address.getText());
				Matcher ma2=pa.matcher(phone.getText());

	        	if(username.getText().length()<5||username.getText().length()>20) {
	            	Alert alert = new Alert(AlertType.WARNING);
		            alert.setTitle("Cảnh báo!");
		            alert.setHeaderText(null);
		            alert.setContentText("Username của bạn quá ngắn hoặc quá dài!");
		            alert.showAndWait();
	            }
	        	else if(ma.matches()) {
	        		Alert alert = new Alert(AlertType.WARNING);
		            alert.setTitle("Error!!!!");
		            alert.setHeaderText(null);
		            alert.setContentText("Username của bạn chỉ bao gồm số!");
		            alert.showAndWait();
	        	}
	        	else if(password.getText().length()<6||retype.getText().length()<6) {
	        		Alert alert = new Alert(AlertType.WARNING);
		            alert.setTitle("Error!!!!");
		            alert.setHeaderText(null);
		            alert.setContentText("Password của bạn quá ngắn!");
		            alert.showAndWait();
	        	}
	        	else if(!password.getText().equalsIgnoreCase(retype.getText())) {
	        		Alert alert = new Alert(AlertType.WARNING);
		            alert.setTitle("Error!!!!");
		            alert.setHeaderText(null);
		            alert.setContentText("Password không trùng nhau!!");
		            alert.showAndWait();
	        	}
	        	else if(ma1.matches()) {
	        		Alert alert = new Alert(AlertType.WARNING);
		            alert.setTitle("Error!!!!");
		            alert.setHeaderText(null);
		            alert.setContentText("Address của bạn chỉ bao gồm số!");
		            alert.showAndWait();
	        	}
	        	else if(phone.getText().length()<10||phone.getText().length()>10||!ma2.matches()) {
	        		Alert alert = new Alert(AlertType.WARNING);
		            alert.setTitle("Error!!!!");
		            alert.setHeaderText(null);
		            alert.setContentText("Số điện thoại của bạn không đúng định dạng!");
		            alert.showAndWait();
	        	}
	        	else {
	        		Account_DAO create=new Account_DAO();
	        		create.add(new Taikhoan(username.getText(),password.getText(),address.getText(),phone.getText(),1));
	        		Alert alert = new Alert(AlertType.CONFIRMATION);
		            alert.setTitle("Success!!!!");
		            alert.setHeaderText(null);
		            alert.setContentText("Bạn đã tạo tài khoản thành công");
		            alert.showAndWait();
		            clean();
	        	}
	        } else {
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Cảnh báo!");
	            alert.setHeaderText(null);
	            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
	            alert.showAndWait();
	            clean();
	        }
	    }
       void  clean() {
    	   username.setText(null);
    	   password.setText(null);
    	   retype.setText(null);
    	   address.setText(null);
    	   phone.setText(null);
       }

}
