package Controller;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.Phieubansp_DAO;
import DAO.Sanpham_DAO;
import DAO.ct_sanpham_DAO;
import Model.ChitietSanpham;
import Model.Phieubansanpham;
import Model.Sanpham;
import PDF.WriteFile_pdf;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Select_phieunhap_controller implements Initializable{

    @FXML
    private TableColumn<Phieubansanpham, String> color;

    
	@FXML
    private TableView<ChitietSanpham> ctsp;

    @FXML
    private TableColumn<Phieubansanpham, Integer> id;

    @FXML
    private TableColumn<ChitietSanpham, Integer> id1;

    @FXML
    private TableColumn<ChitietSanpham, Long> imei;

    @FXML
    private TableColumn<Phieubansanpham, Integer> masp;

    @FXML
    private TextField ncc;

    @FXML
    private TextField nv;

    @FXML
    private TableView<Phieubansanpham> phieuban;

    @FXML
    private TableColumn<Phieubansanpham, Integer> ram;

    @FXML
    private TableColumn<Phieubansanpham, Integer> rom;

    @FXML
    private TableColumn<Phieubansanpham, Integer> sl;

    @FXML
    private TableColumn<Phieubansanpham, String> tensp;

    @FXML
    private TextField text_ma;

    @FXML
    private TableColumn<Phieubansanpham, Integer> tien;
    
    private ObservableList<Phieubansanpham> list;    
    private Phieubansp_DAO sp_DAO;
    private Phieubansanpham sp=null;

    private ObservableList<ChitietSanpham> ct_list;    
    private ct_sanpham_DAO ctsp_DAO;
    private ChitietSanpham ct=null;

    @FXML
    private TextField time;

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
    void print(MouseEvent event) {
    	
    	Thread thread = new Thread(() -> {
   	     
 	        Platform.runLater(() -> {
 	        	WriteFile_pdf pdfWriter = new WriteFile_pdf();
 		        
 				int so = Integer.parseInt(text_ma.getText().replaceAll("\\D+", ""));

 		        pdfWriter.writePN(so);
 	        	
 	        }
    	);});
			thread.start();
		
    }

    
    
    private void loadData(int a,int b) {
		
    	if(ct_list!=null) {
    		ct_list.clear();	  
           }
            ctsp_DAO=new ct_sanpham_DAO();
            ct_list=ctsp_DAO.select(a,b);
            ctsp.setItems(ct_list);
		id1.setCellValueFactory(cellData -> {
	        int rowIndex = ctsp.getItems().indexOf(cellData.getValue()) + 1;
	        return createObservableValue(rowIndex);
	    });
		imei.setCellValueFactory(new PropertyValueFactory<>("imei"));
		
    }
    
        private void load() {
		
		
		id.setCellValueFactory(cellData -> {
	        int rowIndex = phieuban.getItems().indexOf(cellData.getValue()) + 1;
	        return createObservableValue(rowIndex);
	    });
		masp.setCellValueFactory(new PropertyValueFactory<>("masp"));
		tensp.setCellValueFactory(new PropertyValueFactory<>("tensp"));
		ram.setCellValueFactory(new PropertyValueFactory<>("ram"));
		rom.setCellValueFactory(new PropertyValueFactory<>("rom"));
		color.setCellValueFactory(new PropertyValueFactory<>("color"));
		tien.setCellValueFactory(new PropertyValueFactory<>("gianhap"));
		sl.setCellValueFactory(new PropertyValueFactory<>("soluongton"));
		
		 phieuban.setOnMouseClicked(event -> {
				sp=phieuban.getSelectionModel().getSelectedItem();
				if(sp!=null) {
					int so = Integer.parseInt(text_ma.getText().replaceAll("\\D+", ""));
					loadData(sp.getMaphienbansp(),so);
				}
				
	        });
    }

    public void setTextField(int a,String n,String nct,Timestamp timestamp) {
    	text_ma.setText("PN"+Integer.toString(a));
    	nv.setText(n);
    	ncc.setText(nct);
    	if(list!=null) {
       	 list.clear();	  
        }
         sp_DAO=new Phieubansp_DAO();
         list=sp_DAO.ctphieunhap(a);
         phieuban.setItems(list);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         String strDate = sdf.format(timestamp);
    	time.setText(strDate);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		load();
		text_ma.setEditable(false);
		nv.setEditable(false);
		ncc.setEditable(false);
		time.setEditable(false);

	}
	private ObservableValue<Integer> createObservableValue(int value) {
	    return new SimpleIntegerProperty(value).asObject();
	}

}
