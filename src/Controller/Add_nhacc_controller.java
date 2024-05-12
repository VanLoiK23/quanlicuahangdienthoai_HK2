package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.Kho_DAO;
import DAO.Nhacungcap_DAO;
import Model.Khuvuckho;
import Model.Nhacungcap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Add_nhacc_controller {
	
    private int mancc;
    private boolean update;
	public void setMaK(int mancc) {
	    this.mancc = mancc;
	}

    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private Label label;

    @FXML
    private TextField phone;

    @FXML
    private TextField ten;

    @FXML
    void cancel(MouseEvent event) {

    }

    @FXML
    void save(MouseEvent event) {
         String name=ten.getText();
         String diachi=address.getText();
         String em=email.getText();
         String pone=phone.getText();
        
         if (name.isEmpty() || diachi.isEmpty() || em.isEmpty() || pone.isEmpty() ) {
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
    private void getQuery() {
    	Nhacungcap_DAO k=new Nhacungcap_DAO();
        if (update == false) {
        	k.add(new Nhacungcap(ten.getText(),address.getText(),email.getText(),phone.getText()));
        }else{
        	k.update(new Nhacungcap(mancc,ten.getText(),address.getText(),email.getText(),phone.getText()));
        }

    }
    
    void setTextField(String name,String a,String e,String p) {
    	 label.setText("Chỉnh sửa nhà cung cấp");
         ten.setText(name);
         address.setText(a);
         email.setText(e);
         phone.setText(p);
    }
    void setUpdate(boolean b) {
        this.update = b;
    }
}    
