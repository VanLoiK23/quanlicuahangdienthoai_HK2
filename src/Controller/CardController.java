package Controller;

import Model.Sanpham;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CardController {
	@FXML
    private Label giaTien;

    @FXML
    private ImageView srcImg;

    @FXML
    private Label tensp;
    
    @FXML
    private HBox box;
     
    private String [] colors = {"B9E5FF","BDB2FE","FB9AA8","FF5056"};
    public void SetData(Sanpham sp) {
    	Image img=new Image(getClass().getResourceAsStream(sp.getHinhanh()));
    	srcImg.setImage(img);
    	
    	tensp.setText(sp.getTensp());
    	giaTien.setText(sp.getGiaTienkemdonvi());
    	box.setStyle("-fx-background-color: #" + colors[(int)(Math.random() * colors.length)] + ";"+
    	"-fx-background-radius: 15;"+
    	"-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0),10,0,0,10);");
    	
    }
}
