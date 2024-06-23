package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import AtttributeSanPham.Color;
import AtttributeSanPham.Hedieuhanh;
import AtttributeSanPham.Ram;
import AtttributeSanPham.Rom;
import DAO.Hedieuhanh_DAO;
import DAO.Kho_DAO;
import DAO.Nhacungcap_DAO;
import DAO.Nhanvien_DAO;
import DAO.PhieuNhap_DAO;
import DAO.Phieubansp_DAO;
import DAO.Sanpham_DAO;
import DAO.Thuonghieu_DAO;
import DAO.Xuatxu_DAO;
import DAO.ct_phieunhap_DAO;
import DAO.ct_sanpham_DAO;
import Model.ChitietPhieuNhap;
import Model.ChitietSanpham;
import Model.Nhacungcap;
import Model.Nhanvien;
import Model.PhieuNhap;
import Model.Phieubansanpham;
import Model.Sanpham;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Add_phieunhapcontroller implements Initializable{
	
	private int i;
	

    public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	
    private String ten;
	

    public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	
    private int ra;
	

    public int getra() {
		return ra;
	}
	public void setra(int ra) {
		this.ra = ra;
	}
	
    private int ro;
	

    public int getro() {
		return ro;
	}
	public void setro(int ro) {
		this.ro = ro;
	}
	
    private String co;
	

    public String getco() {
		return co;
	}
	public void setco(String co) {
		this.co = co;
	}

	@FXML
    private ChoiceBox<Phieubansanpham> choice_cauhinh;

    @FXML
    private TableColumn<Phieubansanpham, String> color;

    @FXML
    private TableColumn<Phieubansanpham, Integer> dongia;

    @FXML
    private TableColumn<Phieubansanpham, Integer> id;

    @FXML
    private TextField maimei;

    @FXML
    private TableColumn<Sanpham, Integer> masp1;

    @FXML
    private TableColumn<Phieubansanpham, Integer> masp2;

    @FXML
    private TextField masp_phieu;

    @FXML
    private ChoiceBox<Nhacungcap> ncc_phieu;

    @FXML
    private ChoiceBox<Nhanvien> nv_phieu;

    @FXML
    private TextField price;

    @FXML
    private TableColumn<Phieubansanpham, Integer> ram;

    @FXML
    private TableColumn<Phieubansanpham, Integer>rom;

    @FXML
    private TableView<Sanpham> sanpham1;

    @FXML
    private TableView<Phieubansanpham> sanpham2;

    @FXML
    private TableColumn<Sanpham, Integer> soluong1;

    @FXML
    private TableColumn<Phieubansanpham, Integer> soluong2;

    @FXML
    private TableColumn<Sanpham, String> tensp1;

    @FXML
    private TableColumn<Phieubansanpham, String> tensp2;

    @FXML
    private TextField text_masp;

    @FXML
    private TextField text_soluong;

    @FXML
    private TextField text_tensp;

    @FXML
    private TableColumn<Phieubansanpham, String> edit;
    
    @FXML
    private Label total_phieu;
    
   

    
    private ObservableList<Sanpham> list;    
    private Sanpham_DAO sp_DAO;
    private Sanpham sp=null;
    
    private ObservableList<Phieubansanpham> listpb;    
    private List<Phieubansanpham> listp;    
    private Phieubansp_DAO p_DAO;
    private Phieubansanpham p=null;
    
    void load() {
        if(list!=null) {
       	 list.clear();	  
        }
   	
        sp_DAO=new Sanpham_DAO();
        list=sp_DAO.selectAll();
        sanpham1.setItems(list);
   }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();	
		text_masp.setEditable(false);
		text_tensp.setEditable(false);
		masp_phieu.setEditable(false);
		price.setEditable(false);
		
		addToTable();
		
		
	}
	
	@FXML
    void submit(MouseEvent event) {
		

        if (ncc_phieu.getValue() == null || nv_phieu.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn nhà cung cấp và nhân viên nhập hàng");
            alert.showAndWait();
        }
        else {
    		getQuery();
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Tạo Phiếu Nhập thành công");
            alert.showAndWait();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
	}
	
	    @FXML
	    void add(MouseEvent event) {
            String ma=maimei.getText();
            String sl=text_soluong.getText();
            boolean containsDigit = ma.matches(".*\\d.*");
            boolean containsDigit1 = sl.matches(".*\\d.*");
            if(ma.isEmpty()||sl.isEmpty()) {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All DATA");
                alert.showAndWait();
            }
            else if(!containsDigit||!containsDigit1) {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Chỉ bao gồm số không gồm chữ");
                alert.showAndWait();
            }
            else if(ma.length()!=15) {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Mã Imei phải bao gồm 15 chữ số");
                alert.showAndWait();
            }
            else if(containsDigit&&containsDigit1) {
	    	    p_DAO=new Phieubansp_DAO();

	    	    sanpham2.getItems().add(new Phieubansanpham(
	    	    	    getI(),
	    	    	    Long.parseLong(maimei.getText()), 
	    	    	    p_DAO.docso("masp.txt"),
	    	    	    getTen(),
	    	    	    getra(),
	    	    	    getro(),
	    	    	    getco(),
	    	    	    Integer.parseInt(price.getText()),
	    	    	    Integer.parseInt(text_soluong.getText())
	    	    	));

	    	    PhieuNhap_DAO pnt = new PhieuNhap_DAO();

	            int pn = pnt.maphieu();
	            masp_phieu.setText("PN" + pn);
	            
	            ObservableList<Phieubansanpham> items = sanpham2.getItems();
	   	        List<Phieubansanpham> phieubansanphams = new ArrayList<>(items);
	               int sum=0,s=0;
	   	        for (Phieubansanpham phieu : phieubansanphams) {
	   	        	sum+=phieu.getGianhap()*phieu.getSoluongton();
	   	        }
	   	     DecimalFormat formatter = new DecimalFormat("#,###");
	         String formattedString = formatter.format(sum);
	   	        total_phieu.setText(formattedString+" đ");
	   	        
	   	     Nhacungcap_DAO ncc_d=new Nhacungcap_DAO();
	  	     Nhanvien_DAO nv_d=new Nhanvien_DAO();
	  	   if (!nv_phieu.getItems().isEmpty() && !ncc_phieu.getItems().isEmpty()) {
	  	        nv_phieu.getItems().clear();
	  	        ncc_phieu.getItems().clear();
	  	    }
	  	    ncc_phieu.getItems().addAll(ncc_d.selectAll());
	  	    nv_phieu.getItems().addAll(nv_d.selectAll());

            }
	    }	    
	    private void getQuery() {
	        p_DAO = new Phieubansp_DAO();
	        PhieuNhap_DAO pn_d=new PhieuNhap_DAO();
	        Nhacungcap_DAO ncc_d=new Nhacungcap_DAO();
	        ct_phieunhap_DAO ct_d=new ct_phieunhap_DAO();
	  	    Nhanvien_DAO nv_d=new Nhanvien_DAO();
            PhieuNhap_DAO pnt = new PhieuNhap_DAO();
            ct_sanpham_DAO ct = new ct_sanpham_DAO();

	        if(sanpham2!=null) {
	        	ObservableList<Phieubansanpham> items = sanpham2.getItems();
		        List<Phieubansanpham> phieubansanphams = new ArrayList<>(items);
		        int pn = pnt.maphieu();
                masp_phieu.setText("PN" + pn);
                
                String nccValue = (ncc_phieu.getValue() != null) ? ncc_phieu.getValue().toString() : null;
                String nvValue = (nv_phieu.getValue() != null) ? nv_phieu.getValue().toString() : null;

	            pn_d.add(new PhieuNhap(pn,ncc_d.selectByNam(nccValue),nv_d.selectByNam(nvValue),parseMoneyStringToInt(total_phieu.getText())));

               
		        for (Phieubansanpham phieu : phieubansanphams) {
		            int check = p_DAO.updateQuantity(phieu);
		            if (check != 0) {
		                sp_DAO.updatesl(new Sanpham(phieu.getMasp()));
		                
		                ct_d.add(new ChitietPhieuNhap(pn,phieu.getMaphienbansp(),phieu.getSoluongton(),phieu.getGianhap()));
		               
		                long ma = phieu.getImei();

		                for (int i = 1; i <= phieu.getSoluongton(); i++) {
		                    ct.add(new ChitietSanpham(ma, phieu.getMaphienbansp(), pn, 1));
		                    ma++;
		                }
		            }
		        }
	        }
	        
	        
	    }


	
    private void loadData() {
    	
		load();
		masp1.setCellValueFactory(new PropertyValueFactory<>("masp"));
		tensp1.setCellValueFactory(new PropertyValueFactory<>("tensp"));
		soluong1.setCellValueFactory(new PropertyValueFactory<>("soluongton"));
		
		p_DAO=new Phieubansp_DAO();
		sanpham1.setOnMouseClicked(event -> {
			sp=sanpham1.getSelectionModel().getSelectedItem();
			if(sp!=null) {
				Sanpham_controller s=new Sanpham_controller();
				 s.ghiFileMasp(sp.getMasp());
				 listp=p_DAO.cauhinh();
				 setTen(sp.getTensp());
				 if(listp!=null) {
					 setTextField(sp.getMasp(),sp.getTensp(),listp); 
					 choice_cauhinh.setOnAction(e -> {
				            Phieubansanpham selected = choice_cauhinh.getValue();
							 if(selected!=null) {
								 setI(selected.getMaphienbansp());
								 setra(selected.getRam());
								 setro(selected.getRom());
								 setco(selected.getColor());
								 //System.out.println(selected.getMasp());
								 //System.out.println(selected.getMaphienbansp());
								 choice_cauhinh.setValue(selected);
						         price.setText(Integer.toString(selected.getGianhap()));
							 }
							 else {
								 price.setText(null);
							 }
				        });
				 }
//		          setI(sp.getMaphienbansp());
			}
			});
    }
    private void addToTable() {
     	 
        id.setCellValueFactory(cellData -> {
            int rowIndex = sanpham2.getItems().indexOf(cellData.getValue()) + 1;
            return createObservableValue(rowIndex);
        });
        id.setSortable(false);
        masp2.setCellValueFactory(new PropertyValueFactory<>("masp"));
        tensp2.setCellValueFactory(new PropertyValueFactory<>("tensp"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        dongia.setCellValueFactory(new PropertyValueFactory<>("gianhap"));
        soluong2.setCellValueFactory(new PropertyValueFactory<>("soluongton"));
        ram.setCellValueFactory(new PropertyValueFactory<>("ram"));
        rom.setCellValueFactory(new PropertyValueFactory<>("rom"));
        
        sanpham2.setOnMouseClicked(event -> {
			p=sanpham2.getSelectionModel().getSelectedItem();
			if(p!=null) {
				setI(p.getMaphienbansp());
				setTextField1(p.getMasp(),p.getTensp(),p,p.getImei(),p.getSoluongton());
				 
			}
			
        });
        
        Callback<TableColumn<Phieubansanpham, String>, TableCell<Phieubansanpham, String>> cellFoctory = (TableColumn<Phieubansanpham, String> param) -> {
            
            final TableCell<Phieubansanpham, String> cell = new TableCell<Phieubansanpham, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } 
                    else {
                    	 Image image = new Image("/Other/edit.jpg");

                         ImageView editIcon = new ImageView(image);
                         
                         

                         editIcon.setFitWidth(20); 
                         editIcon.setFitHeight(20); 

                       
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                        );
                       
                        editIcon.setOnMouseClicked((MouseEvent event) -> {    
                			p=sanpham2.getSelectionModel().getSelectedItem();
                        	 p_DAO=new Phieubansp_DAO();
     			    	        p.setImei( Long.parseLong(maimei.getText()));
     			    	        p.setSoluongton(Integer.parseInt(text_soluong.getText()));
     			    	    	sanpham2.refresh();
                         });

                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         edit.setCellFactory(cellFoctory);
         
        
    }

    private void setTextField1(int ma,String name,Phieubansanpham pb,Long ime,int sl) {
		  text_masp.setText(Integer.toString(ma));
		  text_tensp.setText(name);		 
	      choice_cauhinh.getItems().clear();
		  choice_cauhinh.getItems().addAll( pb );
		  choice_cauhinh.setValue(choice_cauhinh.getItems().get(0));
		  maimei.setText(Long.toString(ime));
		  text_soluong.setText(Integer.toString( sl));
	        
	  }
    
    private void setTextField(int ma,String name,List<Phieubansanpham> pb) {
		  text_masp.setText(Integer.toString(ma));
		  text_tensp.setText(name);		 
			  choice_cauhinh.getItems().clear();
			  choice_cauhinh.getItems().addAll( pb );
			  choice_cauhinh.setValue(choice_cauhinh.getItems().get(0));
	        
	  }
    
    private ObservableValue<Integer> createObservableValue(int value) {
        return new SimpleIntegerProperty(value).asObject();
    }

    public static int parseMoneyStringToInt(String moneyString) {
        String stringWithoutCommas = moneyString.replaceAll(",", "");
        String stringWithoutUnit = stringWithoutCommas.replaceAll(" đ", "");
        int moneyInt = Integer.parseInt(stringWithoutUnit);
        
        return moneyInt;
    }
}
