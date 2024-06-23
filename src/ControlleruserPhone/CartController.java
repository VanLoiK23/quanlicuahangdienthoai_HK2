package ControlleruserPhone;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO_UserPhone.DAO_giohang;
import ModelUserPhone.ModelGiohang;
import ModelUserPhone.SharedData;
import ModelUserPhone.Taikhoan;
import ModelUserPhone.modelsanpham;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CartController implements Initializable{
	@FXML
	private Button btnhome;
	@FXML
	private Button btnstore;
	@FXML
	private Button btncart;
	@FXML
	private Button btnsignout;
	@FXML
	private GridPane MenuGrid;
	@FXML
	private Pane bgcolor;
	
	viewCartitemController viewCartitemController;
	
	private ObservableList<ModelGiohang> item1 = FXCollections.observableArrayList();
DAO_giohang dao_giohang;
		    public void menuitem() {
		    	
		    	Runnable task = ()->{
		    		try (Socket socket = new Socket("localhost", 9999);
			 		         ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			 		         ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
	    	    		Taikhoan currentUser = SharedData.getInstance().getCurrentUser();

		    			if (currentUser != null) {
		    			    String username = currentUser.getUsername();
							out.writeObject(username);

		    			} else {
		    			    System.out.println("");
		    			}
			 		      
							

		    		
			 		        out.flush();
			 		        item1.clear();
			 		        // Read object from input stream
			 		    	for(int i = 0;i<1000;i++) {
			 		    		ModelGiohang m = (ModelGiohang) in.readObject();

			 			        // Clear current items and add received items
			 			        item1.addAll(m);
			 		    	}
			 		    } catch (EOFException e) {
			 		        // Handle EOFException (connection closed unexpectedly)
			 		      
			 		    } catch (Exception e) {
			 		        // Handle other exceptions
			 		        e.printStackTrace();
			 		    }
		    	  
		    	  Platform.runLater(() -> {
		    		  int col=0,row=0;
			    		MenuGrid.getRowConstraints().clear();
			    		MenuGrid.getColumnConstraints().clear();
			    		for(int i=0;i<item1.size();i++) {
			    			
			    			
			    			try {
			    				viewCartitemController = new viewCartitemController();
			    				
								FXMLLoader l = new FXMLLoader();
								l.setLocation(getClass().getResource("/ViewUserPhone./viewCartitem.fxml"));
								AnchorPane a = l.load();
								viewCartitemController itemController = l.getController();
								itemController.setDatacart(item1.get(i));
								if(col==1) {
									col=0;
									row+=1;
								}
								MenuGrid.add(a, col++, row);//child col row
								GridPane.setMargin(a, new Insets(23));
								
							} catch (Exception e) {
		e.printStackTrace();				}
			    		}
		    	  });
		    	}; new Thread(task).start();
		    	
		    	  
		    		
		    }
		    
		   
		    @FXML
		    void bnthomestore(MouseEvent event) {
		    	try {
		    	
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
		    void btnhome(MouseEvent event) {
		    	try {
		    	       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewUserPhone./Home.fxml"));
		    	       Parent home = loader.load();
		    	       Scene Homesc = new Scene(home);
                       
		    	       Stage currentStage = (Stage) btnhome.getScene().getWindow();
		    	       currentStage.setScene(Homesc);
		    	   } catch (IOException ex) {
		    	       ex.printStackTrace();
		    	   }
		    }
		    

		    @FXML
		    void signoutt(MouseEvent event) {
                System.exit(0);
		    }
	
		

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuitem();
		
	}

	
		
}
