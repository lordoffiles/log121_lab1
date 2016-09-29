package log121_lab1;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client implements PropertyChangeListener {
	
	WindowManager window;
	Cipher cipher;
	ShapeServer server;
	
	public Client() {
		window = new WindowManager();
		window.setVisible(true);
		
		
		
		cipher = new Cipher();
		
		
		connect("");
		
		
		
		server.setProprietyChangeListener(this);
		
		if(server != null) {
			server.startGetLoop();
			
		}
		
		
		
	}
	
	private boolean connect(String message) {
		String connectionInfo;
		
		if(message.equals("")) {	
			connectionInfo = window.prompt();
		} else {
			connectionInfo = window.prompt(message); 
		}
		
		
		InetAddress ip = null;
		int port = 0;
		
		if(connectionInfo != null){
			ArrayList<String> arrayInfo = cipher.split(connectionInfo, "[:]");
		
			try {
				ip = InetAddress.getByName(arrayInfo.get(0));
				port = Integer.parseInt(arrayInfo.get(1));
				
			} 
			catch (UnknownHostException e) {	
				connect(e.toString());
				
			}
			
		} else {
			connect("Erreur");
		}
		
		server = new ShapeServer();
		
		if(ip != null){
			
			
			boolean serverRunning = server.open(ip, port);
			if(serverRunning)
				window.alert("Succes", server.status());
			
		} else {
			
			connect(server.status());
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getNewValue());
		//System.out.println(evt);
		//String serverShape = (String) evt.getNewValue();
		
		
		
	}


	
}
