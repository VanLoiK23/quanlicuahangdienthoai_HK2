package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Model.Sanpham;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sanphamtrongkho_controller {

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label number;
    public void SetData(Sanpham sp) {
    	 FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(sp.getHinhanh());
			Image image = new Image(inputStream);
	    	//Image image=new Image(getClass().getResourceAsStream(sp.getHinhanh()));
	    	img.setImage(image);
	    	name.setText(sp.getTensp());
	    	number.setText("Số lượng :"+sp.getSoluongton());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
    }
}
