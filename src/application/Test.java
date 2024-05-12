package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Test extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root=FXMLLoader.load(getClass().getResource("View_test.fxml"));
		
		Scene scene=new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}
     public static void main(String[] args) {
		launch(args);
	}

}
