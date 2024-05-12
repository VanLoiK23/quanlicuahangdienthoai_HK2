package Giaotiep2may;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main1 extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root=FXMLLoader.load(getClass().getResource("Server.fxml"));
//		--module-path "C:\Users\DELL\Downloads\openjfx-22_windows-x64_bin-sdk\javafx-sdk-22\lib" --add-modules javafx.controls,javafx.fxml
//		scene.setFill(Color.TRANSPARENT);
		
		stage.setScene(new Scene(root));
		stage.show();
	}
     public static void main(String[] args) {
		launch(args);
	}

}

