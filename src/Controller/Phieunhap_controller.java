package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.Node;
import DAO.PhieuNhap_DAO;
import DAO.Phieubansp_DAO;
import DAO.Sanpham_DAO;
import Model.PhieuNhap;
import Model.Sanpham;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Phieunhap_controller implements Initializable{

    @FXML
    private TableColumn<PhieuNhap, Integer> id;
    
    @FXML
    private TableColumn<PhieuNhap, String> edit;

    @FXML
    private TableColumn<PhieuNhap, Integer> maphieunhap;

    @FXML
    private TableColumn<PhieuNhap, String> nhacc;

    @FXML
    private TableColumn<PhieuNhap, String> nhanviennhap;

    @FXML
    private TableView<PhieuNhap> phieunhap;

    @FXML
    private TableColumn<PhieuNhap, LocalDate> time;

    @FXML
    private TableColumn<PhieuNhap, Long> total;
    
    private Main_Controller mainController;
    
    public void setMainController(Main_Controller mainController) {
        this.mainController = mainController;
    }
    
    private ObservableList<PhieuNhap> list;    
    private PhieuNhap_DAO pn_DAO;
    private PhieuNhap pn=null;

    
    public Phieunhap_controller() {
        list = FXCollections.observableArrayList();
        pn_DAO = new PhieuNhap_DAO(); 
        phieunhap = new TableView<>();
    }
    public void search(String query) {
        String searchQuery = query.trim();
        if (!searchQuery.isEmpty()) {
            ObservableList<PhieuNhap> searchResult = FXCollections.observableArrayList();
            for (PhieuNhap sp : list) {
                if (Integer.toString(sp.getMaphieu()).toLowerCase().contains(searchQuery.toLowerCase())) {
                    searchResult.add(sp);
                }
            }
            load();
            phieunhap.setItems(searchResult);
        } else {
        	load();
            phieunhap.setItems(list);
        }
    }

    

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadData();
		 if(mainController != null) {
	            mainController.setPNController(this);
	        }
	}
	    @FXML
	    void add(MouseEvent event) {
	    	 phieunhap.setOnMouseClicked(e -> {
	 			pn=phieunhap.getSelectionModel().getSelectedItem();
	 			if(pn!=null) {
	 				//setI(pn.getMaphienbansp());
	 				//setTextField1(p.getMasp(),p.getTensp(),p,p.getImei(),p.getSoluongton());
	 				 
	 			}
	 			
	         });

            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/View/Add_phieunhap.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
           	 ex.printStackTrace();
            }
           
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.setX(235); 
            stage.setY(70); 
            stage.show();
            
//            Add_sp_controller add = loader.getController();
//            add.setUpdate(false);           
	    }
    
    @FXML
    void load() {
         if(list!=null) {
        	 list.clear();	  
         }
    	
         pn_DAO=new PhieuNhap_DAO();
         list=pn_DAO.selectAll();
         phieunhap.setItems(list);
    }

    @FXML
    void select(MouseEvent event) {
    	if(phieunhap.getSelectionModel().isEmpty()) {
  	    	 Alert alert = new Alert(Alert.AlertType.WARNING);
	             alert.setHeaderText(null);
	             alert.setContentText("Bạn chưa kích chọn hàng muốn xem");
	             alert.showAndWait();
  	        }
   	else {
   		pn = phieunhap.getSelectionModel().getSelectedItem();
   	FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Select_pn_view.fxml"));
   	
   	try {
        loader.load();
    } catch (IOException ex) {
   	 ex.printStackTrace();
    }

   	    Select_phieunhap_controller s = loader.getController();
   	    s.setTextField(pn.getMaphieu(), pn.getTennv(), pn.getTenncc(), pn.getThoigiantao());
        
        
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
   	    stage.setX(235);
   	    stage.setY(70);
   	    stage.show();
   	

   	}
    }

private void loadData() {
	
	load();
	id.setCellValueFactory(cellData -> {
        int rowIndex = phieunhap.getItems().indexOf(cellData.getValue()) + 1;
        return createObservableValue(rowIndex);
    });
    id.setSortable(false);
	maphieunhap.setCellValueFactory(new PropertyValueFactory<>("maphieu"));
	nhacc.setCellValueFactory(new PropertyValueFactory<>("tenncc"));
	nhanviennhap.setCellValueFactory(new PropertyValueFactory<>("tennv"));
	time.setCellValueFactory(new PropertyValueFactory<>("thoigiantao"));
	total.setCellValueFactory(new PropertyValueFactory<>("tongTien"));

	 Callback<TableColumn<PhieuNhap, String>, TableCell<PhieuNhap, String>> cellFoctory = (TableColumn<PhieuNhap, String> param) -> {
            
            final TableCell<PhieuNhap, String> cell = new TableCell<PhieuNhap, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                    	                          
                         Image image1 = new Image("/Other/delete.jpg");
                         
                         ImageView deleteIcon = new ImageView(image1);

                         deleteIcon.setFitWidth(20); 
                         deleteIcon.setFitHeight(20);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                        );
                       
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                        	pn_DAO=new PhieuNhap_DAO();
                        	pn=phieunhap.getSelectionModel().getSelectedItem();
                        	pn_DAO.delete(pn);
                        	load();    
                        });
                       

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         edit.setCellFactory(cellFoctory);
         phieunhap.setItems(list);
           }
private ObservableValue<Integer> createObservableValue(int value) {
    return new SimpleIntegerProperty(value).asObject();
}

}