package log121_lab1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.invoke.CallSite;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import log121_lab1.Shape.Circle;
import log121_lab1.Shape.Line;
import log121_lab1.Shape.Rectangle;
import log121_lab1.Shape.Square;
import log121_lab1.Shape.Oval;



/**
 * Main class
 * @author Vincent Roy
 * @date 29/09/2015
 *
 */
public class Client implements PropertyChangeListener, ActionListener, WindowListener{
	
	private WindowManager window;
	private Cipher cipher;
	private ShapeServer server;
	private Shaper shaper;
	private int i = 0;
	
	public Client() {
		
		window = new WindowManager();
		window.setVisible(true);
		
		window.addWindowListener(this);
		
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
		
		JMenuItem miStartLoop = new JMenuItem("Start requests");
		miStartLoop.setActionCommand("startLoop");
		
		serverMenu.add(miStatusConnection);
		serverMenu.addSeparator();
		serverMenu.add(miOpenConnection);
		serverMenu.add(miCloseConnection);
		serverMenu.addSeparator();
		serverMenu.add(miStartLoop);
		
		
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
		miStartLoop.addActionListener(this);
		
		//////////////////////////////////
		
		cipher = new Cipher();
		
		shaper = new Shaper();
		
		
		
		
		connect("");
		
		
		
		if(server != null) {
			server.startGetLoop();
			
		}
	
		//propertyChange(new PropertyChangeEvent(this, "shape", 1, new ArrayList<String>({"CARRE","100","100","300","300"}) );
		
		
		
	}
	
	/**
	 * Initiates the connection with the server. Prompts the user for the 
	 * server info.
	 * 
	 * Strips the message to get the ip and the port separately. 
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
			if(arrayInfo.size()>1){
				try {
					ip = InetAddress.getByName(arrayInfo.get(0));
					port = Integer.parseInt(arrayInfo.get(1));
					
				} 
				catch (UnknownHostException e) {	
					connect(e.toString());
					
				}
			} else {
				connect("Bad host");
			}
			
		} else {
			connect("Erreur");
		}
		
		server = new ShapeServer();
		
		if(ip != null){
			
			
			boolean serverRunning = server.open(ip, port);
			if(serverRunning) {
				window.alert("Succes", server.status());
				server.setCipher(cipher);
				
				server.setProprietyChangeListener(this);
				
				server.setShaper(shaper);
			}
			else{
				connect("Server unreachable");
			}
			
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
		System.out.println("xx");
		
		i++;
		if(evt.getPropertyName().equals("Alert")&&i>1) {
			server.close();
			window.alert("Error", "Connection lost");
			
			return;
		}
		
		
		if(i>1)	{
			
			String name;
			try{
				name = (String)evt.getOldValue();
			} catch(ClassCastException e) {
				return;
			}
			Shape sheep;
			switch(name){
			case "CARRE":
				sheep = (Square) evt.getNewValue();
				break;
			case "RECTANGLE":
				sheep = (Rectangle) evt.getNewValue();
				break;
			case "CERCLE":
				sheep = (Circle) evt.getNewValue();
				break;
			case "OVALE":
				sheep = (Oval) evt.getNewValue();
				break;
			case "LIGNE":
				sheep = (Line) evt.getNewValue();
				break;
			default:
				return;
			}
			

			try {
				window.addToDisplayQueue(sheep);
			} catch (CloneNotSupportedException e) {
				
				throw new RuntimeException();
			}
			
				
		
		}
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand()){
			case "closeWindow":
				server.close();
				System.exit(0);
				
				break;
			case "statusConnection":
				window.alert("Server Status", server.status());
				break;
			case "openConnection":
				/*
				 * checks if the server is already open before opening 
				 * a new socket
				 */
				
				if(server.isSocketOpen()){
					window.alert("Server Status", server.status());
				} else {
					connect("");
				}
				break;
			case "closeConnection":
				if(server.isSocketOpen()){
					server.close();
					window.alert("Info", "Disconnected from server");
				}
				break;
			case "startLoop":
				if(server.isSocketOpen()){
					server.startGetLoop();
				}
				break;
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		server.close();
		System.exit(0);
		
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		server.close();
	}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}
