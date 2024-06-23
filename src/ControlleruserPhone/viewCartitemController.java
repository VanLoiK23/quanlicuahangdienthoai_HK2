package ControlleruserPhone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.parser.Parser;

import DAO_UserPhone.DAO_giohang;
import DAO_UserPhone.DAO_sanphamdathang;
import DAO_UserPhone.DaoTaiKhoan;
import DAO_UserPhone.PhieuXuat_DAO;
import DAO_UserPhone.Phieubansp_DAO;
import DAO_UserPhone.ctphieuxuat_DAO;
import ModelUserPhone.ChitietPhieuXuat;
import ModelUserPhone.ChitietSanpham;
import ModelUserPhone.ModelGiohang;
import ModelUserPhone.ModelSanPhamDatHang;
import ModelUserPhone.PhieuXuat;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class viewCartitemController implements Initializable {

    @FXML
    private Button btndathang;

    @FXML
    private Button btnxoasanpham;
    
    @FXML
    private ImageView imgcart;

    @FXML
    private Label lbnamecart;

    @FXML
    private Label lbpricecart;

    @FXML
    private Button lienhe;

    @FXML
    private Label romcart;
    @FXML
    private ComboBox<Integer> cbb;
    @FXML
    private TextField tfamountcart;
   
   ModelSanPhamDatHang msp;
   DAO_giohang dao_giohang;
    CartController c;
    
    ModelGiohang giohang;
    public void setDatacart(ModelGiohang modelp) {
    	this.giohang = modelp;
    	DecimalFormat decimalFormat = new DecimalFormat("#,###VND");
        String formattedResult = decimalFormat.format(giohang.getGiatien());
        
    	lbnamecart.setText(giohang.getTensp()); 
  	 int b=giohang.getGiatien();
    	
     lbpricecart.setText(formattedResult);
     
     
     cbb.setValue(1);
		int sl=giohang.getSoluongton();
		for(int i=1;i<=sl;i++) {
		cbb.getItems().add(i);
		}
       cbb.setOnAction(event -> {
            int a = cbb.getValue();
            
            if (a > 0) {
                int kq1 = a * b;
            
             DecimalFormat decimalFormat2 = new DecimalFormat("#,###VND");
             String formattedResult2 = decimalFormat2.format(kq1);
                lbpricecart.setText(formattedResult2);
                giohang.setGiatien(kq1);
            }
           
        });



    	String path =giohang.getHinhanh();
        String encodedUrl = null;
   	   String chuoiMoi = path.substring(1);
        
        
			 Image image = new Image((new File(chuoiMoi).toURI().toString()),164,178,false,true); 
			   imgcart.setImage(image);
			   romcart.setText(giohang.getRom());
		
       
   
	
 }
    
    
    @FXML
    public void delete(MouseEvent e) {
    dao_giohang = new DAO_giohang();
    dao_giohang.delete(giohang);
    Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Thông báo !");
	a.setContentText("Bạn đã xóa sản phẩm này khỏi giỏ hàng");
	a.showAndWait();  
	try {
	       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewUserPhone./Cart.fxml"));
	       Parent cart = loader.load();
	       Scene Homecart = new Scene(cart);

	       Stage currentStage = (Stage) btnxoasanpham.getScene().getWindow();
	       currentStage.setScene(Homecart);
	   } catch (IOException ex) {
	       ex.printStackTrace();
	   }
    }

    
    
    public static int readNumberFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            return Integer.parseInt(line);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
    @FXML
    void btndathang(MouseEvent event) {
//    	msp = new ModelSanPhamDatHang();
//    	  msp.setSoluong(cbb.getValue());
//    	  
//       dao_giohang = new DAO_giohang();
        int gia= cbb.getValue();
        
        Taikhoan currentUser = SharedData.getInstance().getCurrentUser();
		Phieubansp_DAO dd=new Phieubansp_DAO();
		 Pattern pattern = Pattern.compile("\\d+"); 
	        Matcher matcher = pattern.matcher(romcart.getText());
	        while (matcher.find()) {
	     int so =Integer.parseInt(matcher.group());
	     if(so==1) {
	    	 so=1000;
	     }
        int ma=dd.selectMapb(lbnamecart.getText(), so);
        int check=dd.getQuantityByPhieuBanSP(ma);
        if(check>0) {

        if (currentUser != null) {
        Phieubansp_DAO p=new Phieubansp_DAO();
        PhieuXuat_DAO x=new PhieuXuat_DAO();
        ctphieuxuat_DAO cx=new ctphieuxuat_DAO();
        if(x.add(new PhieuXuat(currentUser.getId(),generateRandomNumber(),giohang.getGiatien()))!=0) {
	        cx.add(new ChitietPhieuXuat(x.maphieu(),ma,gia,(giohang.getGiatien())/gia));
            long t=cx.sl(ma);
           for(int i=0;i<gia;i++) {
        	   cx.update(new ChitietSanpham(t,ma,cx.pn(t),x.maphieu(),0));
        	   t--;
           }
            
	        p.updateQuantity(ma,gia);
	        p.updatesl(giohang.getMasp());
        }
        }}}
	        
    	Thread thread1 = new Thread(()->{
    		msp = new ModelSanPhamDatHang();
	    	  msp.setSoluong(cbb.getValue());
	    	  
	       dao_giohang = new DAO_giohang();
	        int giatri= cbb.getValue();
	        int soluongt= giohang.getSoluongton();
	        int soluongconlai= soluongt-giatri;
	       // dao_giohang.updatesoluong(soluongconlai, giohang.getMaphienbansp());
	        
	        
    	}); 
    	Thread threadsocket= new Thread(()->
    	{
    		try(Socket socket = new Socket("localhost", 9999); 
	  	            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	  		             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
	    		Taikhoan currentUser1 = SharedData.getInstance().getCurrentUser();

	  				msp= new ModelSanPhamDatHang(msp.getId(),giohang.getMasp() ,giohang.getTensp(), giohang.getGiatien(),msp.getSoluong() , giohang.getRom(),  giohang.getHinhanh(),currentUser.getUsername());//		    dao.add(giohang);
	  	            out.writeObject(msp);
	  	            
	  	            
	  	            String response = (String) in.readObject();
	  	            System.out.println("Server response: " + response);
	  	            
	  				
	  			socket.close();
	  			} catch (Exception e) {
	  	            e.printStackTrace();
	  	          
	  			}
    	});
    	  Thread thread2 = new Thread((
    			  ) -> {
    	
    	  	        
    	  	  Platform.runLater(() -> {
    	  		     
    	  		  
    	  		  
    	  		
      	      Alert a = new Alert(AlertType.INFORMATION);
	      		a.setTitle("Thông báo !");
	      	a.setContentText("Bạn đã đặt hàng thành công");
	      	a.showAndWait();  
	       dao_giohang.delete(giohang);
	       
	    	try {
	    	       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewUserPhone./Cart.fxml"));
	    	       Parent cart = loader.load();
	    	       Scene Homecart = new Scene(cart);

	    	       Stage currentStage = (Stage) btnxoasanpham.getScene().getWindow();
	    	       currentStage.setScene(Homecart);
	    	   } catch (IOException ex) {
	    	       ex.printStackTrace();
	    	   }
              
              
    	  	        
	 
    		  });
    	  	  

    	  });
    	  thread1.start();
    	  threadsocket.start();
    	  thread2.start();

    }
		
    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			 

			}
			
		
}
