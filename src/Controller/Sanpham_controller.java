package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.Hedieuhanh_DAO;
import DAO.Kho_DAO;
import DAO.Phieubansp_DAO;
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

public class Sanpham_controller implements Initializable{
	
	    @FXML
	    private TableView<Sanpham> sanpham;
	    @FXML
	    private TableColumn<Sanpham, String> image;

	    @FXML
	    private TableColumn<Sanpham, Integer> hedieuhanh;

	    @FXML
	    private TableColumn<Sanpham, Integer> khuvuckho;

	    @FXML
	    private TableColumn<Sanpham, Double> kichthuocman;

	    @FXML
	    private TableColumn<Sanpham, Integer> masp;

	    @FXML
	    private TableColumn<Sanpham, Integer> pin;

	    @FXML
	    private TableColumn<Sanpham, Integer> soluongton;

	    @FXML
	    private TableColumn<Sanpham, String> tensp;

	    @FXML
	    private TableColumn<Sanpham, Integer> thuonghieu;

	    @FXML
	    private TableColumn<Sanpham, Integer> xuatxu;
	    
	    @FXML
	    private TableColumn<Sanpham, String> edit;
	    
	    private ObservableList<Sanpham> list;
	    private Sanpham_DAO sp_DAO;
	    private Sanpham sp=null;
//	    public void search(String query) {
//	        String searchQuery = query.trim();
//	        if (!searchQuery.isEmpty()) {
//	            ObservableList<Sanpham> searchResult = FXCollections.observableArrayList();
//	            for (Sanpham sanpham : list) {
//	                if (sanpham.getTensp().toLowerCase().contains(searchQuery.toLowerCase())) {
//	                    searchResult.add(sanpham);
//	                }
//	            }
//	            this.sanpham.().setItems(searchResult);
//	        } else {
//	            this.tableController.getSanpham().setItems(list);
//	        }
//	    }

	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadData();
		sanpham.setOnMouseClicked(event -> {
	        if (event.getClickCount() == 1) { 
	            sanpham.getSelectionModel().clearSelection();
	        }
	    });
	}
	    @FXML
	    void add(MouseEvent event) {
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/View/Add_sanpham.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
           	 ex.printStackTrace();
            }
            
            Add_sp_controller add = loader.getController();
            add.setUpdate(false);
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
	    }

	    @FXML
	    void load() {
             if(list!=null) {
            	 list.clear();	  
             }
	    	
             sp_DAO=new Sanpham_DAO();
             list=sp_DAO.selectAll();
             sanpham.setItems(list);
	    }

	    @FXML
	    void select(MouseEvent event) {

	    }

	private void loadData() {
		
		load();
		masp.setCellValueFactory(new PropertyValueFactory<>("masp"));
		tensp.setCellValueFactory(new PropertyValueFactory<>("tensp"));
		soluongton.setCellValueFactory(new PropertyValueFactory<>("soluongton"));
		thuonghieu.setCellValueFactory(new PropertyValueFactory<>("tenthuonghieu"));
		hedieuhanh.setCellValueFactory(new PropertyValueFactory<>("tenhedieuhanh"));
		kichthuocman.setCellValueFactory(new PropertyValueFactory<>("kichthuocman"));
		
		image.setCellValueFactory(new PropertyValueFactory<>("hinhanh"));
		image.setCellFactory(param -> new TableCell<Sanpham, String>() {
		    @Override
		    protected void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);

		        if (empty || item == null) {
		            setGraphic(null);
		            setText(null);
		        } else {
		        	File file = new File(item);
	                FileInputStream inputStream;
					try {
						inputStream = new FileInputStream(file);
						Image image = new Image(inputStream);

			            ImageView imageView = new ImageView(image);

			            imageView.setFitWidth(70); 
			            imageView.setFitHeight(70); 

			            setGraphic(imageView);
			            setText(null);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		});



		
		pin.setCellValueFactory(new PropertyValueFactory<>("dungluongpin"));
		xuatxu.setCellValueFactory(new PropertyValueFactory<>("tenxuatxu"));
		khuvuckho.setCellValueFactory(new PropertyValueFactory<>("tenkhuvuc"));
		//edit.setCellValueFactory(new PropertyValueFactory<>("Chức năng"));
		 Callback<TableColumn<Sanpham, String>, TableCell<Sanpham, String>> cellFoctory = (TableColumn<Sanpham, String> param) -> {
	            
	            final TableCell<Sanpham, String> cell = new TableCell<Sanpham, String>() {
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
	                            
	                        	sp_DAO=new Sanpham_DAO();
	                        	sp=sanpham.getSelectionModel().getSelectedItem();
	                        	sp_DAO.delete(sp);
	                        	load();    
	                        });
	                        editIcon.setOnMouseClicked((MouseEvent event) -> {    
	                        	 Sanpham sp = sanpham.getSelectionModel().getSelectedItem();
	                        	 ghiFileMasp(sp.getMasp());
	                             FXMLLoader loader = new FXMLLoader ();
	                             loader.setLocation(getClass().getResource("/View/Add_sanpham.fxml"));
	                             try {
	                                 loader.load();
	                             } catch (IOException ex) {
	                            	 ex.printStackTrace();
	                             }
	                             
	                             Add_sp_controller add = loader.getController();
	                             add.setUpdate(true);
	                             add.setMasp(sp.getMasp());
	                             File file = new File(sp.getHinhanh());
	                             try {
									URL imageURL = file.toURI().toURL();
		                            add.setTextField(imageURL,sp.getTensp(),sp.getTenxuatxu(),sp.getDungluongpin(),sp.getKichthuocman(),sp.getCamerasau(),sp.getCameratruoc(),sp.getTenhedieuhanh(),sp.getTenthuonghieu(),sp.getPhienbanhdh(),sp.getThoigianbaohanh(),sp.getTenkhuvuc());
								} catch (MalformedURLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
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
	         sanpham.setItems(list);
	}
	
	public static void ghiFileMasp(int masp) {
        try {
            FileWriter fileWriter = new FileWriter("masp.txt");
            fileWriter.write(Integer.toString(masp)); 
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }
}
