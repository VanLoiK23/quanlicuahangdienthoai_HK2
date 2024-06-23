package Controller;

import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AtttributeSanPham.Ram;
import AtttributeSanPham.Rom;
import DAO.ram_DAO;
import DAO.rom_DAO;
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

public class rom_controller implements Initializable {

	 @FXML
	    private TableColumn<Rom,String> edit;

	    @FXML
	    private TableColumn<Rom,Integer> id;

	    @FXML
	    private Label label;
	    
	    @FXML
	    private TextField add_name;

	    @FXML
	    private TableColumn<Rom, String> name;

	    @FXML
	    private TableView<Rom> table;
	    
	    private Main_Controller mainController;

	    public void setMainController(Main_Controller mainController) {
	        this.mainController = mainController;
	    }
	    
	    private ObservableList<Rom> list;
	    
	    private rom_DAO r_DAO;
	    private Rom r=null;
	    
	    public rom_controller() {
	        list = FXCollections.observableArrayList();
	        r_DAO = new rom_DAO(); 
	        table = new TableView<>();
	    }
	    public void search(String query) {
	        String searchQuery = query.trim();
	        if (!searchQuery.isEmpty()) {
	            ObservableList<Rom> searchResult = FXCollections.observableArrayList();
	            for (Rom ro : list) {
	                if (Integer.toString(ro.getDungluongRom()).toLowerCase().contains(searchQuery.toLowerCase())) {
	                    searchResult.add(ro);
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
	    	Pattern pa=Pattern.compile("\\d*");
			Matcher ma=pa.matcher(add_name.getText());
	        if (add_name != null && add_name.getText() != null && !add_name.getText().isEmpty() && ma.matches()) {
	             r_DAO.add(new Rom(Integer.parseInt(add_name.getText()))); 
				 clear();
				 load();
				 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	             alert.setHeaderText(null);
	             alert.setContentText("Successfull");
	             alert.showAndWait();
		         setRowColors(table);
	        }
	        else {
	        	Alert alert = new Alert(Alert.AlertType.WARNING);
	             alert.setHeaderText(null);
	             alert.setContentText("Chỉ cần điền dung lượng ");
	             alert.showAndWait();
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
	            mainController.setROController(this);
	        }
		}
		@FXML
		void load() {
	        if(list!=null) {
	       	 list.clear();	  
	        }
	   	
	        r_DAO=new rom_DAO();
	        list=r_DAO.selectAll();
	        table.setItems(list);
	   }
		
		private void setRowColors(TableView<Rom> t) {
		    t.setRowFactory(row -> new TableRow<Rom>() {
		        @Override
		        protected void updateItem(Rom item, boolean empty) {
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
			id.setCellValueFactory(new PropertyValueFactory<>("madl"));
			name.setCellValueFactory(new PropertyValueFactory<>("dungluongRom"));
			
			
			 Callback<TableColumn<Rom, String>, TableCell<Rom, String>> cellFoctory = (TableColumn<Rom, String> param) -> {
		            
		            final TableCell<Rom, String> cell = new TableCell<Rom, String>() {
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
		                            
		                        	r_DAO=new rom_DAO();
		                        	r=table.getSelectionModel().getSelectedItem();
		                        	r_DAO.delete(r);
		                        	load();
		               	            setRowColors(table);
		                        });
		                        editIcon.setOnMouseClicked((MouseEvent event) -> {                  	
		                        	Rom r = table.getSelectionModel().getSelectedItem();
		                            if (r != null) {
		                                String newT = add_name.getText();
		                                if (newT != null) {
		                                    if (!newT.isEmpty()) {
		                                        r.setDungluongRom(Integer.parseInt(newT));
		                                        r_DAO.update(r);
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
