package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.Kho_DAO;
import DAO.Sanpham_DAO;
import Model.Khuvuckho;
import Model.Sanpham;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Kho_controller implements Initializable{
	
	@FXML
    private TableColumn<Khuvuckho, String> edit;

    @FXML
    private TableColumn<Khuvuckho, String> ghichu;

    @FXML
    private TableColumn<Khuvuckho, Integer> makho;

    @FXML
    private TableColumn<Khuvuckho, String> tenkho;
    
    @FXML
    private TableView<Khuvuckho> table;
    
    @FXML
    private VBox vbox;
    
    private ObservableList<Khuvuckho> list;
    private Kho_DAO k_DAO;
    private Khuvuckho k=null;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();
		table.setOnMouseClicked((MouseEvent event) -> {                  	
       	   Khuvuckho k = table.getSelectionModel().getSelectedItem();
           if(k!=null) {
        	   Sanpham_DAO sp=new Sanpham_DAO();
        	   List<Sanpham> Changecard=new ArrayList<>(sp.sptrongkho(k));
        	   if (Changecard != null) {	
        		   for (Node node : new ArrayList<>(vbox.getChildren())) {
                       if (!(node instanceof Label)) {
                           vbox.getChildren().remove(node);
                       }
                   }
        		   try {
           			for(int i=0;i<Changecard.size();i++) {
           			 FXMLLoader fxmlload=new FXMLLoader();
           			 fxmlload.setLocation(getClass().getResource("/View/Sanphamtrongkho_view.fxml"));
           			HBox card=fxmlload.load();
           			Sanphamtrongkho_controller set=fxmlload.getController();;
           			set.SetData(Changecard.get(i));
           			vbox.getChildren().add(card);
           			} 
           		}catch (IOException e) {
           				// TODO Auto-generated catch block
           				e.printStackTrace();
           			}
        	   }
        			
           }
        });
	}
    
	    @FXML
	    void add(MouseEvent event) {
            try {
            	Parent parent=FXMLLoader.load(getClass().getResource("/View/Add_kho.fxml"));
            	Scene scene=new Scene(parent);
            	Stage stage =new Stage();
            	stage.setScene(scene);
            	stage.initStyle(StageStyle.UTILITY);
            	stage.show();
            	load();
            }catch(IOException e) {
            	e.printStackTrace();
            }
	    }

    
    @FXML
    void load() {
         if(list!=null) {
        	 list.clear();	  
         }
    	
         k_DAO=new Kho_DAO();
         list=k_DAO.selectAll();
         table.setItems(list);
    }
    private void loadData() {
		
		load();
		makho.setCellValueFactory(new PropertyValueFactory<>("makhuvuc"));
		tenkho.setCellValueFactory(new PropertyValueFactory<>("tenkhuvuc"));
		ghichu.setCellValueFactory(new PropertyValueFactory<>("ghichu"));
		 Callback<TableColumn<Khuvuckho, String>, TableCell<Khuvuckho, String>> cellFoctory = (TableColumn<Khuvuckho, String> param) -> {
	            
	            final TableCell<Khuvuckho, String> cell = new TableCell<Khuvuckho, String>() {
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
	                            
	                        	k_DAO=new Kho_DAO();
	                        	k=table.getSelectionModel().getSelectedItem();
	                        	k_DAO.delete(k);
	                        	load();    
	                        });
	                        editIcon.setOnMouseClicked((MouseEvent event) -> {                  	
	                        	 Khuvuckho k = table.getSelectionModel().getSelectedItem();
	                             FXMLLoader loader = new FXMLLoader ();
	                             loader.setLocation(getClass().getResource("/View/Add_kho.fxml"));
	                             try {
	                                 loader.load();
	                             } catch (IOException ex) {
	                            	 ex.printStackTrace();
	                             }
	                             
	                             Add_kho_controller add = loader.getController();
	                             add.setUpdate(true);
	                             add.setMaK(k.getMakhuvuc());
		                         add.setTextField(k.getTenkhuvuc(),k.getGhichu());
	                             Parent parent = loader.getRoot();
	                             Stage stage = new Stage();
	                             stage.setScene(new Scene(parent));
	                             stage.initStyle(StageStyle.UTILITY);
	                             stage.show();
//   
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
	         load();
	}
}

