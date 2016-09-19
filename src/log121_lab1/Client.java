package log121_lab1;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
	
	WindowManager window;
	Cipher cipher;
	ShapeServer server;
	
	public Client() {
		window = new WindowManager();
		window.setVisible(true);
		
		cipher = new Cipher();
		
		
		connect();
		
		
		
	}
	
	private boolean connect() {
		String connectionInfo = window.prompt();
		InetAddress ip = null;
		int port = 0;
		
		if(connectionInfo != null){
			String[] arrayInfo = cipher.split(connectionInfo, "[:]");
		
			try {
				ip = InetAddress.getByName(arrayInfo[0]);
				port = Integer.parseInt(arrayInfo[1]);
				
			} 
			catch (UnknownHostException e) {	
				connect();
				
			}
			
		}	
		
		if(ip != null){
			server = new ShapeServer();
			try{
				server.open(ip, port);
			
			} catch(IOException e) {
				return false;
			}
		} else {
			connect();
		}
		return true;
		
		
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Client app = new Client();

			}
			
		});
		
	}
	
}
