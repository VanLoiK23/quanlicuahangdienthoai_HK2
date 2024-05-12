package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Animation_carousel implements Initializable{
	
	@FXML
	private ImageView img;
	

	private Image[] images = {
            new Image("/Other/Welcome.png"),
            new Image("/Other/Imei.png"),
            new Image("/Other/phone.png")
    };

    private int currentIndex = 0;
    

    @FXML
    private void pre(MouseEvent e) {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        img.setImage(images[currentIndex]);
    }

    @FXML
    private void next(MouseEvent e) {
        currentIndex = (currentIndex + 1) % images.length;
        img.setImage(images[currentIndex]);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		img.setImage(images[currentIndex]);
		img.setStyle("-fx-scale-x: " + 1.8 + ";");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6), event -> {
            next(null);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
		
	}
}
