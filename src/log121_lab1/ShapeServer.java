package log121_lab1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeListener;

import javax.management.RuntimeErrorException;
import javax.swing.SwingWorker;

/**
 * Connection to the server
 * @author Vincent
 * @date 29/09/2015
 *
 */
public class ShapeServer implements Connection {
	
	private final long DELAY = 1000;
	
	private Socket socket;
	private DataInputStream in;
	private PrintWriter out;
	private SwingWorker worker;
	private PropertyChangeListener listener;
	private Reader cipher;
	

	

	public ShapeServer() {
		
	}
	
	/**
	 * Creates a new socket with the specified port and address
	 * @param address
	 * @param port
	 * 
	 */
	public boolean open(InetAddress address, int port)  {
		
		try {
			socket = new Socket(address, port);
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				throw new RuntimeException();
			}
			
		} catch(IOException e) {
			
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * returns the address, port and status(up or down) of the connection
	 * 
	 * @return a formated string containing the status of the connection
	 */
	public String status() {
		if(socket != null) {
			return "Connected to "+socket.getInetAddress() + " on port " + socket.getPort();
			
		} else {
			return "Not connected";
			
		}
		
	}
	
	
	/**
	 * sends GET command to server through a PrintWriter connected to the
	 * outputStream of the socket and reads with a bufferedReader from the 
	 * inputStream.
	 */
	public String getResponse() {
		try {	
			out.println("GET");;
				
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String line = "";
			while(reader.ready()){
				line += reader.readLine();
			}
	
			return line;
			
		} catch(IOException e) {
			return "no response from server";
			
		} 
		
	}
	
	/**
	 * SwingWorker to get responses from the server asynchronously from the EDT.
	 * 
	 * NOT WORKING ONLY WORKING ONCE
	 */
	@SuppressWarnings("rawtypes")
	public void startGetLoop() {
		worker = new SwingWorker() {
			
			@Override
			protected Object doInBackground() throws Exception {
				String serverShape;
				String properties;
				
				while(true) {
					
					Thread.sleep(DELAY);
					
					
					serverShape = getResponse();

					ArrayList<String> splitShape = cipher.split(true ,serverShape, "<?/?[A-Za-z]+>", " ");

					if(serverShape.contains("CARRE")) {
						splitShape.add(0, "CARRE");
					} else if(serverShape.contains("RECTANGLE")) {
						splitShape.add(0, "RECTANGLE");
					} else if(serverShape.contains("CERCLE")) {
						splitShape.add(0, "CERCLE");
					} else if(serverShape.contains("OVALE")) {
						splitShape.add(0, "OVALE");
					} else if(serverShape.contains("LIGNE")) {
						splitShape.add(0, "LIGNE");
					}
					
					
					/*
					 * ça aussi ça plante....
					 */
//					int i = 0;
//					while(i < splitShape.size()) {
//						if(splitShape.get(i).equals(" ")) {
//							splitShape.remove(i);
//						}
//					}
					
					
					/*
					 * je pense que ma méthode findTag fait planter le worker...
					 * 
					 * les différentes oppérations sur la liste plantent aussi....
					 */
					
					//String tag = cipher.findTag(serverShape);
					
					//splitShape.add(0,"XD");
					
					//splitShape.add(0, cipher.findTag(serverShape));
					
//					properties = splitShape.get(2);
//					System.out.println("!!"+properties);
					//ArrayList<String> splitProperties = cipher.split(properties, " ");
					
					//splitShape.addAll(2, splitProperties);
					

					if(listener != null){
						
						firePropertyChange("Shape", 1, splitShape);
					}

							
				}
				

			
			}
			
		};
		
		if(listener != null)
			worker.addPropertyChangeListener(listener);
		
		worker.execute();
		
	}
	

	public void setProprietyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Closes the socket
	 */
	public boolean close() {
		try{
			socket.close();
			in.close();
			out.close();
			socket = null;
			
		} catch(IOException e){
			return false;
			
		}
		
		return true;
	}
	

	public void setCipher(Reader cipher) {
		this.cipher = cipher;
		
	}
	
	public boolean isSocketOpen() {
		if(socket != null){
			return true;
					
		}	else{
			return false;
					
		}
				
					
	}

}
