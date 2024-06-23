package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import DAO.PhieuNhap_DAO;
import DAO.PhieuXuat_DAO;
import Model.PhieuNhap;
import Model.PhieuXuat;
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

public class Phieuxuat_controller implements Initializable{

    @FXML
    private TableColumn<PhieuXuat, String> edit;

    @FXML
    private TableColumn<PhieuXuat, Integer> id;

    @FXML
    private TableColumn<PhieuXuat, String> khachhang;

    @FXML
    private TableColumn<PhieuXuat, Integer> maphieuxuat;

    @FXML
    private TableColumn<PhieuXuat, String>nhanviennhap;

    @FXML
    private TableView<PhieuXuat> phieuxuat;

    @FXML
    private TableColumn<PhieuXuat, LocalTime> time;

    @FXML
    private TableColumn<PhieuXuat, Long> total;

private Main_Controller mainController;
    
    public void setMainController(Main_Controller mainController) {
        this.mainController = mainController;
    }
    
    private ObservableList<PhieuXuat> list;    
    private PhieuXuat_DAO px_DAO;
    private PhieuXuat px=null;

    
    public Phieuxuat_controller() {
        list = FXCollections.observableArrayList();
        px_DAO = new PhieuXuat_DAO(); 
        phieuxuat = new TableView<>();
    }
    public void search(String query) {
        String searchQuery = query.trim();
        if (!searchQuery.isEmpty()) {
            ObservableList<PhieuXuat> searchResult = FXCollections.observableArrayList();
            for (PhieuXuat sp : list) {
                if (Integer.toString(sp.getMaphieu()).toLowerCase().contains(searchQuery.toLowerCase())) {
                    searchResult.add(sp);
                }
            }
            load();
            phieuxuat.setItems(searchResult);
        } else {
        	load();
        	phieuxuat.setItems(list);
        }
    }

   

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadData();
		
		 if(mainController != null) {
	            mainController.setPXController(this);
	        }
	}
    
    @FXML
    void load() {
         if(list!=null) {
        	 list.clear();	  
         }
    	
         px_DAO=new PhieuXuat_DAO();
         list=px_DAO.selectAll();
         phieuxuat.setItems(list);
    }

    @FXML
    void select(MouseEvent event) {
    	if(phieuxuat.getSelectionModel().isEmpty()) {
 	    	 Alert alert = new Alert(Alert.AlertType.WARNING);
	             alert.setHeaderText(null);
	             alert.setContentText("Bạn chưa kích chọn hàng muốn xem");
	             alert.showAndWait();
 	        }
  	else {
  		px = phieuxuat.getSelectionModel().getSelectedItem();
  	FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Select_px_view.fxml"));
  	
  	try {
       loader.load();
   } catch (IOException ex) {
  	 ex.printStackTrace();
   }

  	    Select_phieuxuat_controller s = loader.getController();
  	    s.setTextField(px.getMaphieu(), px.getTennv(), px.getTenkh(), px.getThoigiantao());
       
       
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
        int rowIndex = phieuxuat.getItems().indexOf(cellData.getValue()) + 1;
        return createObservableValue(rowIndex);
    });
    id.setSortable(false);
	maphieuxuat.setCellValueFactory(new PropertyValueFactory<>("maphieu"));
	khachhang.setCellValueFactory(new PropertyValueFactory<>("tenkh"));
	nhanviennhap.setCellValueFactory(new PropertyValueFactory<>("tennv"));
	time.setCellValueFactory(new PropertyValueFactory<>("thoigiantao"));
	total.setCellValueFactory(new PropertyValueFactory<>("tongTien"));

	 Callback<TableColumn<PhieuXuat, String>, TableCell<PhieuXuat, String>> cellFoctory = (TableColumn<PhieuXuat, String> param) -> {
            
            final TableCell<PhieuXuat, String> cell = new TableCell<PhieuXuat, String>() {
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
                            
                        	px_DAO=new PhieuXuat_DAO();
                        	px=phieuxuat.getSelectionModel().getSelectedItem();
                        	px_DAO.delete(px);
                        	load();    
                        });
                        

                        HBox managebtn = new HBox( deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         edit.setCellFactory(cellFoctory);
         phieuxuat.setItems(list);
           }
private ObservableValue<Integer> createObservableValue(int value) {
    return new SimpleIntegerProperty(value).asObject();
}

}