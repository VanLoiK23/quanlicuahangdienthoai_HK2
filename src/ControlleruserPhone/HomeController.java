package ControlleruserPhone;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController {
	@FXML
	private Button btnhome;
	@FXML
	private Button btnstore;
	@FXML
	private Button btncart;
	@FXML
	private Button btnsignout;
	@FXML
	private Pane bgcolor;

	@FXML
	public void btnhomecart(MouseEvent event) {
		try {
		       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewUserPhone./Cart.fxml"));
		       Parent cart = loader.load();
		       Scene Homecart = new Scene(cart);

		       Stage currentStage = (Stage) btncart.getScene().getWindow();
		       currentStage.setScene(Homecart);
		   } catch (IOException ex) {
		       ex.printStackTrace();
		   }

	
	}
	
	@FXML
	public void signoutt(MouseEvent event) {
		// TODO Autogenerated
        System.exit(0);

	}
	 @FXML
	    void storeclick(MouseEvent event) {
		 try {
	    		System.out.println("ok");
	    	       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewUserPhone./Trangchu.fxml"));
	    	       Parent cart2 = loader.load();
	    	       Scene Homecart2 = new Scene(cart2);

	    	       Stage currentStage = (Stage) btnstore.getScene().getWindow();
	    	       currentStage.setScene(Homecart2);
	    	   } catch (IOException ex) {
	    	       ex.printStackTrace();
	    	   
	    	}
	    
	    }
	 @FXML
	    void accountclick(MouseEvent event) {
//		 try {
//	    
//	    	       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewUserPhone./ThongTinTaiKhoan.fxml"));
//	    	       Parent account = loader.load();
//	    	       Scene account1 = new Scene(account);
//
//	    	       Stage currentStage = (Stage) btnstore.getScene().getWindow();
//	    	       currentStage.setScene(account1);
//	    	   } catch (IOException ex) {
//	    	       ex.printStackTrace();
//	    	   
//	    	}
	    
	    }
}
