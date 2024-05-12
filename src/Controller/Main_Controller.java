package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Sanpham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main_Controller implements Initializable{
	
	@FXML
	private HBox cartPhone;
	private  List<Sanpham> Changecard;
	
	@FXML
	private AnchorPane carousel;
	
	@FXML
	private BorderPane borderPane;
	
	@FXML
	private VBox vBox;
	
	@FXML
	private TextField search;
	@FXML
	private HBox Search;
	@FXML
	private HBox attribute;
	@FXML
    private ImageView img_exit;
	
	 private Sanpham_controller tableController;

	public void setTableController(Sanpham_controller tableController) {
	        this.tableController = tableController;
	}
	
	@FXML
    public void selected(MouseEvent event) {
		Isselected(event);
	    String path="/View/Sanpham_view.fxml";
	    loader(path);
//	    search.textProperty().addListener((observable, oldValue, newValue) -> {
//	        
//	    	tableController.search(newValue);
//	    });

    }
	
	@FXML
	public void homepage(MouseEvent e) {
		 try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Homepage.fxml"));
	            VBox newPage = loader.load();
	            Node centerContent = borderPane.getCenter();
	            if (centerContent != null) {
	                borderPane.getChildren().remove(centerContent);
	            }

	            borderPane.setCenter(newPage);
	            Search.setVisible(false);
	            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/View/Carousel_for_homepage.fxml"));
	            StackPane newPage1 = loader1.load();
	            carousel.getChildren().clear();
	            carousel.getChildren().add(newPage1);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	}
	@FXML
    public void thuoctinh(MouseEvent event) {
		Isselected(event);
	    ContextMenu contextMenu = new ContextMenu();

	    ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();

	    MenuItem brand = new MenuItem("Thương hiệu");
	    MenuItem xuatxu = new MenuItem("Xuất xứ");
	    MenuItem hedieuhanh = new MenuItem("Hệ điều hành");
	    MenuItem ram = new MenuItem("Ram");
	    MenuItem rom = new MenuItem("Rom");
	    MenuItem color = new MenuItem("Màu sắc");
	    menuItems.addAll(brand, xuatxu, hedieuhanh,ram,rom,color);
        
        ImageView imageView = new ImageView("/Other/brand.png");
        imageView.setFitWidth(20); 
        imageView.setFitHeight(20); 
        ImageView imageView1 = new ImageView("/Other/Xuatxu.jpg");
        imageView1.setFitWidth(20); 
        imageView1.setFitHeight(20); 
        ImageView imageView2 = new ImageView("/Other/hdh.jpg");
        imageView2.setFitWidth(20); 
        imageView2.setFitHeight(20);
        ImageView imageView3 = new ImageView("/Other/ram.png");
        imageView3.setFitWidth(20); 
        imageView3.setFitHeight(20); 
        ImageView imageView4 = new ImageView("/Other/rom.png");
        imageView4.setFitWidth(20); 
        imageView4.setFitHeight(20); 
        ImageView imageView5 = new ImageView("/Other/color.jpg");
        imageView5.setFitWidth(20); 
        imageView5.setFitHeight(20); 
       
        brand.setGraphic(imageView); 
        xuatxu.setGraphic(imageView1); 
        hedieuhanh.setGraphic(imageView2); 
        ram.setGraphic(imageView3); 
        rom.setGraphic(imageView4); 
        color.setGraphic(imageView5); 
        contextMenu.getItems().setAll(menuItems);
        contextMenu.show(attribute, event.getScreenX(), event.getScreenY());
        
        brand.setOnAction(e -> {
            String path="/View/Thuonghieu_view.fxml";
            loader(path);
        });
        xuatxu.setOnAction(e -> {
            String path="/View/Xuatxu_view.fxml";
            loader(path);
        });
        hedieuhanh.setOnAction(e -> {
            String path="/View/hdh_view.fxml";
            loader(path);
        });
        ram.setOnAction(e -> {
            String path="/View/ram_view.fxml";
            loader(path);
        });
        rom.setOnAction(e -> {
            String path="/View/rom_view.fxml";
            loader(path);
        });
        color.setOnAction(e -> {
            String path="/View/color_view.fxml";
            loader(path);
        });
        
    }
	@FXML
    public void kho(MouseEvent event) {
		Isselected(event);
		String path="/View/Kho_view.fxml";
		loader(path);
    }
	@FXML
    public void nhap(MouseEvent event) {
		Isselected(event);
		String path="/View/Phieunhap_view.fxml";
		loader(path);
    }
	@FXML
    public void xuat(MouseEvent event) {
		Isselected(event);
		String path="/View/Phieuxuat_view.fxml";
		loader(path);
    }
	@FXML
    public void khachhang(MouseEvent event) {
		Isselected(event);
		String path="/View/Khachhang_view.fxml";
		loader(path);
    }
	@FXML
    public void ncc(MouseEvent event) {
		Isselected(event);
		String path="/View/Nhacungcap_view.fxml";
		loader(path);
    }
	@FXML
    public void nv(MouseEvent event) {
		Isselected(event);
		String path="/View/Nhanvien_view.fxml";
		loader(path);
    }
	@FXML
    public void taikhoan(MouseEvent event) {
		Isselected(event);

    }
	@FXML
    public void tk(MouseEvent event) {
		Isselected(event);

    }
	@FXML
	public void exit(MouseEvent event) {
		Stage stage = (Stage) img_exit.getScene().getWindow();
        stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Changecard=new ArrayList<>(Changecard());
        Search.setVisible(false);
	try {
		for(int i=0;i<Changecard.size();i++) {
		 FXMLLoader fxmlload=new FXMLLoader();
		 fxmlload.setLocation(getClass().getResource("/View/Card.fxml"));
		HBox card=fxmlload.load();
		CardController set=fxmlload.getController();;
		set.SetData(Changecard.get(i));
		cartPhone.getChildren().add(card);
		} 
	}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/View/Carousel_for_homepage.fxml"));
        StackPane newPage1 = loader1.load();
        carousel.getChildren().add(newPage1);
    } catch (IOException e1) {
        e1.printStackTrace();
    }
	
	}
    
	private List<Sanpham> Changecard(){
		List<Sanpham> list=new ArrayList<>();
		Sanpham sp=new Sanpham();
		sp.setTensp("iPhone 15 Pro Max\n1TB");
		sp.setHinhanh("/Other/iphone-15-pro-max-blue-thumbnew-600x600.jpg");
		sp.setGiaTienkemdonvi("43.490.000₫ ");
		list.add(sp);
		
		sp=new Sanpham();
		sp.setTensp("iPhone 15 Pro Max\nTitan 512GB");
		sp.setHinhanh("/Other/iphone-15-pro-max-titan.jpg");
		sp.setGiaTienkemdonvi("36.990.000₫ ");
		list.add(sp);
		
		sp=new Sanpham();
		sp.setTensp("Samsung Galaxy \nZ Fold 3256GB");
		sp.setHinhanh("/Other/samsung-galaxy Z-fold3_5G.jpg");
		sp.setGiaTienkemdonvi("19.700.000₫ ");
		list.add(sp);
		
		sp=new Sanpham();
		sp.setTensp("Realme GT Neo 5\n516GB");
		sp.setHinhanh("/Other/Realme.jpg");
		sp.setGiaTienkemdonvi("14.780.000₫ ");
		list.add(sp);
		return list;
	}
	private void Isselected(MouseEvent event) {
		HBox selectedHBox = (HBox) event.getSource();
	    selectedHBox.setStyle("-fx-background-color: #ADD8E6;");
        Search.setVisible(true);
        boolean first=true;
	    for (Node node : vBox.getChildren()) {
	    	if(first) {
	    		first=false;
	    		continue;
	    	}
	        if (node != selectedHBox) {
	            node.setStyle("-fx-background-color: white;");
	        }
	    }
	}
	private void loader(String path) {
		try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
    	    Node content=(Node) borderPane.getCenter();
			AnchorPane newPage = loader.load();
			if(content!=null) {
                borderPane.getChildren().remove(content);
                carousel.getChildren().clear();
			}
            borderPane.setCenter(newPage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
