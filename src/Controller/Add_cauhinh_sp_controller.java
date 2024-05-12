package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import AtttributeSanPham.Color;

import AtttributeSanPham.Ram;
import AtttributeSanPham.Rom;
import DAO.Phieubansp_DAO;
import DAO.Sanpham_DAO;
import DAO.color_DAO;
import DAO.ram_DAO;
import DAO.rom_DAO;
import Model.Phieubansanpham;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Add_cauhinh_sp_controller implements Initializable{

    @FXML
    private TableColumn<Phieubansanpham, String> color;

    private int i;
    
    public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@FXML
    private TableColumn<Phieubansanpham, Integer> id;

    @FXML
    private Label label;
    
    @FXML
    private Button bt;

    
    @FXML
    private TableView<Phieubansanpham> sanpham;

    @FXML
    private TableColumn<Phieubansanpham, String> priceNhap;

    @FXML
    private TableColumn<Phieubansanpham, String> priceXuat;

    @FXML
    private TableColumn<Phieubansanpham, String> ram;

    @FXML
    private TableColumn<Phieubansanpham, String> rom;

    @FXML
    private ChoiceBox<Color> text_color;

    @FXML
    private TextField text_priceN;

    @FXML
    private TextField text_priceX;

    @FXML
    private ChoiceBox<Ram> text_ram;

    @FXML
    private ChoiceBox<Rom> text_rom;
    
    private ObservableList<Phieubansanpham> list;
    private Phieubansp_DAO sp_DAO;
    private Phieubansanpham sp=null;

    @FXML
    void load() {
    	if(list!=null) {
       	 list.clear();	  
        }
   	
    	
        if(readFromFile("check.txt")) {
        	sp_DAO=new Phieubansp_DAO();
        	sp_DAO.setU(true);
            list=sp_DAO.selectAll();
            sanpham.setItems(list);
        }
        else {
        	sp_DAO=new Phieubansp_DAO();
        	sp_DAO.setU(false);
            list=sp_DAO.selectAll();
            sanpham.setItems(list);
        }
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  ram_DAO ra=new ram_DAO();
		  text_ram.getItems().addAll(ra.selectAll());
		  
		  
		  rom_DAO ro=new rom_DAO();
		  text_rom.getItems().addAll(ro.selectAll());
		  
		  color_DAO co=new color_DAO();
		  text_color.getItems().addAll(co.selectAll());
		  if(readFromFile("check.txt")) {
			  label.setText("Chỉnh sửa cấu hình");
			  bt.setText("Lưu lại");
		  }
		  loadData();
	}
	    @FXML
	    void add_ch(MouseEvent event) {
	    	    ram_DAO  ra=new ram_DAO();
	    	    rom_DAO  ro=new rom_DAO();
	    	    color_DAO  col=new color_DAO();

	    	    String new_ram = text_ram.getValue() != null ? text_ram.getValue().toString() : null;
	    	    String new_rom = text_rom.getValue() != null ? text_rom.getValue().toString() : null;
	    	    String new_color = text_color.getValue() != null ? text_color.getValue().toString() : null;
                if (new_ram != null&&new_rom != null&&new_color != null && text_priceN.getText()!=null&&text_priceX.getText()!=null&&!text_priceN.getText().isEmpty() && !text_priceX.getText().isEmpty()) {
                	int N =Integer.parseInt(text_priceN.getText());
                    float X =Float.parseFloat(text_priceX.getText()); 
                	sp_DAO.add(new Phieubansanpham(docso("masp.txt"),ra.selectByNam(Integer.parseInt(new_ram)),ro.selectByNam(Integer.parseInt(new_rom))
                    		 ,col.selectByNam(new_color),N,X));
           	         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	             alert.setHeaderText(null);
    	             alert.setContentText("Successfull");
    	             alert.showAndWait();
    	             load();	
           			 Clear();
               }
                else {
                	 Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setHeaderText(null);
                     alert.setContentText("Please Fill All DATA");
                     alert.showAndWait();
                }
	    }

	    @FXML
	    void delete_ch(MouseEvent event) {
	    	if(sanpham.getSelectionModel().isEmpty()) {
	   	    	 Alert alert = new Alert(Alert.AlertType.WARNING);
		             alert.setHeaderText(null);
		             alert.setContentText("Bạn chưa kích chọn hàng muốn xóa");
		             alert.showAndWait();
	   	        }
	    	else {
	    		sp_DAO=new Phieubansp_DAO();
	        	sp=sanpham.getSelectionModel().getSelectedItem();
	        	sp_DAO.delete(sp);
	        	load();	
	    	}
	    }

	    @FXML
	    void edit_ch(MouseEvent event) {
	    	if(sanpham.getSelectionModel().isEmpty()) {
   	    	 Alert alert = new Alert(Alert.AlertType.WARNING);
	             alert.setHeaderText(null);
	             alert.setContentText("Bạn chưa kích chọn hàng muốn chỉnh sửa");
	             alert.showAndWait();
   	        }
	    	else {
	    		ram_DAO  ra=new ram_DAO();
    	    	rom_DAO  ro=new rom_DAO();
    	    	color_DAO  col=new color_DAO();
    	    	
                 String new_ram =text_ram.getValue().toString();
                 String new_rom =text_rom.getValue().toString();
                 String new_color =text_color.getValue().toString();
                 int N =Integer.parseInt(text_priceN.getText());
                 int X =Integer.parseInt(text_priceX.getText());
                 if (new_ram != null&&new_rom != null&&new_color != null && text_priceN.getText()!=null&&text_priceX.getText()!=null) {
                	 sp_DAO.update(new Phieubansanpham(getI(),docso("masp.txt"),ra.selectByNam(Integer.parseInt(new_ram)),ro.selectByNam(Integer.parseInt(new_rom))
                    		 ,col.selectByNam(new_color),N,X));
            	     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     	             alert.setHeaderText(null);
     	             alert.setContentText("Successfull");
     	             alert.showAndWait();
     	             load();	
            	     Clear();
                 }
	    	}
	    }
	    
	  private void loadData() {
		    load();
			id.setCellValueFactory(cellData -> {
	            int rowIndex = sanpham.getItems().indexOf(cellData.getValue()) + 1;
	            return createObservableValue(rowIndex);
	        });
	        id.setSortable(false);
			ram.setCellValueFactory(new PropertyValueFactory<>("ktram"));
			rom.setCellValueFactory(new PropertyValueFactory<>("ktrom"));
			color.setCellValueFactory(new PropertyValueFactory<>("color"));
			priceNhap.setCellValueFactory(new PropertyValueFactory<>("gianhap"));
			priceXuat.setCellValueFactory(new PropertyValueFactory<>("giaxuat"));
			sanpham.setOnMouseClicked(event -> {
				sp=sanpham.getSelectionModel().getSelectedItem();
				if(sp!=null) {
					 setTextField(sp.getKtram(),sp.getKtrom(),sp.getColor(),sp.getGianhap(),sp.getGiaxuat());
			          setI(sp.getMaphienbansp());
				}
				});
	  }
	  private void Clear() {
		  text_ram.setValue(null);
		  text_rom.setValue(null);
		  text_color.setValue(null);
		  text_priceN.setText(null);
		  text_priceX.setText(null);
	  }
	  private void setTextField(int ra,int ro,String co,int giaX,int giaN) {
		  text_ram.setValue(new Ram(ra));
		  text_rom.setValue(new Rom(ro));
		  text_color.setValue(new Color(co));
		  text_priceN.setText(Integer.toString(giaX));
		  text_priceX.setText(Integer.toString(giaN));
	  }
	  private int docso(String fileName) {
	        try {
	            BufferedReader inFile = new BufferedReader(new FileReader(fileName));
	            String line;
	            
	            while ((line = inFile.readLine()) != null) {
	                int number = Integer.parseInt(line);
	                return number;
	            }

	            inFile.close();
	        } catch (IOException e) {
	            System.out.println("Lỗi khi đọc từ tập tin: " + e.getMessage());
	        }
	        return 0;
		} 

	  @FXML
	  void add_sp(MouseEvent event) {
		  Stage stage = (Stage) ((Node) ( event.getSource())).getScene().getWindow();
          stage.close();   
      }
	  
	  private static boolean readFromFile(String fileName) {
	        boolean value = false;
	        File file = new File(fileName);
	        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	            String line = reader.readLine();
	            if (line != null && !line.isEmpty()) {
	                value = Boolean.parseBoolean(line); 
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return value;
	    }
	  private ObservableValue<Integer> createObservableValue(int value) {
	        return new SimpleIntegerProperty(value).asObject();
	    }
}
