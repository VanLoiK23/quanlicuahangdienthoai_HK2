package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Login_Register implements Initializable{
    @FXML
    private VBox vbox;
    
    private Parent fxml;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TranslateTransition t=new TranslateTransition(Duration.seconds(1),vbox);
	    t.setToX(vbox.getLayoutX()*21);
        t.play();
        t.setOnFinished(e->{
        	try {
        		fxml=FXMLLoader.load(getClass().getResource("/View/Login_view.fxml"));
        		vbox.getChildren().removeAll();
        		vbox.getChildren().setAll(fxml);
        	}catch(IOException ex) {
        		ex.printStackTrace();
                System.out.println("Error loading Login_view.fxml: " + ex.getMessage());
        	}
        });
	
	}
	@FXML
	public void open_signin(ActionEvent e1) {
		TranslateTransition t=new TranslateTransition(Duration.seconds(1),vbox);
	    t.setToX(vbox.getLayoutX()*21);
        t.play();
        t.setOnFinished(e->{
        	try {
        		fxml=FXMLLoader.load(getClass().getResource("/View/Login_view.fxml"));
        		vbox.getChildren().removeAll();
        		vbox.getChildren().setAll(fxml);
        	}catch(IOException e2) {
        		
        	}
        });
	}
	@FXML
	public void open_signup(ActionEvent e1) {
		TranslateTransition t=new TranslateTransition(Duration.seconds(1),vbox);
	    t.setToX(0);
        t.play();
        t.setOnFinished(e->{
        	try {
        		fxml=FXMLLoader.load(getClass().getResource("/View/Register_view.fxml"));
        		vbox.getChildren().removeAll();
        		vbox.getChildren().setAll(fxml);
        	}catch(IOException e2) {
        		
        	}
        });
	}
}
