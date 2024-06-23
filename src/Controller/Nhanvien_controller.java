package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import DAO.Nhacungcap_DAO;
import DAO.Nhanvien_DAO;
import Model.Nhacungcap;
import Model.Nhanvien;
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

public class Nhanvien_controller implements Initializable{

    @FXML
    private TableColumn<Nhanvien, String> email;

    @FXML
    private TableColumn<Nhanvien, Integer> manv;

    @FXML
    private TableColumn<Nhanvien, String> name;

    @FXML
    private TableColumn<Nhanvien, Date> ngaysinh;

    @FXML
    private TableColumn<Nhanvien, String> phone;

    @FXML
    private TableColumn<Nhanvien, String> sex;
    
    @FXML
    private TableColumn<Nhanvien, String> edit;

    
    @FXML
    private TableView<Nhanvien> table;
    
    private Main_Controller mainController;

    public void setMainController(Main_Controller mainController) {
        this.mainController = mainController;
    }

    private ObservableList<Nhanvien> list;
    private Nhanvien_DAO nv_DAO;
    private Nhanvien nv=null;
    public Nhanvien_controller() {
        list = FXCollections.observableArrayList();
        nv_DAO = new Nhanvien_DAO(); 
        table = new TableView<>();
    }
    public void search(String query) {
        String searchQuery = query.trim();
        if (!searchQuery.isEmpty()) {
            ObservableList<Nhanvien> searchResult = FXCollections.observableArrayList();
            for (Nhanvien ro : list) {
                if ((ro.getUsername()).toLowerCase().contains(searchQuery.toLowerCase())) {
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
       mainController.setNvController(this);
    }
    }
    @FXML
    void add(MouseEvent event) {
        try {
        	Parent parent=FXMLLoader.load(getClass().getResource("/View/Add_nv.fxml"));
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
    	
         nv_DAO=new Nhanvien_DAO();
         list=nv_DAO.selectAll();
         table.setItems(list);
    }

private void loadData() {
	
	load();
	manv.setCellValueFactory(new PropertyValueFactory<>("manv"));
	name.setCellValueFactory(new PropertyValueFactory<>("username"));
	email.setCellValueFactory(new PropertyValueFactory<>("email"));
	phone.setCellValueFactory(new PropertyValueFactory<>("sdt"));
    ngaysinh.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
    sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
	
	 Callback<TableColumn<Nhanvien, String>, TableCell<Nhanvien, String>> cellFoctory = (TableColumn<Nhanvien, String> param) -> {
            
            final TableCell<Nhanvien, String> cell = new TableCell<Nhanvien, String>() {
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
                            
                        	nv_DAO=new Nhanvien_DAO();
                        	nv=table.getSelectionModel().getSelectedItem();
                        	nv_DAO.delete(nv);
                        	load();    
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {                  	
                        	 Nhanvien n = table.getSelectionModel().getSelectedItem();
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("/View/Add_nv.fxml"));
                             try {
                                 loader.load();
                             } catch (IOException ex) {
                            	 ex.printStackTrace();
                             }
                             
                             Add_nv_controller add = loader.getController();
                             add.setUpdate(true);
                             add.setMaK(n.getManv());
                             
	                         add.setTextField(n.getUsername(),n.getSex(),n.getNgaysinh(),n.getEmail(),n.getSdt());
							
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
