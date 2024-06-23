package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.Nhacungcap_DAO;
import DAO.Nhanvien_DAO;
import Model.Nhacungcap;
import Model.Nhanvien;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Add_nv_controller implements Initializable{

    @FXML
    private DatePicker code;

    @FXML
    private TextField email;

    @FXML
    private Label label;

    @FXML
    private TextField phone;

    @FXML
    private ComboBox<String> sex;

    @FXML
    private TextField ten;
    
    private int manv;
    private boolean update;
	public void setMaK(int manv) {
	    this.manv = manv;
	}

    @FXML
    void cancel(MouseEvent event) {
    	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Xác nhận");
         alert.setHeaderText("Bạn có muốn thoát không?");

         alert.showAndWait().ifPresent(response -> {
             if (response == javafx.scene.control.ButtonType.OK) {
                 Stage stage = (Stage) ((Node) ( event.getSource())).getScene().getWindow();
                 stage.close();
             }
         });
    }

    @FXML
    void save(MouseEvent event) {
         String name=ten.getText();
         String s=sex.getValue();
         LocalDate dat=code.getValue();
         String em=email.getText();
         String pone=phone.getText();
        
         if (name.isEmpty() || s.isEmpty() || dat.toString().isEmpty() || pone.isEmpty() ||em.isEmpty()) {
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
    	Nhanvien_DAO k=new Nhanvien_DAO();
        if (update == false) {
        	k.add(new Nhanvien(ten.getText(),sex.getValue().toString(),code.getValue(),email.getText(),phone.getText()));
        }else{
        	k.update(new Nhanvien(manv,ten.getText(),sex.getValue().toString(),code.getValue(),email.getText(),phone.getText()));
        }

    }
    
    void setTextField(String name,String gt,LocalDate a,String e,String dt) {
    	 label.setText("Chỉnh sửa nhân viên");
         ten.setText(name);
         sex.setValue(gt);
         code.setValue(a);
         email.setText(e);
         phone.setText(dt);
    }
    void setUpdate(boolean b) {
        this.update = b;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sex.setValue("Nam");
		sex.getItems().addAll(
                "Nam",
                "Nữ");
		
		final LocalDate minDate = LocalDate.of(1990, 1, 1);
        final LocalDate maxDate = LocalDate.of(2005, 12, 31);
        
        code.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(minDate) || date.isAfter(maxDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); 
                }
            }
        });
		
	}
}    

