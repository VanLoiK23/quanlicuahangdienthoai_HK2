package Controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import DAO.Thongke_tongquan_DAO;
import Model.Thongke_tongquan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Thongketongquan_controller implements Initializable{

    @FXML
    private Label kh;

    @FXML
    private Label loinhuan;

    @FXML
    private Label nv;

    @FXML
    private Label sl;

    @FXML
    private Label thu;

    @FXML
    private Label von;

    @FXML
    void cot(MouseEvent event) {
    	 Thongke_doanhthu.main(new String[0]);
    }

    @FXML
    void tron(MouseEvent event) {
    	Thongkesp  show=new Thongkesp();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Thongke_tongquan_DAO t=new Thongke_tongquan_DAO();
		t.updateThongKeTongQuan();
		List<Thongke_tongquan> set=t.getAllThongkeTongquan();
		
	    for(Thongke_tongquan list:set) {
	    	sl.setText(Integer.toString(list.getSp()));
	    	kh.setText(Integer.toString(list.getKhachhang()));
	    	nv.setText(Integer.toString(list.getNv()));
	    	von.setText(formatNumber(list.getSovon())+"₫");
	    	thu.setText(formatNumber(list.getDoanthu())+"₫");
	    	loinhuan.setText(formatNumber(list.getLoinhuan())+"₫");
	    }
	}
	public static String formatNumber(Long number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

}
