package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import DAO.Khachhang_DAO;
import DAO.Nhacungcap_DAO;
import Model.Khachhang;
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

public class Khachhang_controller implements Initializable{

    @FXML
    private TableColumn<Khachhang, String> address;

    @FXML
    private TableColumn<Khachhang, Integer> makh;

    @FXML
    private TableColumn<Khachhang, LocalDateTime> ngaythamgia;

    @FXML
    private TableColumn<Khachhang, String> phone;

    @FXML
    private TableView<Khachhang> table;

    @FXML
    private TableColumn<Khachhang, String> tenkh;
    
    private Main_Controller mainController;

    public void setMainController(Main_Controller mainController) {
        this.mainController = mainController;
    }

    private ObservableList<Khachhang> list;
    private Khachhang_DAO kh_DAO;
    private Nhacungcap ncc=null;
    
    public Khachhang_controller() {
        list = FXCollections.observableArrayList();
        kh_DAO = new Khachhang_DAO(); 
        table = new TableView<>();
    }
    public void search(String query) {
        String searchQuery = query.trim();
        if (!searchQuery.isEmpty()) {
            ObservableList<Khachhang> searchResult = FXCollections.observableArrayList();
            for (Khachhang ro : list) {
                if ((ro.getHoten()).toLowerCase().contains(searchQuery.toLowerCase())) {
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
        mainController.setKHController(this);
    }
    }

    @FXML
    void load() {
         if(list!=null) {
        	 list.clear();	  
         }
    	
         kh_DAO=new Khachhang_DAO();
         list=kh_DAO.selectAll();
         table.setItems(list);
    }
private void loadData() {
	
	//int maKH, String hoten, String diachi, String sdt,LocalDateTime ngaythamgia
	load();
	makh.setCellValueFactory(new PropertyValueFactory<>("maKH"));
	tenkh.setCellValueFactory(new PropertyValueFactory<>("hoten"));
	address.setCellValueFactory(new PropertyValueFactory<>("diachi"));
	ngaythamgia.setCellValueFactory(new PropertyValueFactory<>("ngaythamgia"));
	phone.setCellValueFactory(new PropertyValueFactory<>("sdt"));
	

}

}
