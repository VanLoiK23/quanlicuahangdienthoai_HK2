package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import AtttributeSanPham.Thuonghieu;
import AtttributeSanPham.Xuatxu;
import DAO.Sanpham_DAO;
import DAO.Thuonghieu_DAO;
import Model.Sanpham;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Thuonghieu_controller implements Initializable {

    @FXML
    private TableColumn<Thuonghieu,String> edit;

    @FXML
    private TableColumn<Thuonghieu,Integer> id;

    @FXML
    private Label label;
    
    @FXML
    private TextField add_name;

    @FXML
    private TableColumn<Thuonghieu, String> name;

    @FXML
    private TableView<Thuonghieu> table;
    
    private ObservableList<Thuonghieu> list;
    
    private Thuonghieu_DAO th_DAO;
    private Thuonghieu th=null;

    @FXML
    void add(MouseEvent event) {
        if (add_name != null && add_name.getText() != null && !add_name.getText().isEmpty()) {
            th_DAO.add(new Thuonghieu(add_name.getText())); 
			 clear();
			 load();
			 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setHeaderText(null);
             alert.setContentText("Successfull");
             alert.showAndWait();
	         setRowColors(table);
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadData();
		table.setOnMouseClicked(event -> {
	        if (event.getClickCount() == 1) { 
	            table.getSelectionModel().clearSelection();
	        }
	    });
	}
	@FXML
	void load() {
        if(list!=null) {
       	 list.clear();	  
        }
   	
        th_DAO=new Thuonghieu_DAO();
        list=th_DAO.selectAll();
        table.setItems(list);
   }
	
	private void setRowColors(TableView<Thuonghieu> t) {
	    t.setRowFactory(row -> new TableRow<Thuonghieu>() {
	        @Override
	        protected void updateItem(Thuonghieu item, boolean empty) {
	            super.updateItem(item, empty);

	            if (getIndex() % 2 == 0) {
	                setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 8px;"); 
	            } else {
	                setStyle("-fx-background-color: #E0F7FA; -fx-background-radius: 8px;"); 
	            }
	        }
	    });
	}
	


    private void loadData() {
		
		load();
		id.setCellValueFactory(new PropertyValueFactory<>("math"));
		name.setCellValueFactory(new PropertyValueFactory<>("tenthuonghieu"));
		
		
		 Callback<TableColumn<Thuonghieu, String>, TableCell<Thuonghieu, String>> cellFoctory = (TableColumn<Thuonghieu, String> param) -> {
	            
	            final TableCell<Thuonghieu, String> cell = new TableCell<Thuonghieu, String>() {
	                @Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    
	                    if (empty) {
	                        setGraphic(null);
	                        setText(null);

	                    } else {
	                    	 Image image = new Image("/Other/edit.jpg");

	                         ImageView editIcon = new ImageView(image);
	                         
	                         Image image1 = new Image("/Other/delete.jpg");
	                         
	                         ImageView deleteIcon = new ImageView(image1);

	                         editIcon.setFitWidth(20); 
	                         editIcon.setFitHeight(20); 

	                         deleteIcon.setFitWidth(20); 
	                         deleteIcon.setFitHeight(20);
	                        deleteIcon.setStyle(
	                                " -fx-cursor: hand ;"
	                        );
	                        editIcon.setStyle(
	                                " -fx-cursor: hand ;"
	                        );
	                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
	                            
	                        	th_DAO=new Thuonghieu_DAO();
	                        	th=table.getSelectionModel().getSelectedItem();
	                        	th_DAO.delete(th);
	                        	load();
	               	            setRowColors(table);
	                        });
	                        editIcon.setOnMouseClicked((MouseEvent event) -> {                  	
	                            Thuonghieu th = table.getSelectionModel().getSelectedItem();
	                            if (th != null) {
	                                String newTenThuongHieu = add_name.getText();
	                                if (newTenThuongHieu != null) {
	                                    if (!newTenThuongHieu.isEmpty()) {
	                                        th.setTenthuonghieu(newTenThuongHieu);
	                                        th_DAO.update(th);
	                           	            setRowColors(table);
	                           	         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	                                     alert.setHeaderText(null);
	                                     alert.setContentText("Successfull");
	                                     alert.showAndWait();
	                           			    clear();

	                                    }
	                                } 
	                            }
	                        });

	                        HBox managebtn = new HBox(editIcon, deleteIcon);
	                        managebtn.setStyle("-fx-alignment:center");
	                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
	                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

	                        setGraphic(managebtn);

	                        setText(null);
 
	                    }
	                }

	            };

	            return cell;
	        };
	         edit.setCellFactory(cellFoctory);
	         table.setItems(list);
	         setRowColors(table);

	}
    void clear() {
    	add_name.setText(null);
    }
}
