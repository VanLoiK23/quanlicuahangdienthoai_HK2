package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Sanpham;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;


public class Homepage implements Initializable{
	
	@FXML
	private HBox cartPhone;
	private  List<Sanpham> Changecard;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Changecard=new ArrayList<>(Changecard());
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
}
