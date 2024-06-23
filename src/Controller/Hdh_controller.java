package Controller;

import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import AtttributeSanPham.Hedieuhanh;
import AtttributeSanPham.Thuonghieu;
import AtttributeSanPham.Xuatxu;
import DAO.Hedieuhanh_DAO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Hdh_controller implements Initializable {

	 @FXML
	    private TableColumn<Hedieuhanh,String> edit;

	    @FXML
	    private TableColumn<Hedieuhanh,Integer> id;

	    @FXML
	    private Label label;
	    
	    @FXML
	    private TextField add_name;

	    @FXML
	    private TableColumn<Hedieuhanh, String> name;

	    @FXML
	    private TableView<Hedieuhanh> table;
	    
	    private Main_Controller mainController;

	    public void setMainController(Main_Controller mainController) {
	        this.mainController = mainController;
	    }
	    
	    private ObservableList<Hedieuhanh> list;
	    
	    private Hedieuhanh_DAO th_DAO;
	    private Hedieuhanh th=null;
	    
	    public Hdh_controller() {
	        list = FXCollections.observableArrayList();
	        th_DAO = new Hedieuhanh_DAO(); 
	        table = new TableView<>();
	    }
	    public void search(String query) {
	        String searchQuery = query.trim();
	        if (!searchQuery.isEmpty()) {
	            ObservableList<Hedieuhanh> searchResult = FXCollections.observableArrayList();
	            for (Hedieuhanh hd : list) {
	                if (hd.getTenhdh().toLowerCase().contains(searchQuery.toLowerCase())) {
	                    searchResult.add(hd);
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
	            th_DAO.add(new Hedieuhanh(add_name.getText())); 
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
	            mainController.setHDHController(this);
	        }
		}
		@FXML
		void load() {
	        if(list!=null) {
	       	 list.clear();	  
	        }
	   	
	        th_DAO=new Hedieuhanh_DAO();
	        list=th_DAO.selectAll();
	        table.setItems(list);
	   }
		
		private void setRowColors(TableView<Hedieuhanh> t) {
		    t.setRowFactory(row -> new TableRow<Hedieuhanh>() {
		        @Override
		        protected void updateItem(Hedieuhanh item, boolean empty) {
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
			id.setCellValueFactory(new PropertyValueFactory<>("madh"));
			name.setCellValueFactory(new PropertyValueFactory<>("tenhdh"));
			
			
			 Callback<TableColumn<Hedieuhanh, String>, TableCell<Hedieuhanh, String>> cellFoctory = (TableColumn<Hedieuhanh, String> param) -> {
		            
		            final TableCell<Hedieuhanh, String> cell = new TableCell<Hedieuhanh, String>() {
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
		                            
		                        	th_DAO=new Hedieuhanh_DAO();
		                        	th=table.getSelectionModel().getSelectedItem();
		                        	th_DAO.delete(th);
		                        	load();
		               	            setRowColors(table);
		                        });
		                        editIcon.setOnMouseClicked((MouseEvent event) -> {                  	
		                        	Hedieuhanh th = table.getSelectionModel().getSelectedItem();
		                            if (th != null) {
		                                String newT = add_name.getText();
		                                if (newT != null) {
		                                    if (!newT.isEmpty()) {
		                                        th.setTenhdh(newT);
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
