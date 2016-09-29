package log121_lab1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ca.etsmtl.log.util.*;

public class Client implements PropertyChangeListener, ActionListener{
	
	WindowManager window;
	Cipher cipher;
	ShapeServer server;
	Shaper shaper;
	IDLogger logger = IDLogger.getInstance();
	
	public Client() {
		
		window = new WindowManager();
		window.setVisible(true);
		
		/*
		 * Creates the top menu and its elements, then adds it to the window 
		 * frame
		 */
		
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem miClose = new JMenuItem("Close");
		miClose.setActionCommand("closeWindow");
		
		fileMenu.add(miClose);

		
		JMenu serverMenu = new JMenu("Server");
		
		JMenuItem miStatusConnection = new JMenuItem("Connection Status");
		miStatusConnection.setActionCommand("statusConnection");
		
		JMenuItem miOpenConnection = new JMenuItem("Open Connection");
		miOpenConnection.setActionCommand("openConnection");
		
		JMenuItem miCloseConnection = new JMenuItem("Close Connection");
		miCloseConnection.setActionCommand("closeConnection");
		
		serverMenu.add(miStatusConnection);
		serverMenu.addSeparator();
		serverMenu.add(miOpenConnection);
		serverMenu.add(miCloseConnection);
		
		menu.add(fileMenu);
		menu.add(serverMenu);
		
		window.setJMenuBar(menu);
		
		/*
		 * adds the action listeneners for the menu items
		 */
		miClose.addActionListener(this);
		miCloseConnection.addActionListener(this);
		miOpenConnection.addActionListener(this);
		miStatusConnection.addActionListener(this);
		
		
		
		cipher = new Cipher();
		
		shaper = new Shaper();
		
		
		connect("");
		
		
		
		server.setProprietyChangeListener(this);
		
//		if(server != null) {
//			server.startGetLoop();
//			
//		}
	
		//propertyChange(new PropertyChangeEvent(this, "shape", 1, new ArrayList<String>({"CARRE","100","100","300","300"}) );
		
		
		
	}
	
	/**
	 * Initiates the connection with the server. Prompts the user for the 
	 * server info.
	 * 
	 * If it cannot connect, it will ask again for the info
	 * 
	 * @param message to display if there is an error with the connection
	 * @return if it was successful or not
	 */
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
		//ArrayList<String> sl = (ArrayList<String>)evt.getNewValue();
		
		
//		if(evt.getOldValue().equals(1))	{
//			logger.logID(sl.get(1));
			//window.addToDisplayQueue(shaper.create(sl);
			
		//}
		//System.out.println(evt);
		//String serverShape = (String) evt.getNewValue();
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand()){
			case "closeWindow":
				
				break;
			case "statusConnection":
				window.alert("Server Status", server.status());
				break;
			case "openConnection":
				if(!server.isSocketOpen()) {
					connect("");
				} else {
					server.close();
					connect("");
				}
				break;
			case "closeConnection":
				server.close();
				window.alert("Info", "Disconnected from server");
				break;
		}
	}
	
	


	
}
