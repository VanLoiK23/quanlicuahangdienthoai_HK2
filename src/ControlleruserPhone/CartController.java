package ControlleruserPhone;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import DAO.DAO_giohang;
import ModelUserPhone.ModelGiohang;

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
		    		item1.clear();
		    		
					dao_giohang = new DAO_giohang();
					
		    		item1.addAll(dao_giohang.getList());
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
