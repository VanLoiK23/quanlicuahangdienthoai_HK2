package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Sanpham;
import PDF.WriteFile_pdf;
import javafx.application.Platform;
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
	
	private Sanpham_controller sanphamController;

	public void setTableController(Sanpham_controller sanphamController) {
	        this.sanphamController = sanphamController;
	}
	private Thuonghieu_controller thController;

	public void setTHController(Thuonghieu_controller thController) {
	        this.thController = thController;
	}
	private Xuatxu_controller xxController;

	public void setXXController(Xuatxu_controller xxController) {
	        this.xxController = xxController;
	}
	private Hdh_controller hdhController;

	public void setHDHController(Hdh_controller hdhController) {
	        this.hdhController = hdhController;
	}
	private ram_controller raController;

	public void setRAController(ram_controller raController) {
	        this.raController = raController;
	}
	private rom_controller roController;

	public void setROController(rom_controller roController) {
	        this.roController = roController;
	}
	private color_controller clController;

	public void setCLController(color_controller clController) {
	        this.clController = clController;
	}
	private Kho_controller khoController;

	public void setKhoController(Kho_controller khoController) {
	        this.khoController = khoController;
	}
	private Nhacungcap_controller nccController;

	public void setNccController( Nhacungcap_controller nccController) {
	        this.nccController = nccController;
	}
	
	private Nhanvien_controller nvController;
	public void setNvController(Nhanvien_controller nvController) {
		this.nvController=nvController;
	}
	
	private Phieunhap_controller pnController;
	public void setPNController(Phieunhap_controller pnController) {
		this.pnController=pnController;
	}
	
	private Phieuxuat_controller pxController;
	public void setPXController(Phieuxuat_controller pxController) {
		this.pxController=pxController;
	}
	private Add_phieunhapcontroller addpnController;
	public void setAddPNController(Add_phieunhapcontroller addpnController) {
		this.addpnController=addpnController;
	}
	private Khachhang_controller kh_controller;
	public void setKHController(Khachhang_controller kh_controller) {
		this.kh_controller=kh_controller;
	}
	@FXML
	public void selected(MouseEvent event) {
	    Isselected(event);
	    String path = "/View/Sanpham_view.fxml";
	    Sanpham_controller sanphamController = loader(path).getController();
        sanphamController.setMainController(this); 

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (sanphamController != null ) {
                sanphamController.search(newValue);
            }
        });
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
            Thuonghieu_controller thController = loader(path).getController();
            thController.setMainController(this); 

	        search.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (thController != null ) {
	            	thController.search(newValue);
	            }
	        });
        });
        xuatxu.setOnAction(e -> {
            String path="/View/Xuatxu_view.fxml";
            Xuatxu_controller xxController = loader(path).getController();
            xxController.setMainController(this); 

	        search.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (xxController != null ) {
	            	xxController.search(newValue);
	            }
	        });
        });
        hedieuhanh.setOnAction(e -> {
            String path="/View/hdh_view.fxml";
            Hdh_controller hdhController = loader(path).getController();
            hdhController.setMainController(this); 

	        search.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (hdhController != null ) {
	            	hdhController.search(newValue);
	            }
	        });
        });
        ram.setOnAction(e -> {
            String path="/View/ram_view.fxml";
            ram_controller raController = loader(path).getController();
            raController.setMainController(this); 

	        search.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (raController != null ) {
	            	raController.search(newValue);
	            }
	        });
        });
        rom.setOnAction(e -> {
            String path="/View/rom_view.fxml";
            rom_controller roController = loader(path).getController();
            roController.setMainController(this); 

	        search.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (roController != null ) {
	            	roController.search(newValue);
	            }
	        });
        });
        color.setOnAction(e -> {
            String path="/View/color_view.fxml";
            color_controller clController = loader(path).getController();
            clController.setMainController(this); 

	        search.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (clController != null ) {
	            	clController.search(newValue);
	            }
	        });
        });
        
    }
	@FXML
    public void kho(MouseEvent event) {
		Isselected(event);
		String path="/View/Kho_view.fxml";
		Kho_controller khoController = loader(path).getController();
        khoController.setMainController(this); 

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (khoController != null ) {
            	khoController.search(newValue);
            }
        });
    }
	@FXML
    public void nhap(MouseEvent event) {

        Thread thread = new Thread(() -> {
      	     
 	        Platform.runLater(() -> {
 	        	
 	        	Isselected(event);
 	   		String path="/View/Phieunhap_view.fxml";
 	   		Phieunhap_controller pn_Controller = loader(path).getController();
 	   		pn_Controller.setMainController(this); 
 	   		
 	   		search.textProperty().addListener((observable, oldValue, newValue) -> {
 	               if (pn_Controller != null ) {
 	               	pn_Controller.search(newValue);
 	               }
 	           });

 	        }
    	);});
			thread.start();
    }
	@FXML
    public void xuat(MouseEvent event) {
		 Thread thread = new Thread(() -> {
      	     
	 	        Platform.runLater(() -> {
	 	        	
	 	        	Isselected(event);
	 	   		String path="/View/Phieuxuat_view.fxml";
	 	   		Phieuxuat_controller px_Controller = loader(path).getController();
	 	   		px_Controller.setMainController(this); 

	 	           search.textProperty().addListener((observable, oldValue, newValue) -> {
	 	               if (px_Controller != null ) {
	 	               	px_Controller.search(newValue);
	 	               }
	 	           });

	 	        }
	    	);});
				thread.start();
    }
	@FXML
    public void khachhang(MouseEvent event) {
		Isselected(event);
		String path="/View/Khachhang_view.fxml";
		Khachhang_controller kh_controller = loader(path).getController();
		kh_controller.setMainController(this); 

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (kh_controller != null ) {
            	kh_controller.search(newValue);
            }
        });
    }
	@FXML
    public void ncc(MouseEvent event) {
		Isselected(event);
		String path="/View/Nhacungcap_view.fxml";
		Nhacungcap_controller nccController = loader(path).getController();
		nccController.setMainController(this); 

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (nccController != null ) {
            	nccController.search(newValue);
            }
        });
    }
	@FXML
    public void nv(MouseEvent event) {
		Isselected(event);
		String path="/View/Nhanvien_view.fxml";
		Nhanvien_controller nvController = loader(path).getController();
		nvController.setMainController(this); 

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (nvController != null ) {
            	nvController.search(newValue);
            }
        });
    }
	@FXML
    public void tk(MouseEvent event) {
		Isselected(event);
		String path="/View/Thongke_tongquan.fxml";
        loader(path);
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
	public FXMLLoader loader(String path) {
		if(path.equals("/View/Add_phieunhap.fxml")) {
			Search.setManaged(false);
		}
		else {
			Search.setManaged(true);
		}
		try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
    	    Node content=(Node) borderPane.getCenter();
			AnchorPane newPage = loader.load();
			if(content!=null) {
                borderPane.getChildren().remove(content);
                carousel.getChildren().clear();
			}
            borderPane.setCenter(newPage);
            return loader;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
