package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Test extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root=FXMLLoader.load(getClass().getResource("/View/Quan_li_view.fxml"));
//		--module-path "C:\Users\DELL\Downloads\openjfx-22_windows-x64_bin-sdk\javafx-sdk-22\lib" --add-modules javafx.controls,javafx.fxml
		Scene scene=new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		
		stage.setScene(scene);
		stage.setTitle("Hệ thống quản lý kho hàng");
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		
	}
     public static void main(String[] args) {
		launch(args);
	}

}
