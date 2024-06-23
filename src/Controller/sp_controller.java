package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.Sanpham_DAO;
import Model.Sanpham;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class sp_controller implements Initializable{

    @FXML
    private TableColumn<Sanpham, Integer> hdh;

    @FXML
    private TableColumn<Sanpham , Integer> id;

    @FXML
    private TableColumn<Sanpham, String> name;

    @FXML
    private TableView<Sanpham> table;

    @FXML
    private TableColumn<Sanpham, Integer> th;
    private ObservableList<Sanpham> list;    
    private Sanpham_DAO sp_DAO;
    private Sanpham sp=null;
    void load() {
         if(list!=null) {
        	 list.clear();	  
         }
    	
         sp_DAO=new Sanpham_DAO();
         list=sp_DAO.selectAll();
         table.setItems(list);
    }
    private void loaddata() {
    	load();
    	id.setCellValueFactory(new PropertyValueFactory<>("masp"));
		name.setCellValueFactory(new PropertyValueFactory<>("tensp"));
		th.setCellValueFactory(new PropertyValueFactory<>("tenthuonghieu"));
		hdh.setCellValueFactory(new PropertyValueFactory<>("tenhedieuhanh"));
		
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        loaddata();		
	}

}
