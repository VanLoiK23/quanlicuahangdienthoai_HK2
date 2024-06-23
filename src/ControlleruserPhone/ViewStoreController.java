package ControlleruserPhone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.management.ObjectInstance;

import DAO_UserPhone.DAO_giohang;
import DAO_UserPhone.DaoTaiKhoan;
import ModelUserPhone.ModelGiohang;
import ModelUserPhone.SharedData;
import ModelUserPhone.Taikhoan;
import ModelUserPhone.modelsanpham;
import antlr.collections.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
public class ViewStoreController implements Initializable{


    @FXML
    private GridPane MenuGrid;

    @FXML
    private Button btncart;

    @FXML
    private Button btnhome;

    @FXML
    private Button btnsignout;

    @FXML
    private Button btnstore;
    

    @FXML
    private Label name;


@FXML
private ImageView imglb;
modelsanpham model;
private ObservableList<modelsanpham> item = FXCollections.observableArrayList();
@FXML
void tailai(MouseEvent event) {

}

@FXML
void load(MouseEvent event) {
	menuitem();
}

	    public void menuitem() {
	    	//client
	    	Runnable task= ()->{
	    		  try (Socket socket = new Socket("localhost", 9999);
	 	 		         ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	 	 		         ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

	 	 		        out.writeObject("select");
	 	 		        out.flush();
	 	 		        item.clear();
	 	 		    	for(int i = 0;i<1000;i++) {
	 	 		    		modelsanpham m = (modelsanpham) in.readObject();
	 	 			        
	 	 			        item.addAll(m);
	 	 		    	}
	 	 		    } catch (EOFException e) {
	 	 		      
	 	 		    } catch (Exception e) {
	 	 		        e.printStackTrace();
	 	 		    }
	    		  Platform.runLater(() -> {
	    			  int col=0,row=0;
		 	    		MenuGrid.getRowConstraints().clear();
		 	    		MenuGrid.getColumnConstraints().clear();
		 	    		for(int i=0;i<item.size();i++) {
		 	    			
		 	    			
		 	    			try {
		 	    				
		 						FXMLLoader l = new FXMLLoader();
		 						l.setLocation(getClass().getResource("/ViewUserPhone./Viewitem.fxml"));
		 						AnchorPane a = l.load();
		 						ViewitemController itemController = l.getController();
		 						itemController.setData(item.get(i));
		 						if(col==4) {
		 							col=0;
		 							row+=1;
		 						}
		 						MenuGrid.add(a, col++, row);//child col row
		 						GridPane.setMargin(a, new Insets(18));
		 						
		 					} catch (Exception e) {
		 e.printStackTrace();				
		 }
		 	    		}
		 
	    		  });

	 	    		   	}; 
	    	new Thread(task).start();
	    	
	    }
	    
	    @FXML
	    void btnhomecart(MouseEvent event) {

try {

	
	 Platform.runLater(() -> {	    	
		  Taikhoan currentUser = SharedData.getInstance().getCurrentUser();

	        if (currentUser != null) {
	            name.setText(currentUser.getUsername());
	        
		        
	DaoTaiKhoan t=new DaoTaiKhoan();
    writeNumberToFile(t.getId(currentUser.getUsername()),"makh.txt");	}});
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
	    void homeclick(MouseEvent event) {
	    	try {
	    	       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewUserPhone./Home.fxml"));
	    	       Parent home = loader.load();
	    	       Scene Homegd = new Scene(home);
	              

	    	       Stage currentStage = (Stage) btncart.getScene().getWindow();
	    	       currentStage.setScene(Homegd);
	    	   } catch (IOException ex) {
	    	       ex.printStackTrace();
	    	   }
	    }
	    @FXML
	    void signoutt(MouseEvent event) {
	    	System.out.println("ok");
            System.exit(0);
	    }
	   

	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	menuitem();
	    
	    	  Platform.runLater(() -> {	    	
	    		  Taikhoan currentUser = SharedData.getInstance().getCurrentUser();

	    	        if (currentUser != null) {
	    	            name.setText(currentUser.getUsername());
	    	        }
	    		        });

	    			 
	    	
	      }
	
	    public static void writeNumberToFile(int number, String filename) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            writer.write(String.valueOf(number));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    		
		}
