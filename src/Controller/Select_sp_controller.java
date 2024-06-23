package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.Phieubansp_DAO;
import DAO.ct_sanpham_DAO;
import Model.ChitietSanpham;
import Model.Phieubansanpham;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Select_sp_controller implements Initializable{

    @FXML
    private TableView<ChitietSanpham> ctsp;

    @FXML
    private TableColumn<ChitietSanpham, Long> imei;

    @FXML
    private TableColumn<ChitietSanpham, Integer> nhap;

    @FXML
    private ChoiceBox<Phieubansanpham> pb;

    @FXML
    private TextField sl;

    @FXML
    private TableColumn<ChitietSanpham, String> tinhtrang;

    @FXML
    private TableColumn<ChitietSanpham, Integer>xuat;
    
    private Phieubansp_DAO sp_DAO;
    private Phieubansanpham sp=null;

    private ObservableList<ChitietSanpham> ct_list;    
    private ct_sanpham_DAO ctsp_DAO;
    private ChitietSanpham ct=null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sl.setEditable(false);
		sp_DAO=new Phieubansp_DAO();
		pb.getItems().addAll(sp_DAO.cauhinh());
		
		pb.setOnAction(e -> {
            Phieubansanpham selected = pb.getValue();
            if(selected!=null) {
            	sl.setText(Integer.toString(sp_DAO.selectSL(selected.getMaphienbansp())));
            	loadData(selected.getMaphienbansp());
            }
		});
	}
	
	 private void loadData(int a) {
			
	    	if(ct_list!=null) {
	    		ct_list.clear();	  
	           }
	            ctsp_DAO=new ct_sanpham_DAO();
	            ct_list=ctsp_DAO.selectBymasp(a);
	            ctsp.setItems(ct_list);
			imei.setCellValueFactory(new PropertyValueFactory<>("imei"));
			nhap.setCellValueFactory(new PropertyValueFactory<>("maphieunhap"));
			xuat.setCellValueFactory(new PropertyValueFactory<>("maphieuxuat"));
			tinhtrang.setCellValueFactory(new PropertyValueFactory<>("tt"));

			
	    }

}
