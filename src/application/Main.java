package application;
	

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO_UserPhone.DAO_sanphamdathang;
import DAO_UserPhone.DAOsanpham;
import DAO_UserPhone.PhieuXuat_DAO;
import DAO_UserPhone.Phieubansp_DAO;
import DAO_UserPhone.ctphieuxuat_DAO;
import ModelUserPhone.ChitietSanpham;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Main extends Application {
String path =null;
private Parent cartPage;

	@Override
	public void start(Stage primaryStage) {
		try {
	
			Parent root = FXMLLoader.load(getClass().getResource("/ViewUserPhone/Form_main.fxml"));
			Scene scene = new Scene(root);
//			Load các class khác
			 preloadFXML("/ViewUserPhone/Cart.fxml").thenAccept(parent -> cartPage = parent);
			 preloadFXML("/ViewUserPhone/Trangchu.fxml").thenAccept(parent -> cartPage = parent);
			 scene.setFill(Color.TRANSPARENT);
			 scene.setFill(Color.TRANSPARENT);				
				primaryStage.initStyle(StageStyle.TRANSPARENT);
			 scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		 primaryStage.setResizable(false); 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private CompletableFuture<Parent> preloadFXML(String fxmlPath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new FXMLLoader(getClass().getResource(fxmlPath)).load();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

	
	static Socket c;
	public static void main(String[] args) {
		launch(args);
	}
}
