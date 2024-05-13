package ControlleruserPhone;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.management.ObjectInstance;

import DAO.DAOsanpham;

import ModelUserPhone.modelsanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
private ImageView imglb;

DAOsanpham ds;
private ObservableList<modelsanpham> item = FXCollections.observableArrayList();


	    public void menuitem() {
	    		item.clear();
	    		
					ds = new DAOsanpham();
		
				
	    		item.addAll(ds.selectAll());
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
e.printStackTrace();				}
	    		}
	    }
	    
	    @FXML
	    void btnhomecart(MouseEvent event) {

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
					
					
					

			
			}
			
		}
