package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.Kho_DAO;
import DAO.Nhacungcap_DAO;
import Model.Khuvuckho;
import Model.Nhacungcap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class Nhacungcap_controller implements Initializable{

    @FXML
    private TableColumn<Nhacungcap,String> address;

    @FXML
    private TableColumn<Nhacungcap,String> email;

    @FXML
    private TableColumn<Nhacungcap,Integer> mancc;

    @FXML
    private TableColumn<Nhacungcap, String> phone;

    @FXML
    private TableColumn<Nhacungcap, String> tenncc;
   
    @FXML
    private TableColumn<Nhacungcap, String> edit;
    @FXML
    private TableView<Nhacungcap> table;
    
    private Main_Controller mainController;

    public void setMainController(Main_Controller mainController) {
        this.mainController = mainController;
    }

    private ObservableList<Nhacungcap> list;
    private Nhacungcap_DAO ncc_DAO;
    private Nhacungcap ncc=null;
    
    public Nhacungcap_controller() {
        list = FXCollections.observableArrayList();
        ncc_DAO = new Nhacungcap_DAO(); 
        table = new TableView<>();
    }
    public void search(String query) {
        String searchQuery = query.trim();
        if (!searchQuery.isEmpty()) {
            ObservableList<Nhacungcap> searchResult = FXCollections.observableArrayList();
            for (Nhacungcap ro : list) {
                if ((ro.getTenncc()).toLowerCase().contains(searchQuery.toLowerCase())) {
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
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	
	loadData();
	table.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) { 
            table.getSelectionModel().clearSelection();
        }
    });
	
	if(mainController != null) {
        mainController.setNccController(this);
    }
    }
    @FXML
    void add(MouseEvent event) {
        try {
        	Parent parent=FXMLLoader.load(getClass().getResource("/View/Add_nhacc.fxml"));
        	Scene scene=new Scene(parent);
        	Stage stage =new Stage();
        	stage.setScene(scene);
        	stage.initStyle(StageStyle.UTILITY);
        	stage.show();
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }

    @FXML
    void load() {
         if(list!=null) {
        	 list.clear();	  
         }
    	
         ncc_DAO=new Nhacungcap_DAO();
         list=ncc_DAO.selectAll();
         table.setItems(list);
    }
private void loadData() {
	
	load();
	mancc.setCellValueFactory(new PropertyValueFactory<>("mancc"));
	tenncc.setCellValueFactory(new PropertyValueFactory<>("tenncc"));
	address.setCellValueFactory(new PropertyValueFactory<>("diachi"));
	email.setCellValueFactory(new PropertyValueFactory<>("email"));
	phone.setCellValueFactory(new PropertyValueFactory<>("sdt"));
	
	 Callback<TableColumn<Nhacungcap, String>, TableCell<Nhacungcap, String>> cellFoctory = (TableColumn<Nhacungcap, String> param) -> {
            
            final TableCell<Nhacungcap, String> cell = new TableCell<Nhacungcap, String>() {
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
                            
                        	ncc_DAO=new Nhacungcap_DAO();
                        	ncc=table.getSelectionModel().getSelectedItem();
                        	ncc_DAO.delete(ncc);
                        	load();    
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {                  	
                        	 Nhacungcap n = table.getSelectionModel().getSelectedItem();
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("/View/Add_nhacc.fxml"));
                             try {
                                 loader.load();
                             } catch (IOException ex) {
                            	 ex.printStackTrace();
                             }
                             
                             Add_nhacc_controller add = loader.getController();
                             add.setUpdate(true);
                             add.setMaK(n.getMancc());
                             
	                         add.setTextField(n.getTenncc(),n.getDiachi(),n.getEmail(),n.getSdt());
							
                             Parent parent = loader.getRoot();
                             Stage stage = new Stage();
                             stage.setScene(new Scene(parent));
                             stage.initStyle(StageStyle.UTILITY);
                             stage.show();

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

}

}
