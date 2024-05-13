package ControlleruserPhone;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.View;


import DAO.DAO_giohang;
import ModelUserPhone.ModelGiohang;

import ModelUserPhone.modelsanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class ViewitemController implements Initializable {
	@FXML
    private Button btnram1;

    @FXML
    private Button btnram2;

    @FXML
    private Button btnram3;

    @FXML
    private Button btnadd;

    @FXML
    private ImageView imgphone;

    @FXML
    private Label phonename;

    @FXML
    private Label phoneprice;

    @FXML
    private Label soluongkho;

	    ViewStoreController view;	 

	
	modelsanpham sanpham;

ModelGiohang giohang;
	    public void setData(modelsanpham modelp) {
	    	this.sanpham = modelp;
	    giohang = new ModelGiohang();
	    	phonename.setText(modelp.getPhonename());
	    	
	    	
	    	 int giatien = sanpham.getGiaxuat();
	         DecimalFormat decimalFormat = new DecimalFormat("#,###VND");
	         String formattedResult = decimalFormat.format(giatien);
		    	phoneprice.setText(formattedResult);
	    	  giohang.setGiatien(giatien);
	    	  
	    	  
	    	String path =modelp.getImg();
	   Image image = new Image(path,140,150,false,true); 
	    	imgphone.setImage(image);
	    	
	    	btnram1.setStyle("-fx-background-color: #"+"fbb45c");
	    	giohang.setRom(btnram1.getText());
	    	soluongkho.setText(modelp.getSoluongton()+"");
	    	
	    }
	   
	    
	    @FXML
	    void click1(MouseEvent event) {
	    	

	    	btnram1.setStyle("-fx-background-color: #"+"fbb45c");
	    	btnram2.setStyle("-fx-background-color: #"+"f2f1f1");
	    	btnram3.setStyle("-fx-background-color: #"+"f2f1f1");
	         giohang.setRom(btnram1.getText());
	         int giatien = sanpham.getGiaxuat();
	         DecimalFormat decimalFormat = new DecimalFormat("#,###VND");
	         String formattedResult = decimalFormat.format(giatien);
		    	phoneprice.setText(formattedResult);
		    	

	        giohang.setGiatien(giatien);
	     	         
	    		    }

	    @FXML
	    void click2(MouseEvent event) { 
	    giohang = new ModelGiohang();
	    int a=1500000;
	    int b=sanpham.getGiaxuat();
	    int kq =a+b;
//thêm VND
        DecimalFormat decimalFormat = new DecimalFormat("#,###VND");
        String formattedResult = decimalFormat.format(kq);
        
      

	    	btnram2.setStyle("-fx-background-color: #"+"fbb45c");
	    	btnram1.setStyle("-fx-background-color: #"+"f2f1f1");
	    	btnram3.setStyle("-fx-background-color: #"+"f2f1f1");
	         giohang.setRom(btnram2.getText());
	    
	      //xoa VND
	         String result1 = formattedResult.replaceAll(",", "").replaceAll("VND", "");
	         int giatien = Integer.parseInt(result1);
	         giohang.setGiatien(giatien);
		     
	    	phoneprice.setText(formattedResult);


	     	}

	    @FXML
	    void click3(MouseEvent event) {
                 
	    	int a= 3000000;
	    	  int b=sanpham.getGiaxuat();
	  	    int kq =a+b;

	          
	          DecimalFormat decimalFormat = new DecimalFormat("#,###VND");
	          String formattedResult = decimalFormat.format(kq);

	    	
	    	btnram3.setStyle("-fx-background-color: #"+"fbb45c");
	    	btnram1.setStyle("-fx-background-color: #"+"f2f1f1");
	    	btnram2.setStyle("-fx-background-color: #"+"f2f1f1");
	         giohang.setRom(btnram3.getText());
	
		    	
		    	
		    	 String result1 = formattedResult.replaceAll(",", "").replaceAll("VND", "");
		         int giatien = Integer.parseInt(result1);
		         giohang.setGiatien(giatien);
			     
		    	phoneprice.setText(formattedResult);


	    }
	       
	    @FXML
	    void addclick(MouseEvent event) {
	    	int a = Integer.parseInt(soluongkho.getText());
	    	
	    	if(a<=0) {
	    		 Alert a1 = new Alert(AlertType.INFORMATION);
	    			a1.setTitle("Thông báo !");
	    		a1.setContentText("Điện thoại này đang hết hàng");
	    		a1.showAndWait();
	    	}else {
	    		
	    		
	    			DAO_giohang dao = new DAO_giohang();
	    		    giohang = new ModelGiohang(sanpham.getMasp(), sanpham.getPhonename(), sanpham.getImg(), sanpham.getSoluongton(), giohang.getGiatien(), giohang.getRom());
	    		    dao.add(giohang);
	    		
	    		   
	    	}
	    }

	    
	@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		
		}

}
