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
import DAO.Xuatxu_DAO;
import Model.Sanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

public class Xuatxu_controller implements Initializable {

	@FXML
    private TableColumn<Xuatxu,String> edit;

    @FXML
    private TableColumn<Xuatxu,Integer> id;

    @FXML
    private Label label;
    
    @FXML
    private TextField add_name;

    @FXML
    private TableColumn<Xuatxu, String> name;

    @FXML
    private TableView<Xuatxu> table;
    
    private Main_Controller mainController;

    public void setMainController(Main_Controller mainController) {
        this.mainController = mainController;
    }
    
    private ObservableList<Xuatxu> list;
    
    private Xuatxu_DAO xx_DAO;
    private Xuatxu xx=null;
    
    public Xuatxu_controller() {
        list = FXCollections.observableArrayList();
        xx_DAO = new Xuatxu_DAO(); 
        table = new TableView<>();
    }
    public void search(String query) {
        String searchQuery = query.trim();
        if (!searchQuery.isEmpty()) {
            ObservableList<Xuatxu> searchResult = FXCollections.observableArrayList();
            for (Xuatxu xx : list) {
                if (xx.getNoi().toLowerCase().contains(searchQuery.toLowerCase())) {
                    searchResult.add(xx);
                }
            }
            load();
            table.setItems(searchResult);
        } else {
        	load();
            table.setItems(list);
        }
    }

    @FXML
    void add(MouseEvent event) {
        if (add_name != null && add_name.getText() != null && !add_name.getText().isEmpty()) {
            xx_DAO.add(new Xuatxu(add_name.getText())); 
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
		
		if(mainController != null) {
            mainController.setXXController(this);
        }
	}
	@FXML
	void load() {
        if(list!=null) {
       	 list.clear();	  
        }
   	
        xx_DAO=new Xuatxu_DAO();
        list=xx_DAO.selectAll();
        table.setItems(list);
   }
	
	private void setRowColors(TableView<Xuatxu> t) {
	    t.setRowFactory(row -> new TableRow<Xuatxu>() {
	        @Override
	        protected void updateItem(Xuatxu item, boolean empty) {
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
		id.setCellValueFactory(new PropertyValueFactory<>("maxuatxu"));
		name.setCellValueFactory(new PropertyValueFactory<>("noi"));
		
		
		 Callback<TableColumn<Xuatxu, String>, TableCell<Xuatxu, String>> cellFoctory = (TableColumn<Xuatxu, String> param) -> {
	            
	            final TableCell<Xuatxu, String> cell = new TableCell<Xuatxu, String>() {
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
	                            
	                        	xx_DAO=new Xuatxu_DAO();
	                        	xx=table.getSelectionModel().getSelectedItem();
	                        	xx_DAO.delete(xx);
	                        	load();
	               	            setRowColors(table);
	                        });
	                        editIcon.setOnMouseClicked((MouseEvent event) -> {                  	
	                        	Xuatxu th = table.getSelectionModel().getSelectedItem();
	                            if (th != null) {
	                                String newt = add_name.getText();
	                                if (newt != null) {
	                                    if (!newt.isEmpty()) {
	                                        th.setNoi(newt);
	                                        xx_DAO.update(th);
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