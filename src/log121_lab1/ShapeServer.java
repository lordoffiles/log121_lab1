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

import javax.swing.SwingWorker;

public class ShapeServer implements Connection {
	
	private final long DELAY = 1000;
	
	private Socket socket;
	private DataInputStream in;
	private PrintWriter out;
	private SwingWorker worker;
	private PropertyChangeListener listener;
	private Reader cipher;
	

	

	public ShapeServer() {
		cipher = new Cipher();
	}
	
	public boolean open(InetAddress address, int port)  {
		
		try {
			socket = new Socket(address, port);
			in = new DataInputStream(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
			
			
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
	
	/*
	 * (non-Javadoc)
	 * @see log121_lab1.Connection#get()
	 * 
	 * TODO Return the shape from the server // strip the string before and after
	 */
	
	public String getResponse() {
		//System.out.println("get");
		
		try {
			
			
			out.println("GET");;
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String line = "";
			while(reader.ready()){
				line += reader.readLine();
			}
			
			return line;
			
		} catch(IOException e) {
			throw new RuntimeException();
		} 
	
		
	}
	
	@SuppressWarnings("rawtypes")
	public void startGetLoop() {
		worker = new SwingWorker() {
			
			@Override
			protected Object doInBackground() throws Exception {
				String serverShape;
				String properties;
				
				while(!isCancelled()) {
					Thread.sleep(DELAY);
					
					
					serverShape = getResponse();
					
					ArrayList<String> splitShape = cipher.split(serverShape, "<?/?[A-Za-z]+>");
					
					
					splitShape.add(0, cipher.findTag(serverShape));
					properties = splitShape.remove(2);
					
					ArrayList<String> splitProperties = cipher.split(properties, " ");
					
					splitShape.addAll(2, splitProperties);
					
					System.out.println("lel");
					if(listener != null){
						
						
						firePropertyChange("Shape", null, splitShape);
					}
							
				}
				return null;

			
			}
			
			
			

			
		};
		
		if(listener != null)
			worker.addPropertyChangeListener(listener);
		
		worker.execute();
		
	}
	
	public void setProprietyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}
	
	public boolean close() {
		try{
			in.close();
			out.close();
			socket.close();
		} catch(IOException e){
			return false;
		}
		
		return true;
	}

	public void setCipher(Reader cipher) {
		this.cipher = cipher;
	}

}
