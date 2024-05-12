package Giaotiep2may;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.layout.VBox;

public class Server {
   private ServerSocket serverSocket;
   private Socket socket;
   private BufferedReader bufferedReader;
   private BufferedWriter bufferedWriter;
   
   public Server(ServerSocket serverSocket) {
	    this.serverSocket =serverSocket;
	    try {
	        this.socket = serverSocket.accept();
	        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

   
   public void sendtoClient(String message)   {
	   try {
		bufferedWriter.write(message);
		bufferedWriter.newLine();
	 bufferedWriter.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		close(socket,bufferedReader,bufferedWriter);
	}
	   
   }
   public void nhantintu_client(VBox vbox) {
	   new Thread(new Runnable() {

		@Override
		public void run() {
			while(socket.isConnected()) {
				try {
					String messagefromclient=bufferedReader.readLine();
				    Controller_server.addLabel(messagefromclient, vbox);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					close(socket,bufferedReader,bufferedWriter);
                   break;
				}
				
			}
			
		}
		   
	   }).start();;
   }
   public void close(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter)  {
		   try {
			   if(bufferedWriter!=null) {
			bufferedWriter.close();}
			   if(bufferedReader!=null) {
				   bufferedReader.close();	   
			   }
			   if(socket!=null) {
				   socket.close();	   
			   }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	   
   }
}
