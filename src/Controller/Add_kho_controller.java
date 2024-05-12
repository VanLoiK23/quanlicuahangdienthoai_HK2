package Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import AtttributeSanPham.Hedieuhanh;
import AtttributeSanPham.Thuonghieu;
import AtttributeSanPham.Xuatxu;
import DAO.Hedieuhanh_DAO;
import DAO.Kho_DAO;
import DAO.Sanpham_DAO;
import DAO.Thuonghieu_DAO;
import DAO.Xuatxu_DAO;
import Model.Khuvuckho;
import Model.Sanpham;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Add_kho_controller implements Initializable{
	
    private int mak;
	
	public void setMaK(int mak) {
	    this.mak = mak;
	}
 
	private boolean update;
    @FXML
    private TextArea ghichu;

    @FXML
    private Label label;

    @FXML
    private TextField ten;

    @FXML
    void cancel(MouseEvent event) {

    }

    @FXML
    void save(MouseEvent event) {
         String name=ten.getText();
         String ghi=ghichu.getText();
        
         if (name.isEmpty() || ghi.isEmpty() ) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setContentText("Please Fill All DATA");
             alert.showAndWait();

         }
         else {
        	 getQuery();
        	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setHeaderText(null);
             alert.setContentText("Successfull");
             alert.showAndWait();
         }
    }
   
   
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
	}
    
    private void getQuery() {
    	Kho_DAO k=new Kho_DAO();
        if (update == false) {
        	k.add(new Khuvuckho(ten.getText(),ghichu.getText()));
        }else{
        	k.update(new Khuvuckho(mak,ten.getText(),ghichu.getText()));
        }

    }
    
    void setTextField(String name,String ghich) {
    	 label.setText("Chỉnh sửa khu vực kho");
         ten.setText(name);
         ghichu.setText(ghich);
    }
    void setUpdate(boolean b) {
        this.update = b;
    }

}
