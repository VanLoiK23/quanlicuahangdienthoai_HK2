package Giaotiep2may;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;
import Giaotiep2may.Client;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller_client implements Initializable {

	@FXML
    private TextField client;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private Button send_client;
    
    private Client clientInstance ;
    
    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	try {
    		clientInstance=new Client(new Socket("localhost",1330));
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	 vbox.heightProperty().addListener(new ChangeListener<Number>() {

    			@Override
    			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    				sp_main.setVvalue((Double) arg2);
    			}
    	    	  
    	      });
    	 clientInstance.nhantuserver(vbox);
    	 send_client.setOnAction(new EventHandler<ActionEvent>() {

    			@Override
    			public void handle(ActionEvent arg0) {
    				String message=client.getText();
    				if(!message.isEmpty()) {
    					HBox hBox=new HBox();
    					hBox.setAlignment(Pos.CENTER_RIGHT);
    					hBox.setPadding(new Insets(5,5,5,10));
    					
    				   Text text=new Text(message);
    				   TextFlow textFlow=new TextFlow(text);
    				   
    				   textFlow.setStyle("-fx-color:rgb(239,242,255);"+
    				   "-fx-background-color:rgb(15,125,242);"+
    				   "-fx-background-radius:20px;");
    				   textFlow.setPadding(new Insets(5,10,5,10));
    				   text.setFill(Color.color(0.934,0.945, 0.996));
    				   
    				   hBox.getChildren().add(textFlow);
    				   vbox.getChildren().add(hBox);
    				   
    				   clientInstance.sendtoServer(message);
    				   client.clear();
    	    		  
    	    			}
    				
    			}
    	    	  
    	      });
    }
    public static void addLabel(String message,VBox vbox) {
  	  HBox hBox =new HBox();
  	  hBox.setAlignment(Pos.CENTER_LEFT);
		  hBox.setPadding(new Insets(5,5,5,10));

		  Text text=new Text(message);
		   TextFlow textFlow=new TextFlow(text);
		   
		   textFlow.setStyle("-fx-color:rgb(233,233,235);"+
		   "-fx-background-radius:20px;");
		   textFlow.setPadding(new Insets(5,10,5,10));
		   
		   hBox.getChildren().add(textFlow);

		   Platform.runLater(new Runnable() {

			@Override
			public void run() {
				vbox.getChildren().add(hBox);
				
			}
			   
		   });
}

}
