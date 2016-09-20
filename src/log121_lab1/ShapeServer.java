package log121_lab1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
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
	
	public ShapeServer() {
		
	}
	
	public boolean open(InetAddress address, int port)  {
		
		try {
			socket = new Socket(address, port);
			
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
	
	public boolean get() {
		System.out.println("get");
		
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
			
			out.println("GET");;
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			while(reader.ready())
				System.out.println(reader.readLine());
			
			
			
		} catch(IOException e) {
			throw new RuntimeException();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				throw new RuntimeException();
			}
		
		}

		return false;
	}
	
	public void startGetLoop() {
		worker = new SwingWorker() {
			
			@Override
			protected Object doInBackground() throws Exception {
			
				while(true) {
					Thread.sleep(DELAY);
					
					if(listener != null)
						firePropertyChange("Shape", null, get());
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
	
	public boolean close() {
		try{
			socket.close();
		} catch(IOException e){
			return false;
		}
		
		return true;
	}


}
