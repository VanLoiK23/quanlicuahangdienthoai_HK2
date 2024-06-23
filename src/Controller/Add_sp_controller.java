package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Node;

import AtttributeSanPham.Hedieuhanh;
import AtttributeSanPham.Thuonghieu;
import AtttributeSanPham.Xuatxu;
import DAO.Hedieuhanh_DAO;
import DAO.Kho_DAO;
import DAO.Sanpham_DAO;
import DAO.Thuonghieu_DAO;
import DAO.Xuatxu_DAO;
import Model.Khuvuckho;
import Model.Sanpham;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

public class Add_sp_controller implements Initializable{
	
	private int masp;
	
	public void setMasp(int masp) {
	    this.masp = masp;
	}
	public int getMasp() {
	    return masp;
	}


    @FXML
    private TextField baohanh;

    @FXML
    private TextField came_sau;

    @FXML
    private TextField came_truoc;

    @FXML
    private ChoiceBox<Hedieuhanh> hdh;

    @FXML
    private ImageView img;

    @FXML
    private ChoiceBox<Khuvuckho> kho;

    @FXML
    private TextField phienban;

    @FXML
    private TextField pin;

    @FXML
    private TextField size;

    @FXML
    private TextField ten;

    @FXML
    private ChoiceBox<Thuonghieu> thuonghieu;

    private boolean update;
    
    @FXML
    private ChoiceBox<Xuatxu> xuatxu;

    @FXML
    void cancel(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Bạn có muốn thoát không?");

        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                Stage stage = (Stage) ((Node) ( event.getSource())).getScene().getWindow();
                stage.close();
            }
        });
    }

    private boolean cance;
    
    public boolean isCance() {
		return cance;
	}
	public void setCance(boolean cance) {
		this.cance = cance;
	}
	@FXML
    void loadimg(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn ảnh");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Ảnh files (*.jpg, *.jpeg, *.png)", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File initialDirectory = new File("D:\\Code_java\\Do_an_co_so_1\\Sanpham");
        fileChooser.setInitialDirectory(initialDirectory);

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            img.setImage(image);
        }
    }
    
    
    void save() {
         String name=ten.getText();
         
         String xx=xuatxu.getValue().toString();
         String p=pin.getText();
         String dl=size.getText();
         String h=hdh.getValue().toString();
         String p1=phienban.getText();
         String s=came_sau.getText();
         String t=came_truoc.getText();
         String bh=baohanh.getText();
         String th=thuonghieu.getValue().toString();
         String k=kho.getValue().toString();
         if (name.isEmpty() || xx.isEmpty() || p.isEmpty() || dl.isEmpty()||h.isEmpty()||p1.isEmpty()||s.isEmpty()||t.isEmpty()||bh.isEmpty()||th.isEmpty()||k.isEmpty()||img.getImage()==null) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setContentText("Please Fill All DATA");
             alert.showAndWait();
             setCance(true);
         }
         else {
        	 getQuery();
         }
    }
    @FXML
    private Label chinhsuacauhinh;
    @FXML
    void cauhinh(MouseEvent event) {
    	save();
    	if(isCance()==false) {
    		Stage stag = (Stage) ((Node) ( event.getSource())).getScene().getWindow();
            stag.close();
    		try {    	
    			writeToFile("check.txt",update);
            	Parent parent=FXMLLoader.load(getClass().getResource("/View/Cauhinhsp_view.fxml"));
            	Scene scene=new Scene(parent);
            	Stage stage =new Stage();
            	stage.setScene(scene);
            	stage.initStyle(StageStyle.UTILITY);
            	stage.show();
            }catch(IOException e) {
            	e.printStackTrace();
            }	
    	}
    }
    @FXML
    private Label label;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Xuatxu_DAO xx=new Xuatxu_DAO();
    	xuatxu.setValue(new Xuatxu("Việt Nam"));
    	xuatxu.getItems().addAll(xx.selectAll());
    	
    	xuatxu.setOnAction(e -> {
            Xuatxu selected = xuatxu.getValue();
        });
    	Hedieuhanh_DAO hdh1=new Hedieuhanh_DAO();
    	hdh.setValue(new Hedieuhanh("IOS"));
    	hdh.getItems().addAll(hdh1.selectAll());
    	
    	hdh.setOnAction(e -> {
            Hedieuhanh selected = hdh.getValue();
        });
    	Thuonghieu_DAO th=new Thuonghieu_DAO();
    	thuonghieu.setValue(new Thuonghieu("Apple"));
    	thuonghieu.getItems().addAll(th.selectAll());
    	
    	thuonghieu.setOnAction(e -> {
    		Thuonghieu selected = thuonghieu.getValue();
        });
    	Kho_DAO k=new Kho_DAO();
    	kho.setValue(new Khuvuckho("Kho A"));
    	kho.getItems().addAll(k.selectAll());
    	
    	kho.setOnAction(e -> {
    		Khuvuckho selected = kho.getValue();
        });
	}
    
    private void getQuery() {
    	Sanpham_DAO sp=new Sanpham_DAO();
    	Xuatxu_DAO xx=new Xuatxu_DAO();
    	Hedieuhanh_DAO h=new Hedieuhanh_DAO();
    	Thuonghieu_DAO t=new Thuonghieu_DAO();
    	Kho_DAO k=new Kho_DAO();
        if (getUpdate() == false) {
        	if(sp.isTenSPExists(ten.getText())) {
        		 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setHeaderText(null);
                 alert.setContentText("Tên sản phẩm đã tồn tại");
                 alert.showAndWait();
        		 setCance(true);
        	}
        	else {
        		 sp.add(new Sanpham(ten.getText(),getPath(img),xx.selectByNam(xuatxu.getValue().toString()),Integer.parseInt(pin.getText()),Double.parseDouble(size.getText()),h.selectByNam(hdh.getValue().toString()),Integer.parseInt(phienban.getText()),came_sau.getText(),came_truoc.getText(),Integer.parseInt(baohanh.getText()),t.selectByNam(thuonghieu.getValue().toString()),k.selectByNam(kho.getValue().toString())));
                 Sanpham_DAO ma=new Sanpham_DAO();
                 ghiFileMasp(ma.masp(new Sanpham(ten.getText())));	
        	}

        }
        else{
           sp.update(new Sanpham(masp,ten.getText(),getPath(img),xx.selectByNam(xuatxu.getValue().toString()),Integer.parseInt(pin.getText()),Double.parseDouble(size.getText()),h.selectByNam(hdh.getValue().toString()),Integer.parseInt(phienban.getText()),came_sau.getText(),came_truoc.getText(),Integer.parseInt(baohanh.getText()),t.selectByNam(thuonghieu.getValue().toString()),k.selectByNam(kho.getValue().toString())));
        }
 
    }
    
    void setTextField(URL hinhanh,String name,String xx,int dl,double kt,String sau,String truoc,String hdhanh,String th,int pb,int time,String kvk ) {
//file:/D:/Code_java/Do_an_co_so_1/Sanpham/Realme.jpg 
    	 label.setText("Chỉnh sửa sản phẩm");
    	 chinhsuacauhinh.setText("Chỉnh sửa cấu hình");
    	 chinhsuacauhinh.setStyle("-fx-margin-left: 15px;");
    	 Image image = new Image(hinhanh.toString());
         img.setImage(image);
         ten.setText(name);
         xuatxu.setValue(new Xuatxu(xx));
         pin.setText(Integer.toString(dl));
         size.setText(Double.toString(kt));
         came_sau.setText(sau);
         came_truoc.setText(truoc);
         hdh.setValue(new Hedieuhanh(hdhanh));
         thuonghieu.setValue(new Thuonghieu(th));
         phienban.setText(Integer.toString(pb));
         baohanh.setText(Integer.toString(time));
         kho.setValue(new Khuvuckho(kvk));
    }
    void setUpdate(boolean b) {
        this.update = b;
    }
    public boolean getUpdate() {
        return update; 
    }
    String getPath(ImageView view) {
    	Image m=view.getImage();
    	String imageUrl = m.getUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                URI uri = new URI(imageUrl);
                String absolutePath = uri.getPath();
                if (absolutePath.startsWith("file:")) {
                    absolutePath = absolutePath.substring(5);
                }
                return absolutePath;
            }catch(URISyntaxException e) {
               e.printStackTrace();    	
            }
            }
        return null;
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
    private static void writeToFile(String fileName, boolean value) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.valueOf(value)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
