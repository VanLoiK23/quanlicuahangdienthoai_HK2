package ControlleruserPhone;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Parser;

import DAO.DAO_giohang;
import DAO.DAO_sanphamdathang;
import ModelUserPhone.ModelGiohang;
import ModelUserPhone.ModelSanPhamDatHang;
import ModelUserPhone.modelsanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
   Image image = new Image(path,164,178,false,true); 
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
	DAO_sanphamdathang d;

    @FXML
    void btndathang(MouseEvent event) {
    	msp = new ModelSanPhamDatHang();
    	  msp.setSoluong(cbb.getValue());
    	  
    	d = new DAO_sanphamdathang();
       dao_giohang = new DAO_giohang();
        int giatri= cbb.getValue();
        int soluongt= giohang.getSoluongton();
        int soluongconlai= soluongt-giatri;
        dao_giohang.updatesoluong(soluongconlai, giohang.getMasp());
        System.out.println(soluongconlai);
        System.out.println(giohang.getMasp());
  
    	msp= new ModelSanPhamDatHang(msp.getId(),giohang.getMasp() ,giohang.getTensp(), giohang.getGiatien(),msp.getSoluong() , giohang.getRom(),  giohang.getHinhanh());

        d.add(msp);
        
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
    }
	
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		
			}
			
    
}
