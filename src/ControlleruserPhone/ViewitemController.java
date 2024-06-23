package ControlleruserPhone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.View;

import DAO_UserPhone.DAO_giohang;
import DAO_UserPhone.Phieubansp_DAO;
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

	
	
	private int i;
	
    public int getI() {
		return i;
	}


	public void setI(int i) {
		this.i = i;
	}

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
	    	 String chuoiMoi = path.substring(1);
	    	    //Image image = new Image(new File(imagePath).toURI().toString());
	        Image image = new Image((new File(chuoiMoi).toURI().toString()),140,150,false,true); 
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
             writeNumberToFile(sanpham.getMapbansp(),"ma.txt"); 

		    	phoneprice.setText(formattedResult);
		    	
		    	 
	        giohang.setGiatien(giatien);
	     	         
	    		    }

	    @FXML
	    void click2(MouseEvent event) { 
	    giohang = new ModelGiohang();
	    Phieubansp_DAO p=new Phieubansp_DAO();
	    
//thêm VND
        DecimalFormat decimalFormat = new DecimalFormat("#,###VND");
        String formattedResult = decimalFormat.format(p.select512(sanpham, 2));
        
       

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
                 

		    Phieubansp_DAO p=new Phieubansp_DAO();
		   
	          
	          DecimalFormat decimalFormat = new DecimalFormat("#,###VND");
		        String formattedResult = decimalFormat.format(p.select512(sanpham, 3));

	    	
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
	    void addclick(MouseEvent event) throws IOException {
	    	  Thread thread = new Thread(() -> {
	    	     
	    	        Platform.runLater(() -> {
	    	        	int a = Integer.parseInt(soluongkho.getText());
	    		    	
	    		    	if(a<=0) {
	    		    		 Alert a1 = new Alert(AlertType.INFORMATION);
	    		    			a1.setTitle("Thông báo !");
	    		    		a1.setContentText("Điện thoại này đang hết hàng");
	    		    		a1.showAndWait();
	    		    	}else {
	    		    		//id tai khoan
	    	    	        
	    		    		try(Socket socket = new Socket("localhost", 9999); 
	    		                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	    		    	             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

		    		    		Taikhoan currentUser = SharedData.getInstance().getCurrentUser();
	    		    		    giohang = new ModelGiohang(giohang.getId(),sanpham.getMasp(),currentUser.getUsername(), sanpham.getPhonename(), sanpham.getImg(), sanpham.getMapbansp(), sanpham.getSoluongton(), giohang.getGiatien(), giohang.getRom());

	    		                out.writeObject(giohang);
	    		                
	    		                
	    		                String response = (String) in.readObject();
	    		                System.out.println("Server response: " + response);
	    		                Alert a1 = new Alert(AlertType.INFORMATION);
	    		    			a1.setTitle("Thông báo !");
	    	    		a1.setContentText("Thêm vào giỏ hàng thành công");
	    		    		a1.showAndWait();
	    		    		socket.close();
	    					} catch (Exception e) {
	    			            e.printStackTrace();
	    			          
	    					}
	    		    		   
	    		    	}
	    	        });
	    	    });

	    	    // Khởi chạy thread
	    	    thread.start();
	    	
	    }

	    public static void writeNumberToFile(int number, String filename) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            writer.write(String.valueOf(number));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		
		}

}
